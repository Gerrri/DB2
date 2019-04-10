package DB2.Hilfsklassen;

import DB2.Kunde;
import DB2.SQLHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ContentHandlerKunde implements ContentHandler {
    Kunde kunde;
    List<Kunde> kundenList = new ArrayList<>();
    String aktwert;
    SQLHandler sql = SQLHandler.getSQLHandler();;

    //Update infos
    private int in_knr;
    private String in_usp;
    private String in_dtsup;
    private String in_uwert;




    public void startDocument() {
        System.out.println("Anfang des Parsens.");
    }

    public void endDocument() {
        System.out.println("Ende des Parsens: " + kundenList.size() + " Elemente sind wohlgeformt.");




        /*
        for(Kunde k : kundenList){
            int knr = k.getKnr();
            String kname = k.getKname();
            int plz = k.getPlz();
            String ort = k.getOrt();
            String strasse = k.getStrasse();
            double kklimit = k.getKklimit();

            sql.insert("ARTIKEL", "VALUES (" +knr + ", " + kname + ", " + plz + ", " + ort + ", " + strasse + ", " + kklimit + ")");
        }
        */

        //Update auf Kunde

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


        if(qName.equalsIgnoreCase("UPDKUNDE")) {

            in_knr = Integer.parseInt(attributes.getValue("KNR"));  //  knr
            in_usp = attributes.getValue("USP");                    //  Spalte
            in_dtsup = attributes.getValue("DTUSP");                //  Datentyp
            in_uwert = attributes.getValue("UWERT");                //  neuer Wert


            //alle Kunden holen
            Kunde k_neu = sql.select_Kunde_by_KNR(in_knr);

            //logik k_neu aktualisieren

            if (k_neu != null) {

                switch (in_usp) {
                    case "KNAME":
                        k_neu.setKname(in_uwert);
                        sql.update_kunde(k_neu);
                        break;

                    case "PLZ":
                        try {
                            k_neu.setPlz(Integer.parseInt(in_uwert));
                            sql.update_kunde(k_neu);
                        } catch (Exception e) {
                            throw new SAXException();
                        }
                        break;

                    case "ORT":
                        k_neu.setOrt(in_uwert);
                        sql.update_kunde(k_neu);
                        break;

                    case "STRASSE":
                        k_neu.setStrasse(in_uwert);
                        sql.update_kunde(k_neu);
                        break;

                    case "KKLIMIT":
                        try {
                            k_neu.setKklimit(Double.parseDouble(in_uwert));
                            sql.update_kunde(k_neu);
                        } catch (Exception e) {
                            throw new SAXException();
                        }
                        break;


                    default:
                        break;
                }
            }
        }

    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        String h = null;
        h = new String(ch, start, length);
        // System.out.println("-> Start: "+start+" Laenge: "+length+" : "+h+" .");
        aktwert = h;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("-E-> Ende des Elements: " + qName + " .");
        //System.out.println("aktwert: "+aktwert);

        switch(qName){
            case "KNR"      :       in_knr = Integer.parseInt(aktwert);
                break;

            case "USP"    :         in_usp = aktwert;
                break;

            case "DTUSP"      :     in_dtsup = aktwert;
                break;

            case "UWERT"      :     in_uwert = aktwert;
                break;
        }
    }

    public void skippedEntity(String name) throws SAXException { //System.out.println("-S-> Skipped Entity: "+name+" .");
    }

    public void processingInstruction(String target, String data) throws SAXException { //System.out.println("-P-> Process_Instr: "+target+" "+data+" .");
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { //System.out.println("-W-> Whitespace: Pos: "+start+" Laenge: "+length+" .");
    }

    public void endPrefixMapping(String prefix) throws SAXException { //System.out.println("-Pr-> Praefix: "+prefix);
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException { //System.out.println("-PrS-> Praefix: "+prefix);
    }

    public void setDocumentLocator(Locator locator) { //System.out.println("-L-> Locator: "+locator.toString());
    }
}