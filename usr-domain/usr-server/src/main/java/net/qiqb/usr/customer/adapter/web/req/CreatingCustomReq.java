package net.qiqb.usr.customer.adapter.web.req;

import lombok.Data;
import net.qiqb.usr.customer.application.CreateCustomCmd;
import net.qiqb.usr.customer.domain.type.Email;

@Data
public class CreatingCustomReq {
    /**
     * 名称
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remarks;

    public CreateCustomCmd generateCreateCustomCmd() {
        final CreateCustomCmd cmd = new CreateCustomCmd(this.name, new Email(this.email));
        cmd.setNickName(this.nickName);
        cmd.setRemarks(this.remarks);
        return cmd;
    }
}
