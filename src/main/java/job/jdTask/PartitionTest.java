package job.jdTask;

import utils.FileUtil;
import utils.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by lixiaojian on 2018/8/16.
 */
public class PartitionTest {
    private static final String ht_src = "D:/mist.txt";
    private static final Map<Integer, Integer>  itemPartitionMap = new ConcurrentHashMap<>(64);
    private static final Map<Integer, Integer>  itemPartitionMap2 = new ConcurrentHashMap<>(64);
    private static final Map<Integer, Integer>  itemPartitionMap3 = new ConcurrentHashMap<>(64);

    public static void main(String[] args) {

        int partitionNum = 10;
        int psPartitionNum = 40;
        int partitionIndexFrom = 1;

        String ht_srcIpList = FileUtil.readFile(ht_src);
        List<String> uuidList = ListUtil.createList(ht_srcIpList.split("\\n"));
        for (String items : uuidList) {
            String[] itemArr = items.split(",");
            for (int i = 1; i< itemArr.length - 1; i++) {
                int partition = partition(Long.parseLong(itemArr[i].substring(1,itemArr[i].length() - 1)),40,partitionIndexFrom);
                itemPartitionMap.put(partition, itemPartitionMap.getOrDefault(partition, 0) + 1);

                int murPartition = murPartition(itemArr[i].substring(1,itemArr[i].length() - 1),40,partitionIndexFrom);
                itemPartitionMap2.put(partition, itemPartitionMap2.getOrDefault(murPartition, 0) + 1);
            }
        }
        System.out.println("success");
    }





    private static final Map<String ,Map<Integer, Integer>> uuidPartitionMap1 = new ConcurrentHashMap<>(64);
    private static final Map<String ,Map<Integer, Integer>>  uuidPartitionMap2 = new ConcurrentHashMap<>(64);

    private static final Map<Integer, Integer> partitionMap1 = new ConcurrentHashMap<>(64);
    private static final Map<Integer, Integer> partitionMap2 = new ConcurrentHashMap<>(64);
    static {
        for (int i = 1; i <= 40; i++) {
            partitionMap1.put(i, 0);
            partitionMap2.put(i, 0);
        }
    }


    public static void main1(String[] args) {

        int partitionNum = 10;
        int psPartitionNum = 40;
        int partitionIndexFrom = 1;

        String ht_srcIpList = FileUtil.readFile(ht_src);
        List<String> uuidList = ListUtil.createList(ht_srcIpList.split("\\n"));
        int uuidNo = 1;
        for (String items : uuidList) {
            String[] itemArr = items.split(",");
            for (int i = 1; i< itemArr.length - 1; i++) {
                int partition = partition(Long.parseLong(itemArr[i].substring(1,itemArr[i].length() - 1)),partitionNum,partitionIndexFrom);
                int ph1 = getPhysicalPartition(partition, partitionNum, psPartitionNum);
                partitionMap1.put(ph1, partitionMap1.get(ph1) + 1);
                int ph2 = getPhysicalPartitionStrict(partition, partitionNum, psPartitionNum);
                partitionMap2.put(ph2, partitionMap2.get(ph2) + 1);
            }
            uuidPartitionMap1.put("uuid" + uuidNo, partitionMap1);
            uuidPartitionMap2.put("uuid" + uuidNo, partitionMap2);
            uuidNo++;
        }
        System.out.println("success");
    }






    public static int partition(Long itemId, int partitionNum, int partitionIndexFrom) {
        return Math.abs(itemId.hashCode()) % partitionNum + partitionIndexFrom;
    }

    public static int murPartition(String itemId, int partitionNum, int partitionIndexFrom) {
        return Math.abs( MurmurHashUtil.hash32(itemId)) % partitionNum + partitionIndexFrom;
    }

    public static long murPartition64(String itemId, int partitionNum, int partitionIndexFrom) {
        return Math.abs(MurmurHashUtil.hash64(itemId)) % partitionNum + partitionIndexFrom;
    }


    private static final Random random = new Random();

    public static int getPhysicalPartition(int partition, int partitionNum, int psPartitionNum) {
        int times = psPartitionNum / partitionNum;
        if (times > 1) {
            return (partition + random.nextInt(times) * partitionNum);
        } else {
            return partition;
        }
    }


    //-------------------------------------------------------------------------------------------------------------------

    private static final Map<Integer, Integer> psPartitionMap = new ConcurrentHashMap<>(64);

    static {
        for (int i = 1; i < 41; i++) {
            psPartitionMap.put(i, 0);
        }
    }

    public static int getPhysicalPartitionStrict(int partition, int partitionNum, int psPartitionNum) {
        int times = psPartitionNum / partitionNum;
        if (times == 1) {
            return partition;
        } else if (times == 2) {
            return searchFromPsMap(partition, partition + partitionNum);
        } else if (times == 4) {
            return searchFromPsMap(partition, partition + partitionNum, partition + 2 * partitionNum,
                    partition + 3 * partitionNum);
        } else {
            return -1;
        }
    }

    private static int searchFromPsMap(int... index) {
        int minCnt = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i : index) {
            if (psPartitionMap.get(i) < minCnt) {
                minCnt = psPartitionMap.get(i);
                minIndex = i;
            }
        }
        psPartitionMap.put(minIndex, minCnt++);
        return minIndex;
    }

}
