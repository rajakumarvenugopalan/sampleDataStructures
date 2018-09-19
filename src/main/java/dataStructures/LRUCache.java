package dataStructures;

import exceptions.DSStandardException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache<T extends Comparable> {

    /**
     *
     * Least Recently Used Cache. Whenever an element is added add it in the HashMap and move the element to the last of Linked List.
     * When add method is called - check if the element is available in Map, if it is not there add the element to the Map and add the element to end of Linked List.
     * If the element is available in Map, we have below scenarios
     *      a. Node has no Next Element - this is the most recently used item. No need to change the order in Linked List
     *      b. Node has no Previous Element - this is the least recently used item, remove the head element and add it at the end of Linked List
     *      c. Node has Previous and Next Element - remove the element from the Linked List and add it at the end of Linked List.
     *
     * */

    private DoubleLinkedList<T> listItems = new DoubleLinkedList<>();
    private Map<T, DoubleLinkedList.Node> cache = new HashMap<>();

    public void add(T value) {
        if(cache.get(value) == null) {
            cache.put(value, listItems.add(value));
        }
        else {
            DoubleLinkedList.Node currentNode = cache.get(value);
            if(currentNode.getNextNode() == null) {
                //Dont do anything - This is the Most Recently used element.
            }
            else if(currentNode.getPreviousNode() == null) {
                //The item is the least Recently used one. So Delete the root node and add it at the end
                try {
                    listItems.delete();
                }
                catch(DSStandardException ds) {}
                T currentValue = (T)currentNode.getValue();
                cache.put(currentValue, listItems.add((T)currentNode.getValue()));
            }
            else {
                //The element is present in middle of the list. Remove the item and add it at the end.
                DoubleLinkedList.Node prevNode = currentNode.getPreviousNode();
                DoubleLinkedList.Node nextNode = currentNode.getNextNode();
                prevNode.setNextNode(nextNode);
                nextNode.setPreviousNode(prevNode);
                T currentValue = (T)currentNode.getValue();
                cache.put(currentValue, listItems.add((T)currentNode.getValue()));
            }
        }
    }

}
