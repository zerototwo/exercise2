//package com;
//
//import java.util.*;
//
//public class Greedy {
//
//    public static void main(String[] args) {
//
//        Map<String, HashSet<String>> bMap = new HashMap<>();
//        HashSet<String> hashSet1 = new HashSet<>();
//        hashSet1.add("北京");
//        hashSet1.add("上海");
//        hashSet1.add("天津");
//
//        HashSet<String> hashSet2 = new HashSet<>();
//        hashSet2.add("广州");
//        hashSet2.add("北京");
//        hashSet2.add("深圳");
//
//        HashSet<String> hashSet3 = new HashSet<>();
//        hashSet3.add("成都");
//        hashSet3.add("上海");
//        hashSet3.add("杭州");
//
//        HashSet<String> hashSet4 = new HashSet<>();
//        hashSet4.add("上海");
//        hashSet4.add("天津");
//
//        HashSet<String> hashSet5 = new HashSet<>();
//        hashSet5.add("杭州");
//        hashSet5.add("大连");
//
//        bMap.put("K1", hashSet1);
//        bMap.put("K2", hashSet2);
//        bMap.put("K3", hashSet3);
//        bMap.put("K4", hashSet4);
//        bMap.put("K5", hashSet5);
//
//        Set<String> allAreas = new HashSet<>();
//        allAreas.add("北京");
//        allAreas.add("上海");
//        allAreas.add("天津");
//        allAreas.add("广州");
//        allAreas.add("深圳");
//        allAreas.add("成都");
//        allAreas.add("大连");
//        allAreas.add("杭州");
//
//        List<String> selects = new ArrayList<>();
//        HashSet<String> tempSet = new HashSet<>();
//
//        String maxKey = null;
//        while (allAreas.size() > 0) {
//            maxKey = null;
//            for (String key : bMap.keySet()) {
//                tempSet.clear();
//                HashSet<String> areas = bMap.get(key);
//                tempSet.addAll(areas);
//
//                tempSet.retainAll(allAreas);
//                if (tempSet.size()>0&&(maxKey==null || tempSet.size()>bMap.get(maxKey).size())){
//                    maxKey = key;
//                }
//            }
//
//            if (maxKey !=null){
//                selects.add(maxKey);
//                allAreas.removeAll(bMap.get(maxKey));
//            }
//
//        }
//
//        System.out.println(selects);
//
//
//        /**
//         *  *
//         * 本实例用更高效的方法,省略临时变量,交换两个整数类型的变量
//         * 实现整数的高效互换
//         */
//
//                int a = 5;
//                int b = 10;
//                a = a^b;
//                b = a^b;
//                a = a^b;
//                System.out.println(a+":"+b);
//
//
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        System.out.println(list.subList(0,3));
//        System.out.println(Integer.valueOf("020"));
//        list.toArray()
//
//    }
//}
