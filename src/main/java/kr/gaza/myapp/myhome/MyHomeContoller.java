package kr.gaza.myapp.myhome;

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
		MyHomeVO v = new MyHomeVO();
		
		int memberNum = (int)sess.getAttribute("memberNum");
		MyHomeVO vo =  dao.memberRecord(memberNum); 
		MyHomeVO vo2 = dao.memberMiles(memberNum);
		
		MyHomeVO vo3 = dao.memberNeedMiles(v.getMyMileage());
		
		ModelAndView mav = new ModelAndView();
		if(vo!=null) {
			mav.addObject("vo",vo);
			mav.addObject("vo2",vo2);
			mav.addObject("vo3",vo3);
			mav.setViewName("JSP/mypage/myhome");
		}else {
			mav.setViewName("redirect:/");
		}

		
		return mav;
	}
}
