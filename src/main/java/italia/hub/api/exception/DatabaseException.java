package italia.hub.api.exception;

public class DatabaseException extends BusinessException {
	private static final long serialVersionUID = -7083159020205284484L;

	public DatabaseException(Exception e) {
		super("Error en el proceso de datos hacia o desde la Base de datos", e
				.getMessage(), 1005, "CVE_1005",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public DatabaseException(String msg) {
		super("Error en el proceso de datos hacia o desde la Base de datos",
				msg, 1005, "CVE_1005", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
