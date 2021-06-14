
package securityservices.core.shared.services.serializers.xmlapis;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.transform.JDOMSource;
import org.xml.sax.InputSource;
import securityservices.core.shared.services.serializers.Xml;

public class Jdom implements Xml {
    
    protected SAXBuilder builder;
    protected Document document;
    protected Element rootNode, node, elem;
    protected List subnodes;
    protected XMLOutputter jdomToXML;
    protected Boolean iscreated, isseted;
     
    public Jdom() {
        try {
            builder = new SAXBuilder();
            iscreated = true;
        } catch (Exception ex) {
            iscreated = false;
        }   
    }
    
    public Boolean getIscreated() {
        return iscreated;
    }
    
      @Override
    public void set(String xmlDoc) {
        try {
            createDocument();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlDoc));
            document = (Document)builder.build(is);
            isseted = true;
        } catch (Exception ex) {
           isseted = false;
        }
        rootNode = document.getRootElement(); 
        subnodes = rootNode.getChildren();
    }
    
    @Override
        public String toString(){    
            String result = "";
        try {
            TransformerFactory transFabric = TransformerFactory.newInstance();
            Transformer transformer = transFabric.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            
           jdomToXML = new XMLOutputter(); 
           jdomToXML.setFormat(Format.getPrettyFormat());
           StringWriter writer = new StringWriter();
            transformer.transform(new JDOMSource(document), new StreamResult(writer));
            result = writer.getBuffer().toString();
       } catch (TransformerConfigurationException ex) {
            return result; 
        } catch (TransformerException ex) {
            return result; 
        }
        return result; 
    }
    
    @Override
    public void createDocument() {
        document = new Document();
    }

    @Override
    public void setRootNode(String node) {
       rootNode = new Element(node);
       document.addContent(rootNode);
    }

    @Override
    public void setNode(String node, String value) {
         this.node = new Element(node);
         this.node.setText(value);
         this.rootNode.addContent(this.node);
    }

    @Override
    public void setSubNode(String node, String subnode, String value) {
       
    }

    @Override
    public void setArrayNodes(String node, ArrayList<String> subnodelist, ArrayList<String> nodeValueslist) {
      
    }

    @Override
    public void setAtributes(String node, String atribs) {
        
    }

    @Override
    public String getRootNode() {
        return rootNode.getName(); }
    
    @Override
    public String getValueNode(String node) {
         for (int i=0; i < this.subnodes.size(); i++){
            this.node = (Element) subnodes.get(i);
            if (this.node.getName().equals(node)){
                return this.node.getChildText(node);
            }
        }
        return null;
    }


    @Override
    public String[] getSubNodes(String node) {
       return null;
    }

    @Override
    public String[] getAtributes(String node) {
        return null;
    }
    
}
