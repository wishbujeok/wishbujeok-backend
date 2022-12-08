package com.example.util;

public class Util {
    public static Long getRandomNum(long size){
        // idx 1부터 시작
        Long randomNum = Double.valueOf(Math.random()*size).longValue()+1;

        return randomNum;
    }
}
