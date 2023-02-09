import java.util.Stack;

public class BinarySearchTree {

    private int size;
    private BinaryTreeNode root;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     */
    public void insert(int data) {
        if(root==null){
            root=new BinaryTreeNode(data,null,null,null);
            size++;
        }
        else {
            insert(data, root);
        }
    }



    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     * @param curNode The current Node in the traversal
     */
    private void insert(int data, BinaryTreeNode curNode) {
        BinaryTreeNode temp = curNode;
        if(data>temp.getItem()) {
            if(temp.getRight()==null){
                temp.setRight(new BinaryTreeNode(data,temp,null, null));
                size++;
            }
            else{
                insert(data,temp.getRight());
            }
        }
        else{
            if(temp.getLeft()==null){
                temp.setLeft(new BinaryTreeNode(data,temp,null,null));
                size++;
            }
            else{
                insert(data,temp.getLeft());
            }
        }
    }

    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the minimum
     * value in the right child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @return The Node containing the integer to remove or null if one is not found
     */
    public BinaryTreeNode remove(int data) {
        if(root==null){
            return null;
        }
        else{
            return remove(data,root);
        }
    }


    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the minimum
     * value in the right child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @param curNode The current Node in the traversal
     * @return The Node containing the integer to remove or null if one is not found
     */
    private BinaryTreeNode remove(int data, BinaryTreeNode curNode) {
        BinaryTreeNode temp=curNode;
        BinaryTreeNode searchNode=search(data);
        if(searchNode==null){
            return null;
        }
        if(searchNode==root){
            if(root.getLeft()!=null&&root.getRight()!=null) {
                temp = extractRightMin(root);
                temp.getParent().setLeft(null);
                temp.setLeft(root.getLeft());
                temp.setRight(root.getRight());
                root.setLeft(null);
                root.setRight(null);
                root = temp;
            }
            if(root.getLeft()!=null&&root.getRight()==null){
                root=root.getLeft();
            }
            else if(root.getLeft()==null&&root.getRight()!=null){
                root=root.getRight();
            }
        }
        if(searchNode.getRight()!=null){
            BinaryTreeNode replacement=extractRightMin(searchNode);
            temp=searchNode.getParent();
            if(temp.getLeft()!=null&&temp.getLeft().getItem()==data){
                temp.setLeft(replacement);
                if(replacement.getParent().getLeft().getItem()==data){
                    replacement.getParent().setLeft(null);
                }
                else if(replacement.getParent().getRight().getItem()==data){
                    replacement.getParent().setRight(null);
                }
                replacement.setParent(temp);
            }
            if(temp.getRight().getItem()==data){
                temp.setRight(replacement);
                if(replacement.getParent().getLeft().getItem()==data){
                    replacement.getParent().setLeft(null);
                }
                else if(replacement.getParent().getRight().getItem()==data){
                    replacement.getParent().setRight(null);
                }
                replacement.setParent(temp);
            }
        }
        else if(searchNode.getLeft()!=null&&searchNode.getRight()==null){
            if(searchNode.getParent().getLeft().getItem()==searchNode.getItem()){
                searchNode.getParent().setLeft(searchNode.getLeft());
            }
            else if(searchNode.getParent().getRight().getItem()==searchNode.getItem()){
                searchNode.getParent().setRight(searchNode.getRight());
            }
            searchNode.getLeft().setParent(searchNode.getParent());

        }
        else if(searchNode.getLeft()==null&&searchNode.getRight()!=null){
            if(searchNode.getParent().getLeft().getItem()==searchNode.getItem()){
                searchNode.getParent().setLeft(searchNode.getLeft());
            }
            else if(searchNode.getParent().getRight().getItem()==searchNode.getItem()){
                searchNode.getParent().setRight(searchNode.getRight());
            }
            searchNode.getRight().setParent(searchNode.getParent());
        }
        return searchNode;
    }

    /**
     * A recursive method that starts at the right child of a parent and extracts the minimum from this child's tree.
     * @param curNode The current Node in the traversal
     * @return The minimum Node in the child's tree
     */


