package com.carros.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.dto.CarroDto;
import com.carros.model.Carro;
import com.carros.service.CarroService;

@RestController
@RequestMapping("/api/carros")
public class CarroResource {

	@Autowired
	private  CarroService carService;
	
	
	@GetMapping()
	public ResponseEntity<List<CarroDto>> getCarros(){	
		return ResponseEntity.ok(carService.findAll());	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDto> getCarrosPorId(@PathVariable ("id") Integer id ){
		Optional<CarroDto> carro = carService.findById(id);
		
		if(carro.isPresent()){
			return ResponseEntity.ok(carro.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDto>> getCarrosPorTipo(@PathVariable ("tipo") String tipo ){
		List<CarroDto> listaDeCarros = carService.findByType(tipo); 
		
		return listaDeCarros.isEmpty() ?
				ResponseEntity.noContent().build() : 
				ResponseEntity.ok(listaDeCarros);	
	}
	
	@PostMapping()
	@Secured("ROLE_ADMIN")
	public ResponseEntity<CarroDto> saveCar(@RequestBody Carro carro) {
		
		try {
			
			CarroDto carroDto = carService.saveCar(carro);
			
			URI location = getUri(carroDto.getId());
			return ResponseEntity.created(location).build();
				
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CarroDto> updateCar(@PathVariable ("id") Integer id, @RequestBody Carro carro) {
		
		carro.setId(id);
		CarroDto car = carService.updateCar(id,carro);
		return car != null ? ResponseEntity.ok(car) :
							 ResponseEntity.notFound().build();
	}
	
	

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCar(@PathVariable ("id") Integer id) {
		boolean ok = carService.deleteCar(id);
		return ok ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
				

	}
	
	private URI getUri(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
}
