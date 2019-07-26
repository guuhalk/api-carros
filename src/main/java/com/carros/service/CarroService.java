package com.carros.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.dto.CarroDto;
import com.carros.model.Carro;
import com.carros.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carRepository;
	
	public List<CarroDto> findAll(){
		List<Carro> listaDeCarros = carRepository.findAll();
		List<CarroDto> listaCarroDto = listaDeCarros.stream().map(CarroDto:: create).collect(Collectors.toList());
		
		return listaCarroDto;
	}
	
	public Optional<CarroDto> findById(Integer id){
		return carRepository.findById(id).map(CarroDto::create);
	}
	
	public List<CarroDto> findByType(String tipo){
		
		List<Carro> listaDeCarros = carRepository.findByTipo(tipo);
		List<CarroDto> listaCarroDto = listaDeCarros.stream().map(CarroDto::create).collect(Collectors.toList());	
		return listaCarroDto;
	}
	
	
	public CarroDto saveCar(Carro carro){
		Assert.isNull(carro.getId(), "Não foi possivel inserir o registro.");
		
		return CarroDto.create(carRepository.save(carro));  
	}
	
	
	public CarroDto updateCar( Integer id, Carro carro){
		Assert.notNull(id, "Não foi possivel atualizar o registro.");
		
		Optional<Carro> listCar = carRepository.findById(id);
		
		if(listCar.isPresent()){
			Carro db = listCar.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());
			
			carRepository.save(db);
			
			return CarroDto.create(db);
			
		}else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}

	}
	
	public boolean deleteCar(Integer id) {
		Optional<Carro> listCar = carRepository.findById(id);
		
		if(listCar.isPresent()) {
			carRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
}
