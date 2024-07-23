package net.qiqb.usr.customer.infrastructure.persistence.dao;

import net.qiqbframework.persithanding.dao.BasicEntityPoDao;

import java.util.List;

public interface CustomDao extends BasicEntityPoDao<CustomerPo> {
    /**
     * 查询所有
     * @return
     */
    List<CustomerPo> listAll();

    CustomerPo getByName(String name);

}
