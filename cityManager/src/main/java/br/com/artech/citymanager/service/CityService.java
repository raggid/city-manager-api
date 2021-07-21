package br.com.artech.citymanager.service;

import br.com.artech.citymanager.controller.dto.CityDto;
import br.com.artech.citymanager.controller.dto.CitiesByUf;
import br.com.artech.citymanager.domain.City;
import br.com.artech.citymanager.domain.CitySpecification;
import br.com.artech.citymanager.repository.CityRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
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
        City city = repository.findByIbgeId(ibge_id);
        if(city == null){
            return null;
        }

        return new CityDto(city);
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

    public String uploadCsvFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please add a csv file to import the cities from.";
        } else {
            // parse CSV file to create a list of `City` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<CityDto> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(CityDto.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<CityDto> cities = csvToBean.parse();

                // save cities in DB
                List<City> dbCities = new LinkedList<>();
                for (CityDto cityDTO: cities) {
                    dbCities.add(new City(cityDTO));
                }

                repository.saveAll(dbCities);

                return "Cities added successfully!";
            } catch (Exception ex) {
                return "An error occurred while processing the CSV file.";
            }
        }
    }

    public String getNumberOfCities() {
        return String.valueOf(repository.findAll().size());
    }
}
