package com.huzz.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class OparatorTest {

    //@Test
    public  void test() {
        System.out.println(Integer.toBinaryString(-1<<29));
        System.out.println(Integer.toBinaryString(0<<29));
        System.out.println(Integer.toBinaryString(1<<29));
        System.out.println(Integer.toBinaryString(2<<29));
        System.out.println(Integer.toBinaryString(3<<29));
        System.out.println(10 & (~(1 << 29) - 1));
    }

    @Test
    public  void test1() {
        int n = 8;
        n |= n >>> 1;
        System.out.println(n);
        ArrayList<String> strings = new ArrayList<>(0);
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");
        strings.add("ee");

        LinkedList list = new LinkedList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("bb");
        list.add("dd");
        list.add(2,"ee");
        list.addLast("ff");
        list.addFirst("gg");
        LinkedList list1 = new LinkedList<String>();
        list1.add("aa1");
        list1.add("bb1");
        list.addAll(list1);
        list.add(2,list1);

        list.remove(2);

        HashSet<String> strings1 = new HashSet<>();
        strings1.add("ff");
    }

    @Test
    public  void testHashMap() {
        HashMap strings = new HashMap<String,Integer>();
        strings.put("11",11);
        strings.put("12",12);
        strings.put("13",13);
        strings.put("14",14);
        strings.put("15",15);
        strings.put("16",16);
        strings.put("17",17);
        strings.put("18",18);
        strings.put("19",19);
        strings.put("20",20);
        strings.put("14",144);

        LinkedHashMap stringss = new LinkedHashMap<String,Integer>();
        stringss.put("aa","1");
        stringss.put("dd","2");
        Iterator iterator = stringss.entrySet().iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        TreeMap treeMap = new TreeMap<String,Integer>();
        treeMap.put("aa",1);
        treeMap.put("cc",2);
        treeMap.put("bb",3);
        Iterator treeMapIterrator = treeMap.entrySet().iterator();
        while(treeMapIterrator.hasNext()) {
            System.out.println(treeMapIterrator.next());
        }
    }

    @Test
    public  void testConcurrentHashMap() {
        ConcurrentHashMap strings = new ConcurrentHashMap<String,Integer>();
        strings.put("11",11);
        strings.put("12",12);
        strings.put("13",13);
        strings.put("14",14);
        strings.put("15",15);
        strings.put("16",16);
        strings.put("17",17);
        strings.put("18",18);
        strings.put("19",19);
        strings.put("20",20);
        strings.put("14",144);
    }
}
