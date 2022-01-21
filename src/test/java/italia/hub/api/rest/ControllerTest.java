package italia.hub.api.rest;

import italia.hub.api.dto.ClienteDTO;
import italia.hub.api.dto.ClienteResponse;
import italia.hub.api.exception.BusinessException;
import italia.hub.api.service.PruebaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {


    @Autowired
    PruebaService service;

    @Test
    public void getPruebaTest()  {
        Map<String,String> p = service.prueba();
        assertEquals("prueba1",p.get("datoUno"));

    }

    @Test
    public void getByIdTest() throws BusinessException {
        ClienteDTO c1 = new ClienteDTO();
        c1.setNombre("Ulises");
        c1.setApellido("Urbina");
        c1.setActivo(true);

        ClienteDTO c = service.getById(1);
        assertEquals(c1.getNombre(),c.getNombre());

    }

    @Test
    public void getClientesTest() throws BusinessException {
        List<ClienteDTO> clientes = service.getClientes();
        assertFalse(clientes.isEmpty());

    }

    @Test
    public void updateIdTest() throws BusinessException {

        ClienteDTO c1 = new ClienteDTO();
        c1.setId(2);
        c1.setNombre("Alberto");
        c1.setApellido("Reyes");
        c1.setActivo(false);
        ClienteResponse res = service.update(c1);

        assertEquals(c1.getNombre(),res.getCliente().getNombre());

    }

    @Test
    public void updateIdNoExistTest() throws BusinessException {

        ClienteDTO c1 = new ClienteDTO();
        c1.setId(21);
        c1.setNombre("Alberto");
        c1.setApellido("Reyes");
        c1.setActivo(false);
        ClienteResponse res = service.update(c1);

        assertEquals("No se ejecuto la actualizacion, el cliente no existe",res.getMsg());

    }

    @Test
    public void insertTest() throws BusinessException {
        ClienteDTO c = new ClienteDTO();
        c.setId(4);
        c.setNombre("Angela");
        c.setApellido("Rios");
        c.setActivo(true);
        ClienteResponse res = service.insert(c);
        assertEquals(c.getNombre(),res.getCliente().getNombre());
    }

    @Test
    public void bajaLogicaTest() throws BusinessException {
        ClienteResponse res = service.bajaLogica(1);
        assertEquals("Baja Exitosa",res.getMsg());
    }


}
