import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

class JSONParserE {
  
  public void validate(String toValid, String pathSchema) throws IOException{
    try (InputStream inputStream = getClass().getResourceAsStream(pathSchema)) {
      JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
      Schema schema = SchemaLoader.load(rawSchema);
      schema.validate(new JSONObject(toValid)); // throws a ValidationException if this object is invalid
    }
    catch (Exception e) {
       System.out.println(e.getMessage());
    }
    
    
  }
}
