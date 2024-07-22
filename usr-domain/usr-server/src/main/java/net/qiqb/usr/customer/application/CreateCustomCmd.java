package net.qiqb.usr.customer.application;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.domain.type.Email;
import net.qiqbframework.commandhandling.CommandResult;

@Getter
public class CreateCustomCmd implements CommandResult<CreateCustomCmd.Result> {

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


    private final Result result = new Result();

    @Override
    public Result result() {
        return result;
    }

    @Data
    public static class Result {
        /**
         * 创建成功后将客户ID添加到结果中，供其调用
         */
        private CustomerId id;
    }
}
