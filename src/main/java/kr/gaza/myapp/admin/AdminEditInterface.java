package kr.gaza.myapp.admin;

import kr.gaza.myapp.aviation.airplane.AirplaneVO;
import kr.gaza.myapp.aviation.airport.AirportVO;
import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.product.ProductVO;

public interface AdminEditInterface {
	public int insertAdmin(AdminVO vo);
	public int deleteAdmin(int num);
	public int insertAirport(AirportVO vo);
	public int deleteAirport(int num);
	public int insertAirplane(AirplaneVO vo);
	public int insertProduct(ProductVO vo);
	public int deleteProduct(int num);
	public int insertFlight(FlightVO vo);
	public int deleteFlight(int num);
}
