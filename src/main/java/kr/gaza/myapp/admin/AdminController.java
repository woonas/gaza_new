package kr.gaza.myapp.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.gaza.myapp.aviation.airport.AirportVO;
import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.food.FoodVO;
import kr.gaza.myapp.aviation.personType.peopleVO;
import kr.gaza.myapp.aviation.product.ProductVO;
import kr.gaza.myapp.aviation.seatType.SeatVO;
import kr.gaza.myapp.aviation.seatreserve.SeatReserveVO;
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
	
	@RequestMapping("/JSP/admin/admin_flight")
	public ModelAndView AdminFlight() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<FlightVO> lst = dao.flightAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_flight");
		return mav;
	}
	
	@RequestMapping("/JSP/admin/admin_food")
	public ModelAndView AdminFood() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<FoodVO> lst = dao.foodAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_food");
		return mav;
	}
	
	@RequestMapping("/JSP/admin/admin_people")
	public ModelAndView AdminPeople() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<peopleVO> lst = dao.peopleAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_people");
		return mav;
	}
	
	@RequestMapping("/JSP/admin/admin_product")
	public ModelAndView AdminProduct() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<ProductVO> lst = dao.productAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_product");
		
		return mav;
	}
	
	@RequestMapping("/JSP/admin/admin_seatReserve")
	public ModelAndView AdminSeatReserve() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<SeatReserveVO> lst = dao.seatReserveAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_seatReserve");
		return mav;
	}
	
	@RequestMapping("/JSP/admin/admin_seat")
	public ModelAndView AdminSeat() {
		AdminInterface dao = sqlSession.getMapper(AdminInterface.class);
		List<SeatVO> lst = dao.seatAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lst",lst);
		mav.setViewName("JSP/admin/admin_seat");
		return mav;
	}
	
}
