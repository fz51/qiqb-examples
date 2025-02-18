package net.qiqb.usr.customer.application;

import lombok.Getter;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqbframework.modelling.command.BizIdentifierVoucher;

/**
 * 删除客户
 */
@Getter
public class DeleteCustomCmd {

    @BizIdentifierVoucher
    private final CustomerId id;

    public DeleteCustomCmd(CustomerId id) {
        this.id = id;
    }
}
