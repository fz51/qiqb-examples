package net.qiqb.usr.customer.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.customer.domain.Customer;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomDao;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;
import net.qiqbframework.persithanding.DelayBatchPersistHandler;
import net.qiqbframework.persithanding.PersistHandler;
import net.qiqbframework.persithanding.PersistMessage;
import net.qiqbframework.persithanding.PersistType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class CustomPersistence {

    private final CustomDao customDao;


    /**
     * 新增用户
     *
     * @param custom
     */
    @PersistHandler(type = PersistType.ADD)
    public void doAdd(Customer custom, PersistMessage<Customer> persistMessage) {

        customDao.batchInsertPos(Stream.of(custom).map(this::formatCustomPo).toList());

    }

    /**
     * 延迟批量处理
     *
     * @param custom
     */
    //@DelayBatchPersistHandler(type = PersistType.ADD, routingAggregateRootType = Customer.class)
    public void doBatchAdd(List<Customer> custom) {
        customDao.batchInsertPos(custom.stream().map(this::formatCustomPo).toList());
    }


    /**
     * 修改用户
     *
     * @param modifiedCustom
     * @param snapshotCustom
     */
    @PersistHandler(type = PersistType.MODIFY)
    public void doModify(Customer modifiedCustom, Customer snapshotCustom) {
        customDao.dynamicUpdateById(formatCustomPo(modifiedCustom), formatCustomPo(snapshotCustom));
        //   BatchBasicEntityPoDiff.dynamicUpdate(List.of(formatCustomPo(modifiedCustom)), List.of(formatCustomPo(snapshotCustom)), customDao);
    }

    /**
     * key 为新值，value为比较的值
     *
     * @param modifiedCustomMap
     */
    @DelayBatchPersistHandler(type = PersistType.MODIFY, routingAggregateRootType = Customer.class)
    public void doModify(Map<Customer, Customer> modifiedCustomMap) {

        customDao.batchDynamicUpdateById(
                // 转化
                modifiedCustomMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> formatCustomPo(entry.getKey()),
                                entry -> formatCustomPo(entry.getValue())
                        ))
        );

    }

    /**
     * 删除用户
     *
     * @param custom
     */
    @PersistHandler(type = PersistType.REMOVE)
    public void doRemove(Customer custom) {
        customDao.batchDeletePosById(List.of(formatCustomPo(custom)));
    }

    public CustomerPo formatCustomPo(Customer custom) {
        CustomerPo customPo = new CustomerPo();
        customPo.setId(custom.getId().toString());
        customPo.setName(custom.getName());
        customPo.setNickName(custom.getNickName());
        customPo.setEmail(custom.getEmail() == null ? null : custom.getEmail().getVal());
        customPo.setEnabled(custom.isEnabled() ? 1 : 0);
        customPo.setRemarks(custom.getRemarks());
        return customPo;
    }
}