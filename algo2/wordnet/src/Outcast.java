import java.util.Scanner;

public class Outcast {

  private WordNet wordNet;

  public Outcast(WordNet wordNet) {
    this.wordNet = wordNet;
  }


  public String outcast(String[] nouns) {
    int max = 0;
    String outcast = "";
    for(String noun: nouns) {
      int distanceSum = 0;
      for(String compare: nouns) {
        if(noun.equals(compare)) continue;
        distanceSum += wordNet.distance(noun, compare);
      }
      if(distanceSum > max) {
        max = distanceSum;
        outcast = noun;
      }
    }
    return outcast;
  }



//  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    String synset = scanner.next();
//    String hypernyms = scanner.next();
//    WordNet wordnet = new WordNet(synset, hypernyms);
//    Outcast outcast = new Outcast(wordnet);
//    while (scanner.hasNext()) {
//      String file = scanner.next();
//      In in = new In(file);
//      String[] nouns = in.readAllStrings();
//      StdOut.println(file + ": " + outcast.outcast(nouns));
//    }
//  }


}
