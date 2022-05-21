public class MultiLinkedList {

    private ParentNode head;

    public MultiLinkedList() {
        head = null;
    }

    public void addParentNode(Object data) {
        if (head == null) {
            ParentNode newNode = new ParentNode(data);
            head = newNode;
            return;
        }

        ParentNode temp = head;
        while (temp.getNextParent() != null) {
            temp = temp.getNextParent();
        }
        ParentNode newNode = new ParentNode(data);
        temp.setNextParent(newNode);
    }

    public void addChildNode(Object parentData, Object childData) {
        if (head == null) {
            System.out.println("Something went wrong!");
            return;
        }

        ParentNode temp = head;
        while (temp != null) {
            if (parentData.equals(temp.getData())) {
                ChildNode childTemp = temp.getNextChild();
                if (childTemp == null) {
                    ChildNode newNode = new ChildNode(childData);
                    temp.setNextChild(newNode);
                } else {
                    while (childTemp.getNextChild() != null) {
                        childTemp = childTemp.getNextChild();
                    }
                    ChildNode newNode = new ChildNode(childData);
                    childTemp.setNextChild(newNode);
                }
            }
            temp = temp.getNextParent();
        }
    }

    public int sizeParent() {
        int count = 0;
        if (head == null) {
            System.out.println("List is empty!");
            return count;
        }

        ParentNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.getNextParent();
        }

        return count;
    }

    public ParentNode getParentByIndex(int index) {
        ParentNode temp = head;
        if (index > sizeParent() - 1) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            temp = temp.getNextParent();
        }

        return temp;
    }

    public ChildNode getChildByIndex(int indexParent, int indexChild) {
        ParentNode parent = getParentByIndex(indexParent);
        ChildNode temp = parent.getNextChild();
        if(indexChild > parent.sizeChild() - 1)
        {
            return null;
        }

        for(int i = 0; i < indexChild; i++)
        {
            temp = temp.getNextChild();
        }

        return temp;
    }
    
    //TODO I guess
    public void setChild(int parentIndex, int childIndex, Object child) {
    	
    	
		
	}
}
