package DB2;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.AttributesImpl;


import org.xml.sax.*;

public class MySAXParser{

    ContentHandler contentHandler = new ContentHandler() {
        private String nutzdaten;

        @Override
        public void setDocumentLocator(Locator locator) {

        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Parsen gestartet!");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("Parsen beendet!");
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
                //System.out.println("++"+i+". Attribut: "+gNam+" ("+gTy+") : "+gVl);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            //TODO:  SQL insert, wenn valide und wohlgeformt
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            nutzdaten = new String(ch, start, length);
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
    };

}
