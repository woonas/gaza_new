package kr.gaza.myapp.eventPackage.alliance;

import java.util.List;

public interface EPallianceInterface {
	public List<AllianceVO> getAllRecord();
	
	public int eventInsert(AllianceVO vo);
	
	public int eventUpdate(AllianceVO vo);
	
	public int eventDelete(int num);

	public void eventSelect(AllianceVO vo);
}
