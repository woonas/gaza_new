package kr.gaza.myapp.board.noticeBoard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NoticeBoardInterface {
	// 목록
	public List<NoticeBoardVO> getAllRecord(@Param("param1") int param1);
	// 글쓰기
	public int noticeBoardInsert(NoticeBoardVO vo);
	// 수정
	public int noticeBoardUpdate(NoticeBoardVO vo);
	// 삭제
	public int noticeBoardDelete(int num);
	// 조회수
	public void noticeBoardHit(int num);
	// 총레코드수
	public int noticeBoardTotalRecord(int noticeType);
	// 글내용 보기
	public NoticeBoardVO noticeBoardSelect(NoticeBoardVO vo);
	// 글내용 보기에서 다음글, 이전글 제목가져오기
	public String noticeBoardGetTitles(int noticeNum, int tabType);
	// 마지막 레코드 번호 가져오기
	public int noticeBoardGetLastNum(int tabType);
	// 이전 글, 다음 글 가져오기
	public NoticeBoardVO noticeBoardGetPrevNext(int tabType, int noticeNum);
	
}
