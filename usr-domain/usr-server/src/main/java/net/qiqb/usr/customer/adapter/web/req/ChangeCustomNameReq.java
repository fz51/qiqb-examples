package net.qiqb.usr.customer.adapter.web.req;

import lombok.Data;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.application.ChangeCustomNameCmd;

import java.util.List;

@Data
public class ChangeCustomNameReq {
    /**
     * 待修改的客户id
     */
    private String id;
    /**
     * 名称
     */
    private String name;


    public ChangeCustomNameCmd generateChangeCustomNameCmd() {


        return new ChangeCustomNameCmd(new CustomerId(id), name);
    }
}
