package net.qiqb.usr.customer.adapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.adapter.web.req.BatchChangeCustomNameReq;
import net.qiqb.usr.customer.adapter.web.req.ChangeCustomNameReq;
import net.qiqb.usr.customer.adapter.web.req.CreatingCustomReq;
import net.qiqb.usr.customer.adapter.web.req.DeleteCustomReq;
import net.qiqb.usr.customer.application.ChangeCustomNameCmd;
import net.qiqb.usr.customer.application.CreateCustomCmd;
import net.qiqb.usr.customer.application.DeleteCustomCmd;
import net.qiqb.usr.customer.application.EnableCustomCmd;
import net.qiqb.usr.customer.infrastructure.CustomConstant;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomDao;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;
import net.qiqbframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户（customer）
 */
@RestController
@RequestMapping(CustomConstant.MAPPING_PREFIX_PATH + "/custom")
@Slf4j
@AllArgsConstructor
public class CustomController {

    private final CustomDao customDao;

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
        return "创建客户成功";
    }


    /**
     * 模拟批量新增客户
     *
     * @param req
     * @return
     */
    @PostMapping("/batch_create")
    public String batchCreate(@RequestBody CreatingCustomReq req) {
        final CreateCustomCmd cmd = req.generateCreateCustomCmd();
        List<CreateCustomCmd> mockBatch = new ArrayList<>();
        // 一次创建提交1w 用户
        for (int i = 0; i < 1; i++) {
            CreateCustomCmd createCustomCmd = new CreateCustomCmd(cmd.getName() + i, cmd.getEmail());
            createCustomCmd.setRemarks(cmd.getRemarks());
            mockBatch.add(createCustomCmd);
        }

        commandGateway.batchSendAndWait(mockBatch);
        return "创建客户成功";
    }

    /**
     * 修改名称
     *
     * @param req
     * @return
     */
    @PostMapping("/change_name")
    public String changeName(@RequestBody ChangeCustomNameReq req) {
        //
        final ChangeCustomNameCmd cmd = req.generateChangeCustomNameCmd();
        commandGateway.sendAndWait(cmd);
        return "修改客户名称成功";
    }

    /**
     * 批量修改名称
     *
     * @param req
     * @return
     */
    @PostMapping("/batch_change_name")
    public String batchChangeName(@RequestBody BatchChangeCustomNameReq req) {
        // 批量修改用户名称
        List<ChangeCustomNameCmd> mockBatch = new ArrayList<>();
        for (CustomerPo customerPo : customDao.listAll()) {
            ChangeCustomNameCmd createCustomCmd = new ChangeCustomNameCmd(new CustomerId(customerPo.getId() ), customerPo.getName()+ "01");

            mockBatch.add(createCustomCmd);
        }

        commandGateway.batchSendAndWait(mockBatch);

        return "批量修改客户成功";
    }


    /**
     * 创建并启用客户
     *
     * @param req
     * @return
     */
    @PostMapping("/create_and_enable")
    public String createAndEnable(@RequestBody CreatingCustomReq req) {
        final CreateCustomCmd createCustomCmd = req.generateCreateCustomCmd();
        final EnableCustomCmd enableCustomCmd = new EnableCustomCmd(null);
        commandGateway.sendAndWait(List.of(createCustomCmd, enableCustomCmd));
        return "创建客户并启用成功";
    }

    /**
     * 删除客户
     *
     * @param req
     * @return
     */
    @PostMapping("/delete")
    public String delete(@RequestBody DeleteCustomReq req) {
        final DeleteCustomCmd cmd = req.generateDeleteCustomCmd();
        commandGateway.sendAndWait(cmd);
        return "删除成功";
    }
}
