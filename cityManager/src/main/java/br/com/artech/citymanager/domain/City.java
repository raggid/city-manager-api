package br.com.artech.citymanager.domain;

import br.com.artech.citymanager.controller.dto.CityDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
public class City {

    @Id
    @Column(name="ibge_id")
    private Long ibgeId;
    private String uf;
    private String name;
    private Boolean capital;
    private String lon;
    private String lat;
    private String no_accents;
    private String alternative_names;
    private String microregion;
    private String mesoregion;

    public City() {

    }

    public City(CityDto cityDto) {
        this.ibgeId = cityDto.getIbge_id();
        this.uf = cityDto.getUf();
        this.name = cityDto.getName();
        this.capital = cityDto.getCapital();
        this.lon = cityDto.getLon();
        this.lat = cityDto.getLat();
        this.no_accents = cityDto.getNo_accents();
        this.alternative_names = cityDto.getAlternative_names();
        this.microregion = cityDto.getMicroregion();
        this.mesoregion = cityDto.getMesoregion();
    }

    public City(Long ibge_id, String uf, String name, String lon, String lat) {
        this.ibgeId = ibge_id;
        this.uf = uf;
        this.name = name;
        this.capital = Boolean.FALSE;
        this.lon = lon;
        this.lat = lat;
    }

    public Long getIbgeId() {
        return ibgeId;
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
