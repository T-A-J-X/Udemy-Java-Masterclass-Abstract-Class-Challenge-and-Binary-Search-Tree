

public class MyLinkedList implements NodeList {

    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if (this.root == null) {
            //List was empty, so this item becomes head of the list
            this.root = newItem;
            return true;
        }
        
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));//-1, 0 or +1
            if (comparison <0) {//if comparison -1 newItem is greater and should be added
                if (currentItem.goNext() != null) {//if next isn't null, go next
                    currentItem = currentItem.goNext();
                } else {
                    //there is no next so insert at the end
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            } else if (comparison >0) {
                //newItem is less, insert before current item like below
                if (currentItem.goPrevious() != null) {
                    currentItem.goPrevious().setNext(newItem).setPrevious(currentItem.goPrevious());
                    newItem.setNext(currentItem).setPrevious(newItem);
                } else {
                    //the node without a previous becomes the root
                    newItem.setNext(this.root).setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            } else {
                //equal
                System.out.println(newItem.getValue() + " is already present");
                return false;
            }
        }
        return false;
    }
        

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) {
                //found item to delete
                if (currentItem == this.root) {
                    this.root = currentItem.goNext();//basically deletes the record by saving over it with the next Node
                } else {
                    currentItem.goPrevious().setNext(currentItem).goNext();
                    if (currentItem.goNext() != null) {
                        currentItem.goNext().setPrevious(currentItem.goPrevious());
                    }
                }
                return true;
            } else if (comparison<0) {
                currentItem = currentItem.goNext();
            } else { // comparison > 0
                //We are at an item freater than the one to be deleted
                //so the item cannot be in the list
                return false;
            }      
        }
        return false;
    }
    

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.goNext();
            }
        }

        /*
        //OPTIONAL RECURSIVE METHOD BUT NOT OPTIMISED FOR LINKEDLIST
        if (root != null) {
            System.out.println(root.getValue());
            traverse(root.goNext());
        }*/
        
    }

}
