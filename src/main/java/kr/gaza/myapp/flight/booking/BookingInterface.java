package kr.gaza.myapp.flight.booking;

import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.product.ProductVO;
import org.apache.ibatis.annotations.Param;

public interface BookingInterface {
    ProductVO getProductVO(@Param("from") String from, @Param("to") String to);
    FlightVO getFlightVO(@Param("productNum") int productNum, @Param("flightDate") String flightDate);
    Integer getSeatLeft(@Param("flightNum") int flightNum);
}
