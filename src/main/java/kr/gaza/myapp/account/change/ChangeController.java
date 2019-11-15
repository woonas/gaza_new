package kr.gaza.myapp.account.change;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.gaza.myapp.account.member.MemberInterface;
import kr.gaza.myapp.account.member.MemberVO;

@Controller
public class ChangeController {
	@Autowired
	SqlSession sqlSession;
	//회원정보 페이지 이동.
	@RequestMapping(value="/JSP/account/change/account_info")
	public ModelAndView accountView(HttpServletRequest req) {
		MemberInterface dao = sqlSession.getMapper(MemberInterface.class);
		HttpSession ses = req.getSession();
		MemberVO vo = new MemberVO();
		vo.setMemberId((String)ses.getAttribute("memberId"));
		
		MemberVO vo1 = dao.getMember(vo);
		System.out.println(vo1.getMemberPwd());
		System.out.println(vo1.getMemberNameKor());
		ModelAndView mav = new ModelAndView();
		mav.addObject(vo1);
		mav.setViewName("JSP/account/change/account_info");
	    
	    return mav;
	}
}
