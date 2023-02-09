public class Main {
    public static void main(String[] args) throws IndexOutOfBoundsException{
        ArrayList<String> a=new ArrayList<String>();
        a.add("Hello");
        a.add("Hi");
        ArrayList<String> b=new ArrayList<String>();
        b.add("Hello");
        b.add("Hi");
        b.add("Hey");
        b.delete(1);
        System.out.println(b.toString());
        System.out.println(b.get(0));
        System.out.println(a.equals(b));


        SortedArrayList<Integer> a1=new SortedArrayList<Integer>();
        a1.add(3);
        a1.add(2);
        a1.add(7);
        a1.add(10);
        a1.add(1);
        a1.add(5);
        a1.add(20);
        a1.add(5);

        System.out.println(a1.search(1));
        System.out.println(a1.search(2));
        System.out.println(a1.search(3));
        System.out.println(a1.search(5));
        System.out.println(a1.search(7));
        System.out.println(a1.search(10));
        System.out.println(a1.search(20));
        System.out.println(a1.search(-4));
        System.out.println(a1.search(100));


        System.out.println(a1.toString());


        SortedArrayList<String> a2 = new SortedArrayList<String>();
        a2.add("hello");
        a2.add("hey");
        a2.add("howdy");
        a2.add("hi");
        System.out.println(a2.toString());

        System.out.println(a2.search("hello"));
        System.out.println(a2.search("hey"));
        System.out.println(a2.search("hi"));
        System.out.println(a2.search("howdy"));
        System.out.println(a2.search("he"));


    }
}
