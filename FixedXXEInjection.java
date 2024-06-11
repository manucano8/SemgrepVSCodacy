import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.StringReader;
import org.w3c.dom.Document;

@PostMapping(value = "/issue", consumes = MediaType.APPLICATION_XML_VALUE)
public String issue(Model model, @RequestBody String body)
        throws ParserConfigurationException, SAXException, IOException {
    // Secure configuration to prevent XXE
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    
    // Prevent XXE by disabling external entities
    dbFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
    dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    dbFactory.setXIncludeAware(false);
    dbFactory.setExpandEntityReferences(false);

    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(new InputSource(new StringReader(body)));

    String parsedDocument = getNodeString(doc.getFirstChild(), new StringBuffer()).toString();
    logger.debug("Parsed XML: \n" + parsedDocument);
    model.addAttribute("parsedDocument", parsedDocument);

    return "issue";
}
