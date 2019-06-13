package DB2.CouchDB;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class CouchDBRepo extends CouchDbRepositorySupport<DBArtikel16> {

    public CouchDBRepo(CouchDbConnector db) {
        super(DBArtikel16.class, db);
    }

}