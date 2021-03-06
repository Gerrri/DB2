package DB2.Hilfsklassen;

import DB2.Objects.Kunde;
import DB2.SQL.SQLHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

public class ContentHandlerVALKunde extends MyContentHandlerVAL {
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
                        updateKundeAusgabe(k_neu);
                        break;

                    case "PLZ":
                        try {
                            k_neu.setPlz(Integer.parseInt(in_uwert));
                            updateKundeAusgabe(k_neu);
                        } catch (Exception e) {
                            throw new SAXException();
                        }
                        break;

                    case "ORT":
                        k_neu.setOrt(in_uwert);
                        updateKundeAusgabe(k_neu);
                        break;

                    case "STRASSE":
                        k_neu.setStrasse(in_uwert);
                        updateKundeAusgabe(k_neu);
                        break;

                    case "KKLIMIT":
                        try {
                            k_neu.setKklimit(Double.parseDouble(in_uwert));

                            //WENN validierung an und Kunde OK hinzufügen
                            if(val){
                                if(k_neu.validateKunde()){
                                    System.out.println("-I-> Kunde Valide");
                                    updateKundeAusgabe(k_neu);
                                    kundenList.add(k_neu);
                                }else{
                                    throw new SAXException();
                                }
                            } else {
                                updateKundeAusgabe(k_neu);
                                kundenList.add(k_neu);
                            }



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


    public void updateKundeAusgabe(Kunde kunde){
        Kunde akt_kunde;
        akt_kunde = sql.select_Kunde_by_KNR(kunde.getKnr());

        System.out.println("KNR: " + akt_kunde.getKnr() + " | KNAME: " + akt_kunde.getKname() + " | PLZ: "+ akt_kunde.getPlz()+" | ORT: "+ akt_kunde.getOrt() + " | STRASSE: "+ akt_kunde.getStrasse() + " | KKLIMIT: " + akt_kunde.getKklimit());
        sql.update_kunde(kunde);

        akt_kunde = sql.select_Kunde_by_KNR(kunde.getKnr());
        System.out.println("KNR: " + akt_kunde.getKnr() + " | KNAME: " + akt_kunde.getKname() + " | PLZ: "+ akt_kunde.getPlz()+" | ORT: "+ akt_kunde.getOrt() + " | STRASSE: "+ akt_kunde.getStrasse() + " | KKLIMIT: " + akt_kunde.getKklimit());
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        String h = null;
        h = new String(ch, start, length);
        // System.out.println("-> Start: "+start+" Laenge: "+length+" : "+h+" .");
        aktwert = h;
    }

    /*akutell unnötig?*/
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