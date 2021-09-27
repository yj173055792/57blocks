package io.blocks.integratorsjava.producer;

import io.blocks.integratorsjava.pojo.ClassA;
import io.blocks.integratorsjava.pojo.Translation;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProducerA implements IntegratorProducer<ClassA>{
    public static final int ID = 1;

    @Override
    public int getProducerId() {
        return ID;
    }

    @Override
    public ClassA produce() {
        return ClassA.valueOf(1, 5, 10, Instant.now(), getProducerId(),
                Translation.valueOf("Yoga", "en"), Translation.valueOf("瑜伽", "zh-CN"));
    }
}
