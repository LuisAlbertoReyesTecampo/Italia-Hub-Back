package italia.hub.api.exception;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperCallException extends BusinessException {
	private static final long serialVersionUID = -7083159020205284484L;

	public MapperCallException(String shortMessage, String technicalDescription) {
		super(shortMessage, buildMessage(technicalDescription), 1012,
				"CVE_1012", HttpStatus.BAD_REQUEST);
	}

	private static String buildMessage(String technicalDescription) {
		String uid = UUID.randomUUID().toString();
		log.error("UID: " + uid + ". Desc: " + technicalDescription);
		return "Codigo de error: " + uid;
	}

}