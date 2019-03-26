package com.zhangwenfeng.learningcollection.jvm.jps;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Runtime类: 一个与JVM运行时环境有关的类,这个类是 Singleton。
 *  1. Runtime.getRuntime()可以获取当前JVM的运行时环境
 *  2. Runtime上其他大部分的方法都是实例方法,也就是说每次调用都要用到getRuntime()方法
 *  3. Runtime中的 exit() 方法是退出当前JVM的方法。
 */
public class JvmProcessStatusToolsTest {

    /**
     * JPS: Java Virtual Machine Process Status Tool JVM进程状态工具。
     * JPS是用于查看有权访问的hotspot虚拟机的进程,当未指定hostid时,默认查看本机JVM进程,否则查看指定的hostid机器上的JVM进程。此时hostid所指机器必须开启jstatd服务。
     * JPS可以列出JVM进程、主类类名、main函数参数、JVM参数、jar名称等信息
     */


    /**
     * 没添加参数的时候,默认列出 VM标识标示符号 和 简单的class或jar名称
     * 如下:
     * -----------------------
     * 16336 JStack
     * 5504 Launcher
     * 10532 JStack
     * 14356 JavaMonitorApplication
     * 15428 JStack
     * 15700 -- process information unavailable
     * 2708 JUnitStarter
     * 12744 Jps
     * 11404 Launcher
     * 2044 RemoteMavenServer
     * 8572
     * -----------------------
     */
    @Test
    public void testWithoutOption() {
        String result = readStringFromProcessBuilder("jps");
        System.out.println(result);
    }

    /**
     * -m: 输入JVM启动时传递给main()参数
     * 944 Jps -m
     * 4104 Bootstrap start
     */
    @Test
    public void testOption_m() {
        String result = readStringFromProcessBuilder("jps", "-m");
        System.out.println(result);
    }

    /**
     * -V: 列出JVM参数(主要的参数)
     *
     * 4104 Bootstrap -Djava.util.logging.config.file=E:\Tecsun\Tools\apache-tomcat-7.0.50\conf\logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.
     * endorsed.dirs=E:\Tecsun\Tools\apache-tomcat-7.0.50\endorsed -Dcatalina.base=E:\Tecsun\Tools\apache-tomcat-7.0.50 -Dcatalina.home=E:\Tecsun\Tools\apache-tomcat-7.0.50 -Djava.io.tmpdi
     * r=E:\Tecsun\Tools\apache-tomcat-7.0.50\temp
     * 5708 Jps -Denv.class.path=.;D:\Software\java8\jdk_1.8\lib;D:\Software\java8\jdk_1.8\lib\dt.jar;D:\Software\java8\jdk_1.8\lib\tools.jar -Dapplication.home=D:\Software\java8\jdk_1.8 -
     * Xms8m
     */
    @Test
    public void testOption_V() {
        String res = readStringFromProcessBuilder("jps", "-v");
        System.out.println(res);
    }


    /**
     * -V,-L: 常见参数
     *
     * 4104 Bootstrap -Djava.util.logging.config.file=E:\Tecsun\Tools\apache-tomcat-7.0.50\conf\logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.
     * endorsed.dirs=E:\Tecsun\Tools\apache-tomcat-7.0.50\endorsed -Dcatalina.base=E:\Tecsun\Tools\apache-tomcat-7.0.50 -Dcatalina.home=E:\Tecsun\Tools\apache-tomcat-7.0.50 -Djava.io.tmpdi
     * r=E:\Tecsun\Tools\apache-tomcat-7.0.50\temp
     * 5708 Jps -Denv.class.path=.;D:\Software\java8\jdk_1.8\lib;D:\Software\java8\jdk_1.8\lib\dt.jar;D:\Software\java8\jdk_1.8\lib\tools.jar -Dapplication.home=D:\Software\java8\jdk_1.8 -
     * Xms8m
     */
    @Test
    public void testOption_V_L() {

        String res = readStringFromProcessBuilder("jps","-v","-l");
        String[] line = res != null ? res.split("\n") : new String[0];
        for (String s : line) {
            // \s 是匹配任何空白字符、包括空格、制表符、换页符等
            String[] arg = s.split("\\s+");

            // 排除sun.tools进程
            if (arg[1].contains("sun.tools")) {
                continue;
            }
            System.out.println(s);
            if (! arg[1].substring(0,1).equals("-")) {
                // 获取最后.后面的名称
                String smallName = arg[1].contains(".") ? arg[1].substring(arg[1].lastIndexOf(".")+1) : arg[1];
                smallName = smallName.equalsIgnoreCase("jar")? arg[1] : smallName;

            } else {
                System.out.println("-------else" + arg[1]);
            }
        }

    }


    @Test
    public void testOptionl() {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"jps", "-l"});
            StringBuilder sb = readStringFromProcess(p);
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPing() {
        BufferedReader bufferedReader = null;
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ping www.baidu.com");
            bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while (bufferedReader.readLine() != null) {
                System.out.println(line);
            }
            // 使用waitFor()获取执行完成后的结果
            int result = p.waitFor();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPingByProcessBuilder() {
        BufferedReader reader = null;
        Process process = null;
        try {
            ProcessBuilder pbuilder = new ProcessBuilder("ping","192.168.0.125");
            pbuilder.redirectErrorStream(true);
            process = pbuilder.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line=null;
            while((line=reader.readLine())!=null){
                System.out.println(line);
            }
            int result=process.waitFor();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ProcessBuilder + GBK编码 == 成功
     */
    @Test
    public void testReadStringFromProcessBuilder() {
        String sb = readStringFromProcessBuilder("ping", "www.baidu.com");
        try {
            FileOutputStream fo = new FileOutputStream(new File("E://test.txt"));
            fo.write(sb.getBytes("utf-8"));
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println(sb);
    }

    @Test
    public void testJps() {
        String sb = readStringFromProcessBuilder("jps","-l","-v");
        String s = sb;
        String[] line = s != null ? s.split("\n") : new String[0];
        for (int i = 0; i < line.length; i++) {
            System.out.println(line[i]);
            System.out.println("==================");
        }
    }


    public static String readStringFromProcessBuilder(String... cmd) {
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

    public static StringBuilder readStringFromProcess(Process p) {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        try {
            Thread t = new Thread(new InputStreamRunnable(p.getErrorStream(), "ErrorStream"));
            t.start();

            /* "标准输出流"就在当前方法中读取 */
            BufferedInputStream bis = new BufferedInputStream(p.getInputStream());

            inputStreamReader = new InputStreamReader(bis, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            p.destroy();
        }
        return null;
    }

}
