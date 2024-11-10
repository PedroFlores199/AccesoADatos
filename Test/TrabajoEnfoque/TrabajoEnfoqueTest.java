package TrabajoEnfoque;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

class TrabajoEnfoqueTest {

    ArrayList<Contratos> contratosList;

    @BeforeEach
    void setUp() {
        try {
            contratosList = TrabajoEnfoque.CargarXmlAntiguo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    void tearDown() {
        // No estamos trabajando con flujos de datos ahora asi que no hace falta limpiar nada
    }

    @Test
    void cargarXmlAntiguo() {
        try{
            ArrayList <Contratos> cargarXML = TrabajoEnfoque.CargarXmlAntiguo();
            Assertions.assertNotNull(cargarXML, "La lista de contratos no deberia ser nula");
            Assertions.assertFalse(cargarXML.isEmpty(), "La lista de contratos no deberia estar vacia");
            Assertions.assertEquals("3/2022", cargarXML.get(0).getIdexpediente(), "El idex del primer contrato tiene que ser 49/2022");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void crearXmlNuevo() {
        TrabajoEnfoque.CrearXmlNuevo(contratosList);

        File nuevoXml = new File("nuevo-contratos-menores-2022.xml");
        Assertions.assertTrue(nuevoXml.exists(), "El archivo nuevo-contratos-menores-2022.xml tendria existir");

    }

    @Test
    void crearDB() {
        try {
            TrabajoEnfoque.CrearDB(contratosList);

            String url = "jdbc:mariadb://localhost:3306/Contratos";
            String user = "Peanf";
            String pass = "admin";

            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM contratos");

                    int count = resultSet.getInt(1);
                    Assertions.assertEquals(contratosList.size(), count, "El numero de insert que tendra nuestra base de datos tiene que coincidir con los contratos que haya en el ArrayList");

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}