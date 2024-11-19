package com.java.coding.scratch;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTest {

    public static void main(String[] args) {


        String str = "test";
        String name = "nayan";

        System.out.println(StringUtils.upperCase(name));

//        System.out.println(StringUtils.isEmpty("test"));
//        System.out.println(StringUtils.isAllUpperCase("test"));
//        System.out.println(StringUtils.upperCase(str));
//        System.out.println(StringUtils.indexOf(str,"e"));
//        System.out.println(StringUtils.reverse(name));
//
//        System.out.println(RandomStringUtils.randomAscii(8));

        StringBuilder stringBuilder = new StringBuilder("Nayan");
        System.out.println(stringBuilder.insert(0, "Mr. "));
        stringBuilder.append(" awale");

        StringBuffer stringBuffer = new StringBuffer("Nayan");
        stringBuffer.insert(5," ");
        stringBuffer.append("Awale");

        List<String> list = new ArrayList<String>();
        list.add("test");
        list.add(name);
        list.add(str);

        System.out.println(list.get(2));

        Collections.sort(list);
        System.out.println(list);
        for(String item: list){
            if(!list.isEmpty())
            System.out.println(StringUtils.upperCase(item));
        }
    }

    public static String upperCase(String name){
        return name.toUpperCase();
    }


}
