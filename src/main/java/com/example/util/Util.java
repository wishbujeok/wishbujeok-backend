package com.example.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
    public static Long getRandomNum(long size){
        // idx 1부터 시작
        Long randomNum = Double.valueOf(Math.random()*size).longValue()+1;

        return randomNum;
    }

    public static <K, V> Map<K, V> mapOf(Object... args) {
        Map<K, V> map = new LinkedHashMap<>();

        int size = args.length / 2;

        for (int i = 0; i < size; i++) {
            int keyIndex = i * 2;
            int valueIndex = keyIndex + 1;

            K key = (K) args[keyIndex];
            V value = (V) args[valueIndex];

            map.put(key, value);
        }

        return map;
    }
}
