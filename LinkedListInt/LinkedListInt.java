package linkedListInt;

/**
 * CMSI Assignment 2
 * @author <Bennett, Tommy>
 *
 */
public class LinkedListInt {

	int  size;
	Node head;
	
	class Node {
		int  data;
		Node next;
		
		public Node(int d) {
			data = d;
			next = null;
		}
	}
	
	public LinkedListInt() {
		size = 0;
		head = null;
	}
	
	// Returns the current size of the Linked List 
	public int size() {		
		return size;

	}

	// Returns the value stored in the data field of the Node at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public int get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// Iterate to specified position
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	// Sets the value stored in the data field of the Node at the given index with element
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void set(int index, int element) throws IndexOutOfBoundsException {
		if (index < 0 || index >= (size)) {
			throw new IndexOutOfBoundsException();
		}
		
		Node current = head;
		for(int i = 0; i< index; i++) {
			current = current.next;
			}
		current.data = element;

	}

	// Adds a new element to the Linked List by adding a new Node
	public void add(int element) {
		Node node = new Node(element);
		// If there is nothing in the Linked List
		if (head == null) {
			head = node;
		} else {
			Node curr = head;
			// Iterate until we arrive at the last node
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = node;
		}
		size = size + 1;
	}

	// Inserts a new element as a new Node into the Linked List at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void insert(int index, int element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node node = new Node(element);
		Node current = head;
		
		if(index == 0) {
			node.next = head;
			head = node;
			size = size +1;
			return;
		}
		

		
		if (head == null) {
			head = node;
		} 
		
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		node.next = current.next;
		current.next = node;		
		
		size = size +1;
		
	}
		

	// Removes the element at a given index from the Linked List
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void remove(int index) throws IndexOutOfBoundsException {
		Node current = head;
		Node previous = head;
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			head = head.next;
			size = size - 1;
			return;
		}
		
		for(int i = 0; i < index ; i++) {
			previous = current;
			current = current.next;
		}
		previous.next = current.next;
		current.next = null;
		current = null;
		
		size = size - 1;
	}
	
	// Returns true if the Linked List contains the element, else false
	public boolean contains(int element) throws IndexOutOfBoundsException {
		// TODO: Implement the functionality of contains
		// Note: a placeholder of return true is given so the skeleton compiles


		Node current = head;
		for (int i = 0; i < size; i++) {
			if (current.data == element) {
				return true;
			}
		}
		return false;
	}
	
	// Add all the given elements as Nodes to the Linked List at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void addAll(int index, int[] elements) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (elements.length == 0) {
			return;
		}
		
		Node arrayHead = new Node(elements[0]);
		Node arrayCurrent = arrayHead;
		
		for (int i = 1; i < elements.length; i++) {
			arrayCurrent.next = new Node(elements[i]);
			arrayCurrent = arrayCurrent.next;
		}
		Node previous = head;
		Node current = head; 
		
		if (index == 0) {
			arrayCurrent.next = head;
			head = arrayHead;
			size = size + elements.length;
			return;
		}
		
		for (int i = 0; i < index; i++) {
			previous = current;
			current = current.next;
		}
		previous.next = arrayHead;
		arrayCurrent.next = current;
		size = size + elements.length;
		
	}
	
	public String toString() {
		if (head == null) {
			return "[ ]";
		}
		String out = "[ ";
		Node curr = head;
		while (curr.next != null) {
			out = out + curr.data + ", ";
			curr = curr.next;
		}
		
		out = out + curr.data + " ]";
		return out;
	}
	public static void main(String[] args) {
		LinkedListInt a = new LinkedListInt();
		LinkedListInt b = new LinkedListInt();
		
		a.add(7);
		a.add(11);
		a.add(93);
		System.out.println("a = " + a.toString());
		b.add(7);
		b.add(11);
		b.add(93);
		System.out.println("b = " + b.toString());
		
		a.insert(0, 15);
		a.insert(3, 44);
		a.insert(a.size(), 20);
		System.out.println("a = " + a.toString());
		
		a.set(0, 20);
		a.set(3, 15);
		a.set(5, 44);
		System.out.println("a = " + a.toString());
		
		a.remove(0);
		System.out.println("a = " + a.toString()); 
		a.remove(2);
		System.out.println("a = " + a.toString()); 
		a.remove(3);
		System.out.println("a = " + a.toString()); 
		
		a.addAll(0, new int[]{ 0, 1, 2});
		System.out.println("a = " + a.toString());
		
		System.out.println("get a[1] = " + a.get(1));
		System.out.println("get a[3] = " + a.get(3));
		
		System.out.println("a contains 0 = " + a.contains(0));
		System.out.println("a contains 10 = " + a.contains(10));
		
	}
}
