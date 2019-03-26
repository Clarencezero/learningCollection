package com.zhangwenfeng.learningcollection.jvm.jstat;

import com.zhangwenfeng.learningcollection.jvm.core.JpsCommand;
import com.zhangwenfeng.learningcollection.jvm.core.JstatCommand;
import com.zhangwenfeng.learningcollection.jvm.entity.JpsEntiry;
import com.zhangwenfeng.learningcollection.jvm.entity.JstatEntity;
import org.junit.Test;

import java.util.Map;

/**
 * jstat(JVM statistics Monitoring)是用于监视虚拟机运行时状态信息的命令，它可以显示出虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。
 */
public class JVMStatisticMonitoringTest {
    /**
     * class loader 的行为统计。
     * 监视类装载、卸载数量、总空间以及耗费的时间
     */
    @Test
    public void testOption_CLASS() {
        Map<String, JpsEntiry> map = JpsCommand._V_L();

        for (Map.Entry<String, JpsEntiry> stringJpsEntiryEntry : map.entrySet()) {
            String jvmTarget = stringJpsEntiryEntry.getKey();
//            Map<String, JstatEntity> stringJstatEntityMap = JstatCommand._CLASS(jvmTarget);
//            Map<String, JstatEntity> stringJstatEntityMap = JstatCommand._COMPILER(jvmTarget);
            Map<String, JstatEntity> stringJstatEntityMap = JstatCommand._GC(jvmTarget);
        }
    }



}
