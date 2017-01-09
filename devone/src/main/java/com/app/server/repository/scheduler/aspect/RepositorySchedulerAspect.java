package com.app.server.repository.scheduler.aspect;
import com.app.server.repository.aspect.RepositoryAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.alarms.EventAction;
import com.spartan.pluggable.logger.alarms.EventAppLayers;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;

@Aspect
@Component
public class RepositorySchedulerAspect extends RepositoryAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ArtMethodCallStack requestDetails;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    /**
     * checks the entity , calulate health , log the status for all operation in scheduler domain
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("allOperation()")
    public Object aroundAllOtherOpeartion(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIPAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId(), requestDetails.getCustomerId());
        Object object = null;
        setCustomerIdInEntityManager();
        try {
            object = joinPoint.proceed();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("ABS", "SH", EventAppLayers.DATABASE_SERVICE, EventAction.READ_WRITE_UPDATE, e.getClass().getName());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }

    @Pointcut("execution(* com.app.server.repository.scheduler..*(..)) && ! getOperation() && ! findOperation()")
    protected void allOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.scheduler..get*(..))")
    protected void getOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.scheduler..find*(..))")
    protected void findOperation() {
    }
}
