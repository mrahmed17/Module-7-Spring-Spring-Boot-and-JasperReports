package com.mrahmed.ReviewSprintBoot.service;

import com.mrahmed.ReviewSprintBoot.entity.Location;
import com.mrahmed.ReviewSprintBoot.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public List<Location> getALlLocation(){

        return locationRepository.findAll();

    }

    public  void saveLocation(Location l){
        locationRepository
                .save(l);
    }

    public  void deleteLocation(int id){
        locationRepository.deleteById(id);
    }

    public  Location findById(int id){
        return   locationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Location Not Foound by this Id"));
    }







}
