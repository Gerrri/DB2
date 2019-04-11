package DB2.Hilfsklassen;

import org.xml.sax.ContentHandler;

public abstract class MyContentHandlerVAL implements ContentHandler {

    boolean val;
    public void setval(boolean validate){
        val = validate;
    }
    public boolean getval(){
        return val;
    }

}
