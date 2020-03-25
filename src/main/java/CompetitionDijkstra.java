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
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * This class contains a solution for the competition using Dijkstra's algorithm.
 *
 * @author Lexes Jan Mantiquilla
 * @version HT 2020
 */
public class CompetitionDijkstra {
  private static final int KILOMETER_TO_METERS = 1000;

  private Graph graph;
  private int speedA;
  private int speedB;
  private int speedC;

  /**
   * @param filename: A filename containing the details of the city road network
   * @param sA, sB, sC: speeds for 3 contestants
   */
  public CompetitionDijkstra(String filename, int sA, int sB, int sC) {
    try {
      this.graph = new Graph(filename);
    } catch (FileNotFoundException e) {
      this.graph = null;
    }
    this.speedA = sA;
    this.speedB = sB;
    this.speedC = sC;
  }

  /** @return int: minimum minutes that will pass before the three contestants can meet */
  public int timeRequiredforCompetition() {
    int minSpeed = Math.min(Math.min(speedA, speedB), speedC);
    double maxDistanceBetweenAnyTwoNodes = getMaxDistance();
    if (minSpeed < 0 || maxDistanceBetweenAnyTwoNodes < 0) return -1;
    return (int) Math.ceil((maxDistanceBetweenAnyTwoNodes * KILOMETER_TO_METERS) / minSpeed);
  }

  /** @return the distance of the two vertices which are the furthest from each other */
  private double getMaxDistance() {
    DijkstraEntry[][] allShortestPaths = generateAllShortestPaths();
    if (allShortestPaths == null) return -1;
    int numVertices = graph.numVertices;
    double maxDistance = Double.MIN_VALUE;
    for (int i = 0; i < numVertices; i++) {
      for (int j = 0; j < numVertices; j++) {
        if (i == j) continue;
        double IJDistance = allShortestPaths[i][j].shortestDistance;
        if (IJDistance != Double.POSITIVE_INFINITY) maxDistance = Math.max(maxDistance, IJDistance);
      }
    }
    return maxDistance;
  }

  /** @return an array of shortest path tables for each vertex */
  private DijkstraEntry[][] generateAllShortestPaths() {
    if (graph == null) return null;
    DijkstraEntry[][] shortestPaths = new DijkstraEntry[graph.numVertices][];
    for (int i = 0; i < graph.numVertices; i++) {
      shortestPaths[i] = generateShortestPaths(i);
    }
    return shortestPaths;
  }

  /** @return the shortest path table generated using dijkstra's algorithm */
  private DijkstraEntry[] generateShortestPaths(int startVertex) {
    boolean[] seen = new boolean[graph.numVertices];
    Vertex[] vertices = graph.vertices;
    DijkstraEntry[] shortestPaths = initialiseShortestPathsTable();
    shortestPaths[startVertex].shortestDistance = 0;
    Queue<DijkstraEntry> priorityQueue =
        new PriorityQueue<>(Comparator.comparing(dijkstraEntry -> dijkstraEntry.shortestDistance));
    priorityQueue.add(shortestPaths[startVertex]);
    while (!priorityQueue.isEmpty()) {
      DijkstraEntry currEntry = priorityQueue.poll();
      Vertex curr = vertices[currEntry.vertexId];
      seen[curr.label] = true;
      for (Vertex adjacent : curr.adjacent) {
        if (!seen[adjacent.label]) {
          DijkstraEntry toInsert = shortestPaths[adjacent.label];
          double calculatedDistance = currEntry.shortestDistance + curr.costs[adjacent.label];
          if (calculatedDistance < toInsert.shortestDistance) {
            toInsert.shortestDistance = calculatedDistance;
            toInsert.prevId = curr.label;
          }
          priorityQueue.remove(toInsert);
          priorityQueue.add(toInsert);
        }
      }
    }
    return shortestPaths;
  }

  /** @return the initialised shortest paths table according to dijkstra's algorithm */
  private DijkstraEntry[] initialiseShortestPathsTable() {
    DijkstraEntry[] shortestPaths = new DijkstraEntry[graph.numVertices];
    for (int i = 0; i < shortestPaths.length; i++) {
      shortestPaths[i] = new DijkstraEntry(-1, Double.POSITIVE_INFINITY, i);
    }
    return shortestPaths;
  }

  private static class DijkstraEntry {
    private int prevId;
    private double shortestDistance;
    private int vertexId;

    private DijkstraEntry(int prevId, double shortestDistance, int vertex) {
      this.prevId = prevId;
      this.shortestDistance = shortestDistance;
      this.vertexId = vertex;
    }
  }

  private static class Graph {
    private Vertex[] vertices;
    private int numVertices;

    private Graph(String filename) throws FileNotFoundException {
      if (filename == null || "".equals(filename)) return;
      Scanner scanner = new Scanner(new File(filename));
      this.numVertices = scanner.nextInt();
      this.vertices = new Vertex[numVertices];
      int numEdges = scanner.nextInt();
      for (int i = 0; i < numEdges; i++) {
        int vertexFrom = scanner.nextInt();
        int vertexTo = scanner.nextInt();
        double distance = scanner.nextDouble();
        if (vertices[vertexFrom] == null)
          vertices[vertexFrom] = new Vertex(vertexFrom, this.numVertices);
        if (vertices[vertexTo] == null) vertices[vertexTo] = new Vertex(vertexTo, this.numVertices);
        vertices[vertexFrom].adjacent.add(vertices[vertexTo]);
        vertices[vertexFrom].costs[vertexTo] = distance;
      }
      scanner.close();
    }
  }

  private static class Vertex {
    int label;
    double[] costs;
    List<Vertex> adjacent;

    private Vertex(int label, int numVertices) {
      this.label = label;
      this.costs = new double[numVertices];
      this.adjacent = new ArrayList<>();
    }
  }
}
