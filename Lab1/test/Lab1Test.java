import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Lab1Test {

    @Test
    public void Task1Simple(){
        Lab1.getDigitSum(126);// 9
        System.out.println(Lab1.getDigitSum(126));
    }

    @Test
    public void Task2Simple(){
        Lab1.countSevens(7177); // 2
        System.out.println(Lab1.countSevens(7177));
    }

    @Test
    public void Task3Simple(){
        Lab1.printInBinary(6); // 0
        System.out.println(Lab1.printInBinary(6));
    }

    @Test
    public void Task4Simple(){
        Lab1.multiply(6,7); // 0
        System.out.println(Lab1.multiply(7,7));
    }

}
