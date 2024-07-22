package net.qiqb.usr.customer.adapter.web.req;

import lombok.Data;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.application.ChangeCustomNameCmd;

import java.util.Arrays;
import java.util.List;

/**
 * 批量修改
 */
@Data
public class BatchChangeCustomNameReq {
    /**
     * 待修改的客户id
     */
    private String[] id;
    /**
     * 名称
     */
    private String name;


    public List<ChangeCustomNameCmd> generateChangeCustomNameCmd() {


        return Arrays.stream(id).map(i -> new ChangeCustomNameCmd(new CustomerId(i), this.name)).toList();
    }
}
