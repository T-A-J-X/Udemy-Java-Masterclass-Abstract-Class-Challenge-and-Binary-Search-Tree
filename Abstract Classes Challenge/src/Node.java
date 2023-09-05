public class Node extends ListItem {
    private ListItem previous;
    private ListItem next;

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem setPrevious(ListItem item) {
        return this.previous = item;
    }

    @Override
    ListItem goPrevious() {
        if (hasPrevious()) {
            return this.previous;
        } else {
            System.out.println("There isn't a next");
            return null;
        }
    }

    @Override
    ListItem setNext(ListItem item) {
        return this.next = item;
    }

    @Override
    ListItem goNext() {
        if (hasNext()) {
            return this.next;
        } else {
            System.out.println("There isn't a previous");
            return null;
        }
    }

    @Override
    int compareTo(ListItem item) {
        if (item != null) {
            //If item isn't null, it performs a comparison of the string values of the current Node and item.
            return ((String) super.getValue()).compareTo((String)item.getValue());//using Strings, hence why we're passing our objects into Strings
            //The .compareTo is the built-int String operator which will return the -1 if < than value, 0 if equal or +1 if > than value
        } else {
            return -1;//default for anything null
        }
    }

    private boolean hasNext(){
        if (next != null) {
            return true;
        }
        return false;
    }

    private boolean hasPrevious(){
        if (previous != null) {
            return true;
        }
        return false;
    }
    
}
