package net.qiqb.usr.customer.adapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.customer.adapter.web.req.ChangeCustomNameReq;
import net.qiqb.usr.customer.adapter.web.req.CreatingCustomReq;
import net.qiqb.usr.customer.adapter.web.req.DeleteCustomReq;
import net.qiqb.usr.customer.application.ChangeCustomNameCmd;
import net.qiqb.usr.customer.application.CreateCustomCmd;
import net.qiqb.usr.customer.application.DeleteCustomCmd;
import net.qiqb.usr.customer.infrastructure.CustomConstant;
import net.qiqbframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户（customer）
 */
@RestController
@RequestMapping(CustomConstant.MAPPING_PREFIX_PATH + "/custom")
@Slf4j
@AllArgsConstructor
public class CustomController {


    private final CommandGateway commandGateway;

    /**
     * 创建客户
     *
     * @param req
     * @return
     */
    @PostMapping("/create")
    public String create(@RequestBody CreatingCustomReq req) {
        final CreateCustomCmd cmd = req.generateCreateCustomCmd();
        commandGateway.sendAndWait(cmd);
        return "创建成功";
    }

    /**
     * 修改名称
     *
     * @param req
     * @return
     */
    @PostMapping("/change_name")
    public String changeName(@RequestBody ChangeCustomNameReq req) {
        // 批量
        final List<ChangeCustomNameCmd> cmd = req.generateChangeCustomNameCmd();
        commandGateway.sendAndWait(cmd);
        return "修改成功";
    }



    @PostMapping("/delete")
    public String delete(@RequestBody DeleteCustomReq req) {
        final DeleteCustomCmd cmd = req.generateDeleteCustomCmd();
        commandGateway.sendAndWait(cmd);
        return "修改成功";
    }
}
