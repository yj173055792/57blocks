package io.blocks.integratorsjava.producer;

public interface IntegratorProducer<T> {

    int getProducerId();

    T produce();
}
