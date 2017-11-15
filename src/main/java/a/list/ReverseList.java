package a.list;

public class ReverseList {
    public static Node reverseList(Node head) {
        if(head == null || head.next == null)
            return head;
        Node reverseHead = null;
        Node p = head;
        while (p != null) {
            Node tmp = p;
            p = p.next;
            tmp.next = reverseHead;
            reverseHead = tmp;
        }
        return reverseHead;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }


    public static void main(String[] args) {

        Node h3 = new Node(1,null);
        Node h2 = new Node(2,h3);
        Node h1 = new Node(10,h2);
        Node head = new Node(4,h1);
        print(head);
        Node revHead = reverseList(head);
        System.out.println();
        print(revHead);
    }
}

class Node {
    int val;
    Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
