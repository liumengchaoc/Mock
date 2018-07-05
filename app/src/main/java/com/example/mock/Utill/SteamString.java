package com.example.mock.Utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SteamString {
    public static   String transfrom(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String line=null;
        StringBuffer stringBuffer=new StringBuffer();
        while ((line=bufferedReader.readLine())!=null){
    stringBuffer.append(line);
        }
bufferedReader.close();

return  stringBuffer.toString();

    };




}
