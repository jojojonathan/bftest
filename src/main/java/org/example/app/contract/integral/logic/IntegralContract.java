package org.example.app.contract.integral.logic;

import cn.hyperchain.annotations.StoreField;
import cn.hyperchain.contract.BaseContract;
import cn.hyperchain.contract.BaseContractInterface;
import cn.hyperchain.core.HyperMap;
import cn.hyperchain.core.Logger;
import org.example.app.contract.integral.logic.entity.*;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wuziqiang
 * @date 2021/7/5 16:01
 * @description 积分兑换合约主体类
 */
public class IntegralContract extends BaseContract implements IUser, IAirlineAccount, IBankAccount, BaseContractInterface {

    private Logger logger = Logger.getLogger(IntegralContract.class);

    /**
     * 用户信息列表
     */
    @StoreField
    private Map<String, User> users = new HyperMap<>();

    /**
     * 银行账户列表
     */
    @StoreField
    private Map<String, BankAccount> bankAccounts = new HyperMap<>();

    /**
     * 航司账户列表
     */
    @StoreField
    private Map<String, AirlineAccount> airlineAccounts = new HyperMap<>();

    /**
     * 兑换记录
     */
    @StoreField
    private Map<String, IntegralExchangeRecord> records = new HyperMap<>();

    /**
     * 管理员地址信息
     */
    @StoreField
    private Map<String, String> managerAddress = new HyperMap<>();

    /**
     * 角色接口权限集合
     */
    @StoreField
    private Map<String, RolePermission> rolePermission = new HyperMap<>();

    /**
     * 银行账户id持久量
     */
    @StoreField
    private Integer bankAccountNo = 100000;

    /**
     * 航司账户id持久量
     */
    @StoreField
    private Integer airlineAccountNo = 100000;

    /**
     * 兑换记录id持久量
     */
    @StoreField
    private Integer recordId = 1000000;

    @Override
    public IntegralResult registerUser(String userId, String userName, String roleType) {
        logger.notice("注册新用户！用户ID[" + userId + "]");
        //将时间推送至MQ队列中
        event("注册新用户！用户ID[" + userId + "]", "registerUser", "");
        User user = new User(userId, userName, roleType);
        users.put(userId, user);
        IntegralResult result = new IntegralResult();
        result.setFlag(true);
        result.setMsg("注册成功！id [" + userId + "]");
        result.setResult(user);
        return result;
    }

