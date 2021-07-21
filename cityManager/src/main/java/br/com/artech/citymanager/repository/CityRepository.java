package br.com.artech.citymanager.repository;

import br.com.artech.citymanager.controller.dto.CitiesByUf;
import br.com.artech.citymanager.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository  extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    List<City> findByCapitalOrderByName(Boolean aTrue);

    City findByIbgeId(Long ibgeId);

    @Query(value = "SELECT new br.com.artech.citymanager.controller.dto.CitiesByUf(C.uf, COUNT(C.ibgeId)) FROM City C GROUP BY C.uf")
    List<CitiesByUf> findNumberCitiesByUf();

    List<City> findAllByUf(String uf);
}
