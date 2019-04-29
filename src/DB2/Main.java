package DB2;

import DB2.Hilfsklassen.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        SAXParserE parser = new SAXParserE();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        int auswahl;
        boolean update = false;
        String temp;

        do {
            System.out.println("Wofuer wollen Sie das Programm verwenden?");
            System.out.println("Bitte geben Sie '1' ein, wenn Sie Artikel hinzufuegen wollen.");
            System.out.println("Bitte geben Sie '2' ein, wenn Sie Kunden updaten wollen.");
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
        }while(!(auswahl == 1 || auswahl == 2));

        switch (auswahl){
            case 1:
                // test Artikel insert
                ContentHandlerVALArtikel ac_handler = new ContentHandlerVALArtikel();
                MyErrorHandlerE ae_handler = new MyErrorHandlerE();
                parser.parseXmlFile("artikel.xml",ac_handler,ae_handler,true);
                break;
            case 2:
                // test Kunden Update
                ContentHandlerVALKunde kc_handler = new ContentHandlerVALKunde();
                MyErrorHandlerE ce_handler = new MyErrorHandlerE();
                if(update == true){
                    parser.parseXmlFile("ukunde.xml",kc_handler, ce_handler, true);
                }else{
                    parser.parseXmlFile("resettukunde.xml",kc_handler, ce_handler, true);
                }

        }







    }
}
