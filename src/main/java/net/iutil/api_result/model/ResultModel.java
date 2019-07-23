package net.iutil.api_result.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 返回实体
 * @author Erwin Feng
 * @since 2019-07-23 15:01
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 6555943285979897107L;

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
}
