package io.blocks.integratorsjava.pojo;

import lombok.Data;

@Data
public class Translation {
    private String name;
    private String locale;

    public static Translation valueOf(String name, String locale){
        Translation ret = new Translation();
        ret.name = name;
        ret.locale = locale;
        return ret;
    }
}
