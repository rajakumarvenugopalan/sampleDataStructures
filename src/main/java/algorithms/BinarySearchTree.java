package algorithms;

import algorithms.exceptions.DSStandardException;

public class BinarySearchTree<T extends Comparable> {

    private Node rootNode;

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

    public boolean search(T value)
    {
        return rootNode == null ? false : rootNode.findElement(value);
    }

    public void delete(T value) throws DSStandardException {
        if(rootNode == null) {
            throw new DSStandardException("No Elements available in Tree");
        }
        rootNode = rootNode.deleteNode(value);
    }

    public String getTraversedTree(TRAVERSAL_TYPE traversalType) {
        if(rootNode == null) {
            return "No Elements available";
        }
        else {
            return rootNode.findTraversedPath(traversalType);
        }
    }

    public enum TRAVERSAL_TYPE {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        REVERSE_IN_ORDER;
    }

    private class Node {
        private T value;
        private Node leftChild;
        private Node rightChild;

        Node(T value) {
            this.value = value;
        }

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

        public Node getMinimumNode() {
            if(this.getLeftChild() == null) {
                return this;
            }
            else {
                return this.getLeftChild().getMinimumNode();
            }
        }

        public Node deleteNode(T value) {
            int comparedResult = value.compareTo(this.getValue());
            if(comparedResult == -1) {
                if(this.getLeftChild() == null) {
                    return this;
                }
                this.setLeftChild(this.getLeftChild().deleteNode(value));
                return this;
            }
            else if(comparedResult == 1) {
                if(this.getRightChild() == null) {
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
