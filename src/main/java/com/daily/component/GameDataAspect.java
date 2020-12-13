package com.daily.component;


import com.daily.pojo.Comment;
import com.daily.tool.EncrypDES;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @ClassName GameDataAspect
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/28 19:43
 * @VERSION 1.0.0
 */
@Aspect
@Component
public class GameDataAspect {
    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.daily.controller.CommentCtrl.save(..)) && args(point))")
    public void GameDataAspect(Comment point){

    }

    /**
     * @description  使用环绕通知
     */
    @Around("GameDataAspect(point)")
    public void doAroundGameData(ProceedingJoinPoint pjp, Comment point) throws Throwable {
        try{
            System.out.println("球星上场前热身！");
            EncrypDES des2 = new EncrypDES();// 自定义密钥
            point.setContent(des2.encrypt(point.getContent()));
            pjp.proceed();
            System.out.println("球星本场得到" + point.getContent() + "分" );
        }
        catch(Throwable e){
            System.out.println("异常通知：球迷要求退票！");
        }
    }
}
