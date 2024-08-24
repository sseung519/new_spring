package com.example.demo.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
	@Autowired
	private CityDao dao;
	
	public City getCity(int id) {
		return dao.select(id);
	}
    public void addCity(City city) {
        dao.insert(city);
    }

    public List<City> getAllCity(){
        return dao.selectAll();
    }

    public void editCity(City city) {
        dao.update(city);
    }

    public void deleteCity(int id) {
        dao.delete(id);
    }
}
