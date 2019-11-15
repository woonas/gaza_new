package kr.gaza.myapp.flight.booking;

import kr.gaza.myapp.aviation.product.ProductVO;

public interface BookingInterface {
    ProductVO getProductVO(BookingVO vo);
//    FlightVO getFlightVO(BookingVO vo);
//    SeatReserveVO getSeatReserveVO(BookingVO vo);
}
