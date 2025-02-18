package net.qiqb.usr.customer.application;

import lombok.Getter;
import lombok.Setter;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.domain.type.Email;
import net.qiqbframework.modelling.command.BizIdentifierVoucher;

@Getter
public class ChangeCustomNameCmd {

    @BizIdentifierVoucher
    private final CustomerId id;

    private final String name;

    public ChangeCustomNameCmd(CustomerId id, String name) {
        this.id = id;
        this.name = name;
    }
}
