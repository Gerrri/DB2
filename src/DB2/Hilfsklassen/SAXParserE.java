/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB2.Hilfsklassen;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;


/**
 *
 * @author Lammertz,Asbach
 */
public class SAXParserE {
    /*
    public static void main(String[] args) {
        String filename = args[0], info;
        boolean a = true;
        if (args[1].compareTo("true") == 0) {
            info = "Validitaet";
        } else {
            a = false;
            info = "Wohlgeformtheit nur";
        }
        System.out.println("SAXParserE: Jetzt wird der File " + filename + " auf " + info + " geparst.");
        ContentHandlerArtikel handler = new ContentHandlerArtikel();
        MyErrorHandlerE ehandler = new MyErrorHandlerE();
        parseXmlFile(filename, handler, ehandler, a);
    }
    */

    public void parseXmlFile(String filename, ContentHandler handler, MyErrorHandlerE ehandler, boolean val) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(val);
            SAXParser saxpars1 = factory.newSAXParser();
            XMLReader read1 = saxpars1.getXMLReader();
            read1.setContentHandler(handler);
            read1.setErrorHandler(ehandler);
            boolean w1 = saxpars1.isValidating();
            //if(w1) System.out.println("---> Der Parser validiert.");
            String h1 = new File(filename).toURL().toString();
            System.out.println("URI = " + h1);
            read1.parse(new File(filename).toURL().toString());
        } catch (SAXParseException ep) {// A parsing error occurred; the xml input is not valid
            System.out.println("SAx-Parser-Ausnahme in " + filename + " :\n" + ep);
            System.out.println("Parser meldet FEHLER : " + ep.toString());
            System.out.println("an der Entity        : " + ep.getPublicId());
            System.out.println("Zeile,Spalte         : " + ep.getLineNumber() + "," + ep.getColumnNumber());
        } catch (SAXException e) {// A parsing error occurred; the xml input is not valid
            System.out.println("Da ist eine XML-Invaliditaet in " + filename + " :\n" + e);
        } catch (ParserConfigurationException e) {
            System.out.println("Ein Parser-Konfigurationsproblem.");
        } catch (IOException e) {
            System.out.println("XML-File = " + filename + " konnte nicht geoeffnet werden");
        }
    }
}
