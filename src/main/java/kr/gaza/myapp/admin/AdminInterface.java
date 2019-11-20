package kr.gaza.myapp.admin;

import java.util.List;

import kr.gaza.myapp.aviation.airport.AirportVO;
import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.food.FoodVO;
import kr.gaza.myapp.aviation.personType.peopleVO;
import kr.gaza.myapp.aviation.product.ProductVO;
import kr.gaza.myapp.aviation.seatType.SeatVO;
import kr.gaza.myapp.aviation.seatreserve.SeatReserveVO;

public interface AdminInterface {
	public List<AirportVO> airportAllRecord();
	public List<FlightVO> flightAllRecord();
	public List<FoodVO> foodAllRecord();
	public List<peopleVO> peopleAllRecord();
	public List<ProductVO> productAllRecord();
	public List<SeatReserveVO> seatReserveAllRecord();
	public List<SeatVO> seatAllRecord();
}
