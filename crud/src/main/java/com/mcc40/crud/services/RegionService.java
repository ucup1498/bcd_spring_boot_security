/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc40.crud.services;

import com.mcc40.crud.entities.Country;
import com.mcc40.crud.entities.Department;
import com.mcc40.crud.entities.Employee;
import com.mcc40.crud.entities.Location;
import com.mcc40.crud.entities.Region;
import com.mcc40.crud.repositories.RegionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aqira
 */
@Service
public class RegionService {

    RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    //get all 
    public List<Region> getAllRegion() {
        return regionRepository.getAllSql();
    }

    //get by id
    public Region getByIdRegion(int id) {
        return regionRepository.findById(id).get();
    }

    //insert
    public String saveRegion(Region region) {
        String result = null;
        Optional<Region> optionalRegion = regionRepository.findById(region.getRegionId());
        try {
            if (optionalRegion.isPresent() == false) {
                regionRepository.save(region);
                result = "Inserted";
            } else if (optionalRegion.get().equals(true)) {
                Region oldRegion = optionalRegion.get();
                oldRegion.setRegionName(region.getRegionName());
                region = oldRegion;
                result = "Updated";
            }
        } catch (Exception e) {
            result = "Unknown Error";
            System.out.println(e.toString());
        }
        regionRepository.save(region);
        return result;
    }

    //delete
    public boolean deleteRegion(int id) {
        regionRepository.deleteById(id);
        return !regionRepository.findById(id).isPresent();
    }

    public void getJobs(int id) {

        Region region = regionRepository.findById(id).get();
        List<Country> countryList = new ArrayList<>(region.getCountriesCollection());
        for (Country country : countryList) {
            
            List<Location> locationList = new ArrayList<>(country.getLocationsCollection());
            for (Location location : locationList) {
                
                List<Department> departmentList = new ArrayList<>(location.getDepartmentsCollection());
                for (Department department : departmentList) {
                    
                    List<Employee> employeeList = new ArrayList<>(department.getEmployeesCollection());
                    for (Employee employee : employeeList) {
                        
                        System.out.print(employee.getJobId().getJobTitle() + " | ");
                        System.out.print(employee.getFirstName() + " | ");
//                        System.out.print(department.getDepartmentName()+ " | ");
//                        System.out.print(location.getLocationId() + " | ");
//                        System.out.print(country.getCountryName()+ " | ");
                        System.out.println(region.getRegionName());
                    }
                }
            }
        }
    }

}
