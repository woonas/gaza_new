package kr.gaza.myapp.board.noticeBoard;

public class NoticeBoardVO {
	
	private int num;
	private int noticeType;
	private String subject;
	private String content;
	private int hit;
	private String regdate;
	
	// 페이징
	private int pageNum=1; // 현재페이지
	private int onePageRecord=10; // 한페이지당 출력되는 레코드수
	private int totalPage; // 총 페이지 수
	private int totalRecord; // 총 레코드 수
	private int startPage; // 페이지 넘버 시작값
	private int onePageMax=10; // 한 페이지에 출력할 페이지 번호 수
	

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getOnePageMax() {
		return onePageMax;
	}
	public void setOnePageMax(int onePageMax) {
		this.onePageMax = onePageMax;
	}
}