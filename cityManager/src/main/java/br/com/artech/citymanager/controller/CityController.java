package br.com.artech.citymanager.controller;

import br.com.artech.citymanager.controller.dto.CitiesByUf;
import br.com.artech.citymanager.controller.dto.CityDto;
import br.com.artech.citymanager.domain.City;
import br.com.artech.citymanager.repository.CityRepository;
import br.com.artech.citymanager.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService service;

    /**
     * Create a new city based on data received
     * @param cityDto data
     * @return the new city created
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CityDto createCity(@RequestBody CityDto cityDto){
        return service.createCity(cityDto);
    }

    /**
     * Get all capital cities
     * @return All the cities that are capital
     */
    @RequestMapping(value = "/capitais", method = RequestMethod.GET)
    public List<CityDto> getCapitalCities(){
        return service.getCapitalCities();
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public CityDto getCityByIbge(@RequestParam Long ibge_id){
        return service.getCityByIbgeId(ibge_id);
    }

    @RequestMapping(value = "/number_of_cities_by_uf", method = RequestMethod.GET)
    public List<CitiesByUf> getNumberOfCitiesByUf(){
        return service.getNumberOfCitiesByUf();
    }

    @RequestMapping(value = "/most_and_least_cities", method = RequestMethod.GET)
    public List<CitiesByUf> getUfByMostAndLeastCities(){
        return service.getMostAndLeastCitiesByUf();
    }

    @RequestMapping("/get_city_names_by_uf")
    public List<String> getCityNamesByUf(@RequestParam String uf){
        return service.getCityNamesByUf(uf);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteCity(@RequestParam Long ibge_id){
        return service.deleteCity(ibge_id);
    }

    @RequestMapping("/filter_by_column")
    public List<City> filterByColumn(@RequestParam String column, @RequestParam String value){
        return service.filterByColumn(column, value);
    }
}
