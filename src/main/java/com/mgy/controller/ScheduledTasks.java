package com.mgy.controller;

import com.mgy.service.DingtalkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Resource
    private DingtalkService dingtalkService;

    @Scheduled(cron = "0 0 12 * * ?")
    public void zhongwu() {
        dingtalkService.sendToChatbot("@冉合军 中午了，可以吃饭了。currentTime=" + dateFormat.format(new Date()));
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void wanshang() {
        dingtalkService.sendToChatbot("@冉合军 夜深了，可以睡觉了。currentTime=" + dateFormat.format(new Date()));
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 7 * * ?")
    public void zaoshang() {
        dingtalkService.sendToChatbot("@冉合军 起床吃早饭啦，太阳要晒屁股了。currentTime=" + dateFormat.format(new Date()));
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}