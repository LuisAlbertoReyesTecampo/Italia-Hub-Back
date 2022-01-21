package italia.hub.api.utils;

import lombok.Data;

@Data
public final class Constants {

    public static final String MESSAGE_ERROR_NAME ="El nombre es requerido";
    public static final String MESSAGE_ERROR_LASTNAME ="El apellido es requerido";
    public static final String MESSAGE_ERROR_STATUS ="El estado es requerido";
    public static final String URI_UPDATE ="/api/prueba-actualiza-cliente.json";

    public static final String MESSAGE_LENGTH = "La longitud no esta dentro del rango permitido";
    public static final String MESSAGE_ERROR_END_DATE = "La fecha fin es requerida";
}
