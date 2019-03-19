package job.jdTask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import utils.FileUtil;
import utils.JSONUtil;
import utils.ListUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.FileUtil.readFile;

/**
 * Created by lixiaojian10 on 2018/5/22.
 */
public class R2HubIpListGen {

    private static final String jsonIp = "E:/ip_list.json";
    private static final String lf_cluster = "lf";
    private static final String mjq_cluster = "mjq";
    private static final String ht_cluster = "ht";
    private static final String gz_cluster = "gz";
    private static final String lf_src = "D:/lf.txt";
    private static final String mjq_src = "D:/mjq.txt";
    private static final String ht_src = "D:/ht.txt";
    private static final String gz_src = "D:/gz.txt";
    private static final String ip_list = "D:/ip_list.json";

    public static void main(String[] args) {

        List<Map<String,String>> resList = Lists.newLinkedList();

        //File.class.getResource(ip_list).getFile();

        String lf_srcIpList = readFile(lf_src);
        List<String> lf_list = ListUtil.createList(lf_srcIpList.split(","));

        String mjq_srcIpList = readFile(mjq_src);
        List<String> mjq_list = ListUtil.createList(mjq_srcIpList.split(","));

        String ht_srcIpList = readFile(ht_src);
        List<String> ht_list = ListUtil.createList(ht_srcIpList.split(","));

        String gz_srcIpList = readFile(gz_src);
        List<String> gz_list = ListUtil.createList(gz_srcIpList.split(","));


        lf_list.forEach(ip -> {resList.add(ListUtil.createMapSS("cluster",lf_cluster,
                "platform","production",
                "ip",ip.trim()));});

        mjq_list.forEach(ip -> {resList.add(ListUtil.createMapSS("cluster",mjq_cluster,
                "platform","production",
                "ip",ip.trim()));});

        ht_list.forEach(ip -> {resList.add(ListUtil.createMapSS("cluster",ht_cluster,
                "platform","production",
                "ip",ip.trim()));});

        gz_list.forEach(ip -> {resList.add(ListUtil.createMapSS("cluster",gz_cluster,
                "platform","production",
                "ip",ip.trim()));});


        FileUtil.writeFile(ip_list,JSONUtil.json(resList));
        System.out.println("success");
    }



    public static void main1(String[] args) {
        List<String> delIps = Arrays.asList("11.19.72.9","11.19.72.8","11.19.72.7","11.19.72.68","11.19.72.67","11.19.72.66","11.19.72.65","11.19.72.64","11.19.72.63","11.19.72.62","11.19.72.61","11.19.72.60","11.19.72.6","11.19.72.59","11.19.72.58","11.19.72.57","11.19.72.56","11.19.72.55","11.19.72.54","11.19.72.53","11.19.72.52","11.19.72.51","11.19.72.50","11.19.72.5","11.19.72.49","11.19.72.48","11.19.72.47","11.19.72.46","11.19.71.219","11.19.71.218","11.19.71.217","11.19.71.216","11.19.71.211","11.19.71.210","11.19.71.209","11.19.71.208","11.19.71.207","11.19.71.206","11.19.71.205","11.19.71.204","11.19.71.203","11.19.71.202","11.19.71.201","11.19.71.200","11.19.71.199","11.19.71.198","11.19.71.197","11.19.71.196","11.19.71.195","11.19.71.194","11.19.71.193","11.19.71.192","11.19.71.191","11.19.71.190","11.19.71.189","11.19.71.188","11.19.7.45","11.19.7.44","11.19.7.43","11.19.7.42","11.19.7.37","11.19.7.36","11.19.7.35","11.19.7.34","11.19.7.33","11.19.7.32","11.19.7.31","11.19.7.30","11.19.7.29","11.19.7.28","11.19.7.27","11.19.7.26","11.19.7.25","11.19.7.24","11.19.7.23","11.19.7.22","11.19.7.21","11.19.7.20","11.19.7.19","11.19.7.186","11.19.7.185","11.19.7.184","11.19.7.183","11.19.7.182","11.19.7.113","11.19.7.105","11.19.7.104","11.19.7.103","11.19.7.102","11.19.7.101","11.19.7.100","11.19.39.99","11.19.39.98","11.19.39.97","11.19.39.96","11.19.39.95","11.19.39.94","11.19.39.93","11.19.39.92","11.19.39.91","11.19.39.90","11.19.39.9","11.19.39.89","11.19.39.88","11.19.39.87","11.19.39.86","11.19.39.85","11.19.39.84","11.19.39.83","11.19.39.82","11.19.39.81","11.19.39.80","11.19.39.19","11.19.39.189","11.19.39.188","11.19.39.187","11.19.39.186","11.19.39.185","11.19.39.184","11.19.39.183","11.19.39.182","11.19.39.181","11.19.39.180","11.19.39.18","11.19.39.179","11.19.39.178","11.19.39.177","11.19.39.176","11.19.39.175","11.19.39.174","11.19.39.173","11.19.39.172","11.19.39.171","11.19.39.170","11.19.39.17","11.19.39.169","11.19.39.168","11.19.39.167","11.19.39.166","11.19.39.165");
        Set<String> ipSet = delIps.stream().map(ip -> ip.trim()).collect(Collectors.toSet());
        String jsonIps = readFile(jsonIp);
        JSONArray array = JSONUtil.parseJSONArr(jsonIps);
        for (Iterator<Object> it = array.iterator(); it.hasNext();) {
            JSONObject obj = (JSONObject) it.next();
            String ip = obj.getString("ip");
            if (ipSet.contains(ip)) {
                it.remove();
            }
        }

        FileUtil.writeFile(ip_list,JSONUtil.json(array));
        System.out.println("success");
    }
}
