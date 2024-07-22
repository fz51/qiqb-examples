package net.qiqb.usr.customer.infrastructure.persistence;

import net.qiqb.usr.customer.domain.Customer;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomDao;
import net.qiqb.usr.customer.infrastructure.persistence.dao.CustomerPo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.qiqbframework.messaging.task.CurrentMessageTask;
import net.qiqbframework.messaging.task.MessageTask;
import net.qiqbframework.persithanding.PersistHandler;
import net.qiqbframework.persithanding.PersistMessage;
import net.qiqbframework.persithanding.PersistType;
import net.qiqbframework.persithanding.dao.BatchBasicEntityPoDiff;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        final MessageTask<?> messageTask = CurrentMessageTask.get();
        // 先暂存起来，等到保存最后一个聚合根的时候再一起提交数据库
        final ArrayList<Customer> tempCustomerPersist = messageTask.getOrDefaultResource("tempCustomerPersist", new ArrayList<>());
        tempCustomerPersist.add(custom);
        if (persistMessage.hasNext()){
           tempCustomerPersist.add(custom);
           return;
        }
        customDao.batchInsertPos(tempCustomerPersist.stream().map(this::formatCustomPo).toList());
        tempCustomerPersist.clear();
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
        // BatchBasicEntityPoDiff.dynamicUpdate(List.of(formatCustomPo(modifiedCustom)), List.of(formatCustomPo(snapshotCustom)), customDao);
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
        customPo.setEmail(custom.getEmail()== null? null: custom.getEmail().getVal());
        customPo.setEnabled(custom.isEnabled() ? 1 : 0);
        customPo.setRemarks(custom.getRemarks());
        return customPo;
    }
}