import edu.princeton.cs.algs4.*;

import java.awt.*;


public class SeamCarver {

  private Color[][] colors;
  private boolean isTransposed;

  public SeamCarver(Picture picture) {
    if (picture == null) throw new NullPointerException();
    colors = new Color[picture.width()][picture.height()];

    //Initialize Color Array
    for (int i = 0; i < picture.width(); i++) {
      for (int j = 0; j < picture.height(); j++) {
        colors[i][j] = picture.get(i, j);
      }
    }

    isTransposed = false;


  }

  private double[][] createEnergyArray() {
    double[][] energy = new double[colors.length][colors[0].length];
    for (int i = 0; i < colors.length; i++) {
      for (int j = 0; j < colors[0].length; j++) {
        energy[i][j] = calculateEnergy(i, j);
      }
    }
    return energy;
  }

  private double calculateEnergy(int i, int j) {
    if (i == 0 || i == colors.length - 1 || j == 0 || j == colors[0].length - 1) return 1000;
    Color previousHorizontal = colors[i - 1][j];
    Color nextHorizontal = colors[i + 1][j];
    Color previousVertical = colors[i][j - 1];
    Color nextVertical = colors[i][j + 1];

    double horizontalDifference = getGradientSquared(previousHorizontal, nextHorizontal);
    double verticalDifference = getGradientSquared(previousVertical, nextVertical);
    return Math.sqrt(verticalDifference + horizontalDifference);

  }

  private Double getGradientSquared(Color previous, Color next) {
    double horizontalBlueDifference = previous.getBlue() - next.getBlue();
    double horizontalGreenDifference = previous.getGreen() - next.getGreen();
    double horizontalRedDifference = previous.getRed() - next.getRed();

    return Math.pow(horizontalBlueDifference, 2) + Math.pow(horizontalGreenDifference, 2) + Math.pow(horizontalRedDifference, 2);

  }

  private void transposePicture() {
    Color[][] newColors = new Color[colors[0].length][colors.length];
    for (int i = 0; i < colors.length; i++) {
      for (int j = 0; j < colors[0].length; j++) {
        newColors[j][i] = colors[i][j];
      }
    }
    colors = newColors;
    isTransposed = !isTransposed;
  }


  public Picture picture() {
    Picture currentPicture = new Picture(colors.length, colors[0].length);
    for (int i = 0; i < colors.length; i++) {
      for (int j = 0; j < colors[0].length; j++) {
        currentPicture.set(i, j, colors[i][j]);
      }
    }
    return currentPicture;
  }

  public int width() {
    return colors.length;
  }

  public int height() {
    return colors[0].length;
  }

  public double energy(int x, int y) {
    if (x < 0 || x >= colors.length || y < 0 || y >= colors[0].length) throw new IndexOutOfBoundsException();
    return calculateEnergy(x, y);
  }

  public int[] findHorizontalSeam() {
    if (!isTransposed) {
      transposePicture();
    }
    int[] result = findMinSeam();
    if (isTransposed) {
      transposePicture();
    }
    return result;
  }

  public int[] findVerticalSeam() {
    if (isTransposed) {
      transposePicture();
    }
    return findMinSeam();
  }

  private int[] findMinSeam() {
    double[][] minDistTo = new double[colors.length][colors[0].length];
    int[][] pathTo = new int[colors.length][colors[0].length];
    double[][] energy = createEnergyArray();
    for (int i = 0; i < colors.length; i++) {
      for (int j = 0; j < colors[0].length; j++) {
        minDistTo[i][j] = Double.POSITIVE_INFINITY;
        pathTo[i][j] = -1;
      }
    }

    for (int i = 0; i < colors.length; i++) {
      minDistTo[i][0] = 0;
    }

    for (int i = 0; i < colors[0].length; i++) {
      for (int j = 0; j < colors.length; j++) {
        findSeam(j, i, minDistTo, pathTo, energy);
      }
    }
    double minDist = Double.POSITIVE_INFINITY;
    int path = 0;
    for (int i = 0; i < colors.length; i++) {
      if (minDist > minDistTo[i][colors[0].length - 1]) {
        minDist = minDistTo[i][colors[0].length - 1];
        path = i;
      }
    }

    int[] shortestPath = new int[colors[0].length];
    shortestPath[colors[0].length - 1] = path;
    for (int i = colors[0].length - 2; i >= 0; i--) {
      shortestPath[i] = pathTo[shortestPath[i + 1]][i + 1];
    }

    return shortestPath;
  }


  private void findSeam(int col, int row, double[][] minDistTo, int[][] pathTo, double[][] energy) {
    if (row + 1 < energy[0].length) {
      double currentEnergy = minDistTo[col][row] + energy[col][row];
      for (int i = -1; i < 2; i++) {
        if (col + i >= 0 && col + i < energy.length) {
          if (minDistTo[col + i][row + 1] > energy[col + i][row + 1] + currentEnergy) {
            minDistTo[col + i][row + 1] = energy[col + i][row + 1] + currentEnergy;
            pathTo[col + i][row + 1] = col;
          }
        }
      }
    }
  }


  public void removeHorizontalSeam(int[] seam) {
    if (isTransposed) transposePicture();
    colors = removeSeam(seam);
  }

  public void removeVerticalSeam(int[] seam) {
    if (!isTransposed) transposePicture();
    colors = removeSeam(seam);
    if (isTransposed) transposePicture();
  }

  private Color[][] removeSeam(int[] seam) {
    Color[][] updatedPicture = new Color[colors.length][colors[0].length - 1];
    if (seam.length != colors.length) throw new IllegalArgumentException();
    int previousPixel = seam[0];
    for (int i = 0; i < colors.length; i++) {
      if (seam[i] < 0 || seam[i] >= colors[i].length || Math.abs(seam[i] - previousPixel) > 1) throw new IllegalArgumentException();
      previousPixel = seam[i];
      System.arraycopy(colors[i], 0, updatedPicture[i], 0, seam[i]);
      System.arraycopy(colors[i], seam[i] + 1, updatedPicture[i], seam[i], (colors[i].length - 1) - seam[i]);
    }
    return updatedPicture;
  }

}



