package net.qiqb.usr.customer.infrastructure.persistence.dao.mpimpl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CustomMapper extends BaseMapper<CustomerPo> {

}
