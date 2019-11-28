package kr.gaza.myapp.flight.payment;

import kr.gaza.myapp.flight.booking.PassengerVO;

public interface PaymentInterface {
//    public List<PaymentVO> getAllRecord();
//
//    public int paymentInsert(EventVO vo);
//
//    public int paymentUpdate(EventVO vo);
//
//    public int paymentDelete(int num);
//
//    public void paymentSelect(EventVO vo);
	
	public int creditInsert(CreditVO vo);
	
	public int passengerInsert(PassengerVO vo);
	
}
