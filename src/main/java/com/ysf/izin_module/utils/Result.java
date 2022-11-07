package com.ysf.izin_module.utils;

import com.ysf.izin_module.enums.StatusEnum;
import lombok.Data;

@Data
public class Result<T> {
    private T t;
    private StatusEnum status;
    private String message;

    public Result(T t, StatusEnum status, String message) {
        this.t = t;
        this.status = status;
        this.message = message;
    }

    public Result(StatusEnum status, String message) {
        this.status = status;
        this.message = message;
    }
}
