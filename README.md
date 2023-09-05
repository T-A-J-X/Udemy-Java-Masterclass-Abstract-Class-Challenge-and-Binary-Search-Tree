# Udemy-Java-Masterclass-Abstract-Class-Challenge-and-Binary-Search-Tree
In this challenge, we demonstrates an implementation of a linked list and a binary search tree, along with an abstract ListItem class that defines the common functionality for elements in these data structures. We start by spliting a String Array into seperate String objects to add to the MyLinkedList which is a singly linked list that demonstrates the core functionality of a linked list data structure.

The abstract classes ListItem defines a common interface with shared methods and properties while leaving the specific implementations to the concrete subclass Node. It enforces a contract that any class extending ListItem must provide implementations for the required methods, to ensure consistency and structure.

shared fields and methods: 
  protected Object value;
  private ListItem previous;
  private ListItem next;
  setPrevious(ListItem item);
  goPrevious();
  setNext(ListItem item);
  goNext();
  compareTo(ListItem item);

getRoot(): returns the root element of a linkedList or the root node of a binary search tree.
addItem(ListItem item): Adds an item (a child class of ListItem) to the data structure.
removeItem(ListItem item): This method is used to remove an item from the data structure based on the provided ListItem object.
traverse(ListItem root): This method is responsible for traversing and displaying the elements in the data structure in a given order. For linked lists, it might traverse the list from the root to the end, while for binary search trees, it may perform an in-order traversal.
