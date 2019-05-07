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
public class SAXParserE_DTD {


    public void parseXmlFile(String filename, MyContentHandlerVAL handler, MyErrorHandlerE ehandler, boolean val) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(val);
            SAXParser saxpars1 = factory.newSAXParser();
            XMLReader read1 = saxpars1.getXMLReader();

            //validate info an Handler weiter geben (Eigenes abstracte klasse über Handler mit val)
            handler.setval(val);


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
