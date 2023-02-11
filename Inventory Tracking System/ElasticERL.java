
public class ElasticERL {

	public void setEINThreshold(int size) {
		
		//Small amounts of data will be handled by a doubly linked list.
        //Medium amounts will be handled by a hash table of size 12289 with seperate chaining
        //Large amounts will be handled by a binary AVL Tree
        if(size<1000) {
            System.out.println("For a size of " + size + ", we will be building a Doubly Linked List");
        } else if(size<12289) {
            System.out.println("For a size of " + size + ", we will be building a Hash Table with seperate chaining");
        } else {
            System.out.println("For a size of " + size + ", we will be building an AVL Tree");
        }
		
		if(size<1000) {
            DoublyLinkedList dll = new DoublyLinkedList();
            dll.loadData(size, "src/EHITS_test_file1.txt");
            dll.allKeys(dll);
            System.out.println("\nValue at key 33266743: " + dll.getValues(dll, 33266743));
            System.out.println("Key after 33266743 is: " + dll.nextKey(dll, 33266743));
            System.out.println("Key before 33240013 is: " + dll.prevKey(dll, 33255593));
            System.out.println("Range between 57370834 and 3326261: " + dll.rangeKey(57370834,3326261));
            dll.remove(dll, 89999978);
            dll.remove(dll, 33255593);
            dll.allKeys(dll);
            dll.generate();
        }
        else if(size<12289) {
        	HashTable ht = new HashTable();
    		ht.loadData(size, "src/EHITS_test_file1.txt");
//    		ht.printTable();
    		
    		System.out.println("\nallKeys method:");
    		ht.allKeys(ht);
    		
    		System.out.println("\nadd method:\nnothing to print, verify from the following getValues method");
    		ht.add(ht, 10101010, null);
    		
    		System.out.println("\ngetValues method:");
    		ht.getValues(ht, 10101010);
    		
    		System.out.println("\nremove method:");
    		System.out.println(ht.remove(ht, 10101010));
    		
    		System.out.println("\nnextKey method:");
    		System.out.println(ht.nextKey(ht, 33288890));
    		
    		System.out.println("\nprevKey method:");
    		System.out.println(ht.prevKey(ht, 33288890));
    		
    		System.out.println("\nrangeKey method:");
    		System.out.println(ht.rangeKey(33288890, 83290952));
        }
        else {
        	HashTable ht = new HashTable();
    		ht.loadData(size, "src/EHITS_test_file1.txt");
//    		ht.printTable();
    		
    		System.out.println("\nallKeys method:");
    		ht.allKeys(ht);
    		
    		System.out.println("\nadd method:\nnothing to print, verify from the following getValues method");
    		ht.add(ht, 10101010, null);
    		
    		System.out.println("\ngetValues method:");
    		ht.getValues(ht, 10101010);
    		
    		System.out.println("\nremove method:");
    		System.out.println(ht.remove(ht, 10101010));
    		
    		System.out.println("\nnextKey method:");
    		System.out.println(ht.nextKey(ht, 33288890));
    		
    		System.out.println("\nprevKey method:");
    		System.out.println(ht.prevKey(ht, 33288890));
    		
    		System.out.println("\nrangeKey method:");
    		System.out.println(ht.rangeKey(33288890, 83290952));
//            AVLTree avl = new AVLTree();
//            avl.loadData(size, "src/EHITS_test_file1.txt");
//            avl.allKeys(avl);
//            System.out.println("\nValue at key 33266743: " + avl.getValues(avl, 33266743));
//            avl.remove(avl.root, 89999978);
//            avl.remove(avl.root, 33255593);
//            avl.allKeys(avl);
//            avl.generate();
        }
		
	}
	
}
