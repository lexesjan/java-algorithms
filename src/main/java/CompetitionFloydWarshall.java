/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class contains a solution for the competition using Floyd Warshall's algorithm.
 *
 * @author Lexes Jan Mantiquilla
 * @version HT 2020
 */
public class CompetitionFloydWarshall {
  private static final int KILOMETER_TO_METERS = 1000;

  private double[][] distances;
  private int speedA;
  private int speedB;
  private int speedC;
  private int numVertices;
  private boolean invalidGraph;

  /**
   * @param filename: A filename containing the details of the city road network
   * @param sA, sB, sC: speeds for 3 contestants
   */
  public CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
    try {
      initialiseDistanceArray(filename);
    } catch (IOException e) {
      this.invalidGraph = true;
    }
    this.speedA = sA;
    this.speedB = sB;
    this.speedC = sC;
  }

  /** @return int: minimum minutes that will pass before the three contestants can meet */
  public int timeRequiredforCompetition() {
    int minSpeed = Math.min(Math.min(speedA, speedB), speedC);
    int maxSpeed = Math.max(Math.max(speedA, speedB), speedC);
    if (minSpeed < 50 || maxSpeed > 100) return -1;
    generateDistanceArray();
    double maxDistanceBetweenAnyTwoNodes = getMaxDistance();
    if (maxDistanceBetweenAnyTwoNodes <= 0) return -1;
    return (int) Math.ceil((maxDistanceBetweenAnyTwoNodes * KILOMETER_TO_METERS) / minSpeed);
  }

  /** @return the distance of the two vertices which are the furthest from each other */
  private double getMaxDistance() {
    if (this.invalidGraph || distances == null) return -1;
    double maxDistance = Double.NEGATIVE_INFINITY;
    for (int i = 0; i < this.numVertices; i++) {
      for (int j = 0; j < this.numVertices; j++) {
        if (i == j) continue;
        double IJDistance = distances[i][j];
        if (IJDistance == Double.POSITIVE_INFINITY) return -1;
        maxDistance = Math.max(maxDistance, IJDistance);
      }
    }
    return maxDistance;
  }

  /** Runs floyd warshall's algorithm on the graph */
  private void generateDistanceArray() {
    if (this.invalidGraph) return;
    for (int k = 0; k < this.numVertices; k++)
      for (int i = 0; i < this.numVertices; i++)
        for (int j = 0; j < this.numVertices; j++)
          if (distances[i][k] + distances[k][j] < distances[i][j])
            distances[i][j] = distances[i][k] + distances[k][j];
  }

  private void initialiseDistanceArray(String filename) throws IOException {
    if (filename == null || "".equals(filename)) return;
    BufferedReader br = new BufferedReader(new FileReader(filename));
    this.numVertices = Integer.parseInt(br.readLine());
    this.distances = new double[this.numVertices][this.numVertices];
    for (double[] distance : distances) {
      Arrays.fill(distance, Double.POSITIVE_INFINITY);
    }
    int numEdges = Integer.parseInt(br.readLine());
    int i = 0;
    String line = br.readLine();
    while (line != null) {
      Scanner scanner = new Scanner(line);
      int vertexFrom = scanner.nextInt();
      int vertexTo = scanner.nextInt();
      double cost = scanner.nextDouble();
      distances[vertexFrom][vertexFrom] = 0;
      distances[vertexFrom][vertexTo] = cost;
      scanner.close();
      line = br.readLine();
      i++;
    }
    if (i != numEdges) this.invalidGraph = true;
  }
}
