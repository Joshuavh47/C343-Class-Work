public class Main {
    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);


        System.out.println(tree.getPreOrderStr());
        System.out.println(tree.getInOrderStr());
        System.out.println(tree.getPostOrderStr());

    }
}
