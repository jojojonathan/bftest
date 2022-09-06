package org.example.app.contract.integral.logic.entity;

import java.util.Objects;

/**
 * @author wuziqiang
 * @date 2021/7/5 16:55
 * @description 用户
 */
public class User {
    private String userId;
    private String userName;
    private String roleType;

    public User() {
    }

    public User(String userId, String userName, String roleType) {
        this.userId = userId;
        this.userName = userName;
        this.roleType = roleType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(roleType, user.roleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, roleType);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
