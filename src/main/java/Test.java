import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.netty.bootstrap.ServerBootstrap;
import utils.FileUtil;
import utils.JSONUtil;
import utils.ListUtil;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static utils.FileUtil.*;

public class Test {
    public static void main(String[] args) {
//        Lock lock = new ReentrantLock(true);
//        ServerBootstrap bootstrap = new ServerBootstrap();

//          List<Integer> list = Arrays.asList(1,2,4,5,6);
//          int size = list.size();
//
//          new Thread(()->{
//              System.out.println("hello" + Thread.currentThread().getName());
//          }
//          ).start();
//
//        System.out.println(Thread.currentThread().getName() + 11);


        String str = "{\"1\":\"11,23,12,26,28,29,19,53283,1,52993,84,30,31,10,32\",\"2\":\"123,456\"}\n";

        Type btype = new TypeToken<Map<Integer,String>>() {}.getType();

        Map<Integer, String> area2BlackList = new Gson().fromJson(str, btype);
        for (Integer key : area2BlackList.keySet()) {
            Set<String> set = Sets.newHashSet(Arrays.asList(area2BlackList.get(key).split(",")));
            System.out.println(set);
        }
        System.out.println(area2BlackList);

        Type ctype = new TypeToken<List<Long>>() {}.getType();

        ThreadLocal<String> tl = new InheritableThreadLocal<>();

    }
}
