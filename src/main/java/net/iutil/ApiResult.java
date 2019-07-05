package net.iutil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

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
@Getter
public class ApiResult<T> extends BaseResult<T> {

    private static final long serialVersionUID = -2683214929256826770L;

    /* 返回码 */
    private Integer code;

    /**
     * 无参数构造方法
     */
    public ApiResult() {
    }

    /**
     * 成功时调用
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult success() {
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(BaseResult.SUCCESS);
        return apiResult;
    }

    /**
     * 错误时调用
     * @param <T> {@link T}
     * @return {@link ApiResult}
     */
    public static <T> ApiResult error() {
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(BaseResult.SUCCESS);
        return new ApiResult();
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
     * @param message 描述信息
     * @return {@link net.iutil.ApiResult}
     */
    public ApiResult setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    /**
     * 设置数据
     * @param data 数据
     */
    public ApiResult setData(T data) {
       super.setData(data);
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
        StringBuilder sb = new StringBuilder("ApiResult { ");
        sb.append("success=").append(super.getSuccess()); // success
        if (code != null) // code not null
            sb.append(", code=").append(code);
        if (StringUtils.isNotEmpty(super.getMessage())) // msg not empty
            sb.append(", message='").append(super.getMessage()).append('\'');
        if (super.getData() != null) // data not null
            sb.append(", data=").append(super.getData());
        sb.append(" }");
        return sb.toString();
    }
}
