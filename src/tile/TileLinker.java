package tile;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.SAXException;

public class TileLinker {

    public static HashMap<String, Class<?>> availableBlocks = new HashMap<>();
    public static HashMap<String, Class<?>> availableItems = new HashMap<>();
    public static HashMap<String, Class<?>> availableTiles = new HashMap<>();
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

        Class<?> tempClass;
        for (int i = 0; i < blocksElements.getLength(); i++) {
            if (blocksElements.item(i).getNodeType() == Node.ELEMENT_NODE) {
                tempClass = Class.forName(getNodeContent(getFirstChildByName(blocksElements.item(i), "location")));
                availableBlocks.put(getNodeContent(getFirstChildByName(blocksElements.item(i), "name")), tempClass);
                availableTiles.put(getNodeContent(getFirstChildByName(blocksElements.item(i), "name")), tempClass);
            }
        }
        for (int i = 0; i < itemsElements.getLength(); i++) {
            if (itemsElements.item(i).getNodeType() == Node.ELEMENT_NODE) {
                tempClass = Class.forName(getNodeContent(getFirstChildByName(itemsElements.item(i), "location")));
                availableItems.put(getNodeContent(getFirstChildByName(itemsElements.item(i), "name")), tempClass);
                availableTiles.put(getNodeContent(getFirstChildByName(itemsElements.item(i), "name")), tempClass);
            }
        }

    }

    private static Node getFirstChildByName(Node parent, String find) {
        return ((Element) parent).getElementsByTagName(find).item(0);
    }

    private static String getNodeContent(Node in) {
        return in.getTextContent().trim();
    }

}
