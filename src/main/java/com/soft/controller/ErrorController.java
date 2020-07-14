package com.soft.controller;

import com.soft.sys.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/error")
    public Object error() {
        return Result.fail(400, "错误", null);
    }
}
