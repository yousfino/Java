import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AVLTree {

    class Node{
        int key;
        String data;
        Node left;
        Node right;
        Node prev;
        Node next;
        int height;

        public Node(int key, String data){
            this.key = key;
            this.data = data;
            height = 1;
        }
    }

    Node root;

    public int height(Node n){
        if(n == null)
            return 0;

        return n.height;
    }

    public int maxInt(int a, int b){
        if(a > b)
            return a;
        else
            return a;
    }

    public Node rotateRight(Node a){
        Node b = a.left;
        Node c = b.right;
        b.right = a;
        a.left = c;
        a.height = maxInt(height(a.left), height(a.right)) + 1;
        b.height = maxInt(height(b.left), height(b.right)) + 1;
        return b;
    }

    public Node rotateLeft(Node a){
        Node b = a.right;
        Node c = b.left;
        b.left = a;
        a.right = c;
        a.height = maxInt(height(a.left), height(a.right)) + 1;
        b.height = maxInt(height(b.left), height(b.right)) + 1;
        return b;
    }

    public int calculateBalanceFactor(Node n){
        if(n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    public Node add(Node n, int key, String data){

        //Find where we're inserting our new node
        if(n == null)
            return (new Node(key, data));
        if(key < n.key)
            n.left = add(n.left, key, data);
        else if(key > n.key)
            n.right = add(n.right, key, data);
        else
            return n;

        //Calculate balance factors and re-balance if it's needed
        n.height = 1 + maxInt(height(n.left), height(n.right));
        int balanceFactor = calculateBalanceFactor(n);

        if(balanceFactor > 1) {
            if(key < n.left.key)
                return rotateRight(n);
            else if(key > n.left.key) {
                n.left = rotateLeft(n.left);
                return rotateRight(n);
            }
        }

        if(balanceFactor < -1) {
            if(key > n.right.key)
                return rotateLeft(n);
            else if(key < n.right.key) {
                n.right = rotateRight(n.right);
                return rotateLeft(n);
            }
        }
        return n;
    }

    public Node findMinKey(Node n) {
        Node target = n;
        while(target.left != null)
            target = target.left;
        return target;
    }

    public Node remove(Node root, int key) {

        if(root == null)
            return root;
        if(key < root.key)
            root.left = remove(root.left, key);
        else if(key > root.key)
            root.right = remove(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = findMinKey(root.right);
                root.key = temp.key;
                root.right = remove(root.right, temp.key);
            }
        }

        // Check and update balance factors
        root.height = maxInt(height(root.left), height(root.right)) + 1;
        int balanceFactor = calculateBalanceFactor(root);

        if (balanceFactor > 1) {
            if (calculateBalanceFactor(root.left) >= 0)
                return rotateRight(root);
            else {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }
        if (balanceFactor < -1) {
            if (calculateBalanceFactor(root.right) <= 0)
                return rotateLeft(root);
            else {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }
        return root;
    }

    public void inOrder(Node n) {
        if(n != null) {
            inOrder(n.left);
            System.out.print(n.key + " - " + n.data + "   ");
            inOrder(n.right);
        }
    }

    public Node findNodeByKey(Node n, int key) {
        if(n.key == key)
            return n;
        else if(key < n.key && n.left != null)
            findNodeByKey(n.left, key);
        else if(key > n.key && n.right != null)
            findNodeByKey(n.right, key);

        return null;
    }

    public int generate(){

        int randKey;

        do {
            randKey = (int)Math.floor(Math.random()*99999999);
        }while(this.findNodeByKey(this.root, randKey) != null);

        System.out.println("New key generated: " + randKey);
        return randKey;
    }

    public void allKeys(AVLTree avl) {
        inOrder(avl.root);
    }

    public String getValues(AVLTree avl, int key) {
        return findNodeByKey(avl.root, key).data;
    }

    public int nextKey(AVLTree avl, int key) {
        Node target = findNodeByKey(avl.root, key);
        if(target != null)
            if(target.next != null)
                return target.next.key;
        return -1;
    }

    public int prevKey(AVLTree avl, int key) {
        Node target = findNodeByKey(avl.root, key);
        if(target != null)
            if(target.prev != null)
                return target.prev.key;
        return -1;
    }

    /*
    public int rangeKey(int key1, int key2) {
        int rangeCount = 0;
        boolean key2Found = false;
        Node target = findNodeByKey(this.root, key1);

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
    */


    public void loadData(int size, String filename){

        try {
            BufferedReader read = new BufferedReader(new FileReader(filename));
            String line;

            //reads the first line
            line = read.readLine();
            Node temp = null;

            for(int i=0; i<size; i++) {
                String[] arr = line.split(",");

                if(arr.length == 1)
                    this.add(this.root,Integer.parseInt(arr[0]), "");
                else
                    this.add(this.root,Integer.parseInt(arr[0]), arr[1]);
                line = read.readLine();
            }

            read.close();

        }
        catch(IOException e){
            System.out.println(e);
        }
    }

}
