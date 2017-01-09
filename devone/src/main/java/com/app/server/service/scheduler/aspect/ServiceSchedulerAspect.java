package com.app.server.service.scheduler.aspect;
import com.app.server.service.aspect.ServiceAspect;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.msgWriter.config.ExecutionTimer;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;

@Aspect
@Component
public class ServiceSchedulerAspect extends ServiceAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    @Autowired
    private Healthmeter healthmeter;

    public HttpStatus httpStatusCode;

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeservice;

    @Autowired
    private ExecutionTimer executionTimer;

    @Autowired
    private MetricRepository repository;

    public AtomicLong autoRequestId = new AtomicLong(1);

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    /****
     * Method  to validate the api (request) and set request information in it.
     * @param proceedingJoinPoint
     * @return
     * @throws SecurityException
     */
    @Around("allOperation()")
    @Order(1)
    public Object aroundAdvice1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        HttpServletResponse response = servletWebRequest.getResponse();
        HttpSession session = request.getSession();
        long nextAutoNum = autoRequestId.getAndIncrement();
        methodCallStack.setRequestId(UUID.randomUUID().toString().toUpperCase());
        methodCallStack.setAppSessionId(getSessionId(request));
        methodCallStack.setCustomerId(getCustomerId(request));
        setRuntimeInfoObject(request, methodCallStack.getRequestId(), methodCallStack.getAppSessionId());
        if (request.getHeader("token") != null) {
            if (checkToken(proceedingJoinPoint, request.getHeader("token"))) {
                if (request.getHeader("isPublicApi") != null) {
                    if (!validatePublicApi(request)) {
                        AppAlarm appAlarm = Log.getAlarm("ABSSH124900500");
                        ResponseBean exceptionbean = new ResponseBean(appAlarm);
                        exceptionbean.add("message", String.format(appAlarm.getMessage(), "TOKEN IS NOT VALIDATED"));
                        httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
                        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), String.format(appAlarm.getMessage(), "TOKEN IS NOT VALIDATED"));
                        return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
                    }
                }
            } else {
                AppAlarm appAlarm = Log.getAlarm("ABSSH124900500");
                ResponseBean exceptionbean = new ResponseBean(appAlarm);
                exceptionbean.add("message", String.format(appAlarm.getMessage(), "UNAUTHORIZED"));
                httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
                Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), String.format(appAlarm.getMessage(), "UNAUTHORIZED"));
                return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
            }
        }
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            serviceLogic(session, request, response, methodCallStack.getRequestId(), methodCallStack.getAppSessionId());
            Object obj = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) obj;
            httpStatusCode = responseEntity.getStatusCode();
        } catch (com.spartan.pluggable.exception.core.AppBaseException e) {
            AppAlarm appAlarm = Log.getAlarm(e.getAppAlarmId());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getExceptionMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getAlarm("ABSSH124900500");
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), String.format(appAlarm.getMessage(), e.getMessage()));
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        }
        return responseEntity;
    }

    /**
     * Calculating counter for request in ehalth
     * @param oinPoint
     * @return
     * @throws Throwable
     */
    @AfterReturning("allOperation()")
    public void afterReturning(JoinPoint join) throws IOException {
        counterService.increment("counter.HttpStatus." + httpStatusCode.name() + "." + join.getSignature().getDeclaringType().getSimpleName() + "." + join.getSignature().getName() + ".calls");
        counterService.increment("counter.numberof.calls");
    }

    /**
     * Increment thr guage counter for particular request in health
     * @param className
     * @param methodName
     * @return
     */
    public String incrementUricounter(String className, String methodName) {
        counterService.increment(className + "." + methodName);
        Metric metric = repository.findOne("gauge." + className + "." + methodName + "");
        if (metric != null) {
            gaugeservice.submit(className + "." + methodName, (Double) metric.getValue() + 1);
        } else {
            gaugeservice.submit(className + "." + methodName, 1);
        }
        return className + "." + methodName;
    }

    @Pointcut("execution(* com.app.server.service.scheduler..*(..)) && !within(com.spartan.server.session.bizService.SessionDataMgtService+) && ! getOperation() && ! findOperation()")
    protected void allOperation() {
    }

    @Pointcut("execution(* com.app.server.service.scheduler..get*(..))")
    protected void getOperation() {
    }

    @Pointcut("execution(* com.app.server.service.scheduler..find*(..))")
    protected void findOperation() {
    }
}
