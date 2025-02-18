package net.qiqb.usr.customer.domain;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.custom.client.events.CreatedCustomEvent;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.application.*;
import net.qiqb.usr.customer.domain.type.Email;
import net.qiqbframework.commandhandling.CommandHandler;
import net.qiqbframework.common.IdentifierFactory;
import net.qiqbframework.messaging.task.CurrentMessageTask;
import net.qiqbframework.modelling.domain.AggregateContext;
import net.qiqbframework.modelling.domain.BizIdentifier;
import net.qiqbframework.modelling.domain.EntityIdentifier;
import net.qiqbframework.spring.stereotype.Aggregate;

import java.io.Serializable;


/**
 * 客户
 *
 * @author
 * @since
 */
@Aggregate
@Getter
@NoArgsConstructor
@Setter
@Slf4j
public class Customer implements Serializable {
    /**
     * 用户ID
     */
    @EntityIdentifier
    private CustomerId id;
    /**
     * 用户名称，唯一
     */
   // @BizIdentifier
    private String name;
    /**
     *
     */
   // @BizIdentifier
    private String nickName;
    /**
     * 邮件。有效注册只能是一个
     */
    //@BizIdentifier
    private Email email;


    /**
     * 是否可用
     */
    private boolean enabled;
    /**
     * 备注
     */
    private String remarks;


    @CommandHandler
    public Customer(CreateCustomCmd cmd) {
        this.id = new CustomerId(IdentifierFactory.getInstance().generate());
        this.name = cmd.getName();
        this.nickName = StrUtil.isEmpty(cmd.getNickName()) ? this.name : cmd.getNickName();
        this.email = cmd.getEmail();
        this.remarks = cmd.getRemarks();
        // 默认注册需要审核通过
        this.enabled = false;
        // 将结果设置
        cmd.result().setId(this.id);
        AggregateContext.addEvents(new CreatedCustomEvent(this.id));
    }

    //@CommandHandler
    public void changeName(ChangeCustomNameCmd cmd) {
        log.info("修改名称:{},{}", this.name, cmd.getName());
        this.name = cmd.getName();

    }
    //@CommandHandler
    public void enable(EnableCustomCmd cmd) {
        this.enabled = true;
    }



    @CommandHandler
    public void disable(DisableCustomCmd cmd) {
        this.enabled = false;
    }

    @CommandHandler
    public void delete(DeleteCustomCmd cmd) {
        // 删除之前做一些判断操作
        // 标记删除
        AggregateContext.markDeleted();

    }


}