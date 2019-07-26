package com.nidecai.managerndc.controller;
import com.github.pagehelper.PageInfo;
import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.GsonUtil;
import com.nidecai.managerndc.common.codeutil.LoggingUtil;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.entity.Cvorder;
import com.nidecai.managerndc.service.CvorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author river
 * @title: CvorderController
 * @projectName manager-ndc
 * @description: 订单列表的分页显示
 * @date 2019/6/2013:30
 */
@RestController
@RequestMapping(value = "/jmanager/v1/orderList")
public class CvorderController {
    @Autowired
    private CvorderService cvorderService;

    //查询全部订单
    @RequestMapping(value = "/pageList" ,method = RequestMethod.GET)
    @ConvenientStore(value = "marketStatistics")
    public String listOrder(HttpServletRequest request) throws BusinessException {
    	try {
        	LoggingUtil.i("ddd");
            String pageStr = request.getParameter("page");
            String sizeStr = request.getParameter("size");
            int page = Integer.parseInt(pageStr);
            int size = Integer.parseInt(sizeStr);
            Cvorder cvorder = new Cvorder();
            PageInfo<Cvorder> orderList = cvorderService.getOrderList(cvorder, page, size);
            return GsonUtil.GsonString(ResultUtil.getSuccess(orderList));
		    } catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
		}

    }


    //根据订单id查询
    @RequestMapping(value = "/orderId", method = RequestMethod.GET)
    @ConvenientStore(value = "marketManage")
    public String getOneById(HttpServletRequest request) {
        String cvorderId = request.getParameter("id");
        int id = Integer.parseInt(cvorderId);
        Cvorder cvorder = cvorderService.getOneById(id);
        try {
            if (cvorder!=null) {
                return GsonUtil.GsonString(ResultUtil.getSuccess(cvorder));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.SERVERERR));
    }

    //根据订单id删除
    @RequestMapping(value = "/selectId", method = RequestMethod.GET)
    @ConvenientStore(value = "shopManage")
    public String deleteId(HttpServletRequest request) {
        String cvorderId = request.getParameter("id");
        int id = Integer.parseInt(cvorderId);
        int i = cvorderService.deleteId(id);
        try {
            if (i > 0) {
                return  GsonUtil.GsonString(ResultUtil.getSuccess(null));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtil.GsonString(ResultUtil.getFail(null));
    }

    //根据订单id进行批量删除
    @RequestMapping(value = "/deleteIds", method = RequestMethod.GET)
    @ConvenientStore(value = "orderShop")
    public String deleteIds(HttpServletRequest request) {
        String[] cvorderId = request.getParameterValues("cvorderArrayId[]");
        if (cvorderId != null && cvorderId.length != 0) {
            List<Integer> cvorderList = null;
            for (String cvorderIdValue : cvorderId) {
                int id = Integer.parseInt(cvorderIdValue);
                cvorderList = new ArrayList<Integer>();
                cvorderList.add(id);
            }
            try {
                int i = cvorderService.deleteIds(cvorderList);
                if (i > 0) {
                    return  GsonUtil.GsonString(ResultUtil.getSuccess(null));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.SERVERERR));
    }


    //根据订单分配给指定的骑手
    @RequestMapping(value = "/updateRider", method = RequestMethod.GET)
    @ConvenientStore(value = "orderRider")
    public String updateRiderUser(HttpServletRequest request) {
        //骑手的id
        String ridId = request.getParameter("rid");
        int ridUserId = Integer.parseInt(ridId);
        String id = request.getParameter("id");
        int cvOrderId = Integer.parseInt(id);
        int i = cvorderService.updateRiderUser(ridUserId, cvOrderId);
        try {
            if (i > 0) {
                return  GsonUtil.GsonString(ResultUtil.getSuccess(null));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GsonUtil.GsonString(ResultUtil.getFail(null));

    }
}
