package com.example.demoresponsejson.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BootController {
    private static final HashMap<String, Object> INFO;
    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @RequestMapping("/hello")
    public Map<String, Object> hello() {
        return INFO;
    }
    @GetMapping("/result")
    @ResponseBody
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }
    @GetMapping("/badResult")
    public Result<Map<String, Object>> badResult() {
        return Result.failure(ResultStatus.BAD_REQUEST);
    }
    @GetMapping("/token")
    public Result token() {
        // 模拟业务层抛出 token 异常
        throw new TokenVerificationException("token 已经过期");
    }


    @GetMapping("/errorException")
    public Result errorException() {
        //这里故意造成一个其他异常，并且不进行处理
        Integer.parseInt("abc123");
        return Result.success();
    }
}
