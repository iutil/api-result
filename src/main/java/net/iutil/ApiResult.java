package net.iutil;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 接口返回结果封装工具类
 *
 * <p>
 * 包含的属性：
 *     <ul>
 *         <li>code 返回码</li>
 *         <li>msg  描述</li>
 *         <li>data 数据(对象或数组)</li>
 *     </ul>
 *
 * <p>
 *
 * 用例：
 * <ul>
 *     <li>ApiResult.success()</li>
 *     <li>ApiResult.success(T)</li>
 *     <li>ApiResult.error()</li>
 *     <li>ApiResult.error(BaseCodeMsg)</li>
 *     <li>ApiResult.error().setCode(code).setMsg(msg)</li>
 * </ul>
 *
 * @author Wenyi Feng
 * @since 1.1
 */
/* 过滤掉null字段，data里面的每个对象都需要加 */
//@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -2683214929256826770L;

    /* 返回码 */
    private Integer code;

    /* 描述 */
    private String msg;

    /* 数据 */
    private T data;

    /**
     * 无参数构造方法
     */
    public ApiResult() {
    }

    /**
     * 返回码构造方法
     * @param code 返回码
     * @param msg 描述
     */
    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回码构造方法
     * @param baseCodeMsg {@link BaseCodeMsg}
     */
    public ApiResult(BaseCodeMsg baseCodeMsg) {
        if (baseCodeMsg != null) {
            this.code = baseCodeMsg.getCode();
            this.msg = baseCodeMsg.getMsg();
        }
    }

    /**
     * 成功时调用
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(BaseCodeMsg.SUCCESS);
    }

    /**
     * 成功时调用
     * @param data 数据
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = success();
        result.data = data;
        return result;
    }

    /**
     * 错误时调用
     * @param baseCodeMsg 返回码
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult<T> error(BaseCodeMsg baseCodeMsg) {
        return new ApiResult<>(baseCodeMsg);
    }

    /**
     * 错误时调用
     * @param code 返回码
     * @param msg 描述
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult<T> error(Integer code, String msg) {
        return new ApiResult<>(code, msg);
    }

    /**
     * 错误时调用，默认错误，错误码：-1；描述信息：Error
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult<T> error() {
        return new ApiResult<>(BaseCodeMsg.ERROR_INIT);
    }

    /**
     * 获取返回码
     * @return 返回码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取描述信息
     * @return 描述信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 获取数据
     * @return {@link T}
     */
    public T getData() {
        return data;
    }

    /**
     * 设置返回码
     * @param code 返回码
     * @return {@link ApiResult}
     */
    public ApiResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 设置描述信息
     * @param msg 描述信息
     * @return {@link ApiResult}
     */
    public ApiResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 设置数据
     * @param data 数据
     */
    public ApiResult setData(T data) {
        this.data = data;
        return this;
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
        StringBuilder sb = new StringBuilder("ApiResult{");
        if (code != null) // code not null
            sb.append("code=").append(code);
        if (StringUtils.isNotEmpty(msg)) // msg not empty
            sb.append(", msg='").append(msg).append('\'');
        if (data != null) // data not null
            sb.append(", data=").append(data);
        sb.append("}");
        return sb.toString();
    }
}
