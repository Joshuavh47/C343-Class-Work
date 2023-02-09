import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    /*
    TODO: Write an efficiency test which asserts that the SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
    Also be sure to write tests that check whether your SearchEngine has accurate results.
     */
    @Test
    public void isFaster() throws IOException{


        int mode1 = 1;


        long start1 = System.currentTimeMillis();
        SearchEngine engine1 = new SearchEngine(mode1);

        String term = "virus";
        engine1.search(term);
        long end1 = System.currentTimeMillis();

        long time1=end1-start1;

        int mode2 = 2;


        long start2 = System.currentTimeMillis();
        SearchEngine engine2 = new SearchEngine(mode2);

        term = "virus";
        engine2.search(term);
        long end2 = System.currentTimeMillis();

        long time2=end2-start2;

        assertEquals(true,time2<time1);


    }
}
