import javax.swing.plaf.ColorUIResource;

public class SearchTree implements NodeList {
    private ListItem root = null;

    public SearchTree(ListItem root){
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        //Moves left or right as appropriate until it finds a position == null
        if (this.root == null) {
            this.root = newItem;
            return true;
        }

        //Otherwise, start comparing from head of the tree
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison<0) {
                //newItem is greater, move right if possible
                if (currentItem.goNext() != null) {
                    currentItem = currentItem.goNext();
                } else {
                    //There's no node to the right, so add at the end
                    currentItem.setNext(newItem);
                    return true;
                }
            } else if (comparison>0) {
                //newItem is less, move left if possible
                if (currentItem.goPrevious() != null) {
                    currentItem = currentItem.goPrevious();
                } else {
                    //There's no node to the left, so add to the beginning
                    currentItem.setPrevious(newItem);
                    return true;
                }
            } else {
                //Equal so don't add
                System.out.println(newItem.getValue() + " already exists");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
            return true;
        }

        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison <0) {
                parentItem = currentItem;
                currentItem = currentItem.goNext();
            } else if (comparison>0) {
                parentItem = currentItem;
                currentItem = currentItem.goPrevious();
            } else {
                //equal: we've found the item so remove it
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        // recursive method
        if (root != null) {
            traverse(root.goPrevious());
            System.out.println(root.getValue());
            traverse(root.goNext());
        }
        
    }

    private void performRemoval(ListItem item, ListItem parentItem) {
        //remove item from the tree
        if (item.goNext() == null) {
            //no right tree, so make parent point to left tree (which may be null)
           if (parentItem.goNext()==item) {
               parentItem.setNext(item.goPrevious());
           } else if (parentItem.goPrevious()==item){
               parentItem.setPrevious(item.goPrevious());
           } else {
               //parent must be item, which means we were looking at the root of the tree
               this.root = item.goPrevious();
           }
        } else if (item.goPrevious() == null){
            // no left tree, so make parent point to right tree (which may be null)
            if (parentItem.goNext() == item) {
                // item is right child of its parent
                parentItem.setNext(item.goNext());
            } else if (parentItem.goPrevious() == item) {
                //item is left child of its parent
                parentItem.setPrevious(item.goNext());
            } else {
                this.root = item.goNext();
            }
        } else {
            //neither left nor right are null
            //From the right sub-tree, find the smallest value (i.e. the leftmost)
            ListItem current = item.goNext();
            ListItem leftmostParent = item;
            while (current.goPrevious() != null ) {
                leftmostParent = current;
                current = current.goPrevious();
            }
            //Now put the smallest value into our node to be deleted
            item.setValue(current.getValue());
            //and delete the smallest 
            if (leftmostParent == item) {
                //there was no leftmost node, so "current" points to the smallest
                // node (the one that must now be deleted)
                item.setNext(current.goNext());
            } else {
                // set the smallest node's parent to point to the smallest node's right child (which may be null)
                leftmostParent.setPrevious(current.goNext());
            }
        }
    } 

    
}
