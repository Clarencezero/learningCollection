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
 * 同时通过这个日志可以看到是否大量的对象没有在新生代充分的gc掉就进入老生代。原本可以通过新生代回收的对象进入老年代的话必然会full gc 频繁。
 *
 * 什么时候触发GC:
 *  1. 程序调用System.gc()时可以触发。
 *  2. 系统自身来决定GC触发的时机。
 *      系统根据Eden区和FromSpace区的内存大小来决定,当内存大小不足时,则会启动GC线程并停止应用线程。
 *
 *  Minor GC触发条件:当Eden区满时,触发Minor GC
 *  Full GC触发条件:
 *      1. 调用System.gc()时,系统建议执行Full GC,但是不必然执行
 *      2. 老年代空间不足
 *      3. 方法区空间不足
 *      4. Minor GC后进入老年代的平均大小 > 老年代可用空间
 *      5. 由Eden区、From Space区向ToSpace区复制时,对象大小大于To Space可用内存,则把该对象转存到老年代,且老年代的可用空间小于该对象大小
 *
 *
 * 3. 代码原因 dump 内存
 *  jmap -dump:live,format=b,file=xx.bin ［pid］
 *  然后通过MAT（http://www.eclipse.org/mat/）工具来看具体是哪块大内存没有被释放
 */
public class JstatCommand {
    private static final String JSTAT = "jstat";
    /**
     * class: class loader的行为统计。Statistics on the behavior of the class loader.
     */
    private static final String _CLASS = "-class";
    /**
     * compiler: HotSpt JIT编译器行为统计。Statistics of the behavior of the HotSpot Just-in-Time compiler.
     */
    private static final String _COMPILER = "-compiler";
    /**
     * gc: 垃圾回收堆的行为统计。Statistics of the behavior of the garbage collected heap.
     */
    private static final String _GC = "-gc";
    /**
     * gccapacity: 各个垃圾回收代容量(young,old,perm)和他们相应的空间统计。Statistics of the capacities of the generations and their corresponding spaces.
     */
    private static final String _GCCAPACITY = "-gccapacity";
    /**
     * gcutil: 垃圾回收统计概述。Summary of garbage collection statistics.
     */
    private static final String _GCUTIL = "-gcutil";

    /**
     * gccause: 	垃圾收集统计概述（同-gcutil），附加最近两次垃圾回收事件的原因。Summary of garbage collection statistics (same as -gcutil), with the cause of the last and
     */
    private static final String _GCCAUSE = "-gccause";

    /**
     * gcnew: 新生代行为统计
     */
    private static final String _GCNEW = "-gcnew";
    /**
     * gcold: 年老代和永生代行为统计
     */
    private static final String _GCOLD = "-gcold";

    /**
     * gcnewcapacity:	新生代与其相应的内存空间的统计
     */
    private static final String _GCNEWCAPACITY = "-gcnewcapacity";

    /**
     * gcoldcapacity:	年老代行为统计
     */
    private static final String _GCOLDCAPACITY = "-gcoldcapacity";

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


    /**
     * 总结垃圾回收统计
     *
     *  S0：幸存1区当前使用比例
     *  S1：幸存2区当前使用比例
     *   E：伊甸园区使用比例
     *   O：老年代使用比例
     *   M：元数据区使用比例
     * CCS：压缩使用比例
     * YGC：年轻代垃圾回收次数
     * FGC：老年代垃圾回收次数
     *FGCT：老年代垃圾回收消耗时间
     * GCT：垃圾回收消耗总时间
     *   S0     S1     E      O      M     CCS      YGC     YGCT    FGC    FGCT     GCT
     *   0.00   0.00  76.86  42.49  98.05  96.00     11    1.374     2    0.713    2.088
     * @param id
     * @return
     */
    public static Map<String, JstatEntity> _GCUTIL(String id) {
        String res = ExecuteCommand.command(JSTAT, _GCUTIL, id);
        System.out.println(res);
        return null;
    }


    /**
     *  NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC            MCMN     MCMX      MC          CCSMN    CCSMX        CCSC    YGC    FGC
     *  43520.0 698880.0 698880.0 43520.0 41472.0 413184.0    87552.0  1398272.0   119808.0   119808.0      0.0     1081344.0  35416.0      0.0     1048576.0   4096.0     11     2
     *
     * NGCMN：新生代最小容量
     * NGCMX：新生代最大容量
     * NGC：当前新生代容量
     * S0C：第一个幸存区大小
     * S1C：第二个幸存区的大小
     * EC：伊甸园区的大小
     * OGCMN：老年代最小容量
     * OGCMX：老年代最大容量
     * OGC：当前老年代大小
     * OC:当前老年代大小
     * MCMN:最小元数据容量
     * MCMX：最大元数据容量
     * MC：当前元数据空间大小
     * CCSMN：最小压缩类空间大小
     * CCSMX：最大压缩类空间大小
     * CCSC：当前压缩类空间大小
     * YGC：年轻代gc次数
     * FGC：老年代GC次数
     * ---------------------
     * 作者：褚金辉
     * 来源：CSDN
     * 原文：https://blog.csdn.net/maosijunzi/article/details/46049117
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     * @param id
     * @return
     */
    public static Map<String, JstatEntity> _GCCAPACITY(String id) {
        String res = ExecuteCommand.command(JSTAT, _GCCAPACITY, id);
        System.out.println(res);
        return null;
    }


    /**
     *  垃圾收集统计概述,同-gcutil。
     *  LGCC: 最近垃圾回收原因
     *  GCC: 当前垃圾回收的原因
     *   S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT      GCT           LGCC               GCC
     *   0.00   0.00  98.86  42.49  98.05  96.00     11    1.374     2    0.713    2.088  Metadata GC Threshold    No GC
     * @param id
     * @return
     */
    public static Map<String, JstatEntity> _GCCAUSE(String id) {
        String res = ExecuteCommand.command(JSTAT, _GCCAUSE, id);
        System.out.println(res);
        return null;
    }





}
