package dataStructures;

import exceptions.DSStandardException;

public class DoubleLinkedList<T> {

    private Node rootNode;
    private Node lastNode;

    public DoubleLinkedList() {

    }

    public Node add(T value) {
        if(rootNode == null) {
            rootNode = new Node(value);
            lastNode = rootNode;
            return rootNode;
        }
        else {
            Node temp = lastNode;
            lastNode = new Node(value);
            lastNode.setPreviousNode(temp);
            temp.setNextNode(lastNode);
            return lastNode;
        }
    }

    public void delete() throws DSStandardException {
        delete(0);
    }

    public void delete(int count) throws DSStandardException {
        Node nodeToBeDeleted = rootNode;
        for(int x = 0; x < count; x++) {
            if(nodeToBeDeleted.getNextNode() == null) {
                throw new DSStandardException("No " + count + " Elements in List");
            }
            nodeToBeDeleted = nodeToBeDeleted.getNextNode();
        }
        delete(nodeToBeDeleted);
    }

    private void delete(Node nodeToBeDeleted) {
        if(nodeToBeDeleted == null) {

        }
        else if(nodeToBeDeleted.getPreviousNode() == null && nodeToBeDeleted.getNextNode() == null) {
            rootNode = null;
        }
        else if(nodeToBeDeleted.getPreviousNode() == null) {
            rootNode = nodeToBeDeleted.getNextNode();
            rootNode.setPreviousNode(null);
        }
        else if(nodeToBeDeleted.getNextNode() == null) {
            nodeToBeDeleted.getPreviousNode().setNextNode(null);
        }
        else {
            Node temp = nodeToBeDeleted.getPreviousNode();
            temp.setNextNode(nodeToBeDeleted.getPreviousNode());
            nodeToBeDeleted.getNextNode().setPreviousNode(temp);
        }
    }

    public void deleteLast() {
        delete(lastNode);
    }

    class Node<T> {
        T value;
        Node previousNode;
        Node nextNode;

        Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node previousNode) {
            this.previousNode = previousNode;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
