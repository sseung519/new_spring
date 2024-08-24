package member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem")
public class MemController {
	@Autowired
	private MemService service;
	
	@GetMapping("/join")
	public String join() {
		service.addMember();
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute("m1") Member m) {
		System.out.println(m);
		service.addMember();
		return "index";
	}
	
	@GetMapping("/get")
	public String getMember() {
		service.getMember();
		return "member/info";
	}
	
	@GetMapping("/getall")
	public String getAll() {
		service.getAll();
		return "member/getall";
	}
	
	@GetMapping("/edit")
	public String edit() {
		service.editMember();
		return "member/edit";
	}
	
	@GetMapping("/del")
	public String del() {
		service.delMember();
		return "member/del";
	}
}
