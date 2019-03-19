package job.stormProxy;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.MapUtils;
import utils.JSONUtil;
import utils.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StrSplit {
    /*
    private static String msg = "INFO    2019-02-20 09:09:36,605 [grpc-default-executor-420] com.jd.recproxy.server.AccessLogFilter  - {\"errCd\":\"0\",\"recMode\":\"Broadway\",\"ip\":\"10.186.51.189\",\"latency\":\"177\",\"mtd\":\"GET\",\"items\":\"{2530768=[]}\",\"\n" +
            "url\":\"/diviner?ec=utf-8&fq=yes&p=619181&pin=jd_695d38548cc8a&uuid=869000043880343-3479167b0776&lim=1&lid=3%2C51044%2C25708%2C0&hi=%7B%22cd%22%3A%5B200%2C1%5D%2C%22ci%22%3A%220%22%2C%22dataType%22%3A3%2C%22labe\n" +
            "l%22%3A%22-1%22%2C%22pi%22%3A%22619181%22%2C%22subPosition%22%3A%2296%22%2C%22type%22%3A%22goods%22%7D\"}";*/


    /*private static String msg = "INFO    2019-02-20 09:08:01,412 [grpc-default-executor-417] com.jd.recproxy.server.AccessLogFilter  - {\"errCd\":\"0\",\"recMode\":\"Broadway\",\"ip\":\"10.190.84.226\",\"latency\":\"177\",\"mtd\":\"GET\",\"items\":\"{28234005876=[]\n" +
            ", 33217992521=[], 41257313220=[], 16601533308=[], 10019496706=[], 10104797655=[], 28444703099=[], 10320814518=[], 11264310967=[], 1273512857=[]}\",\"url\":\"/diviner?p=619215&uuid=b14e2dca32326a3d0c26c4b894c6f7398\n" +
            "f4ddb30&pin=vin_zhang&lim=10&lid=19,1705,1707,20029&hi=&fe=&ec=gbk\"}";*/



    private static String msg = "{\"@timestamp\":\"2019-02-25T07:24:44.586Z\",\"beat\":{\"hostname\":\"host-10-181-144-2\",\"name\":\"host-10-181-144-2\",\"version\":\"5.5.2\"},\"input_type\":\"log\",\"message\":\"INFO    2019-02-25 15:24:43,646 [io_1] com.jd.recproxy.server.AccessLogFilter  - {\\\"errCd\\\":\\\"0\\\",\\\"recMode\\\":\\\"Hub\\\",\\\"latency\\\":\\\"6\\\",\\\"mtd\\\":\\\"GET\\\",\\\"items\\\":\\\"{254=[], 255=[], 253=[], 256=[]}\\\",\\\"url\\\":\\\"/diviner?p=619036\\u0026uuid=88ab430b58154e699fbd115ddea81e7525f94acd\\u0026pin=jd_4f1c9fc3e00ae\\u0026lid=0\\u0026hi=%7B%22ci%22%3A%220%22%2C%22pi%22%3A%22home%22%2C%22did%22%3A%5B1589148%5D%7D\\u0026fe=\\u0026ec=gbk\\\"}\",\"offset\":781295846,\"source\":\"/export/Logs/recproxy/access.log\",\"type\":\"recproxy\"}";

    private static final String HI_SEPARATOR = "com.jd.recproxy.server.AccessLogFilter|offset";
    private static final String PID_SEPARATOR = "p=";
    private static final String SEPARATOR = "_";
    private static final String URL_PREFIX = "http://diviner.jd.local";
    private static final Set<String> pidWhiteList = Sets.newHashSet("619181","700002", "619215");



    public static void main(String[] args) {
        String[] arr = msg.split(HI_SEPARATOR);
        JSONObject jsonMsg = null;
        try {
            String jsonStr = arr[1].substring(arr[1].indexOf("{"),
                    arr[1].lastIndexOf("}") + 1).trim();
            jsonMsg = JSONUtil.parseJSON(jsonStr);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> paramKeySet = jsonMsg.keySet()
                .stream().map(String::trim).collect(Collectors.toSet());
        if (!paramKeySet.contains("url")) {
            return;
        }

        Map<String, Object> map = Maps.newHashMap();
        for (Iterator<String> it = jsonMsg.keySet().iterator(); it.hasNext();) {
            String paramKey = it.next();
            if (paramKey != paramKey.trim()) {
                map.put(paramKey.trim(), jsonMsg.get(paramKey));
                it.remove();
            }
        }
        if (MapUtils.isNotEmpty(map)) {
            jsonMsg.putAll(map);
        }

        String url = jsonMsg.getString("url");
        int from = url.indexOf(PID_SEPARATOR) + PID_SEPARATOR.length();
        String pid = url.substring(from, from + 6);

        String outHi = jsonMsg.getString("hi");
        String[] inHi = new String[1];
        inHi[0] = null;
        //white list
        //if (pidWhiteList.contains(pid)) {
            String pidHiKey = pid;
            StringBuilder sb = new StringBuilder(URL_PREFIX);
            Arrays.stream(url.split("&")).map(str -> {
                if (!(str.startsWith("pin") || str.startsWith("uuid"))) {
                    sb.append(str).append("&");
                }
                if (str.startsWith("hi=")) {
                    inHi[0] = str.substring(3);
                }
                return "";
            }).collect(Collectors.toList());

            String coldStartUrl;
            if (StringUtil.isNotEmpty(inHi[0])) {
                pidHiKey = pidHiKey + SEPARATOR + inHi[0];
                sb.deleteCharAt(sb.length() - 1);
                coldStartUrl = sb.toString();
            }else if (StringUtil.isNotEmpty(outHi)) {
                pidHiKey = pidHiKey + SEPARATOR + outHi;
                coldStartUrl = sb.append("hi=").append(outHi).toString();
            } else {
                sb.deleteCharAt(sb.length() - 1);
                coldStartUrl = sb.toString();
            }
            System.out.println(coldStartUrl);
        //}
        System.out.println();
    }
}
