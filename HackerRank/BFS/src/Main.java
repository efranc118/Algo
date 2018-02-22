import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static class Node {
    Node(int id) {
      this.id = id;
      this.distance = 0;
      connections = new LinkedList<>();
    }

    int id, distance;
    List<Node> connections;
  }

  public static int[] BFS(int start, Node[] nodes) {
    boolean[] visited = new boolean[nodes.length];
    int[] distances = new int[nodes.length];
    for (int i = 0; i < distances.length; i++) {
      distances[i] = Integer.MAX_VALUE;
    }

    LinkedList<Node> nodeHolder = new LinkedList<>();
    nodeHolder.add(nodes[start]);

    while (!nodeHolder.isEmpty()) {
      Node top = nodeHolder.poll();
      if (visited[top.id]) continue;
      visited[top.id] = true;
      if (distances[top.id] > top.distance) distances[top.id] = top.distance;

      for (Node neighbor : top.connections) {
        if(!visited[neighbor.id] && !nodeHolder.contains(neighbor)) {
          neighbor.distance = top.distance + 6;
          nodeHolder.addLast(neighbor);
        }
      }

    }


    return distances;

  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int queryCount = scan.nextInt();
    for (int i = 0; i < queryCount; i++) {
      int nodeCount = scan.nextInt();
      int connectionCount = scan.nextInt();
      Node[] nodes = new Node[nodeCount];
      for (int j = 0; j < nodeCount; j++) {
        nodes[j] = new Node(j);
      }
      for (int j = 0; j < connectionCount; j++) {
        int nodeA = scan.nextInt() - 1;
        int nodeB = scan.nextInt() - 1;
        nodes[nodeA].connections.add(nodes[nodeB]);
        nodes[nodeB].connections.add(nodes[nodeA]);
      }
      int start = scan.nextInt() - 1;
      int[] result = BFS(start, nodes);
      for (int j = 0; j < result.length; j++) {
        if (j != start) {
          if (result[j] == Integer.MAX_VALUE) {
            System.out.print("-1 ");
          } else {
            System.out.print(result[j] + " ");
          }
        }
      }
      System.out.println();
    }
  }
}
