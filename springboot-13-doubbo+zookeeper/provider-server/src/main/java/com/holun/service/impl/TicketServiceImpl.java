package com.holun.service.impl;

import com.holun.service.TicketService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService//加该注解后，在项目启动时，TicketServiceImpl类提供给的服务就会在注册中心注册
@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "高铁票";
    }
}
