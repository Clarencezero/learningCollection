package com.zhangwenfeng.learningcollection.jvm.core;

import com.zhangwenfeng.learningcollection.jvm.common.ExecuteCommand;
import com.zhangwenfeng.learningcollection.jvm.entity.JpsEntiry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JpsCommand {
    /**
     * JPS _V_L命令
     * @return
     */
    public static Map<String, JpsEntiry> _V_L() {
        Map<String, JpsEntiry> map = new HashMap<>();

        String res = ExecuteCommand.command("jps","-v","-l");
        String[] line = res != null ? res.split("\n") : new String[0];
        for (String s : line) {
            // \s 是匹配任何空白字符、包括空格、制表符、换页符等
            String[] arg = s.split("\\s+");

            // 排除sun.tools进程
            if (arg[1].contains("sun.tools")) {
                continue;
            }
            if (! arg[1].substring(0,1).equals("-")) {
                // 获取最后.后面的名称
                String smallName = arg[1].contains(".") ? arg[1].substring(arg[1].lastIndexOf(".")+1) : arg[1];
                smallName = smallName.equalsIgnoreCase("jar")? arg[1] : smallName;
                map.put(arg[0], new JpsEntiry(arg[1], smallName, Arrays.stream(arg).skip(2).collect(Collectors.toList())));
            } else {
                map.put(arg[0], new JpsEntiry("NULL","NULL", Arrays.stream(arg).skip(1).collect(Collectors.toList())));
            }
        }

        return map;
    }
}
