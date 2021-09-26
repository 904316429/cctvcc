package cn.cctvcc.gateways.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-27 11:16
 */
@RestController("/a")
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "-------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "--------testB";
    }

    @GetMapping("/testD")
    public String testD()
    {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testD";
    }

}
