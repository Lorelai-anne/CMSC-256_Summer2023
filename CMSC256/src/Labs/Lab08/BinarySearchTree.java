package Labs.Lab08;

public class BinarySearchTree <E extends Comparable<? super E>>{
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
}