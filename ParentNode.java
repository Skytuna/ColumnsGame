public class ParentNode {
    private Object data;
    private ParentNode nextParent;
    private ChildNode nextChild;

    public ParentNode(Object data) {
        this.data = data;
        nextParent = null;
        nextChild = null;
    }

    public int sizeChild() {
        int count = 0;
        if (nextChild == null) {
            // System.out.println("List is empty!");
            return count;
        }

        ChildNode temp = nextChild;
        while (temp != null) {
            count++;
            temp = temp.getNextChild();
        }

        return count;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ParentNode getNextParent() {
        return this.nextParent;
    }

    public void setNextParent(ParentNode nextParent) {
        this.nextParent = nextParent;
    }

    public ChildNode getNextChild() {
        return this.nextChild;
    }

    public void setNextChild(ChildNode nextChild) {
        this.nextChild = nextChild;
    }

}
