package com.nidecai.managerndc.common.filter;

import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.GsonUtil;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.service.CVStoreService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author river
 * @title: HttpFilter
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2112:53
 */
@WebFilter(urlPatterns = "/jmanager/*")
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	System.out.println(servletRequest.getClass());
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
        CVStoreService cVStoreService = (CVStoreService) applicationContext.getBean("cVStoreService");
        Map<String, String> cvorderUrlMap = (Map<String, String>) applicationContext.getBean("cvordermap");
        if (servletRequest.getParameter("login_token") != null) {
            List<String> permissions = cVStoreService.getPermissionsByToken(servletRequest.getParameter("login_token"));
            //临时添加权限
            permissions.add("personnelspecific");
            permissions.add("getphoneverifycode");
            permissions.add("getreprot");
            if (permissions != null && permissions.size() != 0) {
            	
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                //获取当前访问的url
                String requestURI = request.getRequestURI();
                String currentUriPermission = cvorderUrlMap.get(requestURI);
                if (currentUriPermission != null) {
                	
                    if (permissions.contains(currentUriPermission)) {
                        filterChain.doFilter(servletRequest, servletResponse);

                    } else {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.getWriter().write(GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.NO_PERMISION)));
                        return;
                    }

                }else {
					//tomcat配置多一层路径的处理
                	String[] splits = requestURI.split("/");
                	String needSub = "/" + splits[1];
                	requestURI = requestURI.replaceFirst(needSub, "");
                	currentUriPermission = cvorderUrlMap.get(requestURI);
                	 if (currentUriPermission != null) {
                         if (permissions.contains(currentUriPermission)) {
                             filterChain.doFilter(servletRequest, servletResponse);

                         } else {
                             httpServletResponse.setContentType("application/json;charset=utf-8");
                             httpServletResponse.getWriter().write(GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.NO_PERMISION)));
                             return;
                         }

                     }
				}
            } else {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.getWriter().write(GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.PARAM_LOST)));
                return;

            }
        } else {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.PARAM_LOST)));
            return;
        }
    }
}
