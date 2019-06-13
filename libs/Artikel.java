import org.codehaus.jackson.annotate.*;

class Artikel {
  
  // Anfang Attribute
  private String id;  // must be String (Ektorp)
  private String revision; // must attribute from Ektorp
  int artnr;
  String artbez;
  double preis;
  // Anfang Methoden
  
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
  
  public int getArtnr() {
    return artnr;
  }
  
  public void setArtnr(int artnr) {
    this.artnr = artnr;
  }
  
  public String getArtbez() {
    return artbez;
  }
  
  public void setArtbez(String artbez) {
    this.artbez = artbez;
  }
  
  public double getPreis() {
    return preis;
  }
  
  public void setPreis(double preis) {
    this.preis = preis;
  }
  
  // Ende Methoden
}
