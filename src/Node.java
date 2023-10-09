/**
 * This class represents a node which will be used in myOwnKLinkedList.
 * @param <T> can represent all types of object.
 */
public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

