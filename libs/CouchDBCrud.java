import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.ViewQuery;


import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class CouchDBCrud {
  public static void main(String[] args) throws MalformedURLException, IOException {
    HttpClient httpClient = new StdHttpClient.Builder().url("http://139.6.17.5:5984").build();
    CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
    // if the second parameter is true, the database will be created if it doesn't exists
    CouchDbConnector db = dbInstance.createConnector("Artikelsammlung", true);
    
    Artsamml artsamml=null;
    
    
    while(true) {
      int mainmenue = menue();
      
      switch (mainmenue){
        case 1: //CREATE
        artsamml = createArtsamml();
        db.create(artsamml);
        System.out.println(""+artsamml.getId() +  "Rev: "+artsamml.getRevision());
        break;
        
        case 2: //READ , SELECT ALL
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        List<Artsamml> liste= db.queryView(q, Artsamml.class);
        System.out.println(liste.size());
        for (Artsamml samml : liste ) {
            samml.showAll();
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
        } // end of for
        
        //        
        //        artsamml = db.get(Artsamml.class, artsamml.getId());
        //        System.out.println(artsamml.getArtsatz().get(0).getArtbez());
        //        System.out.println(artsamml.getArtsatz().get(0).getPreis());
        break;
        
        case 3: // UPDATE
        //        sen.setPreis(90);
        //        db.update(sen);
        //        sen = db.get(Sensor.class, id);
        //        System.out.println(sen.getPreis());
        //        System.out.println(sen.getRevision());
        break;
        
        case 4: // DELETE  
        //        db.delete(sen.getId(), sen.getRevision());
        //        db.delete(sen);
        break;
        
        case 5: //Validate
        JSONParserE val = new JSONParserE();
        String json = readJSON("EinArtikel.json");
        val.validate(json, "Artikelschema.json");
        break;
        
        default : System.exit(0);
      }
      
    }
  }
  
  public static int menue() {
    System.out.println("=================================================");
    System.out.println("=           1. INSERT Artsammlung               =");
    System.out.println("=           2. SELECT ALL Artsammlung           =");
    System.out.println("=           3. UPDATE a Sensor                  =");
    System.out.println("=           4. DELETE a Sensor                  =");
    System.out.println("=           5. Validate                         =");
    System.out.println("=           0. Exit                             =");
    System.out.println("=================================================");
    
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int i=-1;
    do {
      System.out.println("Bitte Auswahl treffen (0-5):");
      
      try {
        i = Integer.parseInt(in.readLine());
      } catch (NumberFormatException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
    } while  (i <0 || i > 5);
    
    
    return i;
  }
  
  
  public static Artsamml createArtsamml() {
    Artsamml s = new Artsamml();
    s.setArtsatz(new ArrayList<Artikel>());
    
    Artikel a = new Artikel();
    a.setArtnr(1);
    a.setArtbez("Apfel");
    a.setPreis(0.99);
    
    s.getArtsatz().add(a);
    
    a = new Artikel();
    a.setArtnr(2);
    a.setArtbez("Birne");
    a.setPreis(1.99);
    
    s.getArtsatz().add(a);
    
    a = new Artikel();
    a.setArtnr(3);
    a.setArtbez("Kiwi");
    a.setPreis(1.99);
    
    s.getArtsatz().add(a);
    
    return s;
    
  }
  
  public static String readJSON(String filename) throws IOException{
    FileReader fr = new FileReader(filename);
    BufferedReader in = new BufferedReader(fr);
    String line = in.readLine(); 
    
    String json="";    
    while (line != null) { 
      System.out.println(line);
      json += line;
      line = in.readLine();
    } // end of while
    fr.close();
    return json;
  }
  
  
  
}
