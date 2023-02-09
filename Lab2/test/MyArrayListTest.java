import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    /*
    TODO: tests
    - Make sure you have 100% code coverage '
    - Make sure you test the full functionality of this class...think edge cases
    - Use JUnit
    - Feel free to add more test methods
     */

    @Test
    public void Test(){
        MyArrayList arr=new MyArrayList();
        for(int i=0;i<10;i++){
            assertEquals(0,arr.get(i));
        }
        Throwable throwable1=assertThrows(IndexOutOfBoundsException.class,()->{
            arr.delete(20);
        });
        assertEquals(IndexOutOfBoundsException.class,throwable1.getClass());
        Throwable throwable2=assertThrows(IndexOutOfBoundsException.class,()->{
            arr.delete(-1);
        });
        assertEquals(IndexOutOfBoundsException.class,throwable2.getClass());
        Throwable throwable3=assertThrows(IndexOutOfBoundsException.class,()->{
            arr.insert(-1,10);
        });
        assertEquals(IndexOutOfBoundsException.class,throwable3.getClass());
        Throwable throwable4=assertThrows(IndexOutOfBoundsException.class,()->{
            arr.insert(20,10);
        });
        assertEquals(IndexOutOfBoundsException.class,throwable4.getClass());
        for(int i=0;i<10;i++){
            arr.insert(i,i+1);
        }
        for(int i=0;i<10;i++){
            assertEquals(i+1,arr.get(i));
        }
        arr.insert(10,11);
        assertEquals(11,arr.get(10));
        for(int i=0;i<11;i++){
            System.out.println(arr.get(i));
        }
        arr.delete(0);

        assertEquals(2,arr.get(0));
        for(int i=0;i<11;i++){
            System.out.println(arr.get(i));
        }
        Throwable throwable5=assertThrows(IndexOutOfBoundsException.class,()->{
            arr.get(1000);
        });
        assertEquals(IndexOutOfBoundsException.class,throwable5.getClass());
        Throwable throwable6=assertThrows(IndexOutOfBoundsException.class,()->{
            arr.get(-2);
        });
        assertEquals(3,arr.search(5));
        assertEquals(-1,arr.search(69));
    }
}
