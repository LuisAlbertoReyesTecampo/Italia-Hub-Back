package italia.hub.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	@SequenceGenerator(sequenceName = "SEQ_CLIENTE", allocationSize = 1, name = "SEQ_CLIENTE")
	@Column(name = "ID_CLIENTE")
	private int id;
	private String nombre;
	private String apellido;
	@Column(name = "FEC_INI_VIGENCIA")
	private Date fechaInicio;
	@Column(name = "FEC_FIN_VIGENCIA")
	private Date fechaFin;
	@Column(name = "BLN_ACTIVO")
	private boolean activo;


}
