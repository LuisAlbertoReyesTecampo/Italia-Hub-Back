package italia.hub.api.exception;

import java.util.UUID;

/**
 * <p>Excepción que modela las propiedades que definen una excepción
 * y es utilizada por el {@link CustomControllerAdvice} para manipular la información
 * en la excepción.
 *
 * <p>Tal y como ocurre en la mayoría de "custom exceptions", sólo contiene
 * constructorescon la definición necesaria, que incluye en algunos caos el
 * código HTTP que será devuelto.
 *
 * @version 1.0-SNAPSHOT
 * @since   1.0-SNAPSHOT
 */
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class ControllerException extends Exception {
    private static final long serialVersionUID = -5047974256813565913L;

	private Exception rootException;
	private final String shortMessage;
	private final String detailedMessage;
	private final int localExceptionNumber;
	private final String localExceptionKey;
	private HttpStatus httpStatus = HttpStatus.ACCEPTED;

	public ControllerException(Exception rootException) {
		this.rootException = rootException;
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.localExceptionNumber = 1000;
		this.localExceptionKey = "cve_1000";
		this.shortMessage = rootException.getMessage();
		this.detailedMessage = rootException.getMessage();
	}

	public ControllerException(String shortMessage, String detailedMessage,
			int localExceptionNumber, String localExceptionKey,
			HttpStatus httpStatus) {
		this.shortMessage = shortMessage;
		this.detailedMessage = detailedMessage;
		this.localExceptionNumber = localExceptionNumber;
		this.localExceptionKey = localExceptionKey;
		this.httpStatus = httpStatus;
	}

	public ControllerException(String shortMessage, String detailedMessage,
			int localExceptionNumber, String localExceptionKey) {
		this(shortMessage, detailedMessage, localExceptionNumber,
				localExceptionKey, HttpStatus.ACCEPTED);
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}

	public int getLocalExceptionNumber() {
		return localExceptionNumber;
	}

	public String getLocalExceptionKey() {
		return localExceptionKey;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Exception getRootException() {
		return rootException;
	}

	public String getRootExceptionMessage() {
		return rootException.getMessage();
	}

	@Override
	public String toString() {
		return "ControllerException [rootException=" + rootException
				+ ", shortMessage=" + shortMessage + ", detailedMessage="
				+ detailedMessage + ", localExceptionNumber="
				+ localExceptionNumber + ", localExceptionKey="
				+ localExceptionKey + ", httpStatus=" + httpStatus + "]";
	}

	/**
	 * Construye un mensaje genérico basado en un ID de rastreo para ocultar el
	 * verdadero mensaje al usuario final y dejar huella para que lo busque y
	 * gestione un administrador con acceso al log de transacciones.
	 * 
	 * @param msg
	 *            Cadena con un mensaje genérico.
	 * @param desc
	 *            Mensaje real a ocultar.
	 * 
	 * @return Cadena con el ID genérico
	 */
	public static String buildMessage(String msg, String desc) {
		String uid = UUID.randomUUID().toString();
		log.error("UID: " + uid + ". Description: " + desc);
		return String.format(msg, uid);
	}
}
