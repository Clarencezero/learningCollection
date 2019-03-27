package com.zhangwenfeng.learningcollection.jvm.core;

import com.zhangwenfeng.learningcollection.jvm.common.ExecuteCommand;
import com.zhangwenfeng.learningcollection.jvm.entity.JstatEntity;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.Map;

/**
 * JSTAT命令可以得出哪些信息:
 * 1. Full GC是否频繁?看下总内存和老年代设置的是否太小了。主要看参数 -Xmx、-Xmn。如果参数过小,必然会GC频繁
 * 2. 添加以下参数
 *  -verbose.gc
 *  -XX:+PrintGC
 *  -XX:+PrintGCTimeStamps
 *  -XX:+PrintGCDateStamps
 *  -XX:+PrintHeapAtGC
 *  -XX:+PrintTenuringDistribution
 *  -XX:+PrintGCApplicationStoppedTime
 *  -XX:+PrintTenuringDistribution
 *  -Xloggc:/data/log/xxx/xxx-gc.log
 *  -XX:+PrintGCDetails
 * 查看每次gc ，老年代还剩余多少空间，一般来说，老年代的空间设置为gc后内存的2-2.5倍是一个较为合理的数值。
 * 同时通过这个日志可以看到是否大量的对象没有在新生代充分的gc掉就进入老生代。原本可以通过新生代回收的对象进入老年代的话必然会full gc 频繁
 *
 * 3. 代码原因 dump 内存
 *  jmap -dump:live,format=b,file=xx.bin ［pid］
 *  然后通过MAT（http://www.eclipse.org/mat/）工具来看具体是哪块大内存没有被释放
 */
public class JstatCommand {
    private static final String JSTAT = "jstat";
    private static final String _CLASS = "-class";
    /**
     * HotSpt JIT编译器行为统计。Statistics of the behavior of the HotSpot Just-in-Time compiler.
     */
    private static final String _COMPILER = "-compiler";
    private static final String _GC = "-gc";
    private static final String _GCCAPACITY = "-gccapacity";
    /**
     * 垃圾回收统计概述。Summary of garbage collection statistics.
     */
    private static final String _GCUTIL = "-gcutil";



    /**
     * 监视类装载、卸载数量、总空间以及耗费的时间
     *
     * Loaded: 加载class的数量
     * Bytes: class字节大小
     * Unloaded: 未加载class的数量
     * Bytes: 未加载class的字节大小
     * Time: 加载时间
     * Loaded  Bytes  Unloaded  Bytes     Time
     *   3436  6512.0        0     0.0       5.30
     * @param id
     * @return
     */
    public static Map<String, JstatEntity> _CLASS(String id) {
        String res = ExecuteCommand.command(JSTAT, _CLASS, id);
        System.out.println(res);

        return null;
    }


    /**
     * 输出JIT编译过的方法数量耗时等。
     *
     * Compiled : 编译数量
     * Failed : 编译失败数量
     * Invalid : 无效数量
     * Time : 编译耗时
     * FailedType : 失败类型
     * FailedMethod : 失败方法的全限定名
     * Compiled Failed Invalid   Time   FailedType FailedMethod
     *     1451      0       0     5.07          0
     * @param id
     * @return
     */
    public static Map<String, JstatEntity> _COMPILER(String id) {
        String res = ExecuteCommand.command(JSTAT, _COMPILER, id);
        System.out.println(res);
        return null;
    }


    /**
     * 垃圾回收堆的行为统计。
     * JVM中的堆: 分为: 新生代、老年代、永久代(JDK1.8 被元数据区(元空间)的区域所取代)
     *      - 新生代分: Eden区、ServivorFrom、ServivorTo三个区
     *      Servivor: 保留一次MinorGC过程中的幸存者
     *      MinorGC复制过程: 采用复制算法。首先,把Eden区和ServivorFrom区域中的存活的对象复制到ServivorTo区域中,如果某些对象达到老年的标准年龄,则复制到老年区,同时把这些对象的年龄+1。
     *      然后,清空Eden和ServivorFrom中的对象;最后,ServivorTo和ServivorFrom互换。
     * 单位: 字节(kb)
     *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC      MU     CCSC   CCSU      YGC    YGCT   FGC     FGCT     GCT
     * 7168.0 9216.0 7153.6  0.0   51200.0  48231.5   116736.0   100956.4  62104.0 59026.2 8360.0  7758.8     92    2.336   3      0.525    2.861
     *
     * S0C : survivor0区的总容量
     * S1C : survivor1区的总容量
     * S0U : survivor0区已使用的容量
     * S1U : survivor1区已使用的容量
     *
     * EC : Eden区的总容量
     * EU : Eden区已使用的容量
     *
     * OC : Old区的总容量
     * OU : Old区已使用的容量
     *
     * PC 当前perm的容量 (KB)(JDK1.8之前叫永久代,JDK1.8后使用元数据区代替(即MC))
     * PU perm的使用 (KB)
     *
     * YGC : 从程序启动到采样时发生YoungGC的次数
     * YGCT : 新生代垃圾回收时间(单位: S)
     * FGC : 从程序启动到采样时发生Full GC的次数
     * FGCT : 老年代垃圾回收时间(单位: S)
     * GCT : 垃圾回收总消耗时间(单位: S)
     *
     * jstat -gc 1261 2000 20
     * @param id
     * @return
     */
    public static Map<String, JstatEntity> _GC(String id) {
        String res = ExecuteCommand.command(JSTAT, _GC, id);
        System.out.println(res);
        return null;
    }


    public static Map<String, JstatEntity> _GCUTIL(String id) {
        String res = ExecuteCommand.command(JSTAT, _GCUTIL, id);
        System.out.println(res);
        return null;
    }



}
