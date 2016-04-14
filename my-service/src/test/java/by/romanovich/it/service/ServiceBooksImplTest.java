package by.romanovich.it.service;

import by.romanovich.it.dao.DaoBooksImpl;
import by.romanovich.it.pojos.books.Books;
import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ServiceBooksImplTest extends Assert{

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoBooksImpl daoBooks = DaoBooksImpl.getDaoBooks(con);

    @Test
    public void testGetServiceBooks() throws Exception {
        assertNotNull(daoBooks);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Books> list = daoBooks.readAll();
        for(Books books : list)
            assertNotNull(books);
    }
}