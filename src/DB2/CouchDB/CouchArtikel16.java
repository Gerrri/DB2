package DB2.CouchDB;

import DB2.SQL.SQLHandler;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

public class CouchArtikel16 {


    public void menu() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder().url("http://feuerbach.nt.fh-koeln.de:5984").build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        // if the second parameter is true, the database will be created if it doesn't exists
        //CouchDbConnector db = dbInstance.createConnector("artikel16", true);
        CouchDbConnector db = new StdCouchDbConnector("artikel16", dbInstance);
        //db.createDatabaseIfNotExists();

        CouchDBRepo repo = new CouchDBRepo(db);

        int choice = 0;
        do {

            System.out.println("====================================================");
            System.out.println("=        1. ARTIKEL.csv erstellen (SQL) + einlesen =");
            System.out.println("=        2. Alle Artikel aus CouchDB lesen         =");
            System.out.println("=        3. Artikel nach ID lesen                  =");
            System.out.println("=        4. Artikel veraendern                     =");
            System.out.println("=        5. Artikel loeschen                       =");
            System.out.println("=        0. Exit                                   =");
            System.out.println("====================================================");

            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException ex) {
                choice = -1;
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n");

            switch (choice) {
                case 1: c1(repo);break;
                case 2: c2(repo);break;
                case 3: c3(repo);break;
                case 4: c4(repo);break;
                case 5: c5(repo);break;

                case 0:
                    System.out.println("Auf Wiedersehen");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ungültige eingabe");

            }
            
        }while (true);
    }

    private void c1(CouchDBRepo repo) throws IOException {
        //CSV erzeugen
        CSV_converter.getInstance().artikelliste_to_CSV(SQLHandler.getSQLHandler().select_alle_Artikel());
        System.out.println("<---- Erfolgreich ---->");

        //CSV auslesen und in CouchDB
        BufferedReader fileReader = new BufferedReader(new FileReader("ARTIKEL.csv"));
        String line;
        while ((line = fileReader.readLine()) != null && !line.equals("")) {
            Artikel artikel = new Artikel();
            String val[] = line.split(";");
            artikel.setArtnr(Integer.parseInt(val[0]));
            artikel.setArtbez(val[1]);
            artikel.setMge(val[2]);
            artikel.setPreis(Double.parseDouble(val[3]));

            for (int i = 4; i < val.length; i += 2) {
                BPos bPos = new BPos();
                bPos.setBestnr(Integer.parseInt(val[i]));
                bPos.setMenge(Integer.parseInt(val[i + 1]));
                artikel.getBPos().add(bPos);
            }
            repo.add(artikel);
        }

        fileReader.close();
        System.out.println("Fertig");
    }

    private void c2(CouchDBRepo repo){
        String format = "%-5s%-15s%-15s%-5s%n";

        List<Artikel> artList = repo.getAll();
        for (Artikel art : artList) {
            System.out.printf(format, art.getArtnr(), art.getArtbez(), art.getMge(), art.getPreis());
            for (BPos pos : art.getBPos()) {
                System.out.printf(format, "", pos.getBestnr(), pos.getMenge(), "");
            }

            System.out.println("");
        }
    }

    private void c3(CouchDBRepo repo){
        String format = "%-5s%-15s%-15s%-5s%n";
        try {
            Artikel artikel = getArtikel_dialog(repo);

            System.out.printf(format, artikel.getArtnr(), artikel.getArtbez(), artikel.getMge(),
                    artikel.getPreis());
            for (BPos pos : artikel.getBPos()) {
                System.out.printf(format, "", pos.getBestnr(), pos.getMenge(), "");
            }
        } catch (DocumentNotFoundException e) {
            System.out.println("kein Dokument mit dieser ID!");
        }
    }

    private void c4(CouchDBRepo repo){
        System.out.println("Welche Artikelbezeichnung soll geändert werden?");
        Artikel artikel = getArtikel_dialog(repo);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Alte Bezeichnung: "+artikel.getArtbez());
        System.out.println("Neue Bezeichnung: ");

        String bez = null;

        try {
            bez = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        artikel.setArtbez(bez);
        repo.update(artikel);

        System.out.println("Artikel Aktualisiert!");
    }


    private void c5(CouchDBRepo repo){
        System.out.println("Welche Artikelsoll gelöscht werden?");
        Artikel artikel = getArtikel_dialog(repo);

        repo.remove(artikel);

        System.out.println("Artikel gelöscht!");
    }

    private Artikel getArtikel_dialog(CouchDBRepo repo){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ID Eingeben: ");
        String id = null;
        do {
            try {
                id = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (id.equals(""));

        return repo.get(id);
    }


}
