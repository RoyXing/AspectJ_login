package com.login.aspect;

import android.util.Log;

import com.login.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author roy.xing
 * @date 2019-07-02
 */
@Aspect //定义切面类
public class ClickBehavoirAspect {
    private static final String TAG = "roy";

    /**
     * 1.应用中用到了那些注解，放到当前的切入点进行处理(找到需要处理的切入点)
     * execution 以方法执行时作为切点，触发Aspect类
     * * *(..)可以处理ClickBehavoir这个注解作用的所有方法
     */
    @Pointcut("execution(@com.login.annotation.ClickBehavior * *(..))")
    public void methodPointCut() {
    }

    /**
     * 2.对切入点如何处理
     */
    @Around("methodPointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取签名方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取方法所属的类名
        String clazzName = signature.getDeclaringType().getSimpleName();

        //获取方法名
        String methodName = signature.getMethod().getName();

        //获取方法的注解值(需要添加用户行为统计)
        String actionName = signature.getMethod().getAnnotation(ClickBehavior.class).value();

        //统计方法的执行时间、统计用户点击某功能行为
        long begin = System.currentTimeMillis();
        Log.e(TAG, "ClickBehavior Method Start >>>");
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - begin;
        Log.e(TAG, "ClickBehavior Method End >>>");
        Log.e(TAG, String.format("统计:%s功能,在%s类的%s方法，用时%d ms", actionName, clazzName, methodName, duration));
        return result;
    }

}
