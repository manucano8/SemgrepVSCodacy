@PostMapping(value = "/issue", consumes = MediaType.APPLICATION_XML_VALUE)
public String issue(Model model, @RequestBody String body)
        throws ParserConfigurationException, SAXException, IOException {
    // Issue - XXE
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(new InputSource(new StringReader(body)));

    String parsedDocument = getNodeString(doc.getFirstChild(), new StringBuffer()).toString();
    logger.debug("Parsed XML: \n" + parsedDocument);
    model.addAttribute("parsedDocument", parsedDocument);

    return "issue";
}
