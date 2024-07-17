package net.qiqb.usr.customer.adapter.web.req;

import lombok.Data;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.application.ChangeCustomNameCmd;
import net.qiqb.usr.customer.application.DeleteCustomCmd;

@Data
public class DeleteCustomReq {
    /**
     * 待删除的客户id
     */
    private String id;


    public DeleteCustomCmd generateDeleteCustomCmd() {
        final DeleteCustomCmd cmd = new DeleteCustomCmd(new CustomerId(this.id));

        return cmd;
    }
}
