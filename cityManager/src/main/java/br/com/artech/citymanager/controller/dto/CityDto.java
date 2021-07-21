package br.com.artech.citymanager.controller.dto;

import br.com.artech.citymanager.domain.City;
import com.opencsv.bean.CsvBindByName;

import java.util.List;
import java.util.stream.Collectors;

public class CityDto {
    @CsvBindByName
    private Long ibge_id;
    @CsvBindByName
    private String uf;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private Boolean capital;
    @CsvBindByName
    private String lon;
    @CsvBindByName
    private String lat;
    @CsvBindByName
    private String no_accents;
    @CsvBindByName
    private String alternative_names;
    @CsvBindByName
    private String microregion;
    @CsvBindByName
    private String mesoregion;

    public CityDto(){}

    public CityDto(City city){
        this.ibge_id = city.getIbgeId();
        this.uf = city.getUf();
        this.name = city.getName();
        this.capital = city.getCapital();
        this.lon = city.getLon();
        this.lat = city.getLat();
        this.no_accents = city.getNo_accents();
        this.alternative_names = city.getAlternative_names();
        this.microregion = city.getMicroregion();
        this.mesoregion = city.getMesoregion();
    }

    public static List<CityDto> convert(List<City> cities){
        return cities.stream().map(CityDto::new).collect(Collectors.toList());
    }

    public Long getIbge_id() {
        return ibge_id;
    }

    public String getUf() {
        return uf;
    }

    public String getName() {
        return name;
    }

    public Boolean getCapital() {
        return capital;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public String getAlternative_names() {
        return alternative_names;
    }

    public String getMicroregion() {
        return microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }
}
