import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HashTable {
	
	private HashNode[] buckets; // array of nodes that points to linked lists
	private final int numOfBuckets = 12289; // size of the array, picked as 12289 b/c prime number and close to the target data size
	private int size;
	
	public HashTable() { // constructor
		this.buckets = new HashNode[numOfBuckets];
	}
	
	private class HashNode {
		
		private Integer key;
		private String value;
		private HashNode next;
		
		public HashNode(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public int size() { // O(1)
		return size;
	}
	
	public boolean isEmpty() { // O(1)
		return size == 0;
	}
	
	public void add(HashTable ht, Integer key, String value) { // O(n)
		
		if (key == null) { // check if input key is not null; exception handling
			throw new IllegalArgumentException("Key is null!");
		}
		int bucketIndex = getBucketIndex(key); // get the key index using the hash function implemented by getBucketIndex()
		HashNode head = buckets[bucketIndex]; // point to the index
		while (head != null) { // get to the end of the linked list
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		size++;
		head = buckets[bucketIndex];
		HashNode node = new HashNode(key, value);
		node.next = head;
		buckets[bucketIndex] = node; // insert the new node at the end of the linked list
	}
	
	private int getBucketIndex(Integer key) { // O(1); method to hash the key into the hash table
		return key % numOfBuckets;
	}
	
	public String get(Integer key) { // O(1); method to get a given key
		
		if (key == null) { // exception handling
			throw new IllegalArgumentException("Key is null!");
		}
		
		int bucketIndex = getBucketIndex(key); // get index where the node with this key exists
		HashNode head = buckets[bucketIndex];
		while (head != null) { // iterate through the linked list until given key matches with a stored key
			if (head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		}
		return null;
	}
	
	public Integer remove(HashTable ht, Integer key) { // O(1); remove method to delete a given key
		
		if (key == null) { // exception handling
			throw new IllegalArgumentException("Key is null!");
		}
		
		int bucketIndex = getBucketIndex(key); // get key index
		HashNode head = buckets[bucketIndex]; // point to the start of the linked list
		HashNode previous = null;
		
		while (head != null) { // iterate through the linked list until key is found
			if (head.key.equals(key)) {
				break;
			}
			previous = head;
			head = head.next; // point to null
		}
		if (head == null) {
			return null;
		}
		size--;
		if (previous != null) {
			previous.next = head.next;
		} else {
			buckets[bucketIndex] = head.next;
		}
		
		return head.key;
	}
	
	public void allKeys(HashTable ht) { // method to print all keys that exists in the hash table
		
		for (int i = 0; i < buckets.length; i++) {
			HashNode head = buckets[i];
			while (head != null) {
				System.out.println(head.key);
				head = head.next;
			}
		}
	}
	
	public void getValues(HashTable ht, Integer key) { // method to get all values for a specified key
		int index = getBucketIndex(key);
		HashNode head = buckets[index];
		while (head != null) {
			if (head.key.equals(key)) {
				System.out.println(head.value);
			}
			head = head.next;
		}
	}
	
	public void printTable() { // O(n); extra method to print the hash table
		
		for (int i = 0; i < buckets.length; i++) {
			
			System.out.print(i + ":  ");
			HashNode head = buckets[i];
			while (head != null) {
				System.out.print("{" + head.key + " -> " + head.value + "} | ");
				head = head.next;
			}
			System.out.println();
		}
	}
	
	public int prevKey(HashTable ht, Integer key) { // O(n); method to get the predecessor key of a given key
		
		if (key == null) {
			throw new IllegalArgumentException("Key is null!");
		}
		
		int prevKey = 0;
		int bucketIndex = getBucketIndex(key);
		HashNode head = buckets[bucketIndex];
		int limit = 0;
		
		while (head != null) { // point to the start of the linked list, then skip all preceding nodes to the node with the specified key
			
			if (head.key.equals(key)) {
				break;
			}
			head = head.next;
		}
		HashNode temp;
		HashNode temp2 = null; // temp pointers to help get to the predecessor key
		for (int i = 0; i <= bucketIndex; i++) { // start from index 0 since we cannot go backward
			
			temp = buckets[i]; // temp first gets to the node with the passed key
			while (temp != null && limit != 1) {
				if (temp.next == null && !temp.key.equals(head.key)) {
					temp2 = temp; // then temp2 points to the predecessor node of the node that temp is pointing to
					break;
				}
				if (temp.key.equals(head.key)) {
					prevKey = temp2.key;
					limit++;
				}
				temp = temp.next;
			}
			if (limit == 1) break;
		}
		
		return prevKey;
	}
	
	public int nextKey(HashTable ht, Integer key) { // O(1); method to get successor key for a given key
		
		if (key == null) {
			throw new IllegalArgumentException("Key is null!");
		}
		
		int nextKey = 0;
		int bucketIndex = getBucketIndex(key); // get index
		
		HashNode head = buckets[bucketIndex]; // point to the start of the linked list
		while (head != null) { // head first finds the target node, then temp points to the successor node
			if (head.key.equals(key)) {
				HashNode temp = head.next;
				
				while (true) {
					if (temp == null) {
						bucketIndex++;
						temp = buckets[bucketIndex];
						if (temp != null) {
							nextKey = temp.key;
							break;
						}
					} else {
						nextKey = temp.key;
						break;
					}
				}
			}
			head = head.next;
		}
		return nextKey;
	}
	
	public int rangeKey(Integer key1, Integer key2) { // O(n); method to return the number of keys between two given keys, including the passed keys
		
		if (key1 == null || key2 == null) {
			throw new IllegalArgumentException("At least one of the given keys is null!");
		}
		
		int counter = 0;
		int bucketIndex1 = getBucketIndex(key1);
		int bucketIndex2 = getBucketIndex(key2); // get indexes
		int limit = 0;
		boolean targetIndex = true;
		/* we consider three different cases
		 * if index of key1 > index of key2
		 * if index of key2 > index of key1
		 * if index of ke1 = index of key2 */
		if (bucketIndex1 < bucketIndex2) { // traverse forward using single linked list pointers
			for (int i = bucketIndex1; i <= bucketIndex2; i++) {
				HashNode head = buckets[i];
				while (targetIndex) {
					if (head.key.equals(key1)) {
						targetIndex = false;
					} else {
						head = head.next;
					}
				}
				while (head != null && limit != 1) {
					counter++;
					if (head.key.equals(key2)) {
						limit++;
					}
					head = head.next;
				}
				if (limit == 1) break;
			}
		} else if (bucketIndex1 > bucketIndex2) { // traverse backwards
			
			for (int i = bucketIndex2; i <= bucketIndex1; i++) {
				
				HashNode head = buckets[i];
				while (targetIndex) {
					if (head.key.equals(key2)) {
						targetIndex = false;
					} else {
						head = head.next;
					}
				}
				while (head != null && limit != 1) {
					counter++;
					if (head.key.equals(key1)) {
						limit++;
					}
					head = head.next;
				}
				if (limit == 1) break;
			}
			
		} else { // if the same key is passed twice
			counter = 1;
		}
		return counter;
	}
	
	public void loadData(int size, String filePath) { // O(n); method to load and store data set in the hash table
		
		this.size = size;
		int counter = 0;
		File file = new File(filePath);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null && counter < size) {
				add(this, Integer.valueOf(line), null);
				line = br.readLine();
				counter++;
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Error with reading from input file!");
		}
	}
	
	public boolean doesKeyExist(Integer keyToFind) { // O(n); method to check if a key from generate() exists or not
		
		int bucketIndex = getBucketIndex(keyToFind);
		HashNode head = buckets[bucketIndex];

	    while (head != null) {
	    	if(head.key.equals(keyToFind)) {
	    		return true;
	    	}
	    	head = head.next;
	    }

	    return false;
	}

	public Integer generate(){ // O(n); generate method to generate random non-existing keys

		int randKey;

		do {
			randKey = (int)Math.floor(Math.random()*99999999);
		}
		while(this.doesKeyExist(randKey));

		System.out.print("New key generated: ");
		return randKey;
	}
}
