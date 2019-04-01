package com.zhangwenfeng.learningcollection.plmcodetemplate;

/**
 * 所以，我对开发人员的要求就是，
 * 绝大部分场景，不允许捕获异常，不要乱加空判断。
 * 只有明显不需要关心的异常，
 * 如关闭资源的时候的io异常，可以捕获然后什么都不干，
 * 其他时候，不允许捕获异常，都抛出去，到controller处理。
 * 空判断大部分时候不需要，你如果写了空判断，你就必须测试为空和不为空二种场景，要么就不要写空判断。
 * 强调，有些空判断是要的，
 * 如：参数是用户输入的情况下。
 * 但是，大部分场景是不需要的（我们的IT系统里面，一半以上不需要），
 * 如参数是其它系统传过来，或者其他地方获取的传过来的，99.99%都不会为空，你判断来干嘛？
 * 就抛一个空指针到前台怎么啦？何况基本上不会出现。

 另外一种后台定时任务队列的异常，其实思路是一样的，有个统一的地方处理异常，
 里面的代码同样不准捕获异常！然后异常的时候邮件通知到我和开发人员，
 开发组长必须知道后台的任何异常，不要等用户投诉了才知道系统出问题了。
 另外，开发组长需要自己定义好系统里面的异常，其实能定义的没有几种，太细了很难落地，
 来，异常不要继承Exception，而是继承RuntimeException，否则到时候从头改到尾就为了加个异常声明你就觉得很无聊。
 */

/**
 * 开发组长定义好异常,继承RuntimeException
 * 不允许开发人员捕获异常。都controller上用AOP处理
 * 后台(队列)异常一定要有通知机制,要第一时间知道异常
 * 少加空判断,加了空判断就要测试为空的场景
 *
 * 非受检异常（运行时异常）和受检异常的区别等
 * 非受检异常指的是java.lang.RuntimeException和java.lang.Error类及其子类，
 * 所有其他的异常类都称为受检异常。
 * 两种类型的异常在作用上并没有差别，唯一的差别就在于使用受检异常时的合法性要在编译时刻由编译器来检查。
 * 正因为如此，受检异常在使用的时候需要比非受检异常更多的代码来避免编译错误。
 *
 * RuntimeException 在默认情况下会得到自动处理,所以通常用不着捕获RuntimeException,但是在自己的封装里,也许仍然要选择
 * 抛出一部分RuntimeException
 *
 * 以下是uncheckedExcepiton。
 *
 * 　　Java.lang.ArithmeticException
 *
 * 　　Java.lang.ArrayStoreExcetpion
 *
 * 　　Java.lang.ClassCastException
 *
 * 　　Java.lang.EnumConstantNotPresentException
 *
 * 　　Java.lang.IllegalArgumentException
 *
 * 　　Java.lang.IllegalThreadStateException
 *
 * 　　Java.lang.NumberFormatException
 *
 * 　　Java.lang.IllegalMonitorStateException
 *
 * 　　Java.lang.IllegalStateException
 *
 * 　　Java.lang.IndexOutOfBoundsException
 *
 * 　　Java.lang.ArrayIndexOutOfBoundsException
 *
 * 　　Java.lang.StringIndexOutOfBoundsException
 *
 * 　　Java.lang.NegativeArraySizeException’
 *
 * 　　Java.lang.NullPointerException
 *
 * 　　Java.lang.SecurityException
 *
 * 　　Java.lang.TypeNotPresentException
 *
 * 　　Java.lang.UnsupprotedOperationException
 *
 * CheckedException
 *
 * 　　除了runtimeException以外的异常，都属于checkedException，
 * 它们都在java.lang库内部定义。Java编译器要求程序必须捕获或声明抛出这种异常。
 *
 *一个方法必须通过throws语句在方法的声明部分说明它可能抛出但并未捕获的所有checkedException。
 *
 * 　　Java.lang.ClassNotFoundException
 *
 * 　　Java.lang.CloneNotSupportedException
 *
 * 　　Java.lang.IllegalAccessException
 *
 * 　　Java.lang.InterruptedException
 *
 * 　　Java.lang.NoSuchFieldException
 *
 * 　　Java.lang.NoSuchMetodException
 *
 * 受检异常的特点在于它强制要求开发人员在代码中进行显式的声明和捕获，否则就会产生编译错误。这种限制从好的方面来说，可以防止开发人员意外地忽略某些出错的情况，因为编译器不允许出现未被处理的受检异常；从不好的方面来说，受检异常对程序中的设计提出了更高的要求。不恰当地使用受检异常，会使代码中充斥着大量没有实际作用、只是为了通过编译而添加的代码。而非受检异常的特点是，如果不捕获异常，不会产生编译错误，异常会在运行时刻才被抛出。
 *
 * 　　非受检异常的好处是可以去掉一些不需要的异常处理代码，而不好之处是开发人员可能忽略某些应该处理的异常。一个典型的例子是把字符串转换成数字时会发生java.lang.NumberFormatException异常，忽略该异常可能导致一个错误的输入就造成整个程序退出。
 *
 * 　　目前的主流意见是，最好优先使用非受检异常。
 */
public class CheckException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CheckException() {}

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
