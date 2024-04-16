@PostMapping("/checkdb")
public String checkDB(@RequestParam(name = "dbpath") String dbpath, Model model) throws MalformedURLException, IOException {
    // Issue - SSRF
    String out = new Scanner(new URL(dbpath).openStream(), "UTF-8").useDelimiter("\\A").next();
    model.addAttribute("dbResponse", out);
    return "checkdb";
}
