package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.SimpleLog;
import entity.User;
import service.SimpleLogService;
import service.UserService;
import util.Activate;
import util.DesUtil;
import util.JavaMailUtils;
import util.SHA1;
import util.UrlUtil;

@Controller
@RequestMapping("/register")
public class RegisterController
{
	@Autowired
	private UserService userService;
	@Autowired
	private SimpleLogService simplelogservice;
	private SHA1 sha1=new SHA1();
	
	@RequestMapping("/")
	public String index()
	{
		return "register/register";
	}
	@RequestMapping("/Register")
	public String register(User user,String code,HttpServletRequest request,RedirectAttributes ra) throws Exception
	{
		SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"注册",user.getName());
		simplelogservice.addLog(sl);
		HttpSession session=request.getSession();	
		System.out.println(user);
		if(user.getName()==null)
		{
			System.out.println("getname=null");
			return "redirect:/Login/";			
		}
		if (userService.findUserByName(user.getName())!=null)
		{
			ra.addFlashAttribute("errormsg", "用户名已存在");
			return "redirect:/register/";
		}
		else if (userService.findUserByEmail(user.getEmail())!=null)
		{
			ra.addFlashAttribute("errormsg", "邮箱已注册");
			return "redirect:/register/";
		}
		else
		{
			int res=Activate.sendmail(user);
			System.out.println(user);
	    	if (res==0)
			{
				return "redirect:/register/sendFailure";
			}
			user.setPwd(sha1.encode(user.getPwd()+user.getName()));
			userService.register(user);
	    	request.getSession().setAttribute("user", user);
			return "/register/checkmail";

		}

	}
	@RequestMapping("/sendFailure")
	public String sendfailure()
	{
		return "register/sendFailure";
	}
//	@RequestMapping("/ifNameExist")
//	public String ifNameExist(String username)
//	{
//		System.out.println(username);
//		if (userService.findUserByName(username)!=null)
//		{
//			return "已存在";
//		}
//		return null;
//	}
	
	@RequestMapping(value="/validate",method=RequestMethod.GET)
	public String validate(HttpServletRequest request) throws IOException, Exception
	{
		String vc=request.getParameter("vc");
		System.out.println(vc);
		String rs1=UrlUtil.getURLDecoderString(vc);
		System.out.println(rs1);
        String decode=DesUtil.decrypt(vc);
        System.out.println(decode);
        String[] s=decode.split(";");
        if(s.length<3)
        {
        	//解码失败
        	return null;
        	//设置编码为utf8后应该不会出错了
        }
        String name=s[0];
        long now=System.currentTimeMillis();
        if(now>Long.valueOf(s[2]))
        {
        	//链接已失效
        	return null;
        }

        userService.setState(1,name);
        return "redirect:/Login/";
        
	}
}
