package br.com.artech.citymanager.controller;

import br.com.artech.citymanager.controller.dto.CitiesByUf;
import br.com.artech.citymanager.controller.dto.CityDto;
import br.com.artech.citymanager.domain.City;
import br.com.artech.citymanager.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(value = "/create")
    public CityDto createCity(@RequestBody CityDto cityDto){
        return service.createCity(cityDto);
    }

    /**
     * Get all capital cities
     * @return All the cities that are capital
     */
    @GetMapping(value = "/capitals")
    public List<CityDto> getCapitalCities(){
        return service.getCapitalCities();
    }

    /**
     * Find a city by the IBGE Id
     * @param ibge_id
     * @return a single city
     */
    @GetMapping(value = "/city")
    public CityDto getCityByIbge(@RequestParam Long ibge_id){
        return service.getCityByIbgeId(ibge_id);
    }

    /**
     * Get the number of cities in each UF
     * @return A list o UFs with the number of cities in each
     */
    @GetMapping(value = "/number_of_cities_by_uf")
    public List<CitiesByUf> getNumberOfCitiesByUf(){
        return service.getNumberOfCitiesByUf();
    }

    /**
     * Get the Uf with the most cities and the UF with the least cities
     * @return A List with 2 UFs and the number o cities in each
     */
    @GetMapping(value = "/most_and_least_cities")
    public List<CitiesByUf> getUfByMostAndLeastCities(){
        return service.getMostAndLeastCitiesByUf();
    }

    /**
     * Get all the cities in a UF
     * @param uf
     * @return A List with all city names
     */
    @GetMapping("/get_city_names_by_uf")
    public List<String> getCityNamesByUf(@RequestParam String uf){
        return service.getCityNamesByUf(uf);
    }

    /**
     * Delete a city by its IBGE Id
     * @param ibge_id
     * @return A String with the result
     */
    @DeleteMapping(value = "/delete")
    public String deleteCity(@RequestParam Long ibge_id){
        return service.deleteCity(ibge_id);
    }

    /**
     * Get all cities filtering by the wanted column
     * @param column
     * @param value
     * @return A List o cities that match the filter
     */
    @GetMapping("/filter_by_column")
    public List<City> filterByColumn(@RequestParam String column, @RequestParam String value){
        return service.filterByColumn(column, value);
    }

    /**
     * Load all the cities from a csv file
     * @param file
     * @return A String with the result
     */
    @PostMapping(value = "/upload_csv_file")
    public String uploadCsvFile(@RequestParam("file") MultipartFile file) {
        return service.uploadCsvFile(file);
    }

    /**
     * Get the number of rows in the City Table
     * @return The number of rows
     */
    @GetMapping("/number_of_cities")
    public String numberofCities(){
        return service.getNumberOfCities();
    }
}
