package io.blocks.integratorsjava.pojo;

import lombok.Data;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Data
public class ClassA {
    private int id;
    private List<Translation> translations;
    private int booked_spots;
    private int total_spots;
    private Instant start_time;
    private int integrator_id;

    public static ClassA valueOf(int id, int booked_spots, int total_spots, Instant start_time, int integrator_id, Translation... translations){
        ClassA ret = new ClassA();
        ret.id = id;
        ret.booked_spots = booked_spots;
        ret.total_spots = total_spots;
        ret.start_time = start_time;
        ret.integrator_id = integrator_id;
        ret.translations = Arrays.asList(translations);
        return ret;
    }
}
