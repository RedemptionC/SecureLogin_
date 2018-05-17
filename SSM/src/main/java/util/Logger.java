package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Logger implements HandlerInterceptor
{
	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2, Exception arg3) 
		      throws Exception { 
		    // TODO Auto-generated method stub 
		    System.out.println("MyInterceptors afterCompletion"); 
		  } 
		  /** 
		   * 该方法在目标方法调用之后，渲染视图之前被调用； 
		   * 可以对请求域中的属性或视图做出修改 
		   * 
		   */
		  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, 
		      Object arg2, ModelAndView arg3) throws Exception { 
		    // TODO Auto-generated method stub 
		    System.out.println("MyInterceptors postHandle");     
		  } 
		  
		  /** 
		   * 可以考虑作权限，日志，事务等等 
		   * 该方法在目标方法调用之前被调用； 
		   * 若返回TURE,则继续调用后续的拦截器和目标方法 
		   * 若返回FALSE,则不会调用后续的拦截器和目标方法 
		   * 
		   */
		  public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, 
		      Object arg2) throws Exception { 
		    // TODO Auto-generated method stub 
		    HandlerMethod handlerMethod = (HandlerMethod) arg2;  
		    System.out.println("MyInterceptors preHandle 调用方法名："+handlerMethod.getMethod().getName()); 
		    /* 
		      写一个日记类和Service，将需要的属性保存到数据库       
		    */
		  
		    return true; 
		  } 
		  
		} 
//<!-- 装配拦截器 -->
//<mvc:interceptors> 
//  <mvc:interceptor> 
//  <mvc:mapping path="/**"/> 
//    <bean class="util.Logger"></bean> 
//  </mvc:interceptor> 
//</mvc:interceptors>

