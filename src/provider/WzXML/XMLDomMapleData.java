package provider.WzXML;

import java.util.Iterator;
import provider.MapleDataEntity;
import org.w3c.dom.NamedNodeMap;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NodeList;
import tools.FilePrinter;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.File;
import org.w3c.dom.Node;
import java.io.Serializable;
import provider.MapleData;

public class XMLDomMapleData implements MapleData, Serializable
{
    private Node node;
    private File imageDataDir;
    
    private XMLDomMapleData(final Node node) {
        this.node = node;
    }
    
    public XMLDomMapleData(final FileInputStream fis, final File imageDataDir) {
        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final Document document = documentBuilder.parse((InputStream)fis);
            this.node = document.getFirstChild();
        }
        catch (ParserConfigurationException e) {
            throw new RuntimeException((Throwable)e);
        }
        catch (SAXException e2) {
            throw new RuntimeException((Throwable)e2);
        }
        catch (IOException e3) {
            throw new RuntimeException((Throwable)e3);
        }
        this.imageDataDir = imageDataDir;
    }
    
    @Override
    public MapleData getChildByPath(final String path) {
        final String[] segments = path.split("/");
        if (segments[0].equals((Object)"..")) {
            return ((MapleData)this.getParent()).getChildByPath(path.substring(path.indexOf("/") + 1));
        }
        Node myNode = this.node;
        for (int x = 0; x < segments.length; ++x) {
            final NodeList childNodes = myNode.getChildNodes();
            boolean foundChild = false;
            if (childNodes != null) {
                for (int i = 0; i < childNodes.getLength(); ++i) {
                    try {
                        final Node childNode = childNodes.item(i);
                        if (childNode != null && childNode.getNodeType() == 1 && childNode.getAttributes().getNamedItem("name").getNodeValue().equals((Object)segments[x])) {
                            myNode = childNode;
                            foundChild = true;
                            break;
                        }
                    }
                    catch (NullPointerException e) {
                        FilePrinter.printError("XMLDocumentMapleData.txt", (Throwable)e, "getChildByPath");
                    }
                }
            }
            if (!foundChild) {
                return null;
            }
        }
        final XMLDomMapleData ret = new XMLDomMapleData(myNode);
        ret.imageDataDir = new File(this.imageDataDir, this.getName() + "/" + path).getParentFile();
        return ret;
    }
    
    @Override
    public List<MapleData> getChildren() {
        final List<MapleData> ret = new ArrayList<MapleData>();
        final NodeList childNodes = this.node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            final Node childNode = childNodes.item(i);
            if (childNode != null && childNode.getNodeType() == 1) {
                final XMLDomMapleData child = new XMLDomMapleData(childNode);
                child.imageDataDir = new File(this.imageDataDir, this.getName());
                ret.add(child);
            }
        }
        return ret;
    }
    
    @Override
    public Object getData() {
        final NamedNodeMap attributes = this.node.getAttributes();
        final MapleDataType type = this.getType();
        switch (type) {
            case DOUBLE: {
                return Double.valueOf(Double.parseDouble(attributes.getNamedItem("value").getNodeValue()));
            }
            case FLOAT: {
                return Float.valueOf(Float.parseFloat(attributes.getNamedItem("value").getNodeValue()));
            }
            case INT: {
                return Integer.valueOf(Integer.parseInt(attributes.getNamedItem("value").getNodeValue()));
            }
            case SHORT: {
                return Short.valueOf(Short.parseShort(attributes.getNamedItem("value").getNodeValue()));
            }
            case STRING:
            case UOL: {
                return attributes.getNamedItem("value").getNodeValue();
            }
            case VECTOR: {
                return new Point(Integer.parseInt(attributes.getNamedItem("x").getNodeValue()), Integer.parseInt(attributes.getNamedItem("y").getNodeValue()));
            }
            case CANVAS: {
                return new FileStoredPngMapleCanvas(Integer.parseInt(attributes.getNamedItem("width").getNodeValue()), Integer.parseInt(attributes.getNamedItem("height").getNodeValue()), new File(this.imageDataDir, this.getName() + ".png"));
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    public final MapleDataType getType() {
        final String nodeName = this.node.getNodeName();
        if (nodeName.equals((Object)"imgdir")) {
            return MapleDataType.PROPERTY;
        }
        if (nodeName.equals((Object)"canvas")) {
            return MapleDataType.CANVAS;
        }
        if (nodeName.equals((Object)"convex")) {
            return MapleDataType.CONVEX;
        }
        if (nodeName.equals((Object)"sound")) {
            return MapleDataType.SOUND;
        }
        if (nodeName.equals((Object)"uol")) {
            return MapleDataType.UOL;
        }
        if (nodeName.equals((Object)"double")) {
            return MapleDataType.DOUBLE;
        }
        if (nodeName.equals((Object)"float")) {
            return MapleDataType.FLOAT;
        }
        if (nodeName.equals((Object)"int")) {
            return MapleDataType.INT;
        }
        if (nodeName.equals((Object)"long")) {
            return MapleDataType.LONG;
        }
        if (nodeName.equals((Object)"short")) {
            return MapleDataType.SHORT;
        }
        if (nodeName.equals((Object)"string")) {
            return MapleDataType.STRING;
        }
        if (nodeName.equals((Object)"vector")) {
            return MapleDataType.VECTOR;
        }
        if (nodeName.equals((Object)"null")) {
            return MapleDataType.IMG_0x00;
        }
        return null;
    }
    
    @Override
    public MapleDataEntity getParent() {
        final Node parentNode = this.node.getParentNode();
        if (parentNode.getNodeType() == 9) {
            return null;
        }
        final XMLDomMapleData parentData = new XMLDomMapleData(parentNode);
        parentData.imageDataDir = this.imageDataDir.getParentFile();
        return parentData;
    }
    
    @Override
    public String getName() {
        return this.node.getAttributes().getNamedItem("name").getNodeValue();
    }
    
    @Override
    public Iterator<MapleData> iterator() {
        return this.getChildren().iterator();
    }
}