    @Override
    public IntegralResult registerForBank(String userId) {
        logger.notice("银行账户注册！用户ID[" + userId + "]");
        event("银行账户注册！用户ID[" + userId + "]", "registerForBank", "");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "registerForBank")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        //校验是否已注册银行账户
        if (bankAccounts.containsKey(userId)) {
            result.setFlag(false);
            result.setMsg("该用户已经注册银行账户！");
            return result;
        }
        BankAccount bankAccount = new BankAccount(createBankAccountNo(), userId);
        bankAccounts.put(userId, bankAccount);
        result.setFlag(true);
        result.setMsg("银行账户注册成功！");
        result.setResult(bankAccount);
        return result;
    }

    @Override
    public IntegralResult registerForAirline(String userId) {
        logger.notice("航司账户注册！用户ID[" + userId + "]");
        event("航司账户注册！用户ID[" + userId + "]", "registerForAirline", "");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "registerForAirline")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        //校验是否已注册银行账户
        if (airlineAccounts.containsKey(userId)) {
            result.setFlag(false);
            result.setMsg("该用户已经注册航司账户！");
            return result;
        }
        AirlineAccount airlineAccount = new AirlineAccount(createAirlineAccountNo(), userId);
        airlineAccounts.put(userId, airlineAccount);
        result.setFlag(true);
        result.setMsg("航司账户注册成功！");
        result.setResult(airlineAccount);
        return result;
    }

    @Override
    public IntegralResult getBankAccountInfo(String userId) {
        logger.notice("查询银行账户信息！用户ID[" + userId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "getBankAccountInfo")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        //校验是否已注册银行账户
        if (!bankAccounts.containsKey(userId)) {
            result.setFlag(false);
            result.setMsg("该用户未注册银行账户！");
            return result;
        }
        result.setFlag(true);
        result.setMsg("查询完成");
        result.setResult(bankAccounts.get(userId));
        return result;
    }

    @Override
    public IntegralResult getBankAccountList(String bankAdminId) {
        logger.notice("查询银行账户列表！用户ID[" + bankAdminId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(bankAdminId, "getBankAccountList")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        result.setFlag(true);
        result.setMsg("已查询完全部的银行账户!共[" + bankAccounts.size() + "]条数据！");
        result.setResult(bankAccounts.values());
        return result;
    }

    @Override
    public IntegralResult getAirlineAccountInfo(String userId) {
        logger.notice("查询航司账户信息！用户ID[" + userId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "getAirlineAccountInfo")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        //校验是否已注册银行账户
        if (!airlineAccounts.containsKey(userId)) {
            result.setFlag(false);
            result.setMsg("该用户未注册航司账户！");
            return result;
        }
        result.setFlag(true);
        result.setMsg("查询完成");
        result.setResult(airlineAccounts.get(userId));
        return result;
    }

    @Override
    public IntegralResult getAirlineAccountList(String airlineAdminId) {
        logger.notice("查询航司账户列表！用户ID[" + airlineAdminId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(airlineAdminId, "getAirlineAccountList")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        result.setFlag(true);
        result.setMsg("已查询完全部的航司账户!共[" + airlineAccounts.size() + "]条数据！");
        result.setResult(airlineAccounts.values());
        return result;
    }

    @Override
    public IntegralResult increaseIntegral(String bankAdminId, String userId, BigInteger integral) {
        logger.notice("增加银行积分！管理员ID[" + bankAdminId + "]，用户ID[" + userId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(bankAdminId, "increaseIntegral")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        //校验是否已注册银行账户
        if (!bankAccounts.containsKey(bankAdminId)) {
            result.setFlag(false);
            result.setMsg("该用户未注册银行账户！");
            return result;
        }
        BankAccount bankAccount = bankAccounts.get(userId);
        bankAccount.setFreezeIntegral(bankAccount.getIntegral().add(integral));
        result.setFlag(true);
        result.setMsg("积分增加完成！");
        return result;
    }

    @Override
    public IntegralResult exchangeToMileage(String userId, BigInteger integral) {
        logger.notice("积分兑换里程申请！用户ID[" + userId + "]，兑换数量[" + integral + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "exchangeToMileage")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        //校验是否已注册银行账户
        if (!bankAccounts.containsKey(userId)) {
            result.setFlag(false);
            result.setMsg("该用户未注册银行账户！");
            return result;
        }
        //校验是否已注册航司账户
        if (!airlineAccounts.containsKey(userId)) {
            result.setFlag(false);
            result.setMsg("该用户未注册航司账户！");
            return result;
        }
        //校验积分余额
        if (bankAccounts.get(userId).getIntegral().compareTo(integral) < 0) {
            result.setFlag(false);
            result.setMsg("申请兑换数量超过账户中已有积分数量！");
            return result;
        }
        //创建兑换记录
        IntegralExchangeRecord record = new IntegralExchangeRecord();
        record.setId(createRecordId());
        record.setUserId(userId);
        record.setExchangeAmount(integral);
        record.setAuditStatus("0");
        record.setCurrentAuditRole("BankAdmin");
        record.setNextAuditRole("AirlineAdmin");
        records.put(record.getId(), record);
        //冻结兑换积分数量
        BankAccount bankAccount = bankAccounts.get(userId);
        bankAccount.setFreezeIntegral(bankAccount.getFreezeIntegral().add(integral));
        result.setFlag(true);
        result.setMsg("积分兑换申请成功！");
        result.setResult(record);
        return result;
    }

    @Override
    public IntegralResult getExchangeRecords(String userId) {
        logger.notice("积分兑换记录查询！用户ID[" + userId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "getExchangeRecords")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        if (records.size() < 1) {
            result.setFlag(false);
            result.setMsg("无审核记录！");
            return result;
        }
        User user = users.get(userId);
        List<IntegralExchangeRecord> recordList;

        Stream<IntegralExchangeRecord> stream = records.values().parallelStream();
        if ("User".equals(user.getRoleType())) {
            //用户查询自己的申请记录
            recordList = stream.filter(record -> record.getUserId().equals(userId)).collect(Collectors.toList());
        } else {
            recordList = stream.filter(record -> "0".equals(record.getAuditStatus()) && user.getRoleType().equals(record.getCurrentAuditRole()))
                    .collect(Collectors.toList());
        }
        if (recordList != null && recordList.size() > 0) {
            result.setFlag(true);
            result.setMsg("查询所有记录完成！");
        } else {
            result.setFlag(false);
            result.setMsg("未查询到相关记录！");
        }
        return result;
    }

    @Override
    public IntegralResult exchangeRecordsAudit(String userId, String recordId, String auditResult) {
        logger.notice("积分兑换里程审核！用户ID[" + userId + "]");
        IntegralResult result = new IntegralResult();
        //权限校验
        if (!checkPermission(userId, "exchangeRecordsAudit")) {
            result.setFlag(false);
            result.setMsg("您无权限调用该接口！");
            return result;
        }
        IntegralExchangeRecord record = records.get(recordId);
        if (record == null) {
            result.setFlag(false);
            result.setMsg("无相关兑换申请记录！");
            return result;
        }
        if (!"0".equals(record.getAuditStatus())) {
            result.setFlag(false);
            result.setMsg("该记录已审核！");
            return result;
        }
        User user = users.get(userId);
        if (!user.getRoleType().equals(record.getCurrentAuditRole())) {
            result.setFlag(false);
            result.setMsg("非本环节审核人！");
            return result;
        }
        if ("pass".equals(auditResult)) {
            //审核通过
            if ("BankAdmin".equals(record.getCurrentAuditRole())) {
                record.setCurrentAuditRole("AirlineAdmin");
                record.setNextAuditRole("");
            } else {
                //记录状态改为 0-通过
                record.setAuditStatus("1");
                //银行账户积分解冻并扣减
                BankAccount bankAccount = bankAccounts.get(record.getUserId());
                bankAccount.setFreezeIntegral(bankAccount.getFreezeIntegral().subtract(record.getExchangeAmount()));
                //航司账户里程增加
                AirlineAccount airlineAccount = airlineAccounts.get(record.getUserId());
                airlineAccount.setMileage(airlineAccount.getMileage().add(record.getExchangeAmount()));
            }
        } else {
            //审核不通过
            record.setAuditStatus("2");
            //解冻积分
            BankAccount bankAccount = bankAccounts.get(record.getUserId());
            bankAccount.setFreezeIntegral(bankAccount.getFreezeIntegral().subtract(record.getExchangeAmount()));
            bankAccount.setIntegral(bankAccount.getIntegral().add(record.getExchangeAmount()));
        }
        result.setFlag(true);
        result.setMsg("审核完成！");
        return result;
    }

    /**
     * 生成银行账户id
     *
     * @return
     */
    private synchronized String createBankAccountNo() {
        bankAccountNo++;
        return String.valueOf(bankAccountNo);
    }

    /**
     * 生成航司账户id
     *
     * @return
     */
    private synchronized String createAirlineAccountNo() {
        airlineAccountNo++;
        return String.valueOf(airlineAccountNo);
    }

    /**
     * 生成兑换记录id
     *
     * @return
     */
    private synchronized String createRecordId() {
        recordId++;
        return String.valueOf(recordId);
    }

    /**
     * 用户-接口权限校验
     *
     * @param userId     用户id
     * @param methodName 接口名
     * @return
     */
    private boolean checkPermission(String userId, String methodName) {
        User user = users.get(userId);
        if (user == null) {
            return false;
        }
        RolePermission rolePermission = this.rolePermission.get(user.getRoleType());
        if (rolePermission == null || rolePermission.getMethodList() == null || rolePermission.getMethodList().size() < 1) {
            return false;
        }
        if (rolePermission.getMethodList().contains(methodName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 使用钩子函数初始化角色权限列表
     * 仅在合约部署时执行一次
     */
    @Override
    public void onInit() {
        String[] userMethodArr = {"registerUser", "registerForAirline", "getAirlineAccountInfo",
                "registerForBank", "getBankAccountInfo", "exchangeToMileage", "getExchangeRecords"};
        String[] bankMethodArr = {"getBankAccountList", "increaseIntegral", "getExchangeRecords", "exchangeRecordsAudit"};
        String[] airlineMethodArr = {"getAirlineAccountList", "getExchangeRecords", "exchangeRecordsAudit"};
        RolePermission userPermission = new RolePermission();
        RolePermission bankPermission = new RolePermission();
        RolePermission airlinePermission = new RolePermission();
        userPermission.setMethodList(Arrays.asList(userMethodArr));
        bankPermission.setMethodList(Arrays.asList(bankMethodArr));
        airlinePermission.setMethodList(Arrays.asList(airlineMethodArr));
        rolePermission.put("User", userPermission);
        rolePermission.put("BankAdmin", bankPermission);
        rolePermission.put("AirlineAdmin", airlinePermission);
    }

}
