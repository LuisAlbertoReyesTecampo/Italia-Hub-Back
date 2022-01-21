package italia.hub.api.exception;

import italia.hub.api.utils.Constants;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request){
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String uri = ((ServletWebRequest)request).getRequest().getRequestURI();
        if(Constants.URI_UPDATE.equals(uri)){
            if(errors.contains(Constants.MESSAGE_ERROR_LASTNAME)){
                errors.remove(Constants.MESSAGE_ERROR_LASTNAME);
            }
            if(errors.contains(Constants.MESSAGE_ERROR_STATUS)){
                errors.remove(Constants.MESSAGE_ERROR_STATUS);
            }
            log.info(errors.toString());
        }
        log.info(uri);
        body.put("errors",errors);
        log.debug(body.toString());
        log.info(body.toString());

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleServiceCallException(MethodArgumentTypeMismatchException e) {
        Map<String, String> errMessages = new HashMap<>();
        errMessages.put("error", "MethodArgumentTypeMismatchException");
        errMessages.put("message", e.getMessage());
        errMessages.put("parameter", e.getName());
        errMessages.put("errorCode", e.getErrorCode());
        return errMessages;
    }
}
