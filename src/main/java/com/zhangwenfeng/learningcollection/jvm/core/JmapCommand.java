package com.zhangwenfeng.learningcollection.jvm.core;

import com.zhangwenfeng.learningcollection.jvm.common.ExecuteCommand;
import com.zhangwenfeng.learningcollection.jvm.entity.JmapEntiry;

import java.util.Map;

public class JmapCommand {
    private static final String JMAP = "jmap";
    /**
     * 生成堆转储快照
     */
    private static final String _DUMP = "-dump:format=b,file=";
    /**
     * 显示Java堆详细信息
     */
    private static final String _HEAP = "-heap";
    /**
     * 显示堆中对象的统计信息
     */
    private static final String _HISTO = "-histo";
    /**
     * 强制生成DUMP快照
     */
    private static final String _F = "-h";

    private static final String DUMP_SUFFIX = ".hprof";

    /**
     * 下载转储快照
     * @param id
     * @param path
     * @return
     */
    public Map<String, JmapEntiry> _DUMP(String id, String path) {
        ExecuteCommand.command(JMAP, _DUMP + path + id + DUMP_SUFFIX, id);
        return null;
    }


    /**
     * $ jmap -heap 28920
     *   Attaching to process ID 28920, please wait...
     *   Debugger attached successfully.
     *   Server compiler detected.
     *   JVM version is 24.71-b01
     *
     *   using thread-local object allocation.
     *   Parallel GC with 4 thread(s)//GC 方式
     *
     *   Heap Configuration: //堆内存初始化配置
     *      MinHeapFreeRatio = 0                        //对应jvm启动参数-XX:MinHeapFreeRatio设置JVM堆最小空闲比率(default 40)
     *      MaxHeapFreeRatio = 100                      //对应jvm启动参数-XX:MaxHeapFreeRatio设置JVM堆最大空闲比率(default 70)
     *      MaxHeapSize      = 2082471936 (1986.0MB)    //对应jvm启动参数-XX:MaxHeapSize=设置JVM堆的最大大小
     *      NewSize          = 1310720 (1.25MB)         //对应jvm启动参数-XX:NewSize=设置JVM堆的‘新生代’的默认大小
     *      MaxNewSize       = 17592186044415 MB        //对应jvm启动参数-XX:MaxNewSize=设置JVM堆的‘新生代’的最大大小
     *      OldSize          = 5439488 (5.1875MB)       //对应jvm启动参数-XX:OldSize=<value>:设置JVM堆的‘老生代’的大小
     *      NewRatio         = 2                        //对应jvm启动参数-XX:NewRatio=:‘新生代’和‘老生代’的大小比率
     *      SurvivorRatio    = 8                        //对应jvm启动参数-XX:SurvivorRatio=设置年轻代中Eden区与Survivor区的大小比值
     *      MetaspaceSize    = 21757952 (20.75MB)       //-XX:MetaspaceSize=256m: 指Metaspace扩容时触发FullGC的初始化阈值。默认(20.8MB)。可以通过jinfo -flag MetaspaceSize查看。
     *                                                  // Metaspace不断扩容到参数指定的量,就会发生FullGC;且之后每次扩容都会发生FullGC。MetaspaceSize和MaxMetaspaceSize设置一样大。建议256MB即可。
     *      MaxPermSize      = 85983232 (82.0MB)        //对应jvm启动参数-XX:MaxPermSize=<value>:设置JVM堆的‘永生代’的最大大小
     *      G1HeapRegionSize = 0 (0.0MB)
     *
     *   Heap Usage://堆内存使用情况
     *   PS Young Generation
     *   Eden Space://Eden区内存分布
     *      capacity = 33030144 (31.5MB)                //Eden区总容量
     *      used     = 1524040 (1.4534378051757812MB)   //Eden区已使用
     *      free     = 31506104 (30.04656219482422MB)   //Eden区剩余容量
     *      4.614088270399305% used                     //Eden区使用比率
     *   From Space:    //其中一个Survivor区的内存分布
     *      capacity = 5242880 (5.0MB)
     *      used     = 0 (0.0MB)
     *      free     = 5242880 (5.0MB)
     *      0.0% used
     *   To Space:  //另一个Survivor区的内存分布
     *      capacity = 5242880 (5.0MB)
     *      used     = 0 (0.0MB)
     *      free     = 5242880 (5.0MB)
     *      0.0% used
     *   PS Old Generation //当前的Old区内存分布
     *      capacity = 86507520 (82.5MB)
     *      used     = 0 (0.0MB)
     *      free     = 86507520 (82.5MB)
     *      0.0% used
     *   PS Perm Generation//当前的 “永生代” 内存分布
     *      capacity = 22020096 (21.0MB)
     *      used     = 2496528 (2.3808746337890625MB)
     *      free     = 19523568 (18.619125366210938MB)
     *      11.337498256138392% used
     *
     *   670 interned Strings occupying 43720 bytes.
     *
     * 22307 interned Strings occupying 2593352 bytes.
     */
    public Map<String, JmapEntiry> _HEAP(String id) {
        String res = ExecuteCommand.command(JMAP, _HEAP, id);
        System.out.println(res);
        return null;
    }

}
