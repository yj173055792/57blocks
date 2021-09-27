package io.blocks.integratorsjava.consumer;

import io.blocks.integratorsjava.pojo.ClassInfo;

public interface IntegratorConsumer {

    int getConsumerId();

    ClassInfo consume();
}
