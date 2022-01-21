package italia.hub.api.rest;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import italia.hub.api.dto.ClienteDTO;
import italia.hub.api.dto.ClienteResponse;
import italia.hub.api.exception.ControllerException;
import italia.hub.api.service.PruebaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Api(value = "prueba")
@RequestMapping(value = "/api")
public class PruebaController {

	@Autowired
	PruebaService pruebaService;

	@GetMapping(value = "/prueba.json", produces = "application/json; charset=utf-8")
	public Map<String, String> prueba()  {

		return pruebaService.prueba();
	}

	@GetMapping(value = "/prueba-consulta-clientes.json", produces = "application/json; charset=utf-8")
	public ResponseEntity<List<ClienteDTO>> getAll() throws ControllerException {
		return new ResponseEntity<>(pruebaService.getClientes(),HttpStatus.OK);
	}

	@ApiOperation(value = "PruebaController::get", notes = "Regresa un objeto Cliente cuyo id "
			+ "coincide con el entero recibido como parametro.")
	@GetMapping(value = "/prueba-consulta-por-idcliente/{id}.json", produces = "application/json; charset=utf-8")
	public ResponseEntity<ClienteDTO> getCliente(
			@ApiParam(name = "id", value = "Representa el id del cliente buscado.")
			@Min(1) @PathVariable int id)
			throws ControllerException {
		return new ResponseEntity<>(pruebaService.getById(id),HttpStatus.OK);
	}

	@ApiOperation(value = "PruebaController::insert", notes = "Recibe un objeto Cliente el cual debe de ser insertado "
			+ " como dato dentro de la base de datos del sistema.")
	@PostMapping(value = "/prueba-inserta-cliente.json", produces = "application/json; charset=utf-8")
	public ResponseEntity<ClienteResponse> insert(
			@ApiParam(name = "cliente", value = "Cliente que será insertado en el sistema.")
			@RequestBody ClienteDTO cliente)
			throws ControllerException {
		ClienteResponse response = pruebaService.insert(cliente);
		return new ResponseEntity<>(response, response.getErrors().isEmpty() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "PruebaController::update", notes = "Recibe un objeto Cliente, este objeto es buscado por "
			+ "id dentro de la base de datos y es actualizado con el resto de "
			+ "datos proporcionados si es que el id en efecto existe. ")
	@PutMapping(value = "/prueba-actualiza-cliente.json", produces = "application/json; charset=utf-8")
	public ResponseEntity<ClienteResponse> update(@ApiParam(name = "cliente",
			value = "Cliente que será actualizado en el sistema, el id debe coincidir con el id del objeto que se desea actualizar.")
			@RequestBody ClienteDTO cliente)
			throws ControllerException {
		ClienteResponse response = pruebaService.update(cliente);
		return new ResponseEntity<>(pruebaService.update(cliente),
				response.getErrors().isEmpty() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "PruebaController::bajaLogica", notes = "Recibe un objeto Cliente, cuyo id es buscado dentro de "
			+ "la base de datos y en caso de existir es eliminado.")
	@GetMapping(value = "/prueba-baja-cliente/{id}.json", produces = "application/json; charset=utf-8")
	public ResponseEntity<ClienteResponse> bajaLogica(
			@ApiParam(name = "id", value = "Representa el id que será removido del sistema.")
			@Min(1) @PathVariable int id)
			throws ControllerException {
		return new ResponseEntity<>(pruebaService.bajaLogica(id),HttpStatus.OK);
	}

}
