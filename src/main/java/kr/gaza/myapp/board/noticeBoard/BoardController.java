package kr.gaza.myapp.board.noticeBoard;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	@Autowired
	SqlSession sqlSession;
	//로그인 페이지로 이동.
	@RequestMapping("/JSP/board/noticeBoard/noticeBoard_list")
	public String noticeBoardView() {
		
		return "JSP/board/noticeBoard/noticeBoard_list";
	}
}
