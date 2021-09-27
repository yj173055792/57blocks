package io.blocks.integratorsjava.ctr;

import io.blocks.integratorsjava.constant.Constant;
import io.blocks.integratorsjava.exception.BusinessException;
import io.blocks.integratorsjava.pojo.Res;
import io.blocks.integratorsjava.service.IntegrateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class IntegrateCtr {

    @Autowired
    private IntegrateService service;

    @GetMapping("/sync/{integratorId}")
    public Res sync(@PathVariable int integratorId){
        try {
            return Res.valueOfSuccess(service.sync(integratorId));
        }catch (BusinessException e){
            return Res.valueOfError(e.getCode());
        }catch (Exception e){
            log.error("获取信息发生异常", e);
            return Res.valueOfError(Constant.UNKNOW_ERROR);
        }
    }
}
