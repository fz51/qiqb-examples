package net.qiqb.usr.customer.domain;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.application.ChangeCustomNameCmd;
import net.qiqb.usr.customer.application.CreateCustomCmd;
import net.qiqb.usr.customer.application.DeleteCustomCmd;
import net.qiqb.usr.customer.domain.type.Email;
import net.qiqbframework.commandhandling.CommandHandler;
import net.qiqbframework.common.IdFactory;
import net.qiqbframework.modelling.domain.AggregateContext;
import net.qiqbframework.modelling.domain.BizIdentifier;
import net.qiqbframework.modelling.domain.EntityIdentifier;
import net.qiqbframework.spring.stereotype.Aggregate;


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
public class Customer {
    /**
     * 用户ID
     */
    @EntityIdentifier
    private CustomerId id;
    /**
     * 用户名称，唯一
     */
    @BizIdentifier
    private String name;
    /**
     *
     */
    private String nickName;
    /**
     * 邮件。有效注册只能是一个
     */
    @BizIdentifier
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
        this.id = new CustomerId(IdFactory.getInstance().generateId());
        this.name = cmd.getName();
        this.nickName = StrUtil.isEmpty(cmd.getNickName()) ? this.name : cmd.getNickName();
        this.email = cmd.getEmail();
        this.remarks = cmd.getRemarks();
        // 默认注册即可用
        this.enabled = true;
    }

    @CommandHandler
    public void changeName(ChangeCustomNameCmd cmd) {
        this.name = cmd.getName();

    }

    @CommandHandler
    public void delete(DeleteCustomCmd cmd) {
        // 删除之前做一些判断操作
        // 标记删除
        AggregateContext.markDeleted();

    }


}