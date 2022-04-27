public class SingleLinkedList {
	private Node head;

	public void add(Object data) {
		Node newNode = new Node(data);

		if (head == null) {
			head = newNode;
			return;
		}

		Node temp = head;
		while (temp.getLink() != null) {
			temp = temp.getLink();
		}
		temp.setLink(newNode);
	}

	public void delete(Object dataToDelete) {
		if (head == null) {
			System.out.println("linked list is empty");
			return;
		}

		while ((int) head.getData() == (int) dataToDelete)
			head = head.getLink();

		Node temp = head;
		Node previous = null;

		while (temp != null) {
			if ((int) temp.getData() == (int) dataToDelete) {
				previous.setLink(temp.getLink());
				temp = previous;
			}

			previous = temp;
			temp = temp.getLink();
		}
	}

	public boolean search(Object item) {
		boolean flag = false;
		if (head == null) {
			System.out.println("linked list is empty");
			return false;
		}

		Node temp = head;
		while (temp != null) {
			if (item == temp.getData())
				flag = true;
			temp = temp.getLink();
		}
		return flag;
	}

	public void display() {
		if (head == null) {
			System.out.println("linked list is empty");
			return;
		}

		Node temp = head;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getLink();
		}
	}

	public int size() {
		int count = 0;
		if (head == null) {
			return count;
		}

		Node tempNode = head;
		while (tempNode != null) {
			count++;
			tempNode = tempNode.getLink();
		}

		return count;
	}

	public Object getIndex(int index) {
		Node tempNode = head;
		if (index > size() - 1) {
			return null;
		}

		for (int i = 0; i < index; i++) {
			tempNode = tempNode.getLink();
		}

		return tempNode.getData();
	}
}
