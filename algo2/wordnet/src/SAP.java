import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.HashSet;


public class SAP {

  private Digraph digraph;

  public SAP(Digraph digraph) {
    this.digraph = new Digraph(digraph);
  }

  public int length(int v, int w) {
    if(v < 0 || v >= digraph.V() || w < 0 || w >=digraph.V()) throw new IndexOutOfBoundsException();

    BreadthFirstDirectedPaths vpaths = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths wpaths = new BreadthFirstDirectedPaths(digraph, w);

    int ancestor = ancestor(v, w);
    if(ancestor == -1) return -1;
    return vpaths.distTo(ancestor) + wpaths.distTo(ancestor);
  }


  public int ancestor(int v, int w) {
    if(v < 0 || v >= digraph.V() || w < 0 || w >=digraph.V()) throw new IndexOutOfBoundsException();
    int closestAncestor = -1;
    int closestLength = Integer.MAX_VALUE;
    BreadthFirstDirectedPaths vpaths = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths wpaths = new BreadthFirstDirectedPaths(digraph, w);

    ArrayList<Integer> sharedAncestors = new ArrayList<>();
    Queue<Integer> ancestors = new Queue<>();
    HashSet<Integer> marked = new HashSet<>();
    ancestors.enqueue(v);
    ancestors.enqueue(w);
    while (!ancestors.isEmpty()) {
      Integer temp = ancestors.dequeue();
      marked.add(temp);
      if (vpaths.hasPathTo(temp) && wpaths.hasPathTo(temp)) {
        sharedAncestors.add(temp);
      }
      for (Integer integer : digraph.adj(temp)) {
        if(!marked.contains(integer))
        ancestors.enqueue(integer);
      }
    }
    for(Integer sharedAncestor: sharedAncestors) {
      if(vpaths.distTo(sharedAncestor) + wpaths.distTo(sharedAncestor) < closestLength) {
        closestAncestor = sharedAncestor;
        closestLength = vpaths.distTo(sharedAncestor) + wpaths.distTo(sharedAncestor);
      }
    }
    return closestAncestor;
  }

  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if(v == null || w == null) throw new NullPointerException();
    for(Integer vInt : v) {
      if(vInt < 0 || vInt >= digraph.V()) throw new IndexOutOfBoundsException();
    }
    for(Integer wInt : w) {
      if(wInt < 0 || wInt >= digraph.V()) throw new IndexOutOfBoundsException();
    }

    BreadthFirstDirectedPaths vpaths = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths wpaths = new BreadthFirstDirectedPaths(digraph, w);

    int ancestor = ancestor(v, w);
    if(ancestor == -1) return -1;
    return vpaths.distTo(ancestor) + wpaths.distTo(ancestor);
  }

  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if(v == null || w == null) throw new NullPointerException();
    for(Integer vInt : v) {
      if(vInt < 0 || vInt >= digraph.V()) throw new IndexOutOfBoundsException();
    }
    for(Integer wInt : w) {
      if(wInt < 0 || wInt >= digraph.V()) throw new IndexOutOfBoundsException();
    }
    int closestAncestor = -1;
    int closestLength = Integer.MAX_VALUE;
    BreadthFirstDirectedPaths vpaths = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths wpaths = new BreadthFirstDirectedPaths(digraph, w);

    ArrayList<Integer> sharedAncestors = new ArrayList<>();
    Queue<Integer> ancestors = new Queue<>();
    HashSet<Integer> marked = new HashSet<>();
    for(Integer subset1: v) {
      ancestors.enqueue(subset1);
    }
    for(Integer subset2: w) {
      ancestors.enqueue(subset2);
    }
    while (!ancestors.isEmpty()) {
      Integer temp = ancestors.dequeue();
      marked.add(temp);
      if (vpaths.hasPathTo(temp) && wpaths.hasPathTo(temp)) {
        sharedAncestors.add(temp);
      }
      for (Integer integer : digraph.adj(temp)) {
        if(!marked.contains(integer))
        ancestors.enqueue(integer);
      }
    }
    for(Integer sharedAncestor: sharedAncestors) {
      if(vpaths.distTo(sharedAncestor) + wpaths.distTo(sharedAncestor) < closestLength) {
        closestAncestor = sharedAncestor;
        closestLength = vpaths.distTo(sharedAncestor) + wpaths.distTo(sharedAncestor);
      }
    }
    return closestAncestor;
  }

//  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    In in = new In(scanner.next());
//    Digraph G = new Digraph(in);
//    SAP sap = new SAP(G);
//    while (!StdIn.isEmpty()) {
//      String line = StdIn.readLine();
//      String[] nums = line.split(",");
//      ArrayList<Integer> vList = new ArrayList<>();
//      for(String num: nums) {
//        vList.add(Integer.parseInt(num));
//      }
//
//      line = StdIn.readLine();
//      nums = line.split(",");
//      ArrayList<Integer> wList = new ArrayList<>();
//      for(String num: nums) {
//        wList.add(Integer.parseInt(num));
//      }
//      int length   = sap.length(vList, wList);
//      int ancestor = sap.ancestor(vList, wList);
//      StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//    }
//  }


}
