import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public final class test {
    public static void main(String[] args) {
        System.out.println("Dispensed item");

        TreeSet<Node>set= new TreeSet<>((a,b)->Integer.compare(a.i,b.i));
        set.add(new Node(1,1));
        set.add(new Node(2,2));
        set.add(new Node(3,3));
        set.add(new Node(4,4));
        set.add(new Node(5,5));

        SortedSet<Node> greater = set.tailSet(new Node(3, 0), false);

        System.out.println(greater.toString());
    }

    private static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i=i;
            this.j=j;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}
