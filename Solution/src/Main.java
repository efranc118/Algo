import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    Interval interval = new Interval(12, 16);


    ArrayList<Interval> intervals = new ArrayList<>();
    for(int i =0; i < 50; i+=5) {
      Interval temp = new Interval(i,i + 3 );
      intervals.add(temp);
    }

    solution.insert(intervals, interval );


      }
}
