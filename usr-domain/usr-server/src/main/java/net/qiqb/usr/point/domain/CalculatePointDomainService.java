package net.qiqb.usr.point.domain;

import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.point.application.CalculatePointLevelCmd;
import net.qiqbframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculatePointDomainService {

    /**
     * 计算积分
     *
     * @return
     */

    @CommandHandler
    public Integer calculateVipLevel(CalculatePointLevelCmd cmd) {
        if (cmd.getPoint() == null || cmd.getPoint().compareTo(0) < 0) {
            throw new IllegalArgumentException("积分必须大于0");
        }
        if (cmd.getPoint().compareTo(50) < 0) {
            return 0;
        }
        if (cmd.getPoint().compareTo(80) < 0) {
            return 1;
        }
        return 2;
    }

    @CommandHandler(commandName = "test")
    public void calculateVipLevel() {
       log.info("信号，没有任何命令载体");
    }

}
