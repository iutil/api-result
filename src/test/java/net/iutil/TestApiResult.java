package net.iutil;

import com.alibaba.fastjson.JSON;
import net.iutil.vo.UserVO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Erwin Feng
 * @since 2019-03-24 14:55
 */
public class TestApiResult {

    /* data */
    private Object obj = new Object();

    @Test
    public void start() {
        ApiResult.error();
        ApiResult.success();

        ApiResult apiResult = ApiResult.error().setCode(-1000);
        System.out.println(apiResult); // ApiResult{code=-1000, msg='Error'}

    }

    // 简单例子
    @Test
    public void simple() {
        // 错误
        ApiResult.error();

        // 错误类型
        ApiResult.error(BaseCodeMsg.ERROR_INIT);

        // 成功
        ApiResult.success();

        // 成功，返回数据
        ApiResult.success(obj);
    }

    // 完整例子
    @Test
    public void complete() {
        // Error(-1, "Error")
        ApiResult.error();

        // Error(-1, "Error")
        ApiResult.error(BaseCodeMsg.ERROR_INIT);

        // Error(404, "Error")
        ApiResult.error().setCode(404);

        // Error(404, "Not Found")
        ApiResult.error().setCode(404).setMsg("Not Found");

        // Success(0, "Success")
        ApiResult.success();

        // Success(0, "Success", obj)
        ApiResult.success(obj);

        // Success(200, "Success")
        ApiResult.success().setCode(200);

        // Success(200, "Success")
        ApiResult.success().setCode(200).setMsg("Success");

        // Success(200, "Success", obj)
        ApiResult.success().setCode(200).setData(obj);

        // Success(200, "Success", obj)
        ApiResult.success().setCode(200).setMsg("Success").setData(obj);
    }

    // 重写ApiResult
    @Test
    public void mApiResult() {
        System.out.println(MyApiResult.ok()); // MyApiResult{code=200, msg='OK', success=true}

        System.out.println(MyApiResult.fail()); // ApiResult{code=400, msg='Not Found'}
    }

    // 自定义返回码
    @Test
    public void customCodeMsg() {
        ApiResult apiResult;

        // 500
        apiResult = ApiResult.error(CodeMsg.ERROR_COMMON_INTERNAL_SERVICE_ERROR);
        System.out.println(apiResult); // ApiResult{code=500100, msg='内部服务错误'}

        // user not register
        apiResult = ApiResult.error(CodeMsg.ERROR_USER_ACCOUNT_NOT_FOUND);
        System.out.println(apiResult); // ApiResult{code=10001, msg='该账号未注册'}

        // ....
        // ApiResult.error(...);
    }

    // about data
    @Test
    public void data() {
        ApiResult apiResult;

        // 字符串
        apiResult = ApiResult.success("string");
        System.out.println(JSON.toJSONString(apiResult));
        /*
        {
            "code":0,
            "data":"string",
            "msg":"Success"
        }
         */

        // int
        apiResult = ApiResult.success(100);
        System.out.println(JSON.toJSONString(apiResult));
        /*
        {
            "code":0,
            "data":100,
            "msg":"Success"
        }
         */

        // object
        apiResult = ApiResult.success(new UserVO()
                .setId(UUID.randomUUID().toString())
                .setName("Erwin Feng")
                .setAge(20)
                .setRegisterTime(new Date()));
        System.out.println(JSON.toJSONString(apiResult));
        /*
        {
            "code":0,
            "data":{
                "age":20,
                "id":"b37120ae-cbbf-4b61-8ab0-9adb001fc09f",
                "name":"Erwin Feng",
                "registerTime":1554025274626
            },
            "msg":"Success"
        }
         */

        // test object field value is null
        // object
        apiResult = ApiResult.success(new UserVO()
                .setId(UUID.randomUUID().toString())
                .setName("Tom")
                .setRegisterTime(new Date()));
        System.out.println(JSON.toJSONString(apiResult));
        /*
        {
            "code":0,
            "data":{
                "id":"f86e5191-0cc8-4bba-a1ae-3bcb2c996ef2",
                "name":"Tom",
                "registerTime":1554025642983
            },
            "msg":"Success"
        }
         */

        // array
        List<String> stringList = new ArrayList<>();
        stringList.add("Java");
        stringList.add("C");
        stringList.add("C++");
        stringList.add("Python");
        stringList.add("JavaScript");
        apiResult = ApiResult.success(stringList);
        System.out.println(JSON.toJSONString(apiResult));
        /*
        {
            "code":0,
            "data":[
                "Java",
                "C",
                "C++",
                "Python",
                "JavaScript"
            ],
            "msg":"Success"
        }
         */

        // array + object
        List<UserVO> userList = new ArrayList<>();
        userList.add(new UserVO()
                .setId(UUID.randomUUID().toString())
                .setName("Erwin Feng")
                .setAge(20)
                .setRegisterTime(new Date()));
        userList.add(new UserVO()
                .setId(UUID.randomUUID().toString())
                .setName("Zhansan")
                .setAge(21)
                .setRegisterTime(new Date()));
        apiResult = ApiResult.success(userList);
        System.out.println(JSON.toJSONString(apiResult));
        /*
        {
            "code":0,
            "data":[
                {
                    "age":20,
                    "id":"32a11aba-f08e-432c-b9a8-ad8dbd66c85b",
                    "name":"Erwin Feng",
                    "registerTime":1554025274629
                },
                {
                    "age":21,
                    "id":"28ba1289-1d0b-4df4-8c46-49f77a82ad66",
                    "name":"Zhansan",
                    "registerTime":1554025274629
                }
            ],
            "msg":"Success"
        }
         */

    }

    @Test
    public void returnMsg() {
        BaseCodeMsg TEST_CODE_MSG = BaseCodeMsg.app(10, "异常测试：%s");
        ApiResult<Object> apiResult = ApiResult.error(TEST_CODE_MSG.fillArgs("自定义异常"));
        System.out.println(apiResult);
        ApiResult<Object> apiResult2 = ApiResult.error(TEST_CODE_MSG.fillArgs("未知异常"));
        System.out.println(apiResult2);

    }

}
