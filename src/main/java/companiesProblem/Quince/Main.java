package companiesProblem.Quince;//package Demo2;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        List<Pair>list = new ArrayList<>();
//        list.add(new Pair(3,9));
//        list.add(new Pair(7,12));
//        list.add(new Pair(3,8));
//        list.add(new Pair(6,8));
//        list.add(new Pair(9,10));
//        list.add(new Pair(2,9));
//        list.add(new Pair(0,9));
//        list.add(new Pair(3,9));
//        list.add(new Pair(0,6));
//        list.add(new Pair(2,8));
//
//
//
////        [[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
//
//        // 1,6  2,8   7,12 10,16
//        // Step 1: we need to sort the array with start time
//        Collections.sort(list,(a,b)->a.first-b.first);
//
//        // Step 2: merge overlapping problem
//        Pair current= list.get(0);
//        List<Pair>ans=new ArrayList<>();
//        ans.add(current);
//
//        for(int i=1;i<list.size();i++){
//            if(current.second>=list.get(i).first){
//                // this is merge overllaping
//                current.first= Math.max(current.first,list.get(i).first);
//                current.second= Math.min(current.second,list.get(i).second);
//            }else{
//                ans.add(list.get(i));
//                current=list.get(i);
//            }
//        }
//
//        System.out.println(ans.size());
//    }
//}
//
//class Pair{
//    int first;
//    int second;
//    public Pair(int first,int second){
//        this.first=first;
//        this.second=second;
//    }
//}
//
//
//
//Q1.
//There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
//
//Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
//
//Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
//
//
//        Input: points = [[c],[2,8],[1,6],[7,12]]
//Output: 2
//Explanation: The balloons can be burst by 2 arrows:
//        - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
//        - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
//
//Input: points = [[1,2],[3,4],[5,6],[7,8]]
//Output: 4
//Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
//
//
//        Input: points = [[1,2],[2,3],[3,4],[4,5]]
//Output: 2
//Explanation: The balloons can be burst by 2 arrows:
//        - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
//        - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
//
//Input 4:
//        [[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
//Expected Answer : 2
//
//
//Step 1:
//        1,6  2,8   7,12 10,16
//
//Step 2:
//        ( 2, 6) 7, 12  10,16
//
//Step 3:
//        2, 6   (10 , 12)
//
//
//        package Demo2;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//
//public class Main {
//    public static void main(String[] args) {
//        List<Pair>list = new ArrayList<>();
//        list.add(new Pair(3,9));
//        list.add(new Pair(7,12));
//        list.add(new Pair(3,8));
//        list.add(new Pair(6,8));
//        list.add(new Pair(9,10));
//        list.add(new Pair(2,9));
//        list.add(new Pair(0,9));
//        list.add(new Pair(3,9));
//        list.add(new Pair(0,6));
//        list.add(new Pair(2,8));
//
//
//
//
//
//
////        [[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
//
//
//        // 1,6  2,8   7,12 10,16
//        // Step 1: we need to sort the array with start time
//        Collections.sort(list,(a,b)->a.first-b.first);
//
//
//        // Step 2: merge overlapping problem
//        Pair current= list.get(0);
//        List<Pair>ans=new ArrayList<>();
//        ans.add(current);
//
//
//        for(int i=1;i<list.size();i++){
//            if(current.second>=list.get(i).first){
//                // this is merge overllaping
//                current.first= Math.max(current.first,list.get(i).first);
//                current.second= Math.min(current.second,list.get(i).second);
//            }else{
//                ans.add(list.get(i));
//                current=list.get(i);
//            }
//        }
//
//
//        System.out.println(ans.size());
//    }
//}
//
//
//class Pair{
//    int first;
//    int second;
//    public Pair(int first,int second){
//        this.first=first;
//        this.second=second;
//    }
//}
//
//
//
//
