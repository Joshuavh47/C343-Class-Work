import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class BST<E extends Comparable<E>> implements Tree<E> {

    private int height;
    private int size;
    private BinaryNode<E> root;

    public BST(){
        this.root = null;
        this.height = 0;
        this.size = 0;
    }

    // TODO: BST
    public BST(BinaryNode<E> root){
        this.root=root;
        this.height=1;
        this.size=1;
    }

    // Access field
    public BinaryNode<E> root() {
        return this.root;
    }

    // Basic properties
    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    // TODO: updateHeight - Update the root height to reflect any changes
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

    // Traversals that return lists
    // TODO: Preorder traversal
    public List<E> preOrderList() {
        if(root==null){
            return new ArrayList<E>();
        }
        else{


            return preOrderList(root);
        }
    }
    private List<E> preOrderList(BinaryNode<E> curNode) {
        Stack<BinaryNode<E>> stack=new Stack<BinaryNode<E>>();
        //pushed root node into stack so that it can be traversed, then called second helper function that contains the
        //stack and return String
        stack.push(root);
        return preOrderList(curNode,stack,new ArrayList<E>());

    }
    private List<E> preOrderList(BinaryNode<E> curNode, Stack<BinaryNode<E>> stack,ArrayList arr){
        //since Stacks are read first in, last out, I used that to store the nodes iteratively so that all the
        //nodes on the left are popped (and therefore added to the return String) before the nodes on the right
        //I felt like using a stack was the best solution for this because storing the return String recursively
        //would've been kind of messy
        while(!stack.empty()) {
            BinaryNode<E> temp=stack.pop();
            arr.add(temp.data());

            if(temp.right()!=null){
                stack.push(temp.right());
            }
            if(temp.left()!=null){
                stack.push(temp.left());
            }
        }
        return arr;
    }

    // TODO: Inorder traversal
    public List<E> inOrderList() {
        if(root==null){
            return new ArrayList<E>();
        }
        return inOrderList(root);
    }

    private List<E> inOrderList(BinaryNode<E> curNode) {
        Stack<BinaryNode<E>> stack=new Stack<BinaryNode<E>>();
        return inOrderList(curNode,stack,new ArrayList<E>());

    }
    private List<E> inOrderList(BinaryNode<E> curNode,Stack<BinaryNode<E>> stack,ArrayList<E> arr){
        BinaryNode<E> temp=curNode;
        //traverses the tree, but doesn't stop when it reaches the end so that it can go back up the tree, adding
        //each node to the return String
        while(temp!=null||!stack.empty()){
            //pushes each node onto the stack root-down, so that the end of the tree is the first node getting
            //popped off the stack
            while(temp!=null){
                stack.push(temp);
                temp=temp.left();
            }
            //temp is null because it is at temp.getLeft() of the last node on the left subtree, so we start popping
            //off the stack and then checking for nodes on the right of each node to traverse and add to the stack.
            //This is why 2 while loops are used
            temp=stack.pop();
            arr.add(temp.data());
            temp=temp.right();

        }
        return arr;
    }

    // TODO: Postorder traversal
    public List<E> postOrderList() {
        if(root==null){
            return null;
        }
        return postOrderList(root);
    }

    private List<E> postOrderList(BinaryNode<E> curNode) {
        Stack<BinaryNode<E>> stack =new Stack<BinaryNode<E>>();
        Stack<BinaryNode<E>> path =new Stack<BinaryNode<E>>();
        stack.push(curNode);
        return postOrderList(curNode,stack,path,new ArrayList<E>());
    }
    private List<E> postOrderList(BinaryNode<E> curNode, Stack<BinaryNode<E>> stack, Stack<BinaryNode<E>> path, ArrayList<E> arr){
        //Used 2 stacks- one to store the nodes in reverse order like the preorder and inorder, and another to store
        //the path that the nodes were traversed in reverse order
        BinaryNode<E> temp=curNode;
        while(!stack.empty()){
            temp=stack.peek();
            if(!path.empty() && path.peek()==temp){
                arr.add(temp.data());
                stack.pop();
                path.pop();
            }
            else{
                path.push(temp);
                if(temp.right()!=null)
                    stack.push(temp.right());
                if(temp.left()!=null)
                    stack.push(temp.left());
            }
        }
        return arr;
    }
    public BinaryNode<E> getRoot(){
        return root;
    }

    // Helpers for BST/AVL methods
    // TODO: extractRightMost
    //    This will be called on the left subtree and will get the maximum value.
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        BinaryNode<E> temp=curNode.left();
        while(temp.right()!=null){
            temp=temp.right();
        }
        return temp;
    }

    public BinaryNode<E> extractRightMin(BinaryNode<E> curNode){
        BinaryNode<E> temp=curNode.right();
        while(temp.left()!=null){
            temp=temp.left();
        }
        return temp;
    }
    // AVL & BST Search & insert same
    // TODO: search
    public BinaryNode<E> search(E elem) {
        if(root==null){
            return null;
        }
        else {
            return search(elem, root);
        }
    }
    private BinaryNode<E> search(E elem, BinaryNode<E> curNode) {
        BinaryNode<E> temp=curNode;
        if(elem.equals(temp.data())){
            return temp;
        }
        else if(elem.compareTo(temp.data())>0){
            if(temp.right()==null){
                return null;
            }
            else{
                return search(elem,temp.right());
            }
        }
        else{
            if(temp.left()==null){
                return null;
            }
            else{
                return search(elem,temp.left());
            }

        }

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

    // TODO: insert
    public void insert(E elem) {
        if(root==null){
            root=new BinaryNode<E>(elem);
            size++;
            height++;
        }
        else {
            insert(elem, root);
        }
    }

    private void insert(E elem, BinaryNode<E> curNode) {
        BinaryNode<E> temp = curNode;
        if(elem.compareTo(temp.data())>=0) {
            if(temp.right()==null){
                temp.setRight(new BinaryNode<E>(elem,null,null,temp));
                size++;
            }
            else{
                insert(elem,temp.right());
            }
        }
        else{
            if(temp.left()==null){
                temp.setLeft(new BinaryNode<E>(elem, null, null, temp));
                size++;
            }
            else{
                insert(elem,temp.left());
            }
        }
        updateHeight();
    }

    // TODO: delete
    public BinaryNode<E> delete(E elem) {
        if(root==null){
            return null;
        }
        else{
            return delete(elem,root);
        }
    }

    private BinaryNode<E> delete(E elem, BinaryNode<E> curNode) {
        BinaryNode<E> temp=curNode;
        BinaryNode<E> searchNode=search(elem);
        if(searchNode==null){
            return null;
        }
        BinaryNode<E> temp1=root;
        if(searchNode==root){
            if(root.left()!=null&&root.right()!=null) {
                temp = extractRightMin(root);
                if(temp==root.right()){
                    if(root.left()!=null){
                        temp.setLeft(root.left());
                    }
                    root=temp;
                    root.setParent(null);
                    return temp1;
                }
                if(temp==root.left()){
                    if(root.right()!=null){
                        temp.setRight(root.left());
                    }
                    root=temp;
                    root.setParent(null);
                    return temp1;
                }
                temp.parent().setLeft(null);
                temp.setLeft(root.left());
                temp.setRight(root.right());
                root.setLeft(null);
                root.setRight(null);
                root = temp;
            }
            else if(root.left()!=null&&root.right()==null){
                temp=root;
                root=root.left();
                temp.setLeft(null);
                root.setParent(null);
            }
            else if(root.getLeft()==null&&root.getRight()!=null){
                root=root.right();
                root.setParent(null);
            }
            height--;
            size--;
            return temp1;
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
        return searchNode;
    }


    // Stuff to help you debug if you want
    // Can ignore or use to see if it works.
    static <E extends Comparable<E>> Tree<E> mkBST (Collection<E> elems) {
        Tree<E> result = new BST<>();
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
