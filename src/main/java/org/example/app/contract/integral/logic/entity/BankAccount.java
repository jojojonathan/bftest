package org.example.app.contract.integral.logic.entity;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author wuziqiang
 * @date 2021/7/5 16:13
 * @description 银行账户
 */
public class BankAccount {
    private String accountId;
    private String userId;
    private BigInteger integral;
    private BigInteger balance;
    private BigInteger freezeIntegral;

    public BankAccount() {
    }

    public BankAccount(String accountId, String userId) {
        this.accountId = accountId;
        this.userId = userId;
        this.integral = new BigInteger("0");
        this.balance = new BigInteger("0");
        this.freezeIntegral = new BigInteger("0");
    }

    public BankAccount(String accountId, String userId, BigInteger integral, BigInteger balance, BigInteger freezeIntegral) {
        this.accountId = accountId;
        this.userId = userId;
        this.integral = integral;
        this.balance = balance;
        this.freezeIntegral = freezeIntegral;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigInteger getIntegral() {
        return integral;
    }

    public void setIntegral(BigInteger integral) {
        this.integral = integral;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getFreezeIntegral() {
        return freezeIntegral;
    }

    public void setFreezeIntegral(BigInteger freezeIntegral) {
        this.freezeIntegral = freezeIntegral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(userId, that.userId) && Objects.equals(integral, that.integral) && Objects.equals(balance, that.balance) && Objects.equals(freezeIntegral, that.freezeIntegral);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, userId, integral, balance, freezeIntegral);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId='" + accountId + '\'' +
                ", userId='" + userId + '\'' +
                ", integral=" + integral +
                ", balance=" + balance +
                ", freezeIntegral=" + freezeIntegral +
                '}';
    }
}
