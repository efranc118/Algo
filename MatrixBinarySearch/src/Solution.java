import java.util.ArrayList;

public class Solution {
  public static int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
    if(a.size() < 1) return 0;
    ArrayList<Integer> ranges = new ArrayList<Integer>();
    for(int i = 0; i < a.size(); i++) {
      ranges.add(a.get(i).get(0));
    }

    Integer min = 0;
    Integer max = ranges.size() - 1;
    Integer mid = 0;
    while(min <= max) {
      mid = (min + max) / 2;
      if(ranges.get(mid) < b) {
        min = mid + 1;
      }
      else if(ranges.get(mid) > b) {
        max = mid;
      }
      else if(ranges.get(mid) == b) return 1;
    }
    //System.out.println(mid);
    if(ranges.get(mid) > b && mid != 0) ranges = a.get(mid - 1);
    else if(ranges.get(mid) < b) ranges = a.get(mid);

    min = 0;
    max = ranges.size() - 1;

    while(min < max) {
      mid = (min + max) / 2;
      if(ranges.get(mid) < b) {
        min = mid + 1;
      }
      else if(ranges.get(mid) > b) {
        max = mid;
      }
      else if(ranges.get(mid) == b) return 1;
    }
    return 0;
  }
}
