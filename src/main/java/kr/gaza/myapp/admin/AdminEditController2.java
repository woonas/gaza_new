package kr.gaza.myapp.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminEditController2 {
	@Autowired
	SqlSession sqlSession;
	//seat 수정폼 이동
	@RequestMapping("/JSP/admin/admin_seatForm")
	public String AdminEditForm() {
		return "JSP/admin/admin_seatForm";
	}
	//seatType 추가 
		@RequestMapping(value="/JSP/admin/seatAddOk",method=RequestMethod.POST)
		public ModelAndView SeatAddOk(AdminVO2 vo, HttpServletRequest req) {
			
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.insertSeat(vo);
				
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_seat");
			}else {
				mav.setViewName("redirect:admin_seatForm");
			}
			return mav;
		}
		//seatType 삭제
		@RequestMapping("/JSP/admin/seatDelOk")
		public ModelAndView SeatDelete(@RequestParam("seatNum") int seatNum) {
			
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.deleteSeat(seatNum);
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_seat");
			}else{
				mav.addObject("seatNum",seatNum);
				mav.setViewName("redirect:admin");
			}
			return mav;
		}
		//seatReserve 수정폼 이동
		@RequestMapping("/JSP/admin/admin_seatReserveForm")
		public String SeatReserveEditForm() {
			return "JSP/admin/admin_seatReserveForm";
		}
		//seatReserve 추가 
		@RequestMapping(value="/JSP/admin/seatReserveAddOk",method=RequestMethod.POST)
		public ModelAndView SeatReserveAddOk(AdminVO2 vo, HttpServletRequest req) {
			
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.insertSeatReserve(vo);
				
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_seatReserve");
			}else {
				mav.setViewName("redirect:admin_seatReserveForm");
			}
			return mav;
		}

		//seatReserve 삭제
		@RequestMapping("/JSP/admin/seatReserveDelOk")
		public ModelAndView SeatReserveDelete(@RequestParam("seatReserveNum") int seatReserveNum) {
			
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.deleteSeatReserve(seatReserveNum);
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_seatReserve");
			}else{
				mav.addObject("seatReserveNum",seatReserveNum);
				mav.setViewName("redirect:admin");
			}
			return mav;
		}
		//people 수정폼 이동
		@RequestMapping("/JSP/admin/admin_peopleForm")
		public String PeopleEditForm() {
			return "JSP/admin/admin_peopleForm";
		}
		//people 추가
		@RequestMapping(value="/JSP/admin/peopleAddOk",method=RequestMethod.POST)
		public ModelAndView PeopleAddOk(AdminVO2 vo, HttpServletRequest req) {
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.insertPeople(vo);
			
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_people");
			}else {
				mav.setViewName("redirect:admin_peopleForm");
			}
			
			return mav;
		}
		//people 삭제
		@RequestMapping("/JSP/admin/peopleDelOk")
		public ModelAndView PeopleDelete(@RequestParam("peopleNum") int peopleNum) {
			
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.deletePeople(peopleNum);
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_people");
			}else{
				mav.addObject("peopleNum",peopleNum);
				mav.setViewName("redirect:admin");
			}
			return mav;
		}
		//food 수정폼 이동
		@RequestMapping("/JSP/admin/admin_foodForm")
		public String FoodEditForm() {
			return "JSP/admin/admin_foodForm";
		}
		//food 추가
		@RequestMapping(value="/JSP/admin/foodAddOk",method=RequestMethod.POST)
		public ModelAndView FoodAddOk(AdminVO2 vo, HttpServletRequest req) {
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.insertFood(vo);
			
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_food");
			}else {
				mav.setViewName("redirect:admin_foodForm");
			}
			
			return mav;
		}
		//food 삭제
		@RequestMapping("/JSP/admin/foodDelOk")
		public ModelAndView FoodDelete(@RequestParam("foodNum") int foodNum) {
			
			AdminEditInterface2 dao = sqlSession.getMapper(AdminEditInterface2.class);
			int cnt = dao.deleteFood(foodNum);
			ModelAndView mav = new ModelAndView();
			if(cnt>0) {
				mav.setViewName("redirect:admin_food");
			}else{
				mav.addObject("foodNum",foodNum);
				mav.setViewName("redirect:admin");
			}
			return mav;
		}
		
		
		
		
		
		
		
		
		
		
		
}
