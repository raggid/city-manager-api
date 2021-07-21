package br.com.artech.citymanager.controller.dto;

public class CitiesByUf {
    private String uf;
    private Long numCities;

    public CitiesByUf(String uf, Long numCities) {
        this.uf = uf;
        this.numCities = numCities;
    }

    public String getUf() {
        return uf;
    }

    public Long getNumCities() {
        return numCities;
    }
}
