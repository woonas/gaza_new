package kr.gaza.myapp.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminEditController {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/JSP/admin/admin_editForm")
	public String AdminEditForm() {
		return "JSP/admin/admin_editForm";
	}
	
	
	@RequestMapping(value="/JSP/admin/adminAddOk",method=RequestMethod.POST)
	public ModelAndView writeOk(AdminVO vo, HttpServletRequest req) {
		
		AdminEditInterface dao = sqlSession.getMapper(AdminEditInterface.class);
		int cnt = dao.InsertAdmin(vo);
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:admin_admin");
		}else {
			mav.setViewName("redirect:admin_editForm");
		}
		return mav;
	}
}
