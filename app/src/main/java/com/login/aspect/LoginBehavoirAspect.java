package com.login.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.login.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author roy.xing
 * @date 2019-07-02
 */
@Aspect //定义切面类
public class LoginBehavoirAspect {

    private static final String TAG = "roy";

    /**
     * 1.应用中用到了那些注解，放到当前的切入点进行处理(找到需要处理的切入点)
     * execution 以方法执行时作为切点，触发Aspect类
     * * *(..)可以处理LoginCheck这个注解作用的所有方法
     */
    @Pointcut("execution(@com.login.annotation.LoginCheck * *(..))")
    public void methodPointCut() {
    }

    /**
     * 2.对切入点如何处理
     */
    @Around("methodPointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        if (false) {
            Log.e(TAG, "用户有身份，可以进行下一步 >>>");
            return joinPoint.proceed();
        } else {
            Log.e(TAG, "用户无身份，需要跳转到登录页面 >>>");
            context.startActivity(new Intent(context, LoginActivity.class));
            return null;
        }
    }

}
