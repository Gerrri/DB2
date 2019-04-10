package DB2;

import DB2.Hilfsklassen.*;

public class Main {

    public static void main(String[] args){
        SAXParserE parser = new SAXParserE();

        ContentHandlerArtikel c_handler = new ContentHandlerArtikel();
        MyErrorHandlerE e_handler = new MyErrorHandlerE();
        parser.parseXmlFile("artikel.xml",c_handler,e_handler,true);

    }
}
