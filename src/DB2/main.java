package DB2;

import DB2.CouchDB.CSV_converter;
import DB2.CouchDB.CouchArtikel16;
import DB2.Hilfsklassen.*;
import DB2.SQL.SQLHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

    public static void main(String[] args) throws IOException {
        SAXParserE_DTD saxParserE_dtd = new SAXParserE_DTD();
        SAXParserE_XSD saxParserE_xsd = new SAXParserE_XSD();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        int auswahl;
        boolean update = false;
        String temp;

        do {
            System.out.println("Wofuer wollen Sie das Programm verwenden?");
            System.out.println("Bitte geben Sie '1' ein, wenn Sie Artikel hinzufuegen wollen. [DTD]");
            System.out.println("Bitte geben Sie '2' ein, wenn Sie Kunden updaten wollen.");
            System.out.println("Bitte geben Sie '3' ein, wenn Sie Artikel hinzufuegen wollen. [XSD]");
            System.out.println("Bitte geben Sie '4' ein, wenn Sie Bestellungen hinzufuegen wollen. [DTD]");
            System.out.println("Bitte geben Sie '6' ein, wenn Sie CouchDB Funktionen nutzen wollen");

            auswahl = Integer.parseInt(br1.readLine());
            if(auswahl == 2){
                System.out.println("Waehlen Sie bitte, ob Sie das Update zuruecksetzen wollen, oder ob Sie es ausführen wollen!");
                System.out.println("Bitte geben Sie 0 ein, wenn Sie es zuruecksetzen wollen!");
                System.out.println("Bitte geben SIe 1 ein, wenn Sie es ausfuehren wollen!");
                temp = br1.readLine();
                if(Integer.parseInt(temp)==0){
                    update =  false;
                }else if(Integer.parseInt(temp)==1){
                    update = true;
                }else{
                    System.out.println("Falsche Eingabe!");
                    auswahl = 3;
                }
            }
        }while(!(auswahl == 1 || auswahl == 2 || auswahl == 3 || auswahl == 4 || auswahl == 5 || auswahl == 6 || auswahl == 7));


        ContentHandlerVALArtikel ac_handler;
        MyErrorHandlerE ae_handler;
        switch (auswahl){
            case 1:
                // test Artikel insert DTD
                ac_handler = new ContentHandlerVALArtikel();
                ae_handler = new MyErrorHandlerE();
                saxParserE_dtd.parseXmlFile("artikel.xml",ac_handler,ae_handler,true);
                break;

            case 2:
                // test Kunden Update
                ContentHandlerVALKunde kc_handler = new ContentHandlerVALKunde();
                MyErrorHandlerE ce_handler = new MyErrorHandlerE();
                if(update == true){
                    saxParserE_dtd.parseXmlFile("ukunde.xml",kc_handler, ce_handler, true);
                }else{
                    saxParserE_dtd.parseXmlFile("resettukunde.xml",kc_handler, ce_handler, true);
                }

            case 3:
                // test Artikel insert XSD
                ac_handler = new ContentHandlerVALArtikel();
                ae_handler = new MyErrorHandlerE();
                saxParserE_xsd.parseXmlFile("ARTIKEL1.xml",ac_handler,ae_handler,true);
                break;

            case 4:
                // test Bestellung insert XSD
                ContentHandlerVALBestellung bc_handler = new ContentHandlerVALBestellung();
                MyErrorHandlerE be_handler = new MyErrorHandlerE();
                saxParserE_xsd.parseXmlFile("bestellung.xml",bc_handler,be_handler,true);
                break;

            case 5:
                SQLHandler handler = SQLHandler.getSQLHandler();
                handler.selectBestellung(1,10);
                break;

            case 6:
                CouchArtikel16 ca = new CouchArtikel16();
                ca.menu();
                break;

            case 7:

        }
    }
}
