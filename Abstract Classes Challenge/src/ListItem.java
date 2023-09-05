public abstract class ListItem {
    protected Object value;
    private ListItem previous;
    private ListItem next;


    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem setPrevious(ListItem item);
    abstract ListItem goPrevious();
    abstract ListItem setNext(ListItem item);
    abstract ListItem goNext();
    abstract int compareTo(ListItem item);


    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
