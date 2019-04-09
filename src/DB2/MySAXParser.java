package DB2;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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
            //TODO: Parsen
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
