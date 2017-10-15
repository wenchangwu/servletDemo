package com.baofu.crossborder.rpc;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String words) {
        if ("hello".equals(words)) {
            return "hello world";
        } else {
            return "error";
        }
    }
}
