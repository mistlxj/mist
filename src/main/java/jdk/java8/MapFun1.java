package jdk.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import utils.JSONUtil;
import utils.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaojian on 2018/5/31.
 */
public class MapFun1 {
    private static  Map<Long, List<String>> sku2ActMap = Maps.newHashMap();

    private static  Map<Long, List<String>> map = Maps.newHashMap();

    public static void main(String[] args) {

        Map<Long, List<String>> actId2Skus = ListUtil.createMapNoCheck(
                123L, Arrays.asList("21","32","45"),
                567L, Arrays.asList("21","56","99", "45"));
        genSku2ActMap(actId2Skus);
        System.out.println(JSONUtil.json(sku2ActMap));
    }


    private static void genSku2ActMap(Map<Long, List<String>> actId2Skus) {
        actId2Skus.entrySet().forEach(e -> {
            long actId = e.getKey();
            List<String> skus = e.getValue();
            skus.forEach(sku -> {
                sku2ActMap.computeIfAbsent(Long.parseLong(sku), k ->
                        Lists.newLinkedList()).add(String.valueOf(actId));
            });
        });
    }
}
