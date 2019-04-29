package DB2;

import DB2.Hilfsklassen.*;

public class Main {

    public static void main(String[] args){
        SAXParserE parser = new SAXParserE();
/*
        // test Artikel insert
        ContentHandlerVALArtikel ac_handler = new ContentHandlerVALArtikel();
        MyErrorHandlerE ae_handler = new MyErrorHandlerE();
        parser.parseXmlFile("artikel.xml",ac_handler,ae_handler,true);

*/

        // test Kunden Update
        ContentHandlerVALKunde kc_handler = new ContentHandlerVALKunde();
        MyErrorHandlerE ce_handler = new MyErrorHandlerE();
        parser.parseXmlFile("ukunde.xml",kc_handler, ce_handler, true);


    }
}
