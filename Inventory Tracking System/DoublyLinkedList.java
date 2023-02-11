import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DoublyLinkedList {

    class Node{
        int key;
        String data;
        Node prev;
        Node next;

        public Node(int key, String data){
            this.key = key;
            this.data = data;
        }

   }

   Node head = null;

   public boolean doesKeyExist(int keyToFind){ // O(n)

       Node target = this.head;

       while(target.next != null){
           if(target.key == keyToFind)
               return true;

           target = target.next;
       }

       return false;
   }

   public int generate(){ // O(n)

        int randKey;

        do {
            randKey = (int)Math.floor(Math.random()*99999999);
        }while(this.doesKeyExist(randKey));

        System.out.println("New key generated: " + randKey);
        return randKey;
    }

    //prints all keys in a reverse chronological order (the newest first)
    public void allKeys(DoublyLinkedList dll){

        Node target = dll.head;

        if(dll.head == null){
            System.out.println("\nList is currently empty.");
            return;
        }

        System.out.println("\nReverse chronological order of all present keys and their data:");
        System.out.println("-----------------------------------------------------------------");

        while(target != null) {
            System.out.println(target.key + " - " + target.data);
            target = target.next;
       }
    }

    public void add(DoublyLinkedList dll, int key, String data){

        Node newNode = new Node(key, data);

        if(dll.head == null) {
            dll.head = newNode;
            dll.head.prev = null;
        }
        else {
            dll.head.prev = newNode;
            newNode.next = dll.head;
            dll.head = newNode;
            dll.head.prev = null;
        }
    }

    public Node findNodeByKey(int key){
        Node target = head;

        while(target.next != null){
            if(target.key == key)
                return target;
            target = target.next;
        }

        return null;
    }

    public void remove(DoublyLinkedList dll, int key){

        Node target = dll.findNodeByKey(key);

        if(target != null){

            if(dll.head == target) {
                dll.head = head.next;
                head.prev = null;
            }

            if(target.next != null)
                target.next.prev = target.prev;


            if(target.prev != null)
                target.prev.next = target.next;

            System.out.println("Entry with " + key + " as a key was found and removed in our ElasticADT");
        }
        else {
            System.out.println("No entry with " + key + " as a key was found in our ElasticADT");
        }

    }

    public String getValues(DoublyLinkedList dll, int key){

        Node target = dll.findNodeByKey(key);
        if(target != null)
            return target.data;
        else
            return "No node found with that key.";
    }

    public int nextKey(DoublyLinkedList dll, int key) {
        Node target = dll.findNodeByKey(key);
        if (target != null) {
            if (target.next != null)
                return target.next.key;
            else
                return -1;
        }
        return -1;
    }

    public int prevKey(DoublyLinkedList dll, int key) {
        Node target = dll.findNodeByKey(key);
        if (target != null) {
            if (target.prev != null)
                return target.prev.key;
            else
                return -1;
        }
        return -1;
    }

    public int rangeKey(int key1, int key2) {
        int rangeCount = 0;
        boolean key2Found = false;
        Node target = findNodeByKey(key1);

        if(target != null) {
            while (target.next != null && target.key != key2) {

                if (target.next.key == key2)
                    key2Found = true;

                target = target.next;
                rangeCount++;
            }
        }
        //Have to make sure we are actually finding key2 and not just stopping the loop when we hit the end of the list
        if(key2Found)
            return rangeCount;
        else
            return -1;
    }

    public void loadData(int size, String filename){

       try {
           BufferedReader read = new BufferedReader(new FileReader(filename));
           String line;

           //reads the first line
           line = read.readLine();

           for(int i=0; i<size; i++) {
               String[] arr = line.split(",");
               if(arr.length == 1)
                   this.add(this,Integer.parseInt(arr[0]), "");
               else
                   this.add(this,Integer.parseInt(arr[0]), arr[1]);
               line = read.readLine();
           }

           read.close();

       }
       catch(IOException e){
           System.out.println(e);
       }
   }
}
