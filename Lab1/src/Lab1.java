public class Lab1 {
    /*
    TODO: Task 1
    -------------------
    Given a non-negative int n, return the sum of its digits recursively (no loops).
    ====== Hint =======
    (Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6),
    while divide (/) by 10 removes the rightmost digit (126 / 10 is 12)).
    ===== Example =====
    getDigitSum(126)
        > The sum of digits is 9.
    --------------------
     */
    public static int getDigitSum(int number) {
        if(number>0) {
            int x = number % 10;
            number /= 10;
            return getDigitSum(number) + x;
        }
        else{
            return 0;
        }
    }


    /*
    TODO: Task 2
    -------------------
    Given a non-negative int n, return the count of the occurrences of 7 as a digit,
    so for example 717 yields 2. (no loops).
    ====== Hint =======
    Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6),
    while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).
    ===== Example =====
    countSevens(717)
        > 7 occurs 2 time(s)
    --------------------
     */

    public static int countSevens(int number) {
        if(number==0){
            return 0;
        }
        int x=number%10;
        if(x==7){
            return 1 + countSevens(number/10);
        }
        else {
            return countSevens(number/10);
        }
    }


    /*
    TODO: Task 3
    -------------------
    Inside a computer system, integers are represented as a sequence of bits,
    each of which is a single digit in the binary number system and can therefore have only the value 0 or 1.
    The table below shows the first few integers represented in binary:
            0 -> 0
            1 -> 1
           10 -> 2
           11 -> 3
          100 -> 4
          101 -> 5
          110 -> 6
    Each entry in the left side of the table is written in its standard binary representation,
    in which each bit position counts for twice as much as the position to its right.
    For instance, you can demonstrate that the binary value 110 represents the decimal number 6 by following this logic:
           place value -> 4      2      1
                          x      x      x
         binary digits -> 1      1      0
                          v      v      v
                          4  +   2   +  0 = 6
                        1*2^2 + 1*2^1 + 0*2^0 = 6
    Binary is a base-2 number system instead of the decimal (base-10) system we are familiar with.
    Write a recursive function PrintInBinary(int num) that prints the binary representation for a given integer.
    For example, calling PrintInBinary(5) would print 101. Your function may assume the integer parameter is non-negative.
    ====== Hint =======
    The recursive insight for this problem is to realize you can identify the least significant binary digit
    by using the modulus operator with value 2. For example, given the integer 35, mod by 2 tells you that the last binary
    digit must be 1 (i.e. this number is odd), and division by 2 gives you the remaining portion of the integer (17).
    What 's the right way to handle the remaining portion?
    What is the simplest possible number(s) to print in binary that can be used as the base case?
    ===== Example =====
    Enter a number to convert to binary: 6
    6 in binary is 110
    --------------------
    */
    public static int printInBinary(int number) {
        if(number==0){
            return 0;
        }
        if(number%2==0){
            return 10*printInBinary(number/2);
        }
        else if (number%2==1){
            return 10*printInBinary(number/2)+1;
        }
        return 0;
    }


    /*
    TODO: Task 4
    -------------------
    Write multiplication between two non-negative ints without using *.
    ====== Hint =======
    Use basic math properties.
    ===== Example =====
    multiply(6, 7) = 42
    --------------------
     */

    public static int multiply(int a, int b){
        if(b>0) {
            return a + multiply(a, b - 1);
        }
        else{
            return 0;
        }
    }
}
