package italia.hub.api.exception;

public class BusinessException extends ControllerException {
	private static final long serialVersionUID = -1218087669509474484L;

	public BusinessException(Exception e) {
		super(e);
	}

	public BusinessException(String shortMessage, String detailedMessage,
			int localExceptionNumber, String localExceptionKey,
			HttpStatus httpStatus) {
		super(shortMessage, detailedMessage, localExceptionNumber,
				localExceptionKey, httpStatus);
	}

	public BusinessException(String shortMessage, String detailedMessage,
			int localExceptionNumber, String localExceptionKey) {
		super(shortMessage, detailedMessage, localExceptionNumber,
				localExceptionKey);
	}

	public BusinessException(String shortMessage, String detailedMessage) {
		super(shortMessage, detailedMessage, 0, "CVE_0000");
	}

}
