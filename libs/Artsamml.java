
import java.util.List;
import org.codehaus.jackson.annotate.*;

class Artsamml {
  // Anfang Attribute
  private String id;  // must be String (Ektorp)
  private String revision; // must attribute from Ektorp
  List<Artikel> artsatz;
  // Ende Attribute
  
  // Anfang Methoden
  public List<Artikel> getArtsatz() {
    return artsatz;
  }
  
  public void setArtsatz(List<Artikel> artsatz) {
    this.artsatz = artsatz;
  }
  
  @JsonProperty("_id")
  public String getId() {
    return id;
  }
  @JsonProperty("_id")
  public void setId(String id) {
    this.id = id;
  }
  
  @JsonProperty("_rev")
  public String getRevision() {
    return revision;
  }
  @JsonProperty("_rev") 
  public void setRevision(String revision) {
    this.revision = revision;
  }
  
  // Ende Methoden
  
  public void showAll(){
    System.out.println(getRevision());
    System.out.println(getId());
    
    for (Artikel a : artsatz ) {
         System.out.println(a.getId());
         System.out.println(a.getRevision());
         System.out.println(a.getArtnr());
         System.out.println(a.getArtbez());
         System.out.println(a.getPreis());
         System.out.println("------------------");  
    } // end of for
  }
  
}
