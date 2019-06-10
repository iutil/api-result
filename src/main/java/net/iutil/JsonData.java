package net.iutil;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * a simple result
 * <p>
 *     不采用code的方式管理错误，只需要知道是错误就行，
 *     错误时返回详细的错误信息，正确时返回数据
 * </p>
 * @author Erwin Feng
 * @since 2019-06-11 01:58
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonData<T> implements Serializable {

    private static final long serialVersionUID = -4790418217804987468L;

    /** 结果，true / false */
    private Boolean ret = false;

    /** 信息 */
    private String msg;

    /** 数据 */
    private T data;

    /**
     * 无参数构造方法
     */
    public JsonData() {
    }

    /**
     * 构造方法
     * @param ret 结果
     * @param msg 描述信息
     */
    public JsonData(Boolean ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    /**
     * 构造方法
     * @param ret 结果
     * @param msg 描述信息
     * @param data 数据
     */
    public JsonData(Boolean ret, String msg, T data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    /**
     * set msg
     * @param msg 描述信息
     * @return {@link JsonData}
     */
    public JsonData setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * set data
     * @param data 数据
     * @return {@link JsonData}
     */
    public JsonData setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * get ret
     * @return 结果
     */
    public Boolean getRet() {
        return ret;
    }

    /**
     * get msg
     * @return 描述信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * get data
     * @return 获取数据
     */
    public T getData() {
        return data;
    }

    /** 定义成功为true */
    private static final Boolean SUCCESS = true;

    /** 定义成功默认的返回信息为Success */
    private static final String DEFAULT_SUCCESS = "Success";

    /** 定义错误默认的返回信息为Error */
    private static final String DEFAULT_ERROR = "Error";

    /**
     * 成功时调用
     * @param <T> {@link T}
     * @return {@link JsonData}
     */
    public static <T> JsonData<T> success() {
        return new JsonData<>(SUCCESS, DEFAULT_SUCCESS);
    }

    /**
     * 成功时调用
     * @param data 数据
     * @param <T> {@link T}
     * @return {@link JsonData}
     */
    public static <T> JsonData<T> success(T data) {
        JsonData<T> result = success();
        result.data = data;
        return result;
    }

    /**
     * 错误时调用
     * @param <T> {@link T}
     * @return {@link JsonData}
     */
    public static <T> JsonData<T> error() {
        return new JsonData<>().setMsg(DEFAULT_ERROR);
    }

    /**
     * 错误时调用
     * @param msg 错误信息
     * @param <T> {@link T}
     * @return {@link JsonData}
     */
    public static <T> JsonData<T> error(String msg) {
        return new JsonData<>().setMsg(msg);
    }

    /**
     * rewrite toString()
     * 如果code为空，则code不返回，其他也一样
     * 例如，code为0 msg为Success data为null
     * 则返回：ApiResult={code=0,msg='Success'}
     * @return 字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("JsonData{");
        // ret
        sb.append("ret=").append(ret);
        if (StringUtils.isNotEmpty(msg)) // msg not empty
            sb.append(", msg='").append(msg).append('\'');
        if (data != null) // data not null
            sb.append(", data=").append(data);
        sb.append("}");
        return sb.toString();
    }
}
