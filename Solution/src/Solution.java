
import java.util.ArrayList;

public class Solution {
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    Integer newStart = null;
    Integer newEnd = null;
    ArrayList<Interval> result = new ArrayList<>();
    int ptr = 0;
    while(ptr < intervals.size()) {
      Interval temp = intervals.get(ptr);
      if(newStart == null) {
        if(newInterval.start < temp.start) {
          newStart = newInterval.start;
        }
        if(temp.start < newInterval.start
          && newInterval.start < temp.end) {
          newStart = Math.min(newInterval.start, temp.start);
        }
      }
      if(newEnd == null) {
        if(newInterval.end < temp.start) {
          newEnd = newInterval.end;
        }
        if(temp.start < newInterval.end
          && newInterval.end < temp.end) {
          newEnd = Math.max(newInterval.end, temp.end);
        }
      }
      if(newStart == null) {
        result.add(temp);
      }
      if(newStart != null && newEnd != null) {
        result.add(new Interval(newStart, newEnd));
        ptr++;
        break;
      }
      ptr++;
    }
    if(newStart == null && newEnd == null) result.add(newInterval);
    while(ptr < intervals.size()) {
      result.add(intervals.get(ptr));
      ptr++;
    }

    if(ptr == intervals.size() - 1 && newEnd == null) {
      result.add(new Interval(newStart, newInterval.end));
    }



    return result;
  }
}
