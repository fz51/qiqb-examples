package net.qiqb.examples;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomDao;
import net.qiqbframework.commandhandling.CommandHandler;
import net.qiqbframework.commandhandling.CommandHandler;
import net.qiqbframework.commandhandling.CommandHandler;
import net.qiqbframework.commandhandling.CommandHandler;
import net.qiqbframework.commandhandling.gateway.CommandGateway;
import net.qiqbframework.rabbitmq.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

/**
 * 客户（customer）
 */
@RestController
@RequestMapping(  "/cloud_user")
@Slf4j
@AllArgsConstructor
public class CloudUserTestController   {

    private final CustomDao customDao;

    private final CommandGateway commandGateway;

    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private SendMessage sendMessage;


    @GetMapping("/sendLog")
    public void sendLog() {
        sendMessage.send();
        streamBridge.send("directEx-out-0", "ces");
    }
}
