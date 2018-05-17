package util;

import java.io.IOException;

public class ValidateUrl
{

	String encode(String name,String email)
	{
		//添加 过期时间，24小时后链接失效
        long endTimes = System.currentTimeMillis()+1*24*3600*1000;
        String para = name+";"+email+";"+endTimes;
		String encode;
		try
		{
			encode = UrlUtil.getURLEncoderString(DesUtil.encrypt(para));
			return encode;
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	String[] decode(String encodedUrl)
	{
        String decode;
		try
		{
			decode = DesUtil.decrypt(UrlUtil.getURLDecoderString(encodedUrl));
	        String[] s=decode.split(";");
	        return s;
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
