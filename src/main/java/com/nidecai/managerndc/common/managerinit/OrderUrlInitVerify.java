package com.nidecai.managerndc.common.managerinit;

import com.github.pagehelper.PageInterceptor;
import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.PackageClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author river
 * @title: OrderInit
 * @projectName manager-ndc
 * @description: 服务启动加载方法
 * @date 2019/6/2016:03
 */

@Configuration
public class OrderUrlInitVerify {
      /*封装所有的url权限到map集合*/
      @Bean("cvordermap")
      public  Map<String,String> init(){
          Map<String,String> urlMap = new HashMap<String,String>();
          List<String> controllerList= null;
          RequestMapping controllerAnnotation=null;
          Method[] methods=null;
          String keyAnnotation="";
          try {
              controllerList = PackageClass.getClassNamefFromPachage("com.nidecai.managerndc.controller");
              for (String controllerClass : controllerList) {
                    if (controllerClass !=null){
                        Class<?> controller = Class.forName(controllerClass);
                        methods = controller.getMethods();
                        controllerAnnotation = controller.getAnnotation(RequestMapping.class);
                        if (controllerAnnotation != null){
                            String[] controllerValue = controllerAnnotation.value();
                            keyAnnotation=controllerValue[0];
                        }
                    }
              }
          } catch (IOException e) {
              e.printStackTrace();
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }

          //循环获取方法上的注解
          for (Method method : methods) {
               RequestMapping methodAnnoation = method.getAnnotation(RequestMapping.class);
               ConvenientStore convenientStoreAnnoation=method.getAnnotation(ConvenientStore.class);
               String valueAnnotaion ="";
               if (methodAnnoation !=null){
                    String[] methodValue=  methodAnnoation.value();
                    valueAnnotaion = methodValue[0];
                    urlMap.put(keyAnnotation+valueAnnotaion,convenientStoreAnnoation.value());
               }
          }
           return  urlMap;
    }

    /*分页工具处理*/
    @Bean
    public PageInterceptor pageHelp(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable","true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }


    /*@Bean("sqlSessionFactoryBean")
    public SqlSessionFactoryBean MapperInit(){
          SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
          sqlSessionFactoryBean.setConfiguration("");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.nidecai.managerndc.mapper");
        memapperScannerConfigurer.setSqlSessionFactoryBeanNa("sqlSessionFactoryBean");
        Properties properties = new Properties();
        properties.setProperty("mappers","tk.mybatis.mapper.common.Mapper");
        mapperScannerConfigurer.setProperties(properties);
        return  mapperScannerConfigurer;
    }
*/
}
