package italia.hub.api.service.impl;

import java.util.*;

import italia.hub.api.dto.ClienteDTO;
import italia.hub.api.dto.ClienteResponse;
import italia.hub.api.helper.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italia.hub.api.exception.BusinessException;
import italia.hub.api.exception.DatabaseException;
import italia.hub.api.model.Cliente;
import italia.hub.api.repository.PruebaRepository;
import italia.hub.api.service.PruebaService;
import org.springframework.transaction.annotation.Transactional;


@Service("pruebaService")
public class PruebaServiceImpl implements PruebaService {

	@Autowired
	PruebaRepository pruebaRepository;

	@Autowired
	private ValidationHelper helper;

	@Override
	public Map<String, String> prueba() {
		Map<String, String> mapa = new HashMap<>();
		mapa.put("datoUno", "prueba1");
		mapa.put("datosDos", "prueba2");
		return mapa;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDTO> getClientes() throws BusinessException {
		List<Cliente> clientes = pruebaRepository.getClientes();
		List<ClienteDTO> clienteDTOS = new ArrayList<>();
		for (Cliente c : clientes) {
			ClienteDTO clienteDTO = new ClienteDTO(c.getId(), c.getNombre(),
					c.getApellido(), c.getFechaInicio(), c.getFechaFin(),c.isActivo());
			clienteDTOS.add(clienteDTO);
		}
		return clienteDTOS;
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteDTO getById(int id) throws BusinessException {
		try {
			Cliente cliente = pruebaRepository.findById(id).orElse(null);
			if (cliente != null) {
				return mappingDTO(cliente);
			}
			return null;
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	@Transactional
	public ClienteResponse insert(ClienteDTO clienteDTO) throws BusinessException {
		try {
			List<String> errors = helper.validateCreateInput(clienteDTO);
			if(errors.isEmpty()) {
//				ClienteDTO dto = mappingDTO(pruebaRepository.save(mappingEntity(clienteDTO)));
//				return new ClienteResponse("Cliente agregado existosamente", dto,null);
				return new ClienteResponse("Cliente agregado existosamente", null,null);
			}else{
				return new ClienteResponse("Errores de validacion",null,errors);
			}
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	@Transactional
	public ClienteResponse update(ClienteDTO clienteDTO) throws BusinessException {
		ClienteResponse response = null;
		try {
			List<String> errors = helper.validateUpdateInput(clienteDTO);
			if(errors.isEmpty()) {
				if (checkCliente(clienteDTO.getId())) {
					ClienteDTO dto = mappingDTO(pruebaRepository.save(mappingEntity(clienteDTO)));
					response =  new ClienteResponse("Datos Actualizados",dto,null);
//					response.setMsg("Datos Actualizados");
//					response.setCliente(dto);
				} else {
					response = new ClienteResponse("No se ejecuto la actualizacion, el cliente no existe",null,errors);
				}
			}
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return response;
	}

	@Override
	@Transactional
	public ClienteResponse bajaLogica(int id) throws BusinessException {
		try {
			pruebaRepository.bajaLogica(id);
			return new ClienteResponse("Baja Exitosa");
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	public Cliente mappingEntity(ClienteDTO clienteDTO) {

		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setActivo(clienteDTO.isActivo());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setFechaInicio(clienteDTO.getFechaInicio());
		cliente.setFechaFin(clienteDTO.getFechaFin());

		return cliente;

	}

	public ClienteDTO mappingDTO(Cliente clienteEntity) {

		ClienteDTO cliente = new ClienteDTO();
		cliente.setId(clienteEntity.getId());
		cliente.setActivo(clienteEntity.isActivo());
		cliente.setApellido(clienteEntity.getApellido());
		cliente.setNombre(clienteEntity.getNombre());
		cliente.setFechaInicio(clienteEntity.getFechaInicio());
		cliente.setFechaFin(clienteEntity.getFechaFin());

		return cliente;

	}

	private boolean checkCliente(int id){
		Optional<Cliente> cliente = pruebaRepository.findById(id);
		return  cliente.isPresent();
	}

}
