package org.example.app.contract.integral.logic.entity;

import java.util.Objects;

/**
 * @author wuziqiang
 * @date 2021/7/5 17:57
 * @description 合约调用结果
 */
public class IntegralResult<T> {
    private boolean flag;
    private String msg;
    private T result;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegralResult<?> that = (IntegralResult<?>) o;
        return flag == that.flag && Objects.equals(msg, that.msg) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flag, msg, result);
    }

    @Override
    public String toString() {
        return "IntegralResult{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
