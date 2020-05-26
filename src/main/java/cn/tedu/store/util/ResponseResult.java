package cn.tedu.store.util;

import java.io.Serializable;

/**
 * @author L-Horatio
 * @date 2020/5/26
 * @time 22:43
 */

/**
 * 服务器端向客户端响应结果的类型
 * @param <T> 服务器端向客户端响应的数据的类型
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -1626793180717240861L;

    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer state) {
        setState(state);
    }

    public ResponseResult(Integer state, String message) {
        this(state);
        setMessage(message);
    }

    public ResponseResult(Integer state, Exception e) {
        this(state, e.getMessage());
    }

    public ResponseResult(Integer state, T data) {
        this(state);
        setData(data);
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
