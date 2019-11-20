package kr.gaza.myapp.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.gaza.myapp.aviation.airport.AirportVO;
@Controller
public class AdminController {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/JSP/admin/admin_airport")
	public ModelAndView AdminAirport() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<AirportVO> lst = dao.airportAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_airport");
		return mav;
	}
	
}
