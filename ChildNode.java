public class ChildNode {
    private Object data;
    private ChildNode nextChild;

    public ChildNode(Object data) {
        this.data = data;
        this.nextChild = null;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ChildNode getNextChild() {
        return this.nextChild;
    }

    public void setNextChild(ChildNode nextChild) {
        this.nextChild = nextChild;
    }

}
