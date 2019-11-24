package kr.gaza.myapp.admin;

import java.util.List;

import kr.gaza.myapp.aviation.airplane.AirplaneVO;
import kr.gaza.myapp.aviation.airport.AirportVO;
import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.product.ProductVO;
import kr.gaza.myapp.board.noticeBoard.NoticeBoardVO;

public interface AdminEditInterface {
	public int insertAdmin(AdminVO vo);
	public int deleteAdmin(int num);
	public int insertAirport(AirportVO vo);
	public int deleteAirport(int num);
	public int insertAirplane(AirplaneVO vo);
	public List<ProductVO> productAllSelect(int num);
	public int insertProduct(ProductVO vo);
	public int updateProduct(ProductVO vo);
	public int deleteProduct(int num);
	public int insertFlight(FlightVO vo);
	public int deleteFlight(int num);
	public int insertNotice(NoticeBoardVO vo);
	public int deleteNotice(int num);
}
