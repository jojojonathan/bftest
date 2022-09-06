package org.example.app.contract.integral.logic;

import cn.hyperchain.contract.BaseContractInterface;
import org.example.app.contract.integral.logic.entity.IntegralResult;

/**
 * @author wuziqiang
 * @date 2021/7/5 16:12
 * @description 积分兑换合约-航空公司账户接口
 */
public interface IAirlineAccount extends BaseContractInterface {

    /**
     * 账户注册
     *
     * @param userId 用户id
     * @return
     */
    IntegralResult registerForAirline(String userId);

    /**
     * 查看账户信息
     *
     * @param userId 用户id
     * @return
     */
    IntegralResult getAirlineAccountInfo(String userId);

    /**
     * 账户信息列表
     *
     * @param airlineAdminId 航司管理员账号
     * @return
     */
    IntegralResult getAirlineAccountList(String airlineAdminId);

}
