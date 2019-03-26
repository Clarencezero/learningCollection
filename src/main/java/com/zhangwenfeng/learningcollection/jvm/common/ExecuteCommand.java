package com.zhangwenfeng.learningcollection.jvm.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCommand {
    public static String command(String... cmd) {
        StringBuilder sb = null;
        try {
            ProcessBuilder pBuilder = new ProcessBuilder(cmd);
            Process process = pBuilder.start();
            pBuilder.redirectErrorStream(true);
            // 带有缓冲的字节流
            BufferedInputStream bis = new BufferedInputStream(process.getInputStream());

            // 字节-字符转换流
            InputStreamReader iReader = new InputStreamReader(bis,"UTF-8");

            // 带有缓冲的字符流
            BufferedReader bReader = new BufferedReader(iReader) ;

            String line;

            sb = new StringBuilder();

            while ((line = bReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
