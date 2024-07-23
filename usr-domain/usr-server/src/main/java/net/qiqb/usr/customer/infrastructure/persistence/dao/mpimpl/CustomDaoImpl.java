package net.qiqb.usr.customer.infrastructure.persistence.dao.mpimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomDao;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;
import lombok.extern.slf4j.Slf4j;
import net.qiqbframework.mp.BasicEntityPoDaoMpImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CustomDaoImpl extends BasicEntityPoDaoMpImpl<CustomMapper, CustomerPo> implements CustomDao {

    @Override
    public List<CustomerPo> listAll() {
        return super.list();
    }

    @Override
    public CustomerPo getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        QueryWrapper<CustomerPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CustomerPo::getName, name)
        ;
        return getOne(queryWrapper);
    }
}
