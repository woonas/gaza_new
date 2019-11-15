package kr.gaza.myapp.myhome;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyHomeContoller {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/JSP/mypage/myhome")
	public ModelAndView myHome(HttpServletRequest req) {
		MyHomeDAOInterface dao = sqlSession.getMapper(MyHomeDAOInterface.class);
		MyHomeVO vo=  dao.memberRecord(memberNum); 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JSP/mypage/myhome");
		
		return mav;
	}
}
