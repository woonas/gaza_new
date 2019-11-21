package kr.gaza.myapp.board.noticeBoard;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	SqlSession sqlSession;
	//로그인 페이지로 이동.
	@RequestMapping("/JSP/board/noticeBoard/noticeBoard_list")
	public ModelAndView noticeBoardList(@RequestParam("pageNum") String pageNum, @RequestParam("tabType") int tabType) {
		
		NoticeBoardInterface dao = sqlSession.getMapper(NoticeBoardInterface.class);
		NoticeBoardVO vo = new NoticeBoardVO();
		
		if(pageNum!=null && !pageNum.equals("")) {
			vo.setPageNum(Integer.parseInt(pageNum));
			vo.setTabType(tabType);
		}
		List<NoticeBoardVO> lst;
		
		lst = dao.getAllRecord(vo.getTabType());
		ModelAndView mav = new ModelAndView();
		mav.addObject("tabType", tabType);
		mav.addObject("vo", vo);
		mav.addObject("lst", lst);
		mav.setViewName("JSP/board/noticeBoard/noticeBoard_list");
		return mav;
	}
}
