package algorithms;

import algorithms.exceptions.DSStandardException;

public class StackQueueUsingLinkedList<T> {

    private Node headElement;
    private Node lastElement;
    private final TYPE type;

    /**
     * By Default create a Queue
     *
     * */
    public StackQueueUsingLinkedList() {
        this(TYPE.QUEUE);
    }

    /**
     * Create a Stack or Queue based on the argument passed.
     *
     * */
    public StackQueueUsingLinkedList(TYPE type) {
        this.type = type;
    }

    /**
     * @Input value
     * @Output void
     * Adds an element to the the end of the linked list.
     *
     * */
    public void push(T value) throws DSStandardException {
        if(value == null) {
            throw new DSStandardException("Adding NULL is not supported in this Object");
        }
        Node addedElement = new Node(value);
        if(headElement == null) {
            headElement = addedElement;
            lastElement = addedElement;
        }
        else {
            lastElement.setNextElement(addedElement);
            addedElement.setPreviousElement(lastElement);
            lastElement = addedElement;
        }
    }

    /**
     * @Output value
     * Returns the value of the next element to be returned or null.
     *
     * */
    public T peek() {
        switch(type){
            case QUEUE:
                if(headElement != null) {
                    return headElement.getValue();
                }
                else {
                    return null;
                }
            default:
                if(lastElement != null) {
                    return lastElement.getValue();
                }
                else {
                    return null;
                }
        }
    }

    /**
     * @Output value
     * Returns the value of the next element to be returned or throws an Exception if no element available. .
     *
     * */
    public T poll() throws DSStandardException{
        switch(type){
            case QUEUE:
                if(headElement != null) {
                    Node elementToReturn = headElement;
                    headElement = headElement.getNextElement();
                    if(headElement != null) {
                        headElement.setPreviousElement(null);
                    }
                    return elementToReturn.getValue();
                }
                else {
                    throw new DSStandardException("No Element available in the Object");
                }
            default:
                if(lastElement != null) {
                    Node elementToReturn = lastElement;
                    lastElement = lastElement.getPreviousElement();
                    if(lastElement != null) {
                        lastElement.setNextElement(null);
                    }
                    return elementToReturn.getValue();
                }
                else {
                    throw new DSStandardException("No Element available in the Object");
                }
        }
    }

    public enum TYPE {
        STACK,
        QUEUE;
    }

    /**
     * Doubly Linked List for supporting both Stack and Queue
     *
     * */

    private class Node {
        T value;
        private Node nextElement;
        private Node previousElement;

        Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node nextElement) {
            this.nextElement = nextElement;
        }

        public Node getPreviousElement() {
            return previousElement;
        }

        public void setPreviousElement(Node previousElement) {
            this.previousElement = previousElement;
        }
    }
}
