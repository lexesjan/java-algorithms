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
  private Graph graph;
  private int speedA;
  private int speedB;
  private int speedC;

  /**
   * @param filename: A filename containing the details of the city road network
   * @param sA, sB, sC: speeds for 3 contestants
   */
  public CompetitionDijkstra(String filename, int sA, int sB, int sC) {
    this.graph = new Graph(filename);
    this.speedA = sA;
    this.speedB = sB;
    this.speedC = sC;
  }

  /** @return int: minimum minutes that will pass before the three contestants can meet */
  public int timeRequiredforCompetition() {
    if (speedA < 0 || speedB < 0 || speedC < 0) return -1;
    return 0;
  }

  /** @return an array of shortest path tables for each vertex */
  private DijkstraEntry[][] generateAllShortestPaths() {
    if (graph == null) return null;
    DijkstraEntry[][] shortestPaths = new DijkstraEntry[graph.vertices.length][];
    for (int i = 0; i < shortestPaths.length; i++) {
      shortestPaths[i] = generateShortestPaths(i);
    }
    return shortestPaths;
  }

  /** @return the shortest path table generated using dijkstra's algorithm */
  private DijkstraEntry[] generateShortestPaths(int startVertex) {
    boolean[] seen = new boolean[graph.vertices.length];
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
      for (Vertex adjacent : curr.adjacents) {
        if (!seen[adjacent.label]) {
          DijkstraEntry toInsert = shortestPaths[adjacent.label];
          double calculatedDistance = currEntry.shortestDistance + curr.costs[adjacent.label];
          if (calculatedDistance < toInsert.shortestDistance) {
            toInsert.shortestDistance = calculatedDistance;
            toInsert.prevId = curr.label;
          }
          priorityQueue.add(toInsert);
        }
      }
    }
    return shortestPaths;
  }

  /** @return the initialised shortest paths table according to dijkstra's algorithm */
  private DijkstraEntry[] initialiseShortestPathsTable() {
    DijkstraEntry[] shortestPaths = new DijkstraEntry[graph.vertices.length];
    for (int i = 0; i < shortestPaths.length; i++) {
      shortestPaths[i] = new DijkstraEntry(-1, Double.MAX_VALUE, i);
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

  private class Graph {
    private Vertex[] vertices;

    private Graph(String filename) {
      if (filename == null || "".equals(filename)) return;
      Scanner scanner = new Scanner(filename);
      this.vertices = new Vertex[scanner.nextInt()];
      int numEdges = scanner.nextInt();
      for (int i = 0; i < numEdges; i++) {
        int vertexFrom = scanner.nextInt();
        int vertexTo = scanner.nextInt();
        double distance = scanner.nextDouble();
        if (vertices[vertexFrom] == null) vertices[vertexFrom] = new Vertex(vertexFrom);
        if (vertices[vertexTo] == null) vertices[vertexTo] = new Vertex(vertexTo);
        vertices[vertexFrom].adjacents.add(vertices[vertexTo]);
        vertices[vertexFrom].costs[vertexTo] = distance;
      }
      scanner.close();
    }
  }

  private class Vertex {
    int label;
    double[] costs;
    List<Vertex> adjacents;

    private Vertex(int label) {
      this.label = label;
      this.costs = new double[graph.vertices.length];
      this.adjacents = new ArrayList<>();
    }
  }
}
