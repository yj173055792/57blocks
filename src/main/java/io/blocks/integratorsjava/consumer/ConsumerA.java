package io.blocks.integratorsjava.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.blocks.integratorsjava.constant.Constant;
import io.blocks.integratorsjava.pojo.ClassInfo;
import io.blocks.integratorsjava.pojo.ClassA;
import io.blocks.integratorsjava.pojo.Res;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Log4j2
public class ConsumerA implements IntegratorConsumer{

    public static final int ID = 1;
    @Value("${comsumerA.url:http://localhost:8080/classes/1}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public int getConsumerId() {
        return ID;
    }

    @Override
    public ClassInfo consume() {
        try {
            ResponseEntity<Res> response = restTemplate.getForEntity(new URI(url), Res.class);
            Res res = response.getBody();
            int code = res.getCode();
            if(code == Constant.SUC){
                Object data = res.getData();
                ClassA classA = objectMapper.convertValue(data, ClassA.class);
                String name = classA.getTranslations().stream().filter(e->e.getLocale().equals(Constant.EN_LANG)).findFirst().get().getName();
                int total = classA.getTotal_spots();
                return ClassInfo.valueOf(classA.getId(), name, total - classA.getBooked_spots(), total, classA.getStart_time());
            }
            log.error("获取信息发生错误 code[{}]", code);
        } catch (Exception e) {
            log.error("获取信息发生异常", e);
        }
        return null;
    }
}
