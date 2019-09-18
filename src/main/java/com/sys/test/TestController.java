package com.sys.test;

import com.sys.socketio.MessageEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {
    @Autowired
    MessageEventHandler messageEventHandler;
    @RequestMapping("/test")
    public String test(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<300;i++) {
                        messageEventHandler.sendData(new Date().getTime());
                            Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();
        return "每秒钟向客户端发送一条消息.....";
    }
}
