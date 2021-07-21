package br.com.artech.citymanager.controller.dto;

import br.com.artech.citymanager.domain.City;

import java.util.List;
import java.util.stream.Collectors;

public class CityDto {
    private Long ibge_id;
    private String uf;
    private String name;
    private Boolean capital;
    private Long lon;
    private Long lat;
    private String no_accents;
    private String alternative_names;
    private String microregion;
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

    public Long getLon() {
        return lon;
    }

    public Long getLat() {
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
