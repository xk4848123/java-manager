package com.nidecai.managerndc;

import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.service.CloseUpShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagerNdcApplication.class)
public class ManagerNdcApplicationTests  {
     @Autowired
     private CloseUpShopService closeUpShopService;
     @Test
     public  void test() throws BusinessException {
          closeUpShopService.closeShop();
     }
}
