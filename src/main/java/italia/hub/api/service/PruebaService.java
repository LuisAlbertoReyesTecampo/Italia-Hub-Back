package italia.hub.api.service;

import java.util.List;
import java.util.Map;

import italia.hub.api.dto.ClienteDTO;
import italia.hub.api.dto.ClienteResponse;
import italia.hub.api.exception.BusinessException;

public interface PruebaService {

	Map<String,String> prueba ();

	List<ClienteDTO> getClientes() throws BusinessException;

	ClienteDTO getById(int id) throws BusinessException;

	ClienteResponse insert(ClienteDTO cliente) throws BusinessException;

	ClienteResponse update(ClienteDTO cliente) throws BusinessException;

	ClienteResponse bajaLogica(int id) throws BusinessException;

}
