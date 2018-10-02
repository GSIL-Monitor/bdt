package cn.com.infaith.module.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author syc
 * @date 2018/9/12 11:47
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
