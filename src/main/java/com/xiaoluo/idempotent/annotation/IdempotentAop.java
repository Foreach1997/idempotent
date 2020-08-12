package com.xiaoluo.idempotent.annotation;

import com.xiaoluo.idempotent.common.IdempotentConstant;
import com.xiaoluo.idempotent.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Project:               idempotent
 *
 * @Author: ljw
 * Created Date:          2020/5/29
 * Description:   {类描述}
 * <p>
 * History:
 * ------------------------------------------------------------------------------
 * Date            |time        |Author    |Change Description
 */
@Component
@Aspect
@Slf4j
public class IdempotentAop {


    @Autowired
    private RedissonClient redissonClient;

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(com.xiaoluo.idempotent.annotation.Idempotent)")
    public void idempotent(){

    }


    @Around(value = "@annotation(idempotent)")
    public Object before(ProceedingJoinPoint joinPoint , Idempotent idempotent) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] params = discoverer.getParameterNames(methodSignature.getMethod());
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], joinPoint.getArgs()[len]);
        }
        Expression expression = parser.parseExpression(idempotent.key());
        String key = String.valueOf(expression.getValue(context));
        RLock rLock = redissonClient.getLock(key);
        if (rLock != null) {
            if (rLock.tryLock(IdempotentConstant.TRY_WAIT_LOCK,idempotent.time(), TimeUnit.MILLISECONDS)){
                try {
                 return  joinPoint.proceed();
                }catch (Exception  e){
                    log.warn("",e);
                    throw new RuntimeException(e);
                }finally {
                    rLock.unlock();
                }
            }else {
                throw new BizException("稍后再试");
            }
        }
        throw new BizException("稍后再试");
    }

}
