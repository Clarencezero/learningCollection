package com.zhangwenfeng.learningcollection.plmcodetemplate.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * 工具类的规范例子
 *
 * @author 晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 */
public class StringUtil {

    public static boolean isEmpty(CharSequence cs) {
//        StringUtils.isEmpty(cs);
        return true;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
