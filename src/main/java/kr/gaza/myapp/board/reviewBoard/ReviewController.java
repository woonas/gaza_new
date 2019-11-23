package kr.gaza.myapp.board.reviewBoard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.gaza.myapp.board.reviewBoard.ReviewBoardInterface;
import kr.gaza.myapp.board.reviewBoard.ReviewBoardVO;

@Controller
public class ReviewController {
	@Autowired
	SqlSession sqlSession;
	//이용후기 리스트 페이지로 이동.
	@RequestMapping("/JSP/board/reviewBoard/reviewBoard_list")
	public ModelAndView reviewBoardList(@RequestParam("pageNum") String pageNum, @RequestParam("reviewType") int reviewType,
			HttpServletRequest req) {

		ReviewBoardInterface dao = sqlSession.getMapper(ReviewBoardInterface.class);
		ReviewBoardVO vo = new ReviewBoardVO();
		
		int totalRecord = dao.reviewBoardTotalRecord(reviewType);
		
		HttpSession sess = req.getSession();
		String writer = (String)sess.getAttribute("memberId");
		
		if(writer == null) {
			writer = "0";
		}
		
		if(pageNum!=null && !pageNum.equals("")) {
			vo.setPageNum(Integer.parseInt(pageNum));
			vo.setReviewType(reviewType);
			vo.setTotalRecord(totalRecord);
			vo.setWriter(writer);
		}
		List<ReviewBoardVO> lst;
		
		lst = dao.getAllRecord(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewType", reviewType);
		mav.addObject("vo", vo);
		mav.addObject("lst", lst);
		
		mav.setViewName("JSP/board/reviewBoard/reviewBoard_list");
		
		return mav;
	}
	//이용후기 글 보기 페이지
	@RequestMapping("/JSP/board/reviewBoard/reviewBoard_view")
	public ModelAndView noticeDetailView(@RequestParam("reviewNum") int reviewNum, @RequestParam("pageNum") int pageNum,
			@RequestParam("reviewType") int reviewType) {
		ReviewBoardInterface dao = sqlSession.getMapper(ReviewBoardInterface.class);
		
		ReviewBoardVO vo = new ReviewBoardVO();
		vo.setReviewNum(reviewNum);
		
		ReviewBoardVO vo2 = dao.reviewBoardSelect(vo);
		vo = dao.reviewBoardGetPrevNext(reviewType, reviewNum);
		vo2.setPageNum(pageNum);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JSP/board/reviewBoard/reviewBoard_view");
		mav.addObject("vo", vo2);
		mav.addObject("reviewType", reviewType);
		mav.addObject("prevNum", vo.getPrevNum());
		mav.addObject("prevSubject", vo.getPrevSubject());
		mav.addObject("nextNum", vo.getNextNum());
		mav.addObject("nextSubject", vo.getNextSubject());
		return mav;
	}
}
