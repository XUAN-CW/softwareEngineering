package edu.guet.controller;

import edu.guet.service.MsmService;
import edu.guet.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;


    //发送短信的方法
    //http://127.0.0.1:8080/send/
    @GetMapping("send/{phone}")
    public void sendMsm(@PathVariable String phone) {
        //生成随机值，传递阿里云进行发送
        String code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if(isSend){
            System.out.println("号码 "+phone+" 的验证码是 "+code);
        }else {
            System.out.println("短信发送失败");
        }
    }
}
