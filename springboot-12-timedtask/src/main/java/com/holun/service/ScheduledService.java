package com.holun.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * cron表达式
     * 秒 分 时 日 月 星期（0-7,0和7代表周日） 年（可省略）
     * 比如:
     * 0 17 11 * * ? 每天11:17:00 执行一次
     * 0 0/5 15,20 * * ? 每天下午3点和晚上8点，从第0分钟开始，每5分钟执行一次
     *
     */
    @Scheduled(cron = "0 17 11 * * ?")
    public void  hello() {
        System.out.println("开启执行");
    }

}
