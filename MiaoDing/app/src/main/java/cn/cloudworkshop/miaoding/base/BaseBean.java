package cn.cloudworkshop.miaoding.base;

import java.io.Serializable;

/**
 * Author：Libin on 2018/10/25 15:29
 * Email：1993911441@qq.com
 * Describe：
 */
public class BaseBean<T> implements Serializable {
    private int code;
    private T data;

    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
