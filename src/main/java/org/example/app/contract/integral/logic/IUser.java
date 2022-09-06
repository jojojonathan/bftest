package org.example.app.contract.integral.logic;

import cn.hyperchain.contract.BaseContractInterface;
import org.example.app.contract.integral.logic.entity.IntegralResult;

/**
 * @author wuziqiang
 * @date 2021/7/5 16:13
 * @description 积分兑换合约-用户接口
 */
public interface IUser extends BaseContractInterface {

    /**
     * 用户注册
     *
     * @param userId   用户id
     * @param userName 用户名
     * @param roleType 角色
     * @return
     */
    IntegralResult registerUser(String userId, String userName, String roleType);
}
