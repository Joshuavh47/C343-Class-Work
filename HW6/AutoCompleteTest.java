import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AutoCompleteTest {
    @Test
    public void autocomplete() throws IOException{
        //tests the accuracy of prefix autocorrect
        String out="";
        AutoComplete a=new AutoComplete("dataset.txt");
        for(Entry e:a.autoComplete("t")){
            out+=e.toString()+"\n";
        }
        out=out.substring(0,out.length()-1);
        assertEquals("the (frequency: 5182)\nto (frequency: 3491)\nthat (frequency: 1277)\nthen (frequency: 409)\nthere (frequency: 324)\nthis (frequency: 319)",out);
        out="";
        for(Entry e:a.autoComplete("i")){
            out+=e.toString()+"\n";
        }
        out=out.substring(0,out.length()-1);
        assertEquals("i (frequency: 5834)\nin (frequency: 1591)\nit (frequency: 1403)\nif (frequency: 383)\nim (frequency: 294)\ninto (frequency: 274)",out);
    }
}
