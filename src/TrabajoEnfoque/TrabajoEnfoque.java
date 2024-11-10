package TrabajoEnfoque;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class TrabajoEnfoque {

    public static void main(String[] args) {

        try {
                ArrayList <Contratos> contratosList = CargarXmlAntiguo();
                CrearXmlNuevo(contratosList);
                CrearDB(contratosList);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Nos va a devolver un arrayList de contratos y si falla va a hacerse responsable el main con el try-catch
    public static ArrayList <Contratos> CargarXmlAntiguo () throws Exception {

            String idexpediente;
            String objeto;
            String nombre;
            double precio;
            String tipoContrato;

            Contratos contratos;
            ArrayList <Contratos> contratosList = new ArrayList<>();

            //Creamos DocumentBuilder para poder cargar el xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Cargamos el xml
            Document document = db.parse("contratos-menores-2022.xml");

            //Normalizamos el xml para ver que la estruptura es correcta
            document.getDocumentElement().normalize();

            // Obtenemos los elementos de contratos menores 2022
            NodeList contratosListXML = document.getElementsByTagName("contratos-menores-2022");

            //Iteramos entre los contratos hasta que ya no quede ninguno
            for (int i = 0; i < contratosListXML.getLength(); i++){

                Element contrato = (Element) contratosListXML.item(i);

                idexpediente = contrato.getElementsByTagName("idexpediente").item(0).getTextContent();
                objeto = contrato.getElementsByTagName("objeto").item(0).getTextContent();
                nombre = contrato.getElementsByTagName("Nombre").item(0).getTextContent();
                precio = Double.parseDouble(contrato.getElementsByTagName("Precio").item(0).getTextContent());
                tipoContrato = contrato.getElementsByTagName("TipoContrato").item(0).getTextContent();

                contratos = new Contratos(idexpediente, objeto, nombre, precio, tipoContrato);
                contratosList.add(contratos);

            }
            return contratosList;
    }



    public static void CrearXmlNuevo (ArrayList <Contratos> contratosList) {

        try {

            //Creamos DocumentBuilder para poder cargar el xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Creamos el nuevo xml
            Document documentNuevo = db.newDocument();

            Element raiz = documentNuevo.createElement("contratos-menores-2022");
            documentNuevo.appendChild(raiz);

            //Iteramos entre los contratos hasta que ya no quede ninguno
            for (int i = 0; i < contratosList.size(); i++) {

                //Creamos la raiz hija del nuevo documento de xml
                Element raizHijo = documentNuevo.createElement("contratos-menores-2022");
                raiz.appendChild(raizHijo);

                //Creamos la etiqueta objeto con el texto del xml antiguo
                Element objetoNuevo = documentNuevo.createElement("objeto");
                objetoNuevo.appendChild(documentNuevo.createTextNode(contratosList.get(i).getObjeto()));
                raizHijo.appendChild(objetoNuevo);

                //Creamos la etiqueta Nombre con el nombre del xml antiguo
                Element nombreNuevo = documentNuevo.createElement("Nombre");
                nombreNuevo.appendChild(documentNuevo.createTextNode(contratosList.get(i).getNombre()));
                raizHijo.appendChild(nombreNuevo);

                //Creamos la etiqueta Precio con el nombre del xml antiguo
                Element precioNuevo = documentNuevo.createElement("Precio");
                precioNuevo.appendChild(documentNuevo.createTextNode(String.valueOf(contratosList.get(i).getPrecio())));
                raizHijo.appendChild(precioNuevo);

                //Creamos la etiqueta TipoContrato con el nombre del xml antiguo
                Element tipoContratoNuevo = documentNuevo.createElement("TipoContrato");
                tipoContratoNuevo.appendChild(documentNuevo.createTextNode(contratosList.get(i).getTipoContrato()));
                raizHijo.appendChild(tipoContratoNuevo);

            }


            //Creamos el tranformer para de este modo hacer la tranformacion de un documento DOM a XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Configuramos el tranformer para que el XML tenga sangria y no este todo en  una linea solo
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); //Activamos la sangria de xml
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Ajustamos la cantidad de espacios de sangría

            // Definimos la fuente de datos que queremos usar
            DOMSource source = new DOMSource(documentNuevo);
            StreamResult result = new StreamResult(new File("nuevo-contratos-menores-2022.xml"));

            //Ejecutamos la tranformacion de DOM a XML
            transformer.transform(source, result);

            System.out.println("XML creado con exito");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    //En este caso usaremos mariadb ya que nunca antes lo he usado y me gustaria probarlo
    public static void CrearDB (ArrayList <Contratos> contratosList){

        //Guardaremos en variables la ruta, el usuario y la contraseña
        String url = "jdbc:mariadb://localhost:3306/Contratos";
        String user = "Peanf";
        String pass = "admin";

        //Creamos la conexion a la base de datos
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            //Creamos la tabla
            String crearTablaSQL = "CREATE TABLE IF NOT EXISTS contratos (IDEXPEDIENTE VARCHAR(255), OBJETO TEXT, NOMBRE TEXT, PRECIO FLOAT, TIPOCONTRATO TEXT)";

            //Preparamos el comando y lo ejecutamos
            try (PreparedStatement crearTablaStmt = connection.prepareStatement(crearTablaSQL)) {
                crearTablaStmt.executeUpdate();
                System.out.println("Tabla creada exitosamente");
            }

            String insertarDatosSQL = "INSERT INTO contratos (IDEXPEDIENTE, OBJETO, NOMBRE, PRECIO, TIPOCONTRATO) VALUES (?,?,?,?,?)";

            //Preparamos el comando y lo ejecutamos iterando las inserciones rellenando asi la tabla
            try (PreparedStatement insertarTablaStmt = connection.prepareStatement(insertarDatosSQL)) {
            for (int i = 0; i < contratosList.size(); i++){
                insertarTablaStmt.setString(1, contratosList.get(i).getIdexpediente());
                insertarTablaStmt.setString(2, contratosList.get(i).getObjeto());
                insertarTablaStmt.setString(3, contratosList.get(i).getNombre());
                insertarTablaStmt.setDouble(4, contratosList.get(i).getPrecio());
                insertarTablaStmt.setString(5, contratosList.get(i).getTipoContrato());
                insertarTablaStmt.executeUpdate();

            }
                System.out.println("Inserciones hechas correctamente");
            }


            //Ahora para comprobar que todo se ha hecho correctamente vamos a visualizarlo en la terminal
            String verDatosSQL = "SELECT * FROM contratos";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(verDatosSQL);

            int contador = 0;
            while (resultSet.next()){
                String idexpediente = resultSet.getString("IDEXPEDIENTE");
                String objeto = resultSet.getString("OBJETO");
                String nombre = resultSet.getString("NOMBRE");
                double precio = resultSet.getDouble("PRECIO");
                String tipoContrato = resultSet.getString("TIPOCONTRATO");

                contador++;
                System.out.println();
                System.out.println("Tabla " + contador);
                System.out.println("Idexpediente: " + idexpediente);
                System.out.println("Objeto: " + objeto);
                System.out.println("Nombre: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("Tipo Contrato: " + tipoContrato);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
