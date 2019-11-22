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
		List<MyHomeVO> flightI = new ArrayList<>();
		
		for(int i=0;i<orderNumList.size();i++) {//3 orderNumList안에 orderNum을 3개 세팅
			
			int num = dao.flightList(orderNumList.get(i).getOrderNum());
			int array[] = new int[orderNumList.size()];
			array[i] = num;
			System.out.println("array"+i+"번="+array[i]);
			
			MyHomeVO flightInfoVO = dao.flightInfo(array[i]);//airplaneName, departTime, arriveTime, productNum을 세팅
			MyHomeVO flightInfoVO2 = dao.DepAri(flightInfoVO.getProductNum());//productNum3개를 넣어서 depart, arrive위치를 구해옴.
			System.out.println("도착지"+flightInfoVO2.getArrive());
			flightInfoVO.setArrive(flightInfoVO2.getArrive());
			flightInfoVO.setDepart(flightInfoVO2.getDepart());
			
			flightI.add(i, flightInfoVO);

		}//orderNumList
			
			
		System.out.println("flightI.size="+flightI.size());
		
		
		ModelAndView mav = new ModelAndView();
		if(vo!=null) {
			mav.addObject("vo",vo);
			mav.addObject("vo2",vo2);
			mav.addObject("vo3",vo3);
			mav.addObject("myNeedMiles",myNeedMiles);
			mav.addObject("flightI",flightI);
			mav.setViewName("JSP/mypage/myhome");
		}else {
			mav.setViewName("redirect:/");
		}

		
		return mav;
	}
}
