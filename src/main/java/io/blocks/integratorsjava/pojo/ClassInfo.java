package io.blocks.integratorsjava.pojo;

import lombok.Data;

import java.time.Instant;

@Data
public class ClassInfo {
    private int id;
    private String name;
    private int availableSpots;
    private int totalSpots;
    private Instant startTime;

    public static ClassInfo valueOf(int id, String name, int availableSpots, int totalSpots, Instant startTime){
        ClassInfo ret = new ClassInfo();
        ret.id = id;
        ret.availableSpots = availableSpots;
        ret.name = name;
        ret.totalSpots = totalSpots;
        ret.startTime = startTime;
        return ret;
    }
}
