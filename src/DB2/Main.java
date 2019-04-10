package DB2;

import DB2.Hilfsklassen.*;

import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;

public class Main {

    public static void main(String[] args){
        SAXParserE parser = new SAXParserE();

        MyContentHandlerE c_handler = new MyContentHandlerE();
        MyErrorHandlerE e_handler = new MyErrorHandlerE();
        parser.parseXmlFile("artikel.xml",c_handler,e_handler,true);

    }
}
