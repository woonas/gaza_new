package kr.gaza.myapp.admin;

import java.util.List;

import kr.gaza.myapp.aviation.airport.AirportVO;
import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.food.FoodVO;

public interface AdminInterface {
	public List<AirportVO> airportAllRecord();
	public List<FlightVO> flightAllRecord();
	public List<FoodVO> foodAllRecord();
}
