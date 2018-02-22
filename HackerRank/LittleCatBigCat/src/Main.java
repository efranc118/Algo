import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {


  public static class ShoppingCenter implements Comparable {
    int id, mask;
    int value;

    @Override public int compareTo(Object o) {
      ShoppingCenter right = (ShoppingCenter) o;
      if (this.value < right.value) return 1;
      if (this.value > right.value) return -1;
      return 0;
    }

    ShoppingCenter(int id, int mask, int value) {
      this.id = id;
      this.mask = mask;
      this.value = value;
    }


  }

  public static class Edge {
    int a, b;
    int cost;

    Edge(int a, int b, int cost) {
      this.a = a;
      this.b = b;
      this.cost = cost;
    }

  }


  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int shoppingCenterCount = scan.nextInt();
    int roadCount = scan.nextInt();
    int typesOfFish = scan.nextInt();
    ShoppingCenter[] itemsForSale = new ShoppingCenter[shoppingCenterCount];
    for (int i = 0; i < shoppingCenterCount; i++) {
      int itemCount = scan.nextInt();
      for(int j = 0; j < itemCount; ++j) {
        itemsForSale[i] |= 1 << (scan.nextInt() - 1);
      }
    }

    ArrayList<Edge>[] roads = new ArrayList[shoppingCenterCount];
    for(int i = 0; i < shoppingCenterCount; i++) {
      roads[i] = new ArrayList<>();
    }

    for(int i = 0; i < roadCount; i++) {
      int roadA = scan.nextInt();
      int roadB = scan.nextInt();
      int cost = scan.nextInt();
      roads[roadA].add(new Edge(roadA, roadB, cost));
      roads[roadB].add(new Edge(roadB, roadA, cost));
    }

    PriorityQueue<>



  }


  private static int[][] dijkstra(int s, ArrayList<Edge>[] roads, int[] itemsForSale) {



  }


}
