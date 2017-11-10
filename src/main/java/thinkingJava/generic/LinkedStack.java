package thinkingJava.generic;

/**
 * Created by lixiaojian on 2017/8/9.
 * 实现堆栈
 */
public class LinkedStack<T> {
    private static Node end = new Node();
    public void push(Node node) {
        end = new Node(node.value, end);
    }

    public Node pop() {
        if (isEmpty()) return null;
        Node res = end;
        end = end.next;
        return res;
    }

    public boolean isEmpty() {  //其实模拟的是走到最后一个 哨兵节点就不在打印其值了
        return (null == end || null == end.value);
    }

    /*节点*/
    static class Node<V> {
        V value;
        Node<V> next;
        Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
        Node() {};
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();
        for (String s : "I love china".split(" ")) {
            stack.push(new Node(s,null));
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().value);
        }

    }
}
