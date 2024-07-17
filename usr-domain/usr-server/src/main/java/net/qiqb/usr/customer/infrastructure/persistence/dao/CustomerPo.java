package net.qiqb.usr.customer.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.qiqbframework.persithanding.dao.BasicEntityPo;

@TableName("qiqb_examples_customer")
@Data
@NoArgsConstructor
public class CustomerPo implements BasicEntityPo {

    private String id;

    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 0:不可用，1:可用
     */
    private Integer enabled;

    private String email;

    private String remarks;

}
