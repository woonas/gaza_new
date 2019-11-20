package kr.gaza.myapp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AdminController {
	@RequestMapping("/JSP/admin/{var}")
	public String infoPage(@PathVariable String var) {
		return "JSP/admin/admin_" + var;
	}
	
}
