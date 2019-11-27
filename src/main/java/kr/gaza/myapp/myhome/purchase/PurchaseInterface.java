package kr.gaza.myapp.myhome.purchase;

import java.util.List;

public interface PurchaseInterface {
	//flightRecord 가져오기.
	public List<PurchaseVO> purchaseRecord(int memberNum,String startDate);
}
