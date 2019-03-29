package com.zhangwenfeng.learningcollection.jvm.core;

import com.zhangwenfeng.learningcollection.jvm.common.ExecuteCommand;
import com.zhangwenfeng.learningcollection.jvm.entity.JstackEntity;

/**
 * jstack是JVM自带的堆栈跟踪工具。用于打印出给定的进程ID的Java堆栈信息,生成JVM当前时刻的线程快照。线程快照是当前JVM内每一条线程正在执行的方法堆栈的集合,
 * 生成线程的主要目的是定位线程出现长时间停顿的原因,如线程间死锁、死循环、请求外部资源导致的长时间等待等。
 * 基础知识:
 *  · 线程状态
 *      1. NEW: 未启动的
 *      2. RUNNABLE: 在JVM内执行的。运行中的状态,如果包含locked字样,表明它获得了某把锁。
 *      3. BLOCKED: 受阻塞并等待监视器锁。被某个锁(synchronized)给阻塞了。
 *      4. WATING: 无限期等待另一个线程执行特定操作,等待某个condition或nonitor发生。一般停留在park()、wait()、join()等语句。
 *      5. TIMED_WATING: 有时限的等待一个线程的特定操作。wait(timeout)
 *      6. TERMINATED: 已退出的。
 *  · Monitor
 *      1. 实现线程之间的互斥与协作的主要手段。
 *      2. 每一个对象都有,也仅有一个。
 *  · 调用修饰
 *      1. 表示线程在方法调用时,额外的重要的操作,线程Dump分析的重要信息
 *      2. locked <地址> 目标: 使用synchronized申请对象锁成功,监视器的拥有者
 *      3. waiting to lock <地址> 目标: 使用synchronized申请对象锁未成功,在进入区等待
 *      4. waiting on <地址> 目标: 使用synchronized申请对象锁成功后,释放锁在等待区等待
 *      5. parking to wait for <地址>
 *   · 线程动作
 *      1. runnable: 状态一般为runnable
 *      2. in Object.wait(): 等待区等待,状态为WAITING或TIMED_WAITING
 *      3. waiting for monitor entry: 进入区等待,状态为BLOCKED
 *      4. waiting on condition: 等待区等待,被park
 *      5. sleeping: 休眠的线程,调用Thread.sleep()
 */
public class JstackCommand {
    private static final String JSTACK = "jstack";
    public static JstackEntity _JSTACK(String id) {
        String res = ExecuteCommand.command(JSTACK, id);
        return null;
    }
}