    private BinaryTreeNode extractRightMin(BinaryTreeNode curNode) {
        BinaryTreeNode temp=curNode.getRight();
        while(temp.getLeft()!=null){
            temp=temp.getLeft();
        }
        return temp;
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @return A Node containing the given integer or null if one is not found
     */
    public BinaryTreeNode search(int data) {
        if(root==null){
            return null;
        }
        else {
            return search(data, root);
        }
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @param curNode The current Node in the traversal
     * @return A Node containing the given integer or null if one is not found
     */
    private BinaryTreeNode search(int data, BinaryTreeNode curNode) {
        BinaryTreeNode temp=curNode;
        if(data==temp.getItem()){
            return temp;
        }
        else if(data>temp.getItem()){
            if(temp.getRight()==null){
                return null;
            }
            else{
                return search(data,temp.getRight());
            }
        }
        else{
            if(temp.getLeft()==null){
                return null;
            }
            else{
                return search(data,temp.getLeft());
            }

        }

    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPreOrderStr() {
        if(root==null){
            return "";
        }
        else{

            String result=getPreOrderStr(root);
            result=result.substring(0, result.length()-2);
            return result;
        }
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPreOrderStr(BinaryTreeNode curNode) {
        Stack<BinaryTreeNode> stack=new Stack<BinaryTreeNode>();
        //pushed root node into stack so that it can be traversed, then called second helper function that contains the
        //stack and return String
        stack.push(root);
        return getPreOrder(curNode,stack,"");

    }



    private String getPreOrder(BinaryTreeNode curNode, Stack<BinaryTreeNode> stack,String str){
        //since Stacks are read first in, last out, I used that to store the nodes iteratively so that all the
        //nodes on the left are popped (and therefore added to the return String) before the nodes on the right
        //I felt like using a stack was the best solution for this because storing the return String recursively
        //would've been kind of messy
        String strOut=str;
        while(!stack.empty()) {
            BinaryTreeNode temp=stack.pop();
            strOut=strOut+temp.getItem()+", ";

            if(temp.getRight()!=null){
                stack.push(temp.getRight());
            }
            if(temp.getLeft()!=null){
                stack.push(temp.getLeft());
            }
        }
        return strOut;
    }


    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getInOrderStr() {
        if(root==null){
            return "";
        }
        String result=getInOrderStr(root);
        result=result.substring(0, result.length()-2);
        return result;
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getInOrderStr(BinaryTreeNode curNode) {
        Stack<BinaryTreeNode> stack=new Stack<BinaryTreeNode>();
        return getInOrderStr(curNode,stack,"");

    }
    private String getInOrderStr(BinaryTreeNode curNode,Stack<BinaryTreeNode> stack,String str){
        String strOut=str;
        BinaryTreeNode temp=curNode;
        //traverses the tree, but doesn't stop when it reaches the end so that it can go back up the tree, adding
        //each node to the return String
        while(temp!=null||!stack.empty()){
            //pushes each node onto the stack root-down, so that the end of the tree is the first node getting
            //popped off the stack
            while(temp!=null){
                stack.push(temp);
                temp=temp.getLeft();
            }
            //temp is null because it is at temp.getLeft() of the last node on the left subtree, so we start popping
            //off the stack and then checking for nodes on the right of each node to traverse and add to the stack.
            //This is why 2 while loops are used
            temp=stack.pop();
            strOut+=temp.getItem()+", ";
            temp=temp.getRight();

        }
        return strOut;
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPostOrderStr() {
        if(root==null){
            return null;
        }
        String result=getPostOrderStr(root);
        result=result.substring(0, result.length()-2);
        return result;
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPostOrderStr(BinaryTreeNode curNode) {
        Stack<BinaryTreeNode> stack =new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> path =new Stack<BinaryTreeNode>();
        stack.push(curNode);
        return getPostOrderStr(curNode,stack,path,"");
    }
    private String getPostOrderStr(BinaryTreeNode curNode, Stack<BinaryTreeNode> stack, Stack<BinaryTreeNode> path, String str){
        //Used 2 stacks- one to store the nodes in reverse order like the preorder and inorder, and another to store
        //the path that the nodes were traversed in reverse order
        BinaryTreeNode temp=curNode;
        String strOut=str;
        while(!stack.empty()){
            temp=stack.peek();
            if(!path.empty() && path.peek()==temp){
                strOut+=temp.getItem()+", ";
                stack.pop();
                path.pop();
            }
            else{
                path.push(temp);
                if(temp.getRight()!=null)
                    stack.push(temp.getRight());
                if(temp.getLeft()!=null)
                    stack.push(temp.getLeft());
            }
        }
        return strOut;
    }
}
