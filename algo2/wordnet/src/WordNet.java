import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordNet {

  private Digraph digraph;
  private HashMap<String, List<Integer>> synMap;
  private HashMap<Integer, String> synsetMap;
  private SAP sap;


  public WordNet(String synsets, String hypernyms) {
    synMap = new HashMap<>();
    synsetMap = new HashMap<>();
    In synSetReader = new In(synsets);
    int count = 0;
    while (synSetReader.hasNextLine()) {
      count++;
      String line = synSetReader.readLine();
      synsetMap.put(Integer.valueOf(line.split(",")[0]),line.split(",")[1] );
      String[] keys = line.split(",")[1].split(" ");
      Integer value = Integer.valueOf(line.split(",")[0]);
      for (String key : keys) {
        if (synMap.containsKey(key)) {
          synMap.get(key).add(value);
        } else {
          synMap.put(key, new ArrayList<>());
          synMap.get(key).add(value);
        }
      }
    }

    digraph = new Digraph(count);

    In hypernymReader = new In(hypernyms);
    while (hypernymReader.hasNextLine()) {
      String line = hypernymReader.readLine();
      String[] edges = line.split(",");
      for (int i = 1; i < edges.length; i++) {
        digraph.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[i]));
      }
    }
    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
    Iterable<Integer> reversePostOrder = depthFirstOrder.reversePost();
    if(depthFirstOrder.pre() != depthFirstOrder.post()) throw new IllegalArgumentException();
    sap = new SAP(digraph);
  }


  public Iterable<String> nouns() {
    return synMap.keySet();
  }

  public boolean isNoun(String word) {
    if(word == null) throw new NullPointerException();
    return synMap.containsKey(word);
  }

  public int distance(String nounA, String nounB) {
    if(nounA == null || nounB == null) throw new NullPointerException();
    List<Integer> intA = synMap.get(nounA);
    List<Integer> intB = synMap.get(nounB);
    if(intA == null || intB == null) throw new IllegalArgumentException();

    return sap.length(intA, intB);
  }

  public String sap(String nounA, String nounB) {
    if(nounA == null || nounB == null) throw new NullPointerException();
    List<Integer> intA = synMap.get(nounA);
    List<Integer> intB = synMap.get(nounB);
    if(intA == null || intB == null) throw new IllegalArgumentException();


    int value = sap.ancestor(intA, intB);
    return synsetMap.get(value);
  }



  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String synset = scanner.next();
    String hypernyms = scanner.next();
    WordNet wordnet = new WordNet(synset, hypernyms);
    System.out.println(wordnet.sap("woolly_indris", "medical_specialty"));
  }
}
