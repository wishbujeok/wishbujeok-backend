package com.example.app.global.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Util {
    private final MessageSourceAccessor messageSourceAccessor;

    public static Long getRandomNum(long size){
        // idx 1부터 시작
        Long randomNum = Double.valueOf(Math.random()*size).longValue()+1;

        return randomNum;
    }

    public static String byteArrToString(byte[] arr){
        String s;
        try {
            s = new String(arr, "UTF8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return s;
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

    public static <K, V> Map<K, V> mapOf(Map<K,V> oldMap, Object... args) {
        Map<K, V> map = oldMap;

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
