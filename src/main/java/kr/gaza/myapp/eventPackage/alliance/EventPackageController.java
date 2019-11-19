package kr.gaza.myapp.eventPackage.alliance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
	public class EventPackageController {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/JSP/eventNpackage/event_list")
	public ModelAndView EventList() {
		EPallianceInterface dao = sqlSession.getMapper(EPallianceInterface.class);
		List<AllianceVO> lst = dao.getAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/eventNpackage/event_list");
		return mav;
	}
	
	@RequestMapping("/JSP/eventNpackage/event_detail")
	public ModelAndView DetailList(HttpServletRequest req) {
		EPallianceInterface dao = sqlSession.getMapper(EPallianceInterface.class);
		AllianceVO vo = new AllianceVO();
		vo.setEventNum(Integer.parseInt(req.getParameter("eventNum")));
		AllianceVO vo2 = dao.eventSelect(vo);
		
		List<AllianceVO> lst = dao.getAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",vo2);
		mav.addObject("lst",lst);
		mav.setViewName("JSP/eventNpackage/event_detail");
		
		return mav;
	}
}
