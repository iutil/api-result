package net.iutil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * 基础返回类
 *
 * @author Erwin Feng
 * @since 2019-07-05 17:55
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 5693766104698006081L;

    /**
     * 成功的标志，true / false
     */
    private Boolean success = false;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 无参数构造方法
     */
    public BaseResult() {
    }

    /**
     * 带参数构造方法
     *
     * @param success 成功的标识
     * @param message 信息
     */
    public BaseResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * 设置success的值
     *
     * @param success 成功的标识
     * @return {@link net.iutil.BaseResult}
     */
    public BaseResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 设置信息
     *
     * @param message 信息
     * @return {@link net.iutil.BaseResult}
     */
    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置数据
     *
     * @param data 数据，泛型T
     * @return {@link net.iutil.BaseResult}
     */
    public BaseResult setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 定义成功为true
     */
    public static final Boolean SUCCESS = true;

    /**
     * 定义成功默认的返回信息为Success
     */
    public static final String DEFAULT_SUCCESS = "Success";

    /**
     * 定义错误默认的返回信息为Error
     */
    public static final String DEFAULT_ERROR = "Error";

    /**
     * 成功时调用
     *
     * @param <T> {@link T}
     * @return {@link net.iutil.BaseResult}
     */
    public static <T> BaseResult<T> success() {
        return new BaseResult<>(SUCCESS, DEFAULT_SUCCESS);
    }

    /**
     * 成功时调用
     *
     * @param data 数据
     * @param <T>  {@link T}
     * @return {@link net.iutil.BaseResult}
     */
    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> baseResult = success();
        baseResult.data = data;
        return baseResult;
    }

    /**
     * 错误时调用
     *
     * @param <T> {@link T}
     * @return {@link net.iutil.BaseResult}
     */
    public static <T> BaseResult<T> error() {
        return new BaseResult<>().setMessage(DEFAULT_ERROR);
    }

    /**
     * 错误时调用
     *
     * @param msg 错误信息
     * @param <T> {@link T}
     * @return {@link net.iutil.BaseResult}
     */
    public static <T> BaseResult<T> error(String msg) {
        return new BaseResult<>().setMessage(msg);
    }

    /**
     * rewrite toString()
     * 如果code为空，则code不返回，其他也一样
     * 例如，code为0 msg为Success data为null
     * 则返回：ApiResult={code=0,msg='Success'}
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BaseResult { ");
        // ret
        sb.append("success=").append(success);
        if (StringUtils.isNotEmpty(message)) // msg not empty
            sb.append(", message='").append(message).append('\'');
        if (data != null) // data not null
            sb.append(", data=").append(data);
        sb.append(" }");
        return sb.toString();
    }
}
