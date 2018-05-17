package controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.CaptchaUtil;

@Controller
@RequestMapping("/checkcode")
public class Captcha
{
    @RequestMapping("/")  
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {  
    	// 通知浏览器不要缓存  
        response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "-1");  
        CaptchaUtil util = CaptchaUtil.Instance();  
        // 将验证码输入到session中，用来验证  
        String code = util.getString();  
//        System.out.println(code);
        request.getSession().setAttribute("code", code);  
        // 输出到web页面  
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());  
    }  
}
