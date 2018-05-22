package job.jdTask;

import com.google.common.collect.Lists;
import utils.FileUtil;
import utils.JSONUtil;
import utils.ListUtil;

import java.util.List;
import java.util.Map;

import static utils.FileUtil.readFile;

/**
 * Created by lixiaojian10 on 2018/5/22.
 */
public class R2HubIpListGen {

    public static void main(String[] args) {
        String lf_cluster = "lf";
        String mjq_cluster = "mjq";
        String lf_src = "D:/lf.txt";
        String mjq_src = "D:/mjq.txt";
        String ip_list = "D:/ip_list.json";
        List<Map<String,String>> resList = Lists.newLinkedList();


        String lf_srcIpList = readFile(lf_src);
        List<String> lf_list = ListUtil.createList(lf_srcIpList.split(","));

        String mjq_srcIpList = readFile(mjq_src);
        List<String> mjq_list = ListUtil.createList(mjq_srcIpList.split(","));


        lf_list.forEach(ip -> {resList.add(ListUtil.createMapSS("cluster",lf_cluster,
                "platform","production",
                "ip",ip));});

        mjq_list.forEach(ip -> {resList.add(ListUtil.createMapSS("cluster",mjq_cluster,
                "platform","production",
                "ip",ip));});


        FileUtil.writeFile(ip_list,JSONUtil.json(resList));
        System.out.println("success");
    }
}
