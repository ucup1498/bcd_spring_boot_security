/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc40.crud.controllers;

import com.mcc40.crud.entities.Country;
import com.mcc40.crud.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mochamad Yusuf
 */
@Controller
@RequestMapping("country")
public class CountryController {

    CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @RequestMapping("") //localhost:8081/
    public String countryGetAll() {
        for (Country country : service.getAllCountries()) {
            System.out.println(country.getId()+ " | " + country.getName());
        }
        return "index"; //index.html
    }

    @RequestMapping("find")
    public String getCountryById(String id) {
        System.out.println(service.getById(id).getId()+ " | "
                + service.getById(id).getName());
        return "index"; //index.html
    }

    @RequestMapping("save")
    public String countrySave(String id, String name) {
        Country country = new Country();
        country.setId(id);
        country.setName(name);
        System.out.println(service.insert(country));
        return "index"; //index.html
    }
    
    @RequestMapping("delete")
    public String deleteCountryById(String id) {
        System.out.println("Mencoba menghapus: " + service.getById(id).getName());
        System.out.println(service.deleteById(id) ? "Delete berhasil" : "Delete gagal");
        return "index"; //index.html
    }
}
