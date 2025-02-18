package net.qiqb.usr.point.adapter.listener;

import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.custom.client.events.CreatedCustomEvent;
import net.qiqbframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class Point4CustomerListener {

    @EventHandler
    public void listenCreatedCustomEvent(CreatedCustomEvent createdCustomEvent){
        log.info("积分模块监听已经创建用户");
    }

}
