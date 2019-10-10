package Classes;

import org.junit.After;
import org.junit.Before;

public class HooksClass {
    @Before
    public void antes(){
        System.out.println("test de antes");
    }

    @After
    public void despues(){
        System.out.println("test de despues");
    }
}
