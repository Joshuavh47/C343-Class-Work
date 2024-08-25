import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    @Test
    public void insert(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(-4);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(6);
        arr.add(10);
        arr.add(23);
        assertEquals(a.inOrderList(),arr);
    }

    @Test
    public void delete(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        a.delete(2);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(-4);
        arr.add(1);
        arr.add(3);
        arr.add(6);
        arr.add(10);
        arr.add(23);
        assertEquals(a.inOrderList(),arr);
        //tests deletion of root node as well
        a.delete(1);
        arr.remove((Integer)1);
        assertEquals(a.inOrderList(),arr);

    }

    @Test
    public void search(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        assertEquals(a.search(1).data(),1);
        assertEquals(a.search(2).data(),2);
        assertEquals(a.search(3).data(),3);
        assertEquals(a.search(10).data(),10);
        assertEquals(a.search(6).data(),6);
        assertEquals(a.search(23).data(),23);
        assertEquals(a.search(-4).data(),-4);
    }

    @Test
    public void extractRightMost(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        a.insert(-20);
        a.insert(-3);
        assertEquals(a.extractRightMost(a.getRoot()).data(),-3);
    }

    @Test
    public void extractRightMin(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(3);
        a.insert(2);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        a.insert(-20);
        a.insert(-3);
        assertEquals(a.extractRightMin(a.getRoot()).data(),2);
    }

    @Test
    public void postOrderList(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(-4);
        arr.add(6);
        arr.add(23);
        arr.add(10);
        arr.add(3);
        arr.add(2);
        arr.add(1);
        assertEquals(a.postOrderList(),arr);
    }

    @Test
    public void inOrderList(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(-4);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(6);
        arr.add(10);
        arr.add(23);
        assertEquals(a.inOrderList(),arr);
    }

    @Test
    public void preOrderList(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(1);
        arr.add(-4);
        arr.add(2);
        arr.add(3);
        arr.add(10);
        arr.add(6);
        arr.add(23);
        assertEquals(a.preOrderList(),arr);
    }

    @Test
    public void getHeight(){
        BST<Integer> a=new BST<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(10);
        a.insert(6);
        a.insert(23);
        a.insert(-4);
        assertEquals(a.getHeight(a.getRoot()),5);
    }
}
