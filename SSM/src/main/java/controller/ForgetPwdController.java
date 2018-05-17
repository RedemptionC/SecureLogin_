package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.SimpleLog;
import entity.User;
import mapper.ISimpleLogMapper;
import service.SimpleLogService;
import service.UserService;
import util.Activate;
import util.DesUtil;
import util.JavaMailUtils;
import util.UrlUtil;

@Controller
@RequestMapping("/forgetpwd")
public class ForgetPwdController
{
	@Autowired
	private UserService userService;
	@Autowired
	private SimpleLogService simplelogservice;
	
	@RequestMapping("/")
	public String forgetPwd()
	{
		return "login/forgetpwd";
	}
	
//	处理忘记密码表单，发送重置密码链接
	@RequestMapping(value="/forgetpwd1",method=RequestMethod.POST)
	public String forgetPwd1(User user,RedirectAttributes ra,HttpServletRequest request) throws Exception
	{
		SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"忘记密码",user.getName());
		simplelogservice.addLog(sl);
		System.out.println(user);
		User u=userService.findUserByNameAndEmail(user);
		System.out.println(u);
		if(u!=null)
		{
	        long endTimes = System.currentTimeMillis()+1*24*3600*1000;
	        System.out.println(endTimes);
	        String para = u.getName()+";"+u.getEmail()+";"+endTimes;
			String encode = UrlUtil.getURLEncoderString(DesUtil.encrypt(para));
			String url="http://118.24.121.62:8080/SSM/Login/reset?vc="+encode;
	    	int rs=JavaMailUtils.sendEmail("smtp.qq.com", "842391412@qq.com", "ztdrepwjemvfbcia", "842391412@qq.com", new String[]{
	    			user.getEmail() //这里就是一系列的收件人的邮箱了
	    	}, "点击链接重置密码","<a href="+url+">"+"点击链接重置密码"+"</a>","text/html;charset=utf-8");
			System.out.println(rs);
			if(rs==1)
			{
				
				return "redirect:/Login/";
			}
			else
			{
				return "redirect:/register/sendFailure";
			}
		}
		//查无此人
		ra.addFlashAttribute("errormsg", "用户不存在");
		return "login/forgetpwd";
	}
}
