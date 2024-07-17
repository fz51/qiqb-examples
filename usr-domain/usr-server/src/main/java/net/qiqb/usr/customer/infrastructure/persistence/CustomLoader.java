package net.qiqb.usr.customer.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.custom.client.types.CustomerId;
import net.qiqb.usr.customer.domain.Customer;
import net.qiqb.usr.customer.domain.type.Email;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomDao;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;
import net.qiqbframework.loadhanding.LoadHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomLoader {


    private final CustomDao authUserDao;

    //@LoadHandler(routingBizIdentifierNaming = "id")
    public Customer loadById(CustomerId id) {
        final CustomerPo customPo = authUserDao.selectPoById(id.getVal());
        if (customPo == null) {
            return null;
        }
        return formatCustom(customPo);
    }

    /**
     * 默认采用批量加载，可以操作执行批量操作。
     *
     * @param ids
     * @return
     */
    @LoadHandler(routingBizIdentifierNaming = "id")
    public Collection<Customer> loadByIds(Collection<CustomerId> ids) {

        final List<CustomerPo> customPos = authUserDao.batchSelectPosByIds(ids.stream().map(CustomerId::getVal).toList());
        if (customPos == null || customPos.isEmpty()) {
            return null;
        }
        return customPos.stream().map(CustomLoader::formatCustom).toList();
    }

    @LoadHandler(routingBizIdentifierNaming = "name")
    public Customer loadByName(String name) {
        final CustomerPo customPo = authUserDao.getByName(name);
        if (customPo == null) {
            return null;
        }
        return formatCustom(customPo);
    }


    private static Customer formatCustom(CustomerPo customPo) {
        Customer custom = new Customer();
        custom.setId(new CustomerId(customPo.getId()));
        custom.setName(customPo.getName());
        custom.setNickName(customPo.getNickName());
        if (customPo.getEmail() != null) {
            custom.setEmail(new Email(customPo.getEmail()));
        }
        custom.setEnabled(customPo.getEnabled() == 1);
        custom.setRemarks(customPo.getRemarks());
        return custom;
    }


}