package DB2.Hilfsklassen;

import DB2.Objects.Artikel;
import DB2.Objects.Bestellung;
import DB2.SQL.SQLHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.List;

public class ContentHandlerVALBestellung extends MyContentHandlerVAL{
    private List<Bestellung> bestllungList = new ArrayList<>();
    private SQLHandler sql;
    private String aktwert;
    private Bestellung bestellung;
    private Artikel artikel;

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Anfang des Parsens.");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Ende des Parsens: " + bestllungList.size() + " Elemente sind wohlgeformt.");

        sql = SQLHandler.getSQLHandler();

        for(Bestellung a : bestllungList){
            sql.insert_bestellung(a);
        }

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        int i;
        String gVl = null;
        String gTy = null;
        String gNam = null;
        String gVl4;
        AttributesImpl a1 = new AttributesImpl(atts);
        int l1 = a1.getLength();
        System.out.println("-A-> Anfang des Elements: " + qName + " Attributanzahl: " + l1);
        for (i = 0; i < l1; i++) {
            gVl = a1.getValue(i);
            gTy = a1.getType(i);
            gNam = a1.getQName(i);
            // System.out.println("++"+i+". Attribut: "+gNam+" ("+gTy+") : "+gVl);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("-E-> Ende des Elements: " + qName + " .");
        //System.out.println("aktwert: "+aktwert);
        try {


            switch (qName) {
                case "KNR":
                    bestellung = new Bestellung();
                    bestellung.setkNR(Integer.parseInt(aktwert));
                    break;

                case "STATUS":
                    bestellung.setStatus(Integer.parseInt(aktwert));
                    break;

                // ab hier Artikel
                case "ARTNR":
                    artikel = sql.select_Artikel_by_ARTNR(Integer.parseInt(aktwert));

                    //WENN validierung an und Artikel OK hinzufügen
                    if (val) {
                        if (artikel.validateArtikel()) {
                            System.out.println("-I-> Artikel Valide");
                            bestellung.addArt(artikel);
                        } else {
                            throw new SAXException();
                        }
                    } else {
                        bestellung.addArt(artikel);
                    }
                    break;

                case "BESTELLUNG2":
                    bestllungList.add(bestellung);
                    break;

            }
        }catch (Exception e){
            //Wenn oben irgendetwas nicht funktioniert (Parsen, checken etc)
            System.out.println("ERROR : " + e.getMessage());
            throw new SAXException();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String h = null;
        h = new String(ch, start, length);
        // System.out.println("-> Start: "+start+" Laenge: "+length+" : "+h+" .");
        aktwert = h;
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }



}
