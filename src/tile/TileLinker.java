package tile;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

import org.xml.sax.SAXException;

public class TileLinker {

    public static ArrayList<Class<?>> availableBlocks = new ArrayList<>();
    public static ArrayList<Class<?>> availableItems = new ArrayList<>();
    static Element rootElement;
    static NodeList blocksElements;
    static NodeList itemsElements;

    static {
        try {
            rootElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("tileLinks.xml")).getDocumentElement();
            blocksElements = rootElement.getElementsByTagName("blocks").item(0).getChildNodes();
//            System.out.println(rootElement.getElementsByTagName("blocks").item(0).getNodeName());
            itemsElements = rootElement.getElementsByTagName("items").item(0).getChildNodes();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() throws ClassNotFoundException {

        for (int i = 0; i < blocksElements.getLength(); i++) {
            if (blocksElements.item(i).getNodeType() == Node.ELEMENT_NODE)
                availableBlocks.add(Class.forName(((Element) blocksElements.item(i)).getElementsByTagName("location").item(0).getTextContent().trim()));
        }
        for (int i = 0; i < itemsElements.getLength(); i++) {
            if (itemsElements.item(i).getNodeType() == Node.ELEMENT_NODE)
                availableItems.add(Class.forName(((Element) itemsElements.item(i)).getElementsByTagName("location").item(0).getTextContent().trim()));
        }

    }

}
