package net.qiqb.usr.point.application;

import lombok.Getter;

/**
 * 计算积分等级命令
 */
@Getter
public class CalculatePointLevelCmd {


    private final Integer point;


    public CalculatePointLevelCmd(Integer point) {
        this.point = point;
    }
}
