package com.shaw.kratos.dto;

public class BaseDO<T> {
    private T id;
    private Long gmtCreate;
    private Long gmtModified;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
