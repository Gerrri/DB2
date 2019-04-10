package DB2.Hilfsklassen;

import DB2.Kunde;
import DB2.SQLHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.List;

class ContentHandlerKunde implements ContentHandler {
    Kunde kunde;
    List<Kunde> kundenList = new ArrayList<>();
    String aktwert;
    SQLHandler sql;

    public void startDocument() {
        System.out.println("Anfang des Parsens.");
    }

    public void endDocument() {
        System.out.println("Ende des Parsens: " + kundenList.size() + " Elemente sind wohlgeformt.");

        sql = SQLHandler.getSQLHandler();


        for(Kunde k : kundenList){
            int knr = k.getKnr();
            String kname = k.getKname();
            int plz = k.getPlz();
            String ort = k.getOrt();
            String strasse = k.getStrasse();
            double kklimit = k.getKklimit();

            sql.insert("ARTIKEL", "VALUES (" +knr + ", " + kname + ", " + plz + ", " + ort + ", " + strasse + ", " + kklimit + ")");
        }


    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        int i;
        String gVl = null;
        String gTy = null;
        String gNam = null;
        String gVl4;
        AttributesImpl a1 = new AttributesImpl(attributes);
        int l1 = a1.getLength();
        System.out.println("-A-> Anfang des Elements: " + qName + " Attributanzahl: " + l1);
        for (i = 0; i < l1; i++) {
            gVl = a1.getValue(i);
            gTy = a1.getType(i);
            gNam = a1.getQName(i);
            // System.out.println("++"+i+". Attribut: "+gNam+" ("+gTy+") : "+gVl);
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
            case "KNR"      :       kunde = new Kunde();
                                    kunde.setKnr(Integer.parseInt(aktwert));
                                    break;

            case "KNAME"    :       kunde.setKname(aktwert);
                                    break;

            case "PLZ"      :       kunde.setPlz(Integer.parseInt(aktwert));
                                    break;

            case "ORT"      :       kunde.setOrt(aktwert);
                                    break;

            case "STRASSE"  :       kunde.setStrasse(aktwert);
                                    break;

            case "KKLIMIT"  :       kunde.setKklimit(Double.parseDouble(aktwert));
                                    kundenList.add(kunde);
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