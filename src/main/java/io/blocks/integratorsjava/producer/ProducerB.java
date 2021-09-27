package io.blocks.integratorsjava.producer;

import io.blocks.integratorsjava.pojo.ClassA;
import io.blocks.integratorsjava.pojo.ClassB;
import io.blocks.integratorsjava.pojo.Translation;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProducerB implements IntegratorProducer<ClassB>{
    public static final int ID = 2;

    @Override
    public int getProducerId() {
        return ID;
    }

    @Override
    public ClassB produce() {
        Map<String, Integer> spots = new HashMap<>();
        spots.put("available", 3);
        spots.put("total", 5);
        return ClassB.valueOf(1, "Yoga", spots, Instant.now(), getProducerId());
    }
}
