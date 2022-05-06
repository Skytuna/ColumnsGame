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

	public void add(Player newPlayer) {
		DoubleNode newNode = new DoubleNode(newPlayer);
		if (head == null && tail == null) {
			head = newNode;
			tail = newNode;
			return;
		}

		if (newPlayer.getScore() > ((Player)head.getData()).getScore()) {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
			return;
		}

		DoubleNode temp = head.getNext();
		while (temp != null) {
			
			if((newPlayer.getScore() > ((Player)temp.getData()).getScore()))
			{
				newNode.setNext(temp);
				newNode.setPrev(temp.getPrev());
				temp.getPrev().setNext(newNode);
				temp.setPrev(newNode);
				return;
			}
			
			temp = temp.getNext();
		}

		newNode.setPrev(tail);
		tail.setNext(newNode);
		tail = newNode;
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

    public DoubleNode getIndex(int index) {
    	DoubleNode temp = head;
        if (index > size() - 1) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        return temp;
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
