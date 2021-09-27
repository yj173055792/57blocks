package io.blocks.integratorsjava.service;

import io.blocks.integratorsjava.constant.Constant;
import io.blocks.integratorsjava.exception.BusinessException;
import io.blocks.integratorsjava.producer.IntegratorProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log4j2
public class IntegratorsService implements ApplicationContextAware {

    private Map<Integer, IntegratorProducer> producerMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IntegratorProducer> beans = applicationContext.getBeansOfType(IntegratorProducer.class);
        beans.forEach((k,v) -> producerMap.put(v.getProducerId(), v));
    }

    public Object produce(int integratorId){
        IntegratorProducer producer = producerMap.get(integratorId);
        if(producer == null){
            log.error("id为[{}]的供应商未找到", integratorId);
            throw BusinessException.valueOf(Constant.INEGRATOR_NOT_FOUND);
        }
        return producer.produce();
    }
}
