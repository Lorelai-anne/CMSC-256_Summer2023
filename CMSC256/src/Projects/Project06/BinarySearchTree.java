/*
 * Project 06 - Binary Search Tree Method Implementation
 * This project builds on the code from Lab 8 by implementing six new methods
 * Lorelai Davis
 * CMSC 256 Section C01
 * 30 June 2023
 */
package Projects.Project06;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<? super E>> {
    public class BinaryNode<E>{
        protected E value;
        protected BinaryNode<E> right,left;
        public BinaryNode(E valueIn){
            value = valueIn;
        }
        public E getValue(){
            return value;
        }
    }
    private int size;
    private BinaryNode<E> root;

    public BinarySearchTree(){
        size = 0;
        root = null;
    }
    public BinarySearchTree(int size,BinaryNode<E> root){
        this.size = size;
        this.root = root;
    }
    private boolean addToParent(BinaryNode<E> parentNode, BinaryNode<E> addNode){
        int compare = addNode.value.compareTo(parentNode.value);
        boolean wasAdded = false;
        if(compare > 0){ //the addNode value is less than the parentNode value
            //if parent has no left node, add new node as left
            if(parentNode.left == null){
                parentNode.left = addNode;
                wasAdded = true;
            }else{
                //otherwise, add to parentNode's left (recursive)
                wasAdded = addToParent(parentNode.left,addNode);
            }
        }else if(compare < 0){ //the addNode value is greater than the parentNode value
            //if parent has no right node, set new node as the right node
            if(parentNode.right == null){
                parentNode.right = addNode;
                wasAdded = true;
            }else{
                //otherwise, add new value to parentNode's right (recursive)
                wasAdded = addToParent(parentNode.right, addNode);
            }
        }return wasAdded;
    }
    public boolean add(E inValue){
        BinaryNode<E> node = new BinaryNode<>(inValue); //creates new node to hold new element
        boolean wasAdded = true;
        if(root == null){ //if tree is empty (if the root is null), set root to new null
            root = node;
        }else{ //otherwise add value to the tree using the root at the parent
            wasAdded = addToParent(root,node);
        }
        if(wasAdded){ //if element was successfully added, increment size by one and return boolean
            size++;
        }return wasAdded;
    }
    public boolean remove(E removeValue){
        if(root == null){ //if root is empty, returns false since no value can be removed
            return false;
        }
        if(removeValue.compareTo(root.value) == 0){ //if node to remove is the root, new root must be set
            if(root.left == null){ //if left child is null
                root = root.right; //makes the right child the new root
            }else if(root.right == null){ //if right is null
                root = root.left; //set left child as the root
            }else{ //if neither are null
                BinaryNode<E> formerRight = root.right;
                root = root.left; //set root as left
                addToParent(root,formerRight); //add right to left node
            }
            size--;
            return true;
            //if node to remove is not root, call to find the element in the tree
        }return removeSubNode(root,removeValue);
    }
    private boolean removeSubNode(BinaryNode<E> parent,E removeValue){
        //comparing the parent node to the child nodes value
        int compareParent = removeValue.compareTo(parent.value);
        BinaryNode<E> subTree = null;
        if(compareParent > 0){
            subTree = parent.left;
        }else{
            subTree = parent.right;
        }
        if(subTree == null){ //if branch is null, value doesn't exist in tree
            return false;
        }
        if(subTree.value.compareTo(removeValue) == 0){
            BinaryNode<E> replacement;
            if(subTree.left == null){
                replacement = subTree.right;
            }else if(subTree.right == null){
                replacement = subTree.left;
            }else{
                BinaryNode<E> formerRight = subTree.right;
                replacement = subTree.left;
                addToParent(replacement,formerRight);
            }
            if(compareParent > 0){
                parent.left = replacement;
            }else{
                parent.right = replacement;
            }size--;
            return true;
        }return removeSubNode(subTree,removeValue);
    }
    public int size(){
        return size;
    }
    public BinaryNode<E> getRoot() {
        return root;
    }
    public void clear(){
        root = null;
        size = 0;
    }

    /**
     * @return the node containing the largest entry in the tree
     */
    public BinaryNode<E> findLargest() {
        BinaryNode<E> largest = root;
        BinaryNode<E> node;
        if (root == null) {
            return root; //if tree is empty, the largest is the root
        }
        if (root.right == null) { //if the right child is null, go left
            while (largest.left != null){
                largest = largest.left;
            }
        } else if (root.left == null) { //if left is empty, go right
            while(largest.right != null){
                largest = largest.right;
            }
        }else if(root.right != null && root.left != null){ //go right by default
            while(largest.right != null){
                largest = largest.right;
            }
        }
        return largest;
    }
    /**
     * @return removes and returns the node containing the largest entry in the tree
     */
    public BinaryNode<E> removeLargest(){
        if(root == null){
            return root;
        }
        //calling findLargest to fine value
        BinaryNode<E> largest = findLargest();
        remove(largest.value); //remove the largest value from the tree
        return largest; //return the old largest value
    }

    /**
     * @return the height of the binary tree
     */
    public int getHeight() {
        return getNodeHeight(root);
    }
    private int getNodeHeight(BinaryNode<E> node){
        //if node being passed is null, height is subtracted
        if(node == null){
            return -1;
        }
        //recursive method to find max value between nodes
        return Math.max(getNodeHeight(node.left),getNodeHeight(node.right))+1;
    }
    public boolean isLeafNode(BinaryNode<E> root){
        return root.left == null && root.right == null;
    }
    /**
     * @return true or false, depending on if the tree is full or not
     */
    public boolean isFullBinaryTree(){
        if(root == null){
            return true;
        }
        if(root.left == null && root.right == null){
            return true;
        }
        Queue<BinaryNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()){
            root = q.peek();
            q.remove();
            if(root == null){
                return false;
            }
            if(!isLeafNode(root)){
                q.add(root.left);
                q.add(root.right);
            }
        }
        return true;
    }

    /**
     * @return the number of leaf nodes
     */
    public int getNumberOfLeaves(){
        return leaves(root);
    }
    private int leaves(BinaryNode<E> node){
        //if the tree is empty, there are no leaves
        if(node == null){
            return 0;
        }if(node.left == null && node.right == null){
            //if the left and right nodes exist but are null, there is one leaf
            return 1;
        }else{
            //else call method until nodes are null
            return leaves(node.left) + leaves(node.right);
        }
    }

    /**
     * @return returns number of internal nodes
     */
    public int getNumberOfInternalNodes(){
        return internal(root); //call the recursive method on the tree
    }
    private int internal(BinaryNode<E> node){ //recursive method to find largest
        if(node == null){ //if root is null, return 0
            return 0;
        }if(node.left == null && node.right == null){
            return 0; //if the right and left child is null, they have no children so return 0
        }else{
            //else start the recursive process
            return internal(node.left)+internal(node.right)+1;
        }
    }
}
