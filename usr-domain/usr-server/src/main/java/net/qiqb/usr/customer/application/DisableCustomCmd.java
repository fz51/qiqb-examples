package net.qiqb.usr.customer.application;

import lombok.Getter;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqbframework.modelling.command.BizIdentifierVoucher;

/**
 * 禁用客户
 */
@Getter
public class DisableCustomCmd {

    @BizIdentifierVoucher
    private final CustomerId id;


    public DisableCustomCmd(CustomerId id) {
        this.id = id;
    }
}
