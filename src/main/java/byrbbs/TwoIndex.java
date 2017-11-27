package byrbbs;

import java.util.LinkedList;
import java.util.List;

/*诸如 010110111011 串中获取所有 1 连续串的首尾索引*/
public class TwoIndex {

    public static void main(String[] args) {
        List<Index> list = getTwoIndex("1010");
        System.out.println(list.size());
    }

    public static List<Index> getTwoIndex(String s) {
        // todo invalid check
        char[] ch = s.toCharArray();
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for (int i=ch.length-1;i>=0;i--) {
           if (ch[i] == '1') {
               stack.push(i);
           }
        }
        List<Index> resList = new LinkedList<Index>();
        while (!stack.isEmpty()) {
            int start = stack.pop();
            if (stack.peek() != null && stack.peek() == start + 1) {
                int end = stack.pop();
                int tmp = start;
                while (end == tmp + 1) {
                    if (stack.peek() != null && stack.peek() == end + 1) {
                        tmp = end;
                        end = stack.pop();
                    } else {
                        resList.add(new Index(start, end));
                        break;
                    }
                }
            }
        }
        return resList;
    }
}

class Index{
    private Integer start;
    private Integer end;

    public Index(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}
