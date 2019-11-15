package kr.gaza.myapp.myhome;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyHomeContoller {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/JSP/mypage/myhome")
	public ModelAndView myHome(HttpServletRequest req) {
		MyHomeDAOInterface dao = sqlSession.getMapper(MyHomeDAOInterface.class);
		MyHomeVO vo=  dao.memberRecord(3939);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JSP/mypage/myhome");
		
		return mav;
	}
}
