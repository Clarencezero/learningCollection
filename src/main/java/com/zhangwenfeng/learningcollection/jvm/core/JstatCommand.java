package com.zhangwenfeng.learningcollection.jvm.core;

import com.zhangwenfeng.learningcollection.jvm.common.ExecuteCommand;
import com.zhangwenfeng.learningcollection.jvm.entity.JstatEntity;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.Map;

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
     * 单位: 字节(kb)
     *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
     * 7168.0 9216.0 7153.6  0.0   51200.0  48231.5   116736.0   100956.4  62104.0 59026.2 8360.0 7758.8     92    2.336   3      0.525    2.861
     *
     * S0C : survivor0区的总容量
     * S1C : survivor1区的总容量
     * S0U : survivor0区已使用的容量
     * S1U : survivor1区已使用的容量
     *
     * EC : Eden区的总容量
     *      * EU : Eden区已使用的容量
     *      *
     * OC : Old区的总容量
     * OU : Old区已使用的容量
     *
     * PC 当前perm的容量 (KB)
     * PU perm的使用 (KB)
     * YGC : 新生代垃圾回收次数
     * YGCT : 新生代垃圾回收时间
     * FGC : 老年代垃圾回收次数
     * FGCT : 老年代垃圾回收时间
     * GCT : 垃圾回收总消耗时间
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



}
