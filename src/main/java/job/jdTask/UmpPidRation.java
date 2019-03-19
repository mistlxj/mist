package job.jdTask;

import com.google.common.collect.Sets;
import utils.FileUtil;
import utils.ListUtil;
import utils.StringUtil;

import java.util.List;
import java.util.Set;

import static utils.FileUtil.readFile;

/**
 * ump recproxy.proxy.$pid可用率报警统计
 */
public class UmpPidRation {

    private static final String allPid = "E:/markDown_pid.txt";
    private static final String realPid = "E:/ump_key.txt";
    private static final String finalPid = "E:/markDown_final_pid.txt";
    private static final String yesPid = "E:/yes_key.txt";

    public static void main(String[] args) {

        String yesPidStr = readFile(yesPid);
        List<String> yesPids = ListUtil.createList(yesPidStr.split("\r\n"));
        Set<String> yesPidSet = Sets.newHashSet(yesPids);

        String finalPidStr = readFile(finalPid);
        List<String> finalPids = ListUtil.createList(finalPidStr.split("\r\n"));
        StringBuilder finalSb = new StringBuilder();
        for (String str : finalPids) {
            String[] arr = str.split("\\|");
            if (arr.length == 3 && StringUtil.isNumeric(arr[0])) {
                String res =  yesPidSet.contains(arr[0]) ? "是" : "否";
                finalSb.append(arr[0]).append("|").append(arr[1]).append("|")
                        .append(arr[2]).append("|").append(res).append("|").append("\r\n");
            } else {
                finalSb.append(str).append("\r\n");
            }
        }
        FileUtil.writeFile(finalPid, finalSb.toString());
        System.out.println("success");
    }



    public static void wain(String[] args) {
        String realPidStr = readFile(realPid);
        List<String> realPids = ListUtil.createList(realPidStr.split("\r\n"));
        Set<String> realPidSet = Sets.newHashSet(realPids);

        String allPidStr = readFile(allPid);
        List<String> allPids = ListUtil.createList(allPidStr.split("\r\n"));
        StringBuilder finalSb = new StringBuilder();
        for (String str : allPids) {
            if (str.length() > 6 && StringUtil.isNumeric(str.substring(0, 6))) {
                if (realPidSet.contains(str.substring(0, 6))) {
                    finalSb.append(str).append("\r\n");
                } else {
                    continue;
                }
            } else {
                finalSb.append(str).append("\r\n");
            }
        }


        FileUtil.writeFile(finalPid, finalSb.toString());
        System.out.println("success");
    }
}
