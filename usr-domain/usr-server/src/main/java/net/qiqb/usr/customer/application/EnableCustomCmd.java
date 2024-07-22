package net.qiqb.usr.customer.application;

import lombok.Getter;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqbframework.modelling.command.BizIdentifierVoucher;

/**
 * 启用客户
 */
@Getter
public class EnableCustomCmd {

    @BizIdentifierVoucher
    private final CustomerId id;


    public EnableCustomCmd(CustomerId id) {
        this.id = id;
    }
}
