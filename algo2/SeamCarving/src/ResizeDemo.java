/******************************************************************************
 * Compilation:  javac ResizeDemo.java
 * Execution:    java ResizeDemo input.png columnsToRemove rowsToRemove
 * Dependencies: SeamCarver.java SCUtility.java
 * <p>
 * <p>
 * Read image from file specified as command line argument. Use SeamCarver
 * to remove number of rows and columns specified as command line arguments.
 * Show the images and print time elapsed to screen.
 ******************************************************************************/

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Scanner;

public class ResizeDemo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String fileName = scanner.next();
    int removeColumns = scanner.nextInt();
    int removeRows = scanner.nextInt();


    Picture inputImg = new Picture(fileName);

    StdOut.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
    SeamCarver sc = new SeamCarver(inputImg);

    Stopwatch sw = new Stopwatch();

    for (int i = 0; i < removeRows; i++) {
      int[] horizontalSeam = sc.findHorizontalSeam();
      sc.removeHorizontalSeam(horizontalSeam);
    }

    for (int i = 0; i < removeColumns; i++) {
      int[] verticalSeam = sc.findVerticalSeam();
      sc.removeVerticalSeam(verticalSeam);
    }
    Picture outputImg = sc.picture();

    StdOut.printf("new image size is %d columns by %d rows\n", sc.width(), sc.height());

    StdOut.println("Resizing time: " + sw.elapsedTime() + " seconds.");
    inputImg.show();
    outputImg.show();
  }

}
