package kr.gaza.myapp.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.gaza.myapp.aviation.airport.AirportVO;

@Controller
public class AdminEditController {
	@Autowired
	SqlSession sqlSession;
	//어드민 수정폼 이동
	@RequestMapping("/JSP/admin/admin_editForm")
	public String AdminEditForm() {
		return "JSP/admin/admin_editForm";
	}
	
	//어드민 추가 
	@RequestMapping(value="/JSP/admin/adminAddOk",method=RequestMethod.POST)
	public ModelAndView AdminAddOk(AdminVO vo, HttpServletRequest req) {
		
		AdminEditInterface dao = sqlSession.getMapper(AdminEditInterface.class);
		int cnt = dao.insertAdmin(vo);
			
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:admin_admin");
		}else {
			mav.setViewName("redirect:admin_editForm");
		}
		return mav;
	}
	
	
	@RequestMapping("/JSP/admin/admin_airportForm")
	public String AdminAirportForm() {
		return "JSP/admin/admin_airportForm";
	}
	
	//에어포트 추가
	@RequestMapping(value="/JSP/admin/airpotAddOk",method=RequestMethod.POST)
	public ModelAndView AdminAirpotAddOk(AirportVO vo, HttpServletRequest req) {
		
		AdminEditInterface dao = sqlSession.getMapper(AdminEditInterface.class);
		int cnt = dao.insertAirport(vo);
			
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:admin_airport");
		}else {
			mav.setViewName("redirect:admin_editForm");
		}
		return mav;
	}
	
	//어드민 삭제
	@RequestMapping("/JSP/admin/del")
	public ModelAndView AdminDelete(@RequestParam("adminNum") int adminNum) {
		AdminEditInterface dao = sqlSession.getMapper(AdminEditInterface.class);
		int cnt = dao.deleteAdmin(adminNum);
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0) { // 삭제성공
			mav.setViewName("redirect:admin_admin");
		}else { //삭제 실패
			mav.addObject("adminNum",adminNum);
			mav.setViewName("redirect:admin");
		}
		return mav;
	}
}
