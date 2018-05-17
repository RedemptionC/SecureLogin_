package util;

import entity.User;

public class Activate
{
	public static int sendmail(User user) throws Exception
	{
//		在这里，发送邮件，点击邮件里的链接激活
        long endTimes = System.currentTimeMillis()+1*24*3600*1000;
        System.out.println(endTimes);
        String para = user.getName()+";"+user.getEmail()+";"+endTimes;
		String encode = UrlUtil.getURLEncoderString(DesUtil.encrypt(para));
		String url="http://118.24.121.62:8080/SSM/register/validate?vc="+encode;
    	int res=JavaMailUtils.sendEmail("smtp.qq.com", "842391412@qq.com", "ztdrepwjemvfbcia", "842391412@qq.com", new String[]{
    			user.getEmail() //这里就是一系列的收件人的邮箱了
    	}, "点击链接激活账户", "<a href="+url+">"+"点击链接激活账户"+"</a>","text/html;charset=utf-8");
    	return res;
	}
	

}
