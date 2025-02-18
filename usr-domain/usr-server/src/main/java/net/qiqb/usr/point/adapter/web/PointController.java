package net.qiqb.usr.point.adapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.customer.infrastructure.CustomConstant;
import net.qiqb.usr.point.adapter.web.req.CalculatePointLevelReq;
import net.qiqb.usr.point.application.CalculatePointLevelCmd;
import net.qiqbframework.commandhandling.CommandMessage;
import net.qiqbframework.commandhandling.GenericCommandMessage;
import net.qiqbframework.commandhandling.gateway.CommandGateway;
import net.qiqbframework.messaging.GenericMessage;
import net.qiqbframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分（point）
 */
@RestController
@RequestMapping(CustomConstant.MAPPING_PREFIX_PATH + "/point")
@Slf4j
@AllArgsConstructor
public class PointController {


    private final CommandGateway commandGateway;

    /**
     * 计算积分
     *
     * @param req
     * @return
     */
    @PostMapping("/calculate_level")
    public String create(@RequestBody CalculatePointLevelReq req) {
        final CalculatePointLevelCmd cmd = req.generateCalculateVipLevelCmd();
        final Message<?> message = GenericCommandMessage.of("32");


        final Object level = commandGateway.sendAndWait(cmd);

        return "积分等级为：" + level;
    }

}
