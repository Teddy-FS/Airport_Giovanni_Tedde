package com.rabobank.airport_FS.repositories;

import com.rabobank.airport_FS.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane,String> {

}
