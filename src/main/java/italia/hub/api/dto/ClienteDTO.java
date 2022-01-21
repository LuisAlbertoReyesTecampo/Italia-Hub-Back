package italia.hub.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import italia.hub.api.utils.Constants;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int id;
	String nombre;
	String apellido;
	Date fechaInicio;
	Date fechaFin;
	boolean activo;

}
