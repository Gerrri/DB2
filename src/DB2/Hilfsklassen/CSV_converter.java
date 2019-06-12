package DB2.Hilfsklassen;

import DB2.Objects.Artikel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSV_converter {
    private static CSV_converter instance = null;

    private CSV_converter(){}

    public static CSV_converter getInstance() {
        if (instance == null) {
            synchronized(CSV_converter.class) {
                if (instance == null) {
                    instance = new CSV_converter();
                }
            }
        }

        return instance;
    }

    public void artikelliste_to_CSV(List<Artikel> al){

        try {
            FileWriter csvWriter = new FileWriter("Artikel.csv");

            for(Artikel a:al){
                csvWriter.append((char) a.getArtnr());
                csvWriter.append(a.getArtbez());
                csvWriter.append(a.getMge());

            }


            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
