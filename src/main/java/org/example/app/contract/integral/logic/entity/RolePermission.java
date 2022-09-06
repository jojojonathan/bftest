package org.example.app.contract.integral.logic.entity;

import java.util.List;
import java.util.Objects;

public class RolePermission {
    /**
     * 接口名称集合
     */
    private List<String> methodList;

    public List<String> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<String> methodList) {
        this.methodList = methodList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermission that = (RolePermission) o;
        return Objects.equals(methodList, that.methodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodList);
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "methodList=" + methodList +
                '}';
    }
}
