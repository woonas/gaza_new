package kr.gaza.myapp.myhome;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyHomeContoller {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping(value="/JSP/mypage/myhome", method=RequestMethod.GET)
	public ModelAndView myHome(HttpServletRequest req) {
		MyHomeDAOInterface dao = sqlSession.getMapper(MyHomeDAOInterface.class);
		HttpSession sess = req.getSession();
		
		int memberNum = (int)sess.getAttribute("memberNum");
		MyHomeVO vo =  dao.memberRecord(memberNum); 
		MyHomeVO vo2 = dao.memberMiles(memberNum);
		
		MyHomeVO vo3 = dao.memberGrade(vo2.getMyMileage());
		MyHomeVO vo4 = dao.memberNeedMileage(vo2.getMyMileage());
		int myNeedMiles = (vo4.getNeedMileage() - vo2.getMyMileage());
		
		List<MyHomeVO> orderNumList = dao.orderNumList(memberNum); 
		List<MyHomeVO> flightL	= new ArrayList<>();
		List<MyHomeVO> flightI = new ArrayList<>();
		MyHomeVO flightDepAri = new MyHomeVO();
		
		for(int i=0;i<orderNumList.size();i++) {
			flightL = dao.flightList(orderNumList.get(i).getOrderNum());
			System.out.println(flightL.get(i).getFlightNum());
				flightI = dao.flightInfo(flightL.get(i).getFlightNum());
				System.out.println(flightI.get(i).getProductNum());
				flightDepAri = dao.DepAri(flightI.get(i).getProductNum());
				System.out.println(flightDepAri.getDepart());
			}
		
		ModelAndView mav = new ModelAndView();
		if(vo!=null) {
			mav.addObject("vo",vo);
			mav.addObject("vo2",vo2);
			mav.addObject("vo3",vo3);
			mav.addObject("myNeedMiles",myNeedMiles);
			mav.addObject("flightI",flightI);
			mav.addObject("flightDepAri",flightDepAri);
			mav.setViewName("JSP/mypage/myhome");
		}else {
			mav.setViewName("redirect:/");
		}

		
		return mav;
	}
}
