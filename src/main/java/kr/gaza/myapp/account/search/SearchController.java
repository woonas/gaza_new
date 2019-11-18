package kr.gaza.myapp.account.search;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.gaza.myapp.account.member.MemberInterface;
import kr.gaza.myapp.account.member.MemberVO;

@Controller
public class SearchController {
	@Autowired
	SqlSession sqlSession;
	//아이디 찾기 페이지 이동.
	@RequestMapping("/JSP/account/search/idsearch")
	public ModelAndView idSearchView() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JSP/account/search/idsearch");
		return mav;
	}
	//아이디 찾기 ajax
	@RequestMapping(value="/JSP/account/search/idSearchOk", method=RequestMethod.POST, produces="application/text;charset=UTF-8")
	@ResponseBody
	public ModelAndView idSearchOk(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
			@RequestParam("year") String year, @RequestParam("month") String month, @RequestParam("day") String day,
			@RequestParam("emailAddress") String eAddr, @RequestParam("emailDomain") String eDomain,
			@RequestParam("emailDomainText") String eDomainText, HttpServletRequest req) {
		MemberInterface dao = sqlSession.getMapper(MemberInterface.class);
		MemberVO vo = new MemberVO();
		vo.setLastNameKor(lastName);
		vo.setFirstNameKor(firstName);
		vo.setBirthDay(year+"/"+month+"/"+day);
		if(eDomain.equals("")) {
			vo.setEmail(eAddr+"@"+eDomainText);
		}else {
			vo.setEmail(eAddr+"@"+eDomain);
		}
		String id = dao.findId(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("findId", id);
		mav.setViewName("JSP/account/search/searchResult");
		
		return mav;
	}
}
