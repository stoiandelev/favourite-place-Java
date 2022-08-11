
/**
 * Documentation
 * SLO/SLI/SLA
 * We must add dependency for this -> Spring AOP;
 * SLA -> Service-level agreement ;
 * for example in 99,5% by time DB MySQL in UP and RUNNING. For one year this is almost
 * 4 hour(0.05 % - without DB in production);
 * SLA is calculated with SLI -> Service-level Indicator;
 * We must see latency(латентност) -> time for execution on this method;
 * Example -> RestAPI must response bellow 1 second or 1000 ms, when API is called;
 * SLO -> Service-level objective must more strictly than SLA;
 * Must be created monitoring on time on response before to we violate the SLA;
 * We do this with help Aspect;
 * We can configuration fake time with Thread.sleep(millisecond);
 * Created Aspect who track the time for execution on REST method and if something's in not
 * in time, to WARN;
 * Created Configuration SLO who read from Application.yml;
 * Slos-config is on milliseconds -> 1 seconds e equal on 1000 milliseconds.
 * Aspect control time for execution on Rest Controller, and WARN if something's is not
 * in setting time;
 * ProceedingJoinPoint is an extension of the JoinPoint
 * that exposes the additional proceed() method;
 * StopWatch is Java chronometer;
 * In if statement usually we watch the request, if something is fall, or we count the all
 * request etc. this is only for example.
 *
 */


package com.example.favouritePlaceInTheWorld.web.sla;
import com.example.favouritePlaceInTheWorld.model.entity.PlaceEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SLOAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceEntity.class);

    private final SLOsConfig slOsConfig;

    public SLOAspect(SLOsConfig slOsConfig) {
        this.slOsConfig = slOsConfig;
    }

    @Around(value = "@annotation(TrackLatency)")
    public Object trackLatency(ProceedingJoinPoint pjp, TrackLatency TrackLatency) throws Throwable {

        String latencyId = TrackLatency.latency();
        SLOsConfig.SLOConfig config = slOsConfig
                .getSlos()
                .stream()
                .filter(s -> s.getId().equals(latencyId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException
                        ("SLO with id " + latencyId + " is not configure!"));

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();

        long actualExecutionTime = stopWatch.getLastTaskTimeMillis();
        if (actualExecutionTime > config.getThreshold()) {
            //Basically we can have very complications here, we just log.

            LOGGER.warn("Method {} was to slow. Execution time {} millis.",
                    latencyId, actualExecutionTime);
        }

        return result;


    }
}
