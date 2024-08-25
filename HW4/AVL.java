import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class AVL<E extends Comparable<E>> implements Tree<E>{

    private int height;
    private int size;
    private BinaryNode<E> root;
    private int RRotations; // this will be used to see if the amount of rotations was correct
    private int LRotations; // this will be used to see if the amount of rotations was correct

    public AVL(){
        this.root = null;
        this.height = 0;
        this.size = 0;
        this.RRotations = 0;
        this.LRotations = 0;
    }

    public AVL(BinaryNode<E> root){
        this.root = root;
        this.height = root.height();
        this.size = root.size();
        this.RRotations = 0;
        this.LRotations = 0;
    }

    // Access fields
    public int getRRotations(){
        return this.RRotations;
    }
    public int getLRotations(){
        return this.LRotations;
    }
    public BinaryNode<E> root() {
        return this.root;
    }
    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    public BinaryNode<Node> searchNode(String elem){
        if(root==null){
            return null;
        }
        else {
            return searchNode(elem,(BinaryNode<Node>)root);
        }
    }
    public BinaryNode<Node> searchNode(String elem, BinaryNode<Node> curNode){
        BinaryNode<Node> temp=curNode;
        if(elem.equals(temp.data().getKeyword())){
            return temp;
        }
        else if(elem.compareTo(temp.data().getKeyword())>0){
            if(temp.right()==null){
                return null;
            }
            else{
                return searchNode(elem,temp.right());
            }
        }
        else{
            if(temp.left()==null){
                return null;
            }
            else{
                return searchNode(elem,temp.left());
            }

        }
    }

    // TODO: updateHeight - same as BST
    public void updateHeight() {
        int leftHeight=getHeight(root.left());
        int rightHeight=getHeight(root.right());
        if(leftHeight>=rightHeight){
            height=leftHeight+1;
        }
        else{
            height=rightHeight+1;
        }
    }
    public int getHeight(BinaryNode<E> node){
        if(node==null){
            return 0;
        }
        int leftHeight=0;
        int rightHeight=0;
        if(node.left()!=null){
            leftHeight=getHeight(node.left());
        }
        if(node.right()!=null){
            rightHeight=getHeight(node.right());
        }
        if(leftHeight>=rightHeight){
            return leftHeight+1;
        }
        else{
            return rightHeight+1;
        }
    }

    public BinaryNode<E> getRoot(){
        return root;
    }


    // Traversals that return lists
    // TODO: Preorder traversal
    public List<E> preOrderList() {
        return new ArrayList<>();
    }
    private String getPreOrderStr(BinaryNode<E> curNode) {
        Stack<BinaryNode<E>> stack=new Stack<BinaryNode<E>>();
        //pushed root node into stack so that it can be traversed, then called second helper function that contains the
        //stack and return String
        stack.push(root);
        return getPreOrder(curNode,stack,"");

    }



    private String getPreOrder(BinaryNode<E> curNode, Stack<BinaryNode<E>> stack,String str){
        //since Stacks are read first in, last out, I used that to store the nodes iteratively so that all the
        //nodes on the left are popped (and therefore added to the return String) before the nodes on the right
        //I felt like using a stack was the best solution for this because storing the return String recursively
        //would've been kind of messy
        String strOut=str;
        while(!stack.empty()) {
            BinaryNode<E> temp=stack.pop();
            strOut=strOut+temp.data()+", ";

            if(temp.getRight()!=null){
                stack.push(temp.right());
            }
            if(temp.getLeft()!=null){
                stack.push(temp.left());
            }
        }
        return strOut;
    }

    // TODO: Inorder traversal
    public List<E> inOrderList() {
        return new ArrayList<>();
    }

    // TODO: Postorder traversal
    public List<E> postOrderList() {
        return new ArrayList<>();
    }


    /*
    TODO: rotateRight
     *              x                          y
     *            /   \                      /   \
     *           y     C     ===>           A     x
     *         /   \                             /  \
     *        A    B                            B    C
     * You should never rotateRight if the left subtree is empty.
     * Make sure you increment the RRotations.
    */
    public void rotateRight(BinaryNode<E> node){
        BinaryNode<E> temp=node.left();
        BinaryNode<E> leftChild = node.left();

        node.setLeft(leftChild.right());
        leftChild.setRight(node);

        RRotations++;
    }

    /*
     TODO: rotateLeft
      *              x                          y
      *            /   \                      /   \
      *           y     C     <==           A     x
      *         /   \                             /  \
      *        A    B                            B    C
      * You should never rotateLeft if the right subtree is empty.
      * Make sure you increment the LRotations.
      * Symmetrical to above.
     */
    public void rotateLeft(BinaryNode<E> node){
        BinaryNode<E> temp=node.right();
        BinaryNode<E> rightChild = node.right();
        node.setRight(rightChild.left());
        rightChild.setLeft(node);
        LRotations++;
    }

    /*
     TODO: possibleRotateRight
      * If the current node is unbalanced with the right tree height being smaller
      * than the left subtree height, rotate right. Otherwise, don't do anything.
    */
    public void possibleRotateRight(BinaryNode<E> node){
        int rightHeight=getHeight(node.right());
        int leftHeight=getHeight(node.left());
        if(leftHeight-rightHeight>=2){
            rotateRight(node);
        }
    }

    /*
     TODO: possibleRotateLeft
      * If the current node is unbalanced with the left tree height being smaller
      * than the right subtree height, rotate left. Otherwise, don't do anything.
    */
    public void possibleRotateLeft(BinaryNode<E> node){
        int rightHeight=1;
        if(node.right()!=null){
            rightHeight=getHeight(node.right());
        }

        int leftHeight=1;
        if(node.left()!=null){
            leftHeight=getHeight(node.left());
        }
        if(leftHeight-rightHeight<=-2){
            rotateLeft(node);
        }
    }

    /*
     TODO: mkBalanced
      * Given a node, balance it if the heights are unbalanced.
      * Hint: rotations!!!
    */
    public void mkBalanced(BinaryNode<E> node){
        int balanceFactor = balanceFactor(node);
        if (balanceFactor < -1) {
            if (node.left()!=null&&balanceFactor(node.left()) <= 0) {

                rotateRight(node);
            }
            else {
                rotateLeft(node.left());
                rotateRight(node);
            }
        }

        if (balanceFactor > 1) {
            if (node.right()!=null&&balanceFactor(node.right()) >= 0) {
                rotateLeft(node);
            }
            else {
                rotateRight(node.right());
                rotateLeft(node);
            }
        }
    }

    public int balanceFactor(BinaryNode<E> node){
        int leftHeight=0;
        if(node.left()!=null){
            leftHeight=getHeight(node.left());
        }
        int rightHeight=0;
        if(node.right()!=null){
            rightHeight=getHeight(node.right());
        }
        return leftHeight-rightHeight;
    }


    // Helpers for BST/AVL methods
    // TODO: extractRightMost - identical to BST
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        return null;
    }

    // AVL & BST Search & insert same
    // TODO: search - identical to BST
    public BinaryNode<E> search(E elem) {
        return null;
    }

    /*
     TODO: insert - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */

    public void insert(E elem) {
        if(root==null){
            root=new BinaryNode<E>(elem);
        }
        else{
            insert(elem,root);
        }
    }
    private void insert(E elem, BinaryNode<E> curNode) {
        BinaryNode<E> temp=curNode;
        ArrayList<BinaryNode<E>> arr=new ArrayList<BinaryNode<E>>();
        boolean inserted=false;
        while(!inserted){
            if(elem.compareTo(temp.data())<0){
                //arr.add(temp);
                if(temp.left()==null){
                    temp.setLeft(new BinaryNode<E>(elem));
                    temp.left().setParent(temp);
                    inserted=true;
                }
                else{
                    temp=temp.left();
                }
            }
            else{
                //arr.add(temp);
                if(temp.right()==null){
                    temp.setRight(new BinaryNode<E>(elem));
                    temp.right().setParent(temp);
                    inserted=true;
                }
                else{
                    temp=temp.right();
                }
            }
        }
        size++;
        updateHeight();
        mkBalanced(root);
    }

    public BinaryNode<E> extractRightMin(BinaryNode<E> curNode){
        BinaryNode<E> temp=curNode.right();
        while(temp.left()!=null){
            temp=temp.left();
        }
        return temp;
    }

    /*
     TODO: delete - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
    public BinaryNode<E> delete(E elem) {
        if(root==null){
            return null;
        }
        else{
            return delete(elem,root);
        }
    }

    public BinaryNode<E> delete (E elem, BinaryNode<E> curNode){
        BinaryNode<E> temp=curNode;
        BinaryNode<E> searchNode=search(elem);
        if(searchNode==null){
            return null;
        }
        if(searchNode==root){
            if(root.left()!=null&&root.right()!=null) {
                temp = extractRightMin(root);
                temp.parent().setLeft(null);
                temp.setLeft(root.left());
                temp.setRight(root.right());
                root.setLeft(null);
                root.setRight(null);
                root = temp;
            }
            if(root.left()!=null&&root.right()==null){
                root=root.left();
            }
            else if(root.getLeft()==null&&root.getRight()!=null){
                root=root.right();
            }
        }
        if(searchNode.right()!=null&&searchNode.left()!=null){
            BinaryNode<E> replacement=extractRightMin(searchNode);
            temp=searchNode.parent();
            if(temp.left()!=null&&elem.compareTo(temp.left().data())==0){
                temp.setLeft(replacement);
                if(elem.compareTo(replacement.parent().left().data())==0){
                    replacement.parent().setLeft(null);
                }
                else if(elem.compareTo(replacement.parent().right().data())==0){
                    replacement.parent().setRight(null);
                }
                replacement.setParent(temp);
            }
            if(elem.compareTo(temp.right().data())==0){
                temp.setRight(replacement);
                if(elem.compareTo(replacement.parent().left().data())==0){
                    replacement.parent().setLeft(null);
                }
                else if(elem.compareTo(replacement.parent().right().data())==0){
                    replacement.parent().setRight(null);
                }
                replacement.setParent(temp);
            }
        }
        else if(searchNode.left()!=null&&searchNode.right()==null){
            if(searchNode.data().compareTo(searchNode.parent().left().data())==0){
                searchNode.parent().setLeft(searchNode.left());
            }
            else if(searchNode.data().compareTo(searchNode.parent().right().data())==0){
                searchNode.parent().setRight(searchNode.right());
            }
            searchNode.left().setParent(searchNode.parent());

        }
        else if(searchNode.left()==null&&searchNode.right()!=null){
            if(searchNode.parent().left()!=null&&searchNode.data().compareTo(searchNode.parent().left().data())==0){
                searchNode.parent().setLeft(searchNode.left());
            }
            else if(searchNode.parent().right()!=null&&searchNode.data().compareTo(searchNode.parent().right().data())==0){
                searchNode.parent().setRight(searchNode.right());
            }
            searchNode.right().setParent(searchNode.parent());
        }
        size--;
        updateHeight();
        mkBalanced(root);
        return searchNode;
    }

    // Stuff to help you debug if you want
    // Can ignore or use to see if it works.
    static <E extends Comparable<E>> Tree<E> mkAVL (Collection<E> elems) {
        Tree<E> result = new AVL<>();
        for (E e : elems) result.insert(e);
        return result;
    }
    public TreePrinter.PrintableNode getLeft() {
        return this.root.hasLeft() ? this.root.left() : null;
    }
    public TreePrinter.PrintableNode getRight() {
        return this.root.hasRight() ? this.root.right() : null;
    }
    public String getText() {
        return (this.root != null) ? this.root.getText() : "";
    }

}
