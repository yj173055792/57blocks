package io.blocks.integratorsjava.pojo;

import io.blocks.integratorsjava.constant.Constant;
import lombok.Data;

@Data
public class Res {
    private int code;
    private String msg;
    private Object data;

    public static Res valueOfSuccess(Object data){
        Res ret = new Res();
        ret.data = data;
        ret.code = Constant.SUC;
        return ret;
    }

    public static Res valueOfError(int code){
        Res ret = new Res();
        ret.code = code;
        return ret;
    }

    public static Res valueOfError(int code, String msg){
        Res ret = new Res();
        ret.code = code;
        ret.msg = msg;
        return ret;
    }

}
