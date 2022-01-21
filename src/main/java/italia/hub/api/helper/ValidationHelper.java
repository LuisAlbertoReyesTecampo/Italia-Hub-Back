package italia.hub.api.helper;

import italia.hub.api.dto.ClienteDTO;
import italia.hub.api.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ValidationHelper {

    public List<String> validateCreateInput(ClienteDTO dto){
        List<String> errors = new ArrayList<>();
        this.validaString(dto.getNombre(), Constants.MESSAGE_ERROR_NAME,errors);
        this.validaString(dto.getApellido(),Constants.MESSAGE_ERROR_LASTNAME,errors);

        return errors;
    }

    private void validaString(String value, String error,List<String> errors){

        if(value == null || value.isEmpty()){
            log.info(error);
            errors.add(error);
        }else if(value.length() == 0 || value.length() > 10 ){
            log.info(Constants.MESSAGE_LENGTH);
            errors.add(Constants.MESSAGE_LENGTH);

        }
    }

    public List<String> validateUpdateInput(ClienteDTO dto) {
        List<String> errors = new ArrayList<>();
//        this.validaString(dto.getNombre(), Constants.MESSAGE_ERROR_NAME,errors);
//        this.validaString(dto.getApellido(),Constants.MESSAGE_ERROR_LASTNAME,errors);
//        this.validaNull(dto.getNombre());
//        this.validaNull(dto.isActivo());

        if(!this.validaNull(dto.getNombre()))
            this.validaStringUpdate(dto.getNombre(),Constants.MESSAGE_ERROR_NAME,errors);
        if(!this.validaNull(dto.getApellido()))
            this.validaStringUpdate(dto.getApellido(),Constants.MESSAGE_ERROR_LASTNAME,errors);
        if(!this.validaNull(dto.getFechaFin()))
            this.validaStringUpdate(dto.getFechaFin(),Constants.MESSAGE_ERROR_END_DATE,errors);
//        if(!this.validaNull(dto.isActivo()))
//            this.validaStringUpdate(dto.isActivo(),Constants.MESSAGE_ERROR_NAME,errors);

        return errors;
    }

    private void validaStringUpdate(Object nombre, String messageErrorName, List<String> errors) {


    }

    private boolean validaNull(Object value){
        if(value == null)
            return true;
        return false;
    }
}
