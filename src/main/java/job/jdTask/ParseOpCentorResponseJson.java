package job.jdTask;

import utils.FileUtil;
import utils.JSONUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by lixiaojian on 2018/6/1.
 */


public class ParseOpCentorResponseJson {
    private static final String DISCONNECTED = "disconnected";
    private static final String PATH = "D:/disconnected.txt";

    public static void main(String[] args) throws IOException {
        ParseOpCentorResponseJson parse = new ParseOpCentorResponseJson();
        parse.parse("broadway");
    }

    private void parse(String system) throws IOException {
        String response = FileUtil.readFile(PATH);

        Data data = JSONUtil.parseObject(response, Data.class);
        if (data != null && data.getNodes() != null) {
            Object object = data.getNodes();
            Map<String, Object> map = (Map<String, Object>) object;
            List rs = (List) map.get("broadway");
            List rs2 = (List) rs.get(0);
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (Object o2 : rs2) {
                Map map1 = (Map) o2;
                if (map1.get("result").equals(DISCONNECTED)) {
                    i++;
                    if (sb.length() > 0) {
                        sb.append(",").append(map1.get("ip"));
                    } else {
                        sb.append(map1.get("ip"));
                    }
                }
            }
            System.out.println(sb.toString());
            System.out.println(i);
        }


    }
}

class Data {
    private Object nodes;

    public Object getNodes() {
        return nodes;
    }

    public void setNodes(Object nodes) {
        this.nodes = nodes;
    }
}

