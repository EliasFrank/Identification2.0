package com.example.identification20.myserver;

public class MyResult {
    private static String result;
    private static String imagePath;
    public MyResult(){}
    public static void setResult(String r){
        result = r;
    }
    public static String getResult(){
        return result;
    }
    public static void setImagePath(String r){
       imagePath = r;
    }
    public static String getImagePath(){
        return imagePath;
    }
}
