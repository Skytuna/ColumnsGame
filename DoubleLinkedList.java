public class DoubleLinkedList {
	private DoubleNode head;
	private DoubleNode tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}

	public void append(Object data) {
		if (head == null && tail == null) {
			DoubleNode newNode = new DoubleNode(data);
			head = newNode;
			tail = newNode;
			return;
		}

		DoubleNode newNode = new DoubleNode(data);
		newNode.setPrev(tail);
		tail.setNext(newNode);
		tail = newNode;
	}

	public void add(String str) {
		DoubleNode newNode = new DoubleNode(str);
		if (head == null && tail == null) {
			head = newNode;
			tail = newNode;
			return;
		}

		if (str.compareTo((String) head.getData()) < 0) {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
			return;
		}

		DoubleNode temp = head;
		while (temp.getNext() != null && (str.compareTo((String) temp.getNext().getData()) >= 0)) {
			temp = temp.getNext();
		}
		newNode.setPrev(temp);
		newNode.setNext(temp.getNext());
		if (temp.getNext() != null) {
			temp.getNext().setPrev(newNode);
		} else {
			tail = newNode;
		}
		temp.setNext(newNode);
	}

	public void remove(int index) {
		if (index >= size()) {
			return;
		}

		DoubleNode temp = head;

		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}

		if (temp.getPrev() == null) {
			head = temp.getNext();
			head.setPrev(null);
			return;
		}

		if (temp.getNext() == null) {
			tail = temp.getPrev();
			tail.setNext(null);
			return;
		}

		temp.getNext().setPrev(temp.getPrev());
		temp.getPrev().setNext(temp.getNext());
	}

	public void display() {
		if (head == null) {
			System.out.println("linked list is empty");
			return;
		}

		DoubleNode temp = head;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}

	public boolean search(Object item) {
		boolean flag = false;
		if (head == null) {
			System.out.println("linked list is empty");
			return false;
		}

		DoubleNode temp = head;
		while (temp != null) {
			if (item == temp.getData())
				flag = true;
			temp = temp.getNext();
		}
		return flag;
	}

	public int size() {
		int count = 0;
		DoubleNode temp = head;
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}
}
