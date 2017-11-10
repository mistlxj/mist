package thinkingJava.generic;

/**
 * Created by lixiaojian on 2017/8/9.
 * 这个版本更好地使用了泛型， 更便于给用户使用
 * 内部类Node中的泛型参数V 可以去掉，因为内部类可以共用外部类的泛型参数【已测试验证】
 */
public class LinkedStackII<T> {
    private Node<T> top = new Node();
    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        T res = top.value;
        if (!top.isEnd()) {
            top = top.next;
        }
        return res;
    }

    public T peek() {
        return top.value;
    }


    /*节点*/
     class Node<V> {
        V value;
        Node next;
        Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
        Node() {};

        boolean isEnd() {
            return (null == value && null == next);
        }
    }

    public static void main(String[] args) {
        LinkedStackII<String> stack = new LinkedStackII<String>();
        for (String s : "I love china".split(" ")) {
            stack.push(s);
        }
        while (null != stack.peek()) {
            System.out.println(stack.pop());
        }

    }
}
