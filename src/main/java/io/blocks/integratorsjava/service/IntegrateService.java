package io.blocks.integratorsjava.service;

import io.blocks.integratorsjava.constant.Constant;
import io.blocks.integratorsjava.consumer.IntegratorConsumer;
import io.blocks.integratorsjava.pojo.ClassInfo;
import io.blocks.integratorsjava.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log4j2
@EnableScheduling
public class IntegrateService implements ApplicationContextAware {

    private Map<Integer, IntegratorConsumer> consumerMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IntegratorConsumer> beans = applicationContext.getBeansOfType(IntegratorConsumer.class);
        beans.forEach((k,v) -> consumerMap.put(v.getConsumerId(), v));
    }

    @Scheduled(fixedRate = 30000)
    public void consume(){
        try {
            for(IntegratorConsumer consumer : consumerMap.values()){
                ClassInfo classInfo = consumer.consume();
                if(classInfo != null){
                    log.info("供应商[{}] 数据拉取成功 id[{}] name[{}] availableSpots[{}] totalSpots[{}] startTime[{}]",
                            consumer.getConsumerId(), classInfo.getId(), classInfo.getName(), classInfo.getAvailableSpots(),
                            classInfo.getTotalSpots(), classInfo.getStartTime());
                }
            }
        }catch (Exception e){
            log.error("汇总定时线程执行发生错误", e);
        }
    }

    public ClassInfo sync(int integratorId){
        IntegratorConsumer consumer = consumerMap.get(integratorId);
        if(consumer == null){
            log.error("id为[{}]的供应商未找到", integratorId);
            throw BusinessException.valueOf(Constant.INEGRATOR_NOT_FOUND);
        }
        ClassInfo ret = consumer.consume();
        if(ret == null){
            throw BusinessException.valueOf(Constant.BUSINESS_ERROR);
        }
        return ret;
    }
}
