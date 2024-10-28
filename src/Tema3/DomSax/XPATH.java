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

public class XPATH {

    public static void main(String[] args) {

        try {
            File file = new File("inputxml.txt");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();


            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/class/student";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

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
