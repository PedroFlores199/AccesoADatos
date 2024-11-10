package Tema3.DomSax;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

// A diferecia de DOM Y SAX, XPATH se encarga de seleccionar nodos específicos en un documento XML a través de expresiones de una ruta que le hemos marcado.

public class XPATH {

    public static void main(String[] args) {

        try {

            File file = new File("inputxml.txt");

            //Configuramos el parser, analizamos el xml y cargamso el documento en el objeto doc
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // Creamos la instacia de XPath y selecionamos todos los estudiantes, es decir,
            // que obtenemos todo lo que hay dentro de la etiqueta estudiantes y por ultimo
            // cremos la lista de nodos para asi iterarlo
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/class/student";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc,
                    XPathConstants.NODESET
            );

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("Current Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no:" + eElement.getAttribute("rollno"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name: " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Marks : " + eElement.getElementsByTagName("marks").item(0).getTextContent());
                    System.out.println();
                }

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
