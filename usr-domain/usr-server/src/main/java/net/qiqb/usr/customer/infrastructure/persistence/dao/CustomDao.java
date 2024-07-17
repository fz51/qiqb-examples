package net.qiqb.usr.customer.infrastructure.persistence.dao;

import net.qiqbframework.persithanding.dao.BasicEntityPoDao;

public interface CustomDao extends BasicEntityPoDao<CustomerPo> {

    CustomerPo getByName(String name);

}
