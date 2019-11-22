package kr.gaza.myapp.admin;

import kr.gaza.myapp.aviation.airport.AirportVO;

public interface AdminEditInterface {
	public int insertAdmin(AdminVO vo);
	public int deleteAdmin(int num);
	public int insertAirport(AirportVO vo);
	
}
