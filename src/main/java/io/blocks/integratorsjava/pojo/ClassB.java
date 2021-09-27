package io.blocks.integratorsjava.pojo;

import lombok.Data;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class ClassB {
    private int id;
    private String name;
    private Map<String, Integer> spots;
    private Instant start_time;
    private int integrator_id;

    public static ClassB valueOf(int id, String name, Map<String, Integer> spots, Instant start_time, int integrator_id){
        ClassB ret = new ClassB();
        ret.id = id;
        ret.name = name;
        ret.spots = spots;
        ret.start_time = start_time;
        ret.integrator_id = integrator_id;
        return ret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getSpots() {
        return spots;
    }

    public void setSpots(Map<String, Integer> spots) {
        this.spots = spots;
    }

    public Instant getStart_time() {
        return start_time;
    }

    public void setStart_time(Instant start_time) {
        this.start_time = start_time;
    }

    public int getIntegrator_id() {
        return integrator_id;
    }

    public void setIntegrator_id(int integrator_id) {
        this.integrator_id = integrator_id;
    }
}
