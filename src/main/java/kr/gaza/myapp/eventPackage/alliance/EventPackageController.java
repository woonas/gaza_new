package kr.gaza.myapp.eventPackage.alliance;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventPackageController {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/JSP/eventNpackage/best_package")
	public ModelAndView EventList() {
		EPallianceInterface dao = sqlSession.getMapper(EPallianceInterface.class);
		List<AllianceVO> lst = dao.getAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("/JSP/eventNpackage/best_package");
		return mav;
	}
}
