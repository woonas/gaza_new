package kr.gaza.myapp.eventPackage.travel;

import java.util.List;

public class EPtravelInterface {
	public List<TravelVO> getAllRecord();
	
	public List<TravelVO> getBestRecord(int onePageRecord);
	
	public List<TravelVO> getSaleRecord(int onePageRecord);
}
