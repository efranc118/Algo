import java.util.PriorityQueue;

public class Kiloman {

  Kiloman() {
    visited = new boolean[32768];
    numWeapons = 3;
  }

  boolean[] visited;
  int numWeapons;


  public static class Node implements Comparable {
    int weapons;
    int shots;

    Node(int weapons, int shots) {
      this.weapons = weapons;
      this.shots = shots;
    }


    @Override public int compareTo(Object o) {
      Node right = (Node) o;
      if (this.shots < right.shots) return -1;
      if (this.shots > right.shots) return 1;
      return 0;
    }
  }


  int leastShots(String[] damageChart, int[] bossHealth) {
    PriorityQueue<Node> pq = new PriorityQueue<>();

    pq.add(new Node(0, 0));


    while (!pq.isEmpty()) {
      Node temp = pq.poll();

      if (visited[temp.weapons]) continue;
      visited[temp.weapons] = true;

      if(temp.weapons == (1 << numWeapons ) - 1) {
        return temp.shots;
      }

      for(int i = 0; i < damageChart.length; i++) {
        if(((temp.weapons >> i) & 1) == 1) continue;

        int best = bossHealth[i];
        for(int j = 0; j < damageChart.length; j++) {
          if (i == j) continue;
          if((((temp.weapons >> j) & 1) == 1) && damageChart[j].charAt(i) != '0') {
            int shotsNeeded = bossHealth[i] / (damageChart[j].charAt(i) - '0');
            if (bossHealth[i] % (damageChart[j].charAt(i) - '0') != 0) shotsNeeded++;
            best = Math.min(best, shotsNeeded);
          }
        }
        pq.add(new Node(temp.weapons | (1 << i), temp.shots + best));
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Kiloman kiloMan = new Kiloman();
    String[] damageChart = new String[3];
    int[] bossHealth = new int[3];
    damageChart[0] = "070";
    damageChart[1] = "500";
    damageChart[2] = "140";
    bossHealth[0] = bossHealth[1] = bossHealth[2] = 150;

    int result = kiloMan.leastShots(damageChart, bossHealth);
    System.out.println(result);
  }
}
