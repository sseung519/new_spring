package member;

import org.springframework.stereotype.Repository;

@Repository
public class MemDao {
	public void insert() {
		System.out.println("dao insert");
	}
	
	public void select() {
		System.out.println("dao select");
	}
	
	public void selectAll() {
		System.out.println("dao selectAll");
	}
	
	public void update() {
		System.out.println("dao update");
	}
	
	public void delete() {
		System.out.println("dao delete");
	}
}
