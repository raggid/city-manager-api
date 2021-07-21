package br.com.artech.citymanager.service;

import br.com.artech.citymanager.controller.dto.CityDto;
import br.com.artech.citymanager.controller.dto.CitiesByUf;
import br.com.artech.citymanager.domain.City;
import br.com.artech.citymanager.domain.CitySpecification;
import br.com.artech.citymanager.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    public CityDto createCity(CityDto cityDto) {
        return new CityDto(repository.save(new City(cityDto)));
    }

    public List<CityDto> getCapitalCities() {
        List<City> cities = repository.findByCapitalOrderByName(Boolean.TRUE);
        return CityDto.convert(cities);
    }

    public CityDto getCityByIbgeId(Long ibge_id) {
        return new CityDto(repository.findByIbgeId(ibge_id));
    }

    public List<CitiesByUf> getNumberOfCitiesByUf() {
        return repository.findNumberCitiesByUf();
    }

    public List<CitiesByUf> getMostAndLeastCitiesByUf(){
        List<CitiesByUf> ufs = repository.findNumberCitiesByUf();
        Optional<CitiesByUf> most = ufs.stream().max(Comparator.comparingLong(CitiesByUf::getNumCities));
        Optional<CitiesByUf> least = ufs.stream().min(Comparator.comparingLong(CitiesByUf::getNumCities));
        ufs.clear();
        if(most.isPresent()) {
            ufs.add(most.get());
            ufs.add(least.get());
        }
        return ufs;
    }


    public List<String> getCityNamesByUf(String uf) {
        List<City> cities = repository.findAllByUf(uf);
        return cities.stream().map(City::getName).collect(Collectors.toList());
    }

    public String deleteCity(Long ibge_id) {
        try{
            repository.deleteById(ibge_id);
            return "Deleted";
        }catch (Exception e){
            return "Error trying to delete";
        }
    }

    public List<City> filterByColumn(String column, String value) {
        CitySpecification cs = new CitySpecification(column, value);
        List<City> cities = repository.findAll(cs);
        return cities;
    }
}
