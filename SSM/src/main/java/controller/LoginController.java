package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import util.IsEmail;
import util.JavaMailUtils;
import util.SHA1;
import util.UrlUtil;

@Controller
@RequestMapping("/Login")
public class LoginController {
	
	@Autowired
	private SimpleLogService simplelogservice;
	@Autowired
	private UserService userService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	private SHA1 sha1=new SHA1();
	//呈现登陆表单
	@RequestMapping("/")
	public String index()
	{
		return "login/login";
	}

//	处理登陆表单
	@RequestMapping(value="/Login",method=RequestMethod.POST)
	public String login(User user,String code,String id,RedirectAttributes ra)
	{
		HttpSession session=request.getSession();
//		判断账号是否存在，不存在，则提示有错
		User ae=userService.accountExist(id);
		String sessioncode=(String)session.getAttribute("code");
		if(code.equalsIgnoreCase(sessioncode)!=true)
		{
			ra.addFlashAttribute("errormsg", "验证码错误");
			return "redirect:/Login/";
		}
		int times1=simplelogservice.faliLoginInOneDay(id);
		System.out.println(times1);
		if(times1>5)
		{
			ra.addFlashAttribute("errormsg2","已错误登陆5次,24小时内不能登陆");
			return "redirect:/Login/";
		}
		if(ae==null)
		{
			user.setName(id);
			System.out.println("not exist");
			SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"账户不存在",user.getName());
			System.out.println(user);
			simplelogservice.addLog(sl);
			ra.addFlashAttribute("errormsg", "用户名或密码错误");
			return "redirect:/Login/";
		}
//		若存在，判断密码是否正确，不正确，提示有错
		else
		{
			boolean pc=userService.pwdCorrect(ae, user);
			if(pc==false)
			{
				user.setName(id);

				System.out.println("not correct");
				SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"用户名或密码错误",user.getName());
				simplelogservice.addLog(sl);
				ra.addFlashAttribute("errormsg", "用户名或密码错误");
				String times=String.valueOf(simplelogservice.faliLoginInOneDay(user.getName()));
				ra.addFlashAttribute("errormsg2","已错误登陆"+times+"次");
				return "redirect:/Login/";
			}
//			若正确，判断账号是否已经激活，若未激活，转到激活页面
			else
			{
				if(ae.getActivated()!=1)
				{
					user.setName(id);

					SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"账号未激活",user.getName());
					simplelogservice.addLog(sl);
					session.setAttribute("user", ae);
					return "login/notActivated";
				}
				else
				{
					session.setAttribute("user", ae);
					return "redirect:/Login/welcome";
				}
			}
		}

	}


	
//	登陆成功页面
	@RequestMapping("/welcome")
	public String welcome()
	{

		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"登陆成功",user.getName());
		simplelogservice.addLog(sl);
		System.out.println(user);
		if(user!=null)
			return "login/welcome";
		else 
		{
			return "redirect:/Login/";
		}
	}
	
//	登出
	@RequestMapping("/Logout")
	public String logout(User user)//这里是否有user对象？
	{
		SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"登出",user.getName());
		simplelogservice.addLog(sl);
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/Login/";
	}
	
//	转到忘记密码页面
	@RequestMapping("/forgetpwd")
	public String forgetPwd()
	{
		return "redirect:/forgetpwd/";
	}

//	从链接中获取用户名，校验，转到重置密码表单
	@RequestMapping("/reset")
	public String reset() throws IOException, Exception
	{

		request.setCharacterEncoding("utf-8");
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
        User user=new User();
        user.setName(s[0]);
        request.getSession().setAttribute("user", user);
        long now=System.currentTimeMillis();
        if(now>Long.valueOf(s[2]))
        {
        	//链接已失效
        	return null;
        }
        return "login/reset";
	}
	
//	处理重置密码表单
	@RequestMapping("/resetpwd")
	public String resetpwd(User user,RedirectAttributes ra)
	{
		SimpleLog sl=new SimpleLog(new java.sql.Timestamp(System.currentTimeMillis()),request.getRemoteAddr(),"重置密码",user.getName());
		simplelogservice.addLog(sl);
		User user1=(User)request.getSession().getAttribute("user");
		String pwd=user.getPwd();
		user.setPwd(sha1.encode(user.getPwd()+user1.getName()));
		user.setName(user1.getName());
		int rs=userService.resetPwd(user);
		if(rs!=0)
		{
			return "redirect:/Login/";
		}
		else
		{
			ra.addFlashAttribute("errormsg","失败，请重试");
			return "login/reset";
		}
	}
	@RequestMapping("/activate")
	public String activate(HttpServletRequest request) throws Exception
	{
		User user=(User)request.getSession().getAttribute("user");
		System.out.println(user);
		int rs=Activate.sendmail(user);
		System.out.println(rs);
		if(rs==1)
		{
			return "login/login";
		}
		else
		{
			return "register/sendFailure";
		}
	}
//	test ajax
//	@RequestMapping("/ajax")
//	@ResponseBody
//	public void ajax(String username) {
//		System.out.println(username+"ajax");
//	}
//	@RequestMapping("/ajaxjson")
//	@ResponseBody
//	public List<String> ajaxjson() {
//		List<String> ls=new ArrayList<String>();
//		ls.add("hello");ls.add("ajax");ls.add("json");
//		return ls;
//	}
}
