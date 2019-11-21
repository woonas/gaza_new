package kr.gaza.myapp.myhome;

import java.util.List;

public interface MyHomeDAOInterface {
	public MyHomeVO memberRecord(int memberNum);
	
	public MyHomeVO memberMiles(int memberNum);
	
	public MyHomeVO memberGrade(int mymileage);
	
	public MyHomeVO memberNeedMileage(int mymileage);
	
	public List<MyHomeVO> orderNumList(int memberNum);
	
	public List<MyHomeVO> flightList(int orderNum);
	
	public List<MyHomeVO> flightInfo(int flightNum);
	
	public MyHomeVO DepAri(int productNum);
}
