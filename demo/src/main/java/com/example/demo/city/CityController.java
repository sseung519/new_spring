package com.example.demo.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("city")
public class CityController {
	@Autowired
	private CityService service;
	
	@GetMapping("/detail")
	public String detail(int id, Model m) { //Model: 뷰 페이지에 전달할 값을 받는 용도
		City city = service.getCity(id);
		if(city == null) {
            m.addAttribute("msg", "없는 아이디");
        } else {
            m.addAttribute("city", city); //addAttribute("뷰페이지에서 부를 이름", "전달할 값")
        }
		System.out.println(city);
		return "city/detail";
	}

    @GetMapping("/info")
    public String info() {
        return "city/info";
    }

    @GetMapping("/add")
    public String add(Model model) {
        ArrayList<String> list = new ArrayList<>();
        list.add("KOR");
        list.add("USA");
        list.add("ENG");
        list.add("ARG");
        list.add("NLD");
        model.addAttribute("list", list);
        return "city/add";
    }
    @PostMapping("/add")
    public String add(City city) {
        service.addCity(city);
        return "index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("cities", service.getAllCity());
        return "city/list";
    }

    @PostMapping("/edit")
    public String edit(City city) {
        service.editCity(city);
        return "index";
    }

    @GetMapping("/del")
    public String del(int id) {
        service.deleteCity(id);
        return "index";
    }

}
