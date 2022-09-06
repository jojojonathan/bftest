package org.example.app.contract.integral.logic;

import cn.hyperchain.contract.BaseContractInterface;
import org.example.app.contract.integral.logic.entity.IntegralResult;

import java.math.BigInteger;

/**
 * @author wuziqiang
 * @date 2021/7/5 16:12
 * @description 积分兑换合约-银行账户接口
 */
public interface IBankAccount extends BaseContractInterface {

    /**
     * 账户注册
     *
     * @param userId 用户id
     * @return
     */
    IntegralResult registerForBank(String userId);

    /**
     * 查看账户信息
     *
     * @param userId 用户id
     * @return
     */
    IntegralResult getBankAccountInfo(String userId);

    /**
     * 账户信息列表
     *
     * @param bankAdminId 银行管理员账号
     * @return
     */
    IntegralResult getBankAccountList(String bankAdminId);

    /**
     * 增加积分
     *
     * @param bankAdminId 银行管理员账号
     * @param userId      用户id
     * @param integral    积分
     * @return
     */
    IntegralResult increaseIntegral(String bankAdminId, String userId, BigInteger integral);

    /**
     * 积分兑换里程申请
     *
     * @param userId   用户id
     * @param integral 积分值
     * @return
     */
    IntegralResult exchangeToMileage(String userId, BigInteger integral);

    /**
     * 积分兑换记录审核
     *
     * @param userId      用户id
     * @param recordId    记录id
     * @param auditResult 审核结果
     * @return
     */
    IntegralResult exchangeRecordsAudit(String userId, String recordId, String auditResult);

    /**
     * 积分兑换记录
     * 根据角色展示记录
     *
     * @param userId 用户id
     * @return
     */
    IntegralResult getExchangeRecords(String userId);

}
