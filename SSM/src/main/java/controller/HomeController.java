package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {
	@RequestMapping("/")
	public String index(){
		//对应home文件夹下的index.jsp
		return "home/index";
	}
}
