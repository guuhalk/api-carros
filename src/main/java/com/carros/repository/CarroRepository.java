package com.carros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carros.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer>{

	List<Carro> findByTipo(String tipo);
}
