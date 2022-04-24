package cn.bounter.mdc.controller;

import cn.bounter.mdc.entity.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


@Slf4j
@RestController
@RequestMapping("/api/mdc")
public class MdcController {

    @Autowired
    private Executor mdcExecutor;

    @GetMapping("/test")
    public ResponseData test() {
        log.info("这是父线程的TraceId");

        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            log.info("这是子线程的TraceId");
            return null;
        }, mdcExecutor);

        return ResponseData.success();
    }
}
