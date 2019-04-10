package DB2;

import DB2.Hilfsklassen.*;

public class Main {

    public static void main(String[] args){
        SAXParserE parser = new SAXParserE();

        // test Artikel insert
        ContentHandlerArtikel ac_handler = new ContentHandlerArtikel();
        MyErrorHandlerE ae_handler = new MyErrorHandlerE();
        parser.parseXmlFile("artikel.xml",ac_handler,ae_handler,true);



        // test Kunden Update
        ContentHandlerKunde kc_handler = new ContentHandlerKunde();
        MyErrorHandlerE ce_handler = new MyErrorHandlerE();
        parser.parseXmlFile("ukunde.xml",kc_handler, ce_handler, true);


    }
}
