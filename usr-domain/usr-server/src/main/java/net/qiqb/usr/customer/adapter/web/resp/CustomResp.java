package net.qiqb.usr.customer.adapter.web.resp;

import lombok.Data;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;

@Data
public class CustomResp {


    public static CustomResp formatResp(CustomerPo customItemPo) {
        final CustomResp customNavResp = new CustomResp();

        return customNavResp;
    }
}
