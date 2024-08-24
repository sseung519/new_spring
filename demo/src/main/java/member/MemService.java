package member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemService {
	@Autowired
	private MemDao dao;
	
	public void addMember() {
		dao.insert();
		System.out.println("service addMember");
	}
	
	public void getMember() {
		dao.select();
		System.out.println("service getMember");
	}
	
	public void getAll() {
		dao.selectAll();
		System.out.println("service getAll");
	}
	
	public void editMember() {
		dao.update();
		System.out.println("service editMember");
	}
	
	public void delMember() {
		dao.delete();
		System.out.println("service delMember");
	}
}

