package net.qiqb.usr.point.adapter.web.req;

import lombok.Data;
import net.qiqb.usr.point.application.CalculatePointLevelCmd;

/**
 * 计算积分等级
 */
@Data
public class CalculatePointLevelReq {

    /**
     * 名称
     */
    private Integer point;


    public CalculatePointLevelCmd generateCalculateVipLevelCmd() {


        return new CalculatePointLevelCmd(point);
    }
}
