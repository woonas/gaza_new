package kr.gaza.myapp.admin;

public interface AdminEditInterface2 {
	public int insertSeat(AdminVO2 vo);
	public int deleteSeat(int num);
	public int insertSeatReserve(AdminVO2 vo);
	public int deleteSeatReserve(int num);
	public int insertPeople(AdminVO2 vo);
	public int deletePeople(int num);
}
