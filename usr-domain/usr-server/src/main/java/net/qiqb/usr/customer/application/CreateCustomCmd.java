package net.qiqb.usr.customer.application;

import net.qiqb.usr.customer.domain.type.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateCustomCmd {

    private final String name;


    private final Email email;

    @Setter
    private String nickName;

    @Setter
    private String remarks;


    public CreateCustomCmd(String name, Email email) {
        this.name = name;
        this.email = email;
    }
}
