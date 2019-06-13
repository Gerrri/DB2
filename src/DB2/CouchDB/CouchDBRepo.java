package DB2.CouchDB;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class CouchDBRepo extends CouchDbRepositorySupport<Artikel> {

    public CouchDBRepo(CouchDbConnector db) {
        super(Artikel.class, db);
    }

}