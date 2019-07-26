package com.nidecai.managerndc.common.codeutil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author river
 * @title: PackageClass
 * @projectName manager-ndc
 * @description: 从项目文件获取对应包下所有的类
 * @date 2019/6/2018:32
 */
public class PackageClass {

    public static List<String> getClassNamefFromPachage(String packageName) throws IOException, ClassNotFoundException {
        Enumeration<URL> iterator = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
        List<String>list = new ArrayList<String>();

        URL url = null;
        File file = null;
        File[] fls = null;
        Class<?> c = null;
        String className = null;
        String classFullName = null;
        while(iterator.hasMoreElements()) {
            url = iterator.nextElement();
            if ("file".equals(url.getProtocol())) {
                file = new File(url.getPath());
//                System.out.println(file);
                if (file.isDirectory()){
                    fls= file.listFiles();
                    for(File fl :fls) {
                        className = fl.getName();
                        className = className.substring(0,className.lastIndexOf("."));
                        classFullName = packageName+"."+className;
                        c=Thread.currentThread().getContextClassLoader().loadClass(classFullName);
                        list.add(classFullName);
                    }
                }
            }
        }
        return list;

    }
}
