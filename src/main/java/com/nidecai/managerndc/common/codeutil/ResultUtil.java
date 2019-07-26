package com.nidecai.managerndc.common.codeutil;

import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.entitycommon.ResultDTO;

import java.util.List;

/**
 * @author river
 * @title: ResultUtil
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2016:32
 */
public class ResultUtil {

    private static ResultDTO<Object> successResult = new ResultDTO<Object>(true, null,
            CommonMessageEnum.SUCCESS.getMsg(), CommonMessageEnum.SUCCESS.getCode(), 0);

    private static ResultDTO<Object> failResult = new ResultDTO<Object>(false, null, CommonMessageEnum.FAIL.getMsg(),
            CommonMessageEnum.FAIL.getCode(), 0);

    static ResultDTO<Object> defaultSuccess() {
        return successResult;
    }

    static ResultDTO<Object> defaultFail() {
        return failResult;
    }

    public static ResultDTO<Object> getSuccess(Object object) {
        if (object == null) {
            return defaultSuccess();
        }
        return success(object);
    }

    public static ResultDTO<Object> getFail(CommonMessageEnum commonMessageEnum) {
        if (commonMessageEnum == null) {
            return defaultFail();
        }
        return fail(commonMessageEnum);
    }

    static ResultDTO<Object> success(Object object) {
        ResultDTO<Object> successResult = new ResultDTO<Object>();
        successResult.setFlag(true);
        successResult.setData(object);
        if (object instanceof List) {
            List<?> theList = (List<?>) object;
            successResult.setTotal(theList.size());
        }
        successResult.setMsg(CommonMessageEnum.SUCCESS.getMsg());
        successResult.setCode(CommonMessageEnum.SUCCESS.getCode());
        return successResult;
    }

    static ResultDTO<Object> fail(CommonMessageEnum commonMessageEnum) {
        ResultDTO<Object> failResult = new ResultDTO<Object>();
        failResult.setFlag(false);
        failResult.setMsg(commonMessageEnum.getMsg());
        failResult.setCode(commonMessageEnum.getCode());
        return failResult;
    }
}
