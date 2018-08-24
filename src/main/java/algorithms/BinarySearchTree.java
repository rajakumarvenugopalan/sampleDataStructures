package algorithms;

import algorithms.exceptions.DSStandardException;

public class BinarySearchTree<T extends Comparable> {

    private Node rootNode;

    /**
     *
     * @Input value (any Comparable type)
     * @Output void
     * @Throws DSStandardException if value passed is Null
     * Adds the element to Binary Search Tree
     *
     * */
    public void add(T value) throws DSStandardException {
        if(value == null)
            throw new DSStandardException("I don't like Null :-) ");
        Node nodeToAdd = new Node(value);
        if(rootNode == null) {
            rootNode = nodeToAdd;
        }
        else {
            rootNode.addChild(nodeToAdd);
        }
    }

    /**
     *
     * @Input value (any Comparable type)
     * @Output boolean
     * Returns true if the element is available in the Binary Search Tree
     *
     * */
    public boolean search(T value)
    {
        return rootNode == null ? false : rootNode.findElement(value);
    }

    /**
     *
     * @Input value (any Comparable type)
     * @Output void
     * @Throws DSStandardException if value passed is Null or when No elements available in Tree
     * Deletes the element from the Binary Search Tree
     *
     * */
    public void delete(T value) throws DSStandardException {
        if(rootNode == null || value == null) {
            throw new DSStandardException("No Elements available in Tree");
        }
        rootNode = rootNode.deleteNode(value);
    }

    /**
     *
     * @Input TraversalType
     * @Output String (Stringified format of traversal path)
     * Returns the elements values in the order retrieved, based on one of the below Enum arguments
     * PRE_ORDER
     * IN_ORDER
     * POST_ORDER
     * REVERSE_IN_ORDER
     *
     * */
    public String getTraversedTree(TRAVERSAL_TYPE traversalType) {
        if(rootNode == null) {
            return "No Elements available";
        }
        else {
            return rootNode.findTraversedPath(traversalType);
        }
    }

    public enum TRAVERSAL_TYPE {
        PRE_ORDER,          // Parent, Left Child, Right Child
        IN_ORDER,           // Left Child, Parent, Right Child (Ascending Order)
        POST_ORDER,         // Left Child, Right Child, Parent
        REVERSE_IN_ORDER;   //Right Child, Parent, Left Child (Descending Order)
    }

    /**
     *
     * Inner Class to maintain the Binary Tree
     * */
    private class Node {
        private T value;
        private Node leftChild;
        private Node rightChild;

        Node(T value) {
            this.value = value;
        }

        /**
         *
         * Adds the child node with the below condition
         * if the new node is lesser than the parent, add it as a Left Child Node
         * if the new node is higher than the parent, add it as a Right Child Node
         * if the new node is equal to the parent then ignore and return (No Duplicates possible in Binary Search tree)
         * */
        public void addChild(Node childNode) {
            int comparedResult = this.getValue().compareTo(childNode.getValue());
            if(comparedResult == 1) {
                if(this.getLeftChild() == null)
                    this.setLeftChild(childNode);
                else
                    this.getLeftChild().addChild(childNode);

            }
            else if(comparedResult == -1) {
                if(this.getRightChild() == null)
                    this.setRightChild(childNode);
                else
                    this.getRightChild().addChild(childNode);
            }
        }

        /**
         *
         * Returns in String format the order of retrieval of elements.
         *
         * */
        public String findTraversedPath(TRAVERSAL_TYPE traversalType) {
            StringBuilder sb = new StringBuilder();
            if(traversalType == TRAVERSAL_TYPE.IN_ORDER) {
                if(this.getLeftChild() != null) {
                    sb.append(" " + this.getLeftChild().findTraversedPath(traversalType));
                }
                sb.append(" " + this.toString());
                if(this.getRightChild() != null) {
                    sb.append(" " + this.getRightChild().findTraversedPath(traversalType));
                }
            }
            else if(traversalType == TRAVERSAL_TYPE.PRE_ORDER) {
                sb.append(" " + this.toString());
                if(this.getLeftChild() != null) {
                    sb.append(" " + this.getLeftChild().findTraversedPath(traversalType));
                }
                if(this.getRightChild() != null) {
                    sb.append(" " + this.getRightChild().findTraversedPath(traversalType));
                }
            }
            else if(traversalType == TRAVERSAL_TYPE.POST_ORDER) {
                if(this.getLeftChild() != null) {
                    sb.append(" " + this.getLeftChild().findTraversedPath(traversalType));
                }
                if(this.getRightChild() != null) {
                    sb.append(" " + this.getRightChild().findTraversedPath(traversalType));
                }
                sb.append(" " + this.toString());
            }
            else {
                if(this.getRightChild() != null) {
                    sb.append(" " + this.getRightChild().findTraversedPath(traversalType));
                }
                sb.append(" " + this.toString());
                if(this.getLeftChild() != null) {
                    sb.append(" " + this.getLeftChild().findTraversedPath(traversalType));
                }
            }
            return sb.toString();
        }

        /**
         *
         * Checks if the value is present in the Current Node and its child nodes.
         * Returns true if the equivalent is found, else returns false
         * */
        public boolean findElement(T value) {
            int comparedResult = this.getValue().compareTo(value);
            if(comparedResult == 0)
                return true;
            else {
                if(comparedResult == -1)
                    return this.getLeftChild() == null ? false : this.getLeftChild().findElement(value);
                else
                    return this.getRightChild() == null ? false : this.getRightChild().findElement(value);
            }
        }

        /**
         * Returns the Minimum element available in the Current Node.
         * Useful when deleting a Node with both Left and Right Childs.
         *
         * */
        public Node getMinimumNode() {
            if(this.getLeftChild() == null) {
                return this;
            }
            else {
                return this.getLeftChild().getMinimumNode();
            }
        }

        /**
         *
         * Deletes the node "value" from the current Node.
         * When deleting a Node with no children return null
         * when deleting a Node with one children (Left or Right) return the corresponding Child
         * when deleting a Node with two childrens find the Minimum node from Right child or Maximum Node from the Left Child and replace the deleted node
         *
         * */
        public Node deleteNode(T value) {
            int comparedResult = value.compareTo(this.getValue());
            if(comparedResult == -1) {
                if(this.getLeftChild() == null) {
                    //This means the element value is not present in this Node. We can do an alternative of using find before delete operation, but that will make the function 2 log n
                    return this;
                }
                this.setLeftChild(this.getLeftChild().deleteNode(value));
                return this;
            }
            else if(comparedResult == 1) {
                if(this.getRightChild() == null) {
                    //This means the element value is not present in this Node. We can do an alternative of using find before delete operation, but that will make the function 2 log n
                    return this;
                }
                this.setRightChild(this.getRightChild().deleteNode(value));
                return this;
            }
            else {
                if(this.getRightChild() == null) {
                    return this.getLeftChild();
                }
                else if(this.getLeftChild()== null) {
                    return this.getRightChild();
                }
                Node temp = this;
                Node node =  temp.getRightChild().getMinimumNode();
                node.setRightChild(temp.getRightChild().deleteNode(node.getValue()));
                node.setLeftChild(temp.getLeftChild());
                return node;
            }
        }

        public String toString() {
            return value.toString();
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
