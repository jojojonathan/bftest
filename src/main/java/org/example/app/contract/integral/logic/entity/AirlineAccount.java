package org.example.app.contract.integral.logic.entity;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author wuziqiang
 * @date 2021/7/5 15:12
 * @description 航空公司账户
 */
public class AirlineAccount {
    private String accountId;
    private String userId;
    private BigInteger mileage;

    public AirlineAccount() {
    }

    public AirlineAccount(String accountId, String userId) {
        this.accountId = accountId;
        this.userId = userId;
        this.mileage = new BigInteger("0");
    }

    public AirlineAccount(String accountId, String userId, BigInteger mileage) {
        this.accountId = accountId;
        this.userId = userId;
        this.mileage = mileage;
    }

    public AirlineAccount(String accountId) {
        this.accountId = accountId;
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

    public BigInteger getMileage() {
        return mileage;
    }

    public void setMileage(BigInteger mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlineAccount that = (AirlineAccount) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(userId, that.userId) && Objects.equals(mileage, that.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, userId, mileage);
    }

    @Override
    public String toString() {
        return "AirlineAccount{" +
                "accountId='" + accountId + '\'' +
                ", userId='" + userId + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
