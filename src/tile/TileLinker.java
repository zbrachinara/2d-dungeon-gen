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
            itemsElements = rootElement.getElementsByTagName("items").item(0).getChildNodes();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() throws ClassNotFoundException {

        for (int i = 0; i < blocksElements.getLength(); i++) {
            availableBlocks.add(Class.forName(blocksElements.item(i).getTextContent()));
        }
        for (int i = 0; i < itemsElements.getLength(); i++) {
            availableItems.add(Class.forName(itemsElements.item(i).getTextContent()));
        }

    }

}
