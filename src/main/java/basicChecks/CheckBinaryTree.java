package basicChecks;

import dataStructures.BinarySearchTree;
import dataStructures.exceptions.DSStandardException;

public class CheckBinaryTree {

    public static void main(String... args) throws DSStandardException {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        binarySearchTree.add(60);
        binarySearchTree.add(40);
        binarySearchTree.add(100);
        binarySearchTree.add(20);
        binarySearchTree.add(80);
        binarySearchTree.add(120);
        binarySearchTree.add(30);
        binarySearchTree.add(10);
        binarySearchTree.add(50);
        binarySearchTree.add(55);
        binarySearchTree.add(110);
        binarySearchTree.add(120);
        binarySearchTree.add(105);
        binarySearchTree.add(125);

        System.out.println("In Order --> " + binarySearchTree.getTraversedTree(BinarySearchTree.TRAVERSAL_TYPE.IN_ORDER));
        System.out.println("Pre Order --> " + binarySearchTree.getTraversedTree(BinarySearchTree.TRAVERSAL_TYPE.PRE_ORDER));
        System.out.println("Post Order --> " + binarySearchTree.getTraversedTree(BinarySearchTree.TRAVERSAL_TYPE.POST_ORDER));
        System.out.println("Reverse In Order --> " + binarySearchTree.getTraversedTree(BinarySearchTree.TRAVERSAL_TYPE.REVERSE_IN_ORDER));

        binarySearchTree.delete(125);
        binarySearchTree.delete(120);
        binarySearchTree.delete(40);
        binarySearchTree.delete(200);

        System.out.println("Sample");
    }
}
