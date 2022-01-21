package italia.hub.api.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ClienteResponse {

    @NonNull
    private String msg;
    private ClienteDTO cliente;
    List<String> errors;
}
