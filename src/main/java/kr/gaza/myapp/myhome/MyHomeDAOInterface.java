package kr.gaza.myapp.myhome;

import java.util.List;

public interface MyHomeDAOInterface {
	public MyHomeVO memberRecord(int memberNum);
	
	public List<ReservationVO> flightRecord(int memberNum);
}
