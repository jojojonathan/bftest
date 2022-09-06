package org.example.app.contract.integral.logic.entity;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author wuziqiang
 * @date 2021/7/5 19:05
 * @description 兑换记录
 */
public class IntegralExchangeRecord {
    /**
     * 记录id
     */
    private String id;
    /**
     * 申请人id
     */
    private String userId;
    /**
     * 申请转换数量
     */
    private BigInteger exchangeAmount;
    /**
     * 当前审核角色
     */
    private String currentAuditRole;
    /**
     * 下一审核角色
     */
    private String nextAuditRole;
    /**
     * 审核状态
     * 0-审核中
     * 1-通过
     * 2-不通过
     */
    private String auditStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigInteger getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(BigInteger exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public String getCurrentAuditRole() {
        return currentAuditRole;
    }

    public void setCurrentAuditRole(String currentAuditRole) {
        this.currentAuditRole = currentAuditRole;
    }

    public String getNextAuditRole() {
        return nextAuditRole;
    }

    public void setNextAuditRole(String nextAuditRole) {
        this.nextAuditRole = nextAuditRole;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegralExchangeRecord that = (IntegralExchangeRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(exchangeAmount, that.exchangeAmount) && Objects.equals(currentAuditRole, that.currentAuditRole) && Objects.equals(nextAuditRole, that.nextAuditRole) && Objects.equals(auditStatus, that.auditStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, exchangeAmount, currentAuditRole, nextAuditRole, auditStatus);
    }

    @Override
    public String toString() {
        return "IntegralExchangeRecord{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", exchangeAmount=" + exchangeAmount +
                ", currentAuditRole='" + currentAuditRole + '\'' +
                ", nextAuditRole='" + nextAuditRole + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                '}';
    }
}
