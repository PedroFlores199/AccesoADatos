package Tema3.EjerciciosPracticos;

//Realiza un documento de word que contenga una tabla de 3 columnas que contengan la siguiente
//información.
//• Línea de código: escribir la línea de código que provoca una excepción
//• Nombrar excepción
//• Descripción: describe en qué condiciones se produciría dicha excepción y qué soluciones
//podemos tener al capturarla
//Desarrolla dicha información en una tabla.

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio1 {

    public static void main(String[] args) {
        lecturaXML();
        escrituraXML();
    }

    public static void lecturaXML() {
        File file = new File("ej1.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // almacenamos los nodos para luego mostrar la cantidad de ellos con el método getLength()
            NodeList nList = doc.getElementsByTagName("coche");
            System.out.println("Número de coches: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("\nCoche id: " + eElement.getAttribute("id"));
                    System.out.println("Marca: " +
                            eElement.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Modelo: " +
                            eElement.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println(
                            "Cilindrada: " +
                                    eElement.getElementsByTagName("cilindrada").item(0).getTextContent());
                    Element e = (Element)
                            eElement.getElementsByTagName("color").item(0);
                    if (e != null) {
                        System.out.println("Color: " + e.getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escrituraXML() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("concesionario");
            doc.appendChild(eRaiz);

            // definimos el nodo que contendrá los elementos
            Element eCoche = doc.createElement("coche");
            eRaiz.appendChild(eCoche);

            // atributo para el nodo coche
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            eCoche.setAttributeNode(attr);

            // definimos cada uno de los elementos y le asignamos un valor
            Element eMarca = doc.createElement("marca");
            eMarca.appendChild(doc.createTextNode("Renault"));
            eCoche.appendChild(eMarca);
            Element eModelo = doc.createElement("modelo");
            eModelo.appendChild(doc.createTextNode("Megane"));
            eCoche.appendChild(eModelo);
            Element eCilindrada = doc.createElement("cilindrada");
            eCilindrada.appendChild(doc.createTextNode("1.5"));
            eCoche.appendChild(eCilindrada);

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ej2.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

