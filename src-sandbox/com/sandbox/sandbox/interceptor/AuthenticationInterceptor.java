package com.sandbox.sandbox.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sandbox.sandbox.model.User;
/**

 * AbstractInterceptor无法正常拦截，现改为使用AuthorityInterceptor
 *详见com.sandbox.sandbox.interceptor.AuthorityInterceptor

 */
public class AuthenticationInterceptor extends AbstractInterceptor {

	private static Logger logger = LogManager.getLogger(AuthenticationInterceptor.class.getClass().getSimpleName());
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入拦截器");
		HttpServletRequest  request = ServletActionContext.getRequest();
		Map<String, Object> session = arg0.getInvocationContext().getSession();
		User user = (User)session.get("user");
		if(user == null){
			String location = request.getRequestURL().toString();
			if(location.endsWith(".action")){
				String param = request.getQueryString();
				location = (param == null) ? location:location + "?" +param;
				logger.debug("用户跳转{}",location);
				session.put("location", location);
				return "login";
			}
		}
		return arg0.invoke();
	}

}
