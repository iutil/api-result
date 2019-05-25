package net.iutil;

/**
 * 自定义返回工具类
 * @author Erwin Feng
 */
public class MyApiResult<T> extends ApiResult<T> {

    private static final long serialVersionUID = -6085866099085313725L;

    // add param success
    /* 成功的标识，ture:成功; false:失败 */
    private Boolean success = false;

    public Boolean getSuccess() {
        return success;
    }

    private MyApiResult(Integer code, String msg, Boolean success) {
        super(code, msg);
        this.success = success;
    }

    // ok
    public static ApiResult ok() {
        // return new Result<>(BaseCodeMsg.SUCCESS);
        // return new Result<>(200, "OK");
        return new MyApiResult(200, "OK", true);
    }

    // fail
    public static ApiResult fail() {
        // return new Result<>();
        return new ApiResult<>(400, "Not Found");
    }

    // toString
    @Override
    public String toString() {
        Integer code = this.getCode();
        String msg = this.getMsg();
        T data = this.getData();

        StringBuilder sb = new StringBuilder("MyApiResult{");

        if (code != null) // code not null
            sb.append("code=").append(code);
        if (StringUtils.isNotEmpty(msg)) // msg not empty
            sb.append(", msg='").append(msg).append('\'');
        if (success) // true
            sb.append(", success=").append(success);
        if (data != null) // data not null
            sb.append(", data=").append(data);

        sb.append("}");
        return sb.toString();
    }
    // ...
    // 请参考 {@see com.fengwenyi.javalib.result.Result}
}
