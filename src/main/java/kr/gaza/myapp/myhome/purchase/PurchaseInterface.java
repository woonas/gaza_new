package kr.gaza.myapp.myhome.purchase;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseInterface {
	//flightRecord
	List<PurchaseVO> purchaseRecord(@Param("memberNum") int memberNum, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
