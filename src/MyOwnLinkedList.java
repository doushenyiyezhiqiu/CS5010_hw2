/**
 * The class is I created for using linkedlist-like class in this project.
 * It contains almost all functions the linkedlist class have.
 * @param <T> "T" can represent all types of object.
 */
public class MyOwnLinkedList<T> {

    private Node<T> head;
    private int size;

    public MyOwnLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(T data) {
        if (head == null) return;
        if (head.value.equals(data)) {
            head = head.next;
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.value.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }

    public void removeUsingIndex(int index) {
        if (index < 0 && index > size - 1)  return;
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if (head == null) {
            return null;
        }
        Node<T> current = head;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return current.value;
    }

    public int size() {
        return size;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value).append(", ");
            current = current.next;
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
