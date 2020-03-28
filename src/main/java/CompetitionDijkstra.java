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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public static void main(String[] args) {
    CompetitionDijkstra competitionDijkstra =
        new CompetitionDijkstra("input/competition/input-J.txt", 55, 60, 75);
    System.out.println(competitionDijkstra.timeRequiredforCompetition());
  }

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
    } catch (IOException e) {
      this.graph = null;
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
    double maxDistanceBetweenAnyTwoNodes = getMaxDistance();
    if (maxDistanceBetweenAnyTwoNodes <= 0) return -1;
    return (int) Math.ceil((maxDistanceBetweenAnyTwoNodes * KILOMETER_TO_METERS) / minSpeed);
  }

  /** @return the distance of the two vertices which are the furthest from each other */
  private double getMaxDistance() {
    double[][] distances = generateAllShortestPaths();
    if (distances == null) return -1;
    int numVertices = graph.numVertices;
    double maxDistance = Double.NEGATIVE_INFINITY;
    for (int i = 0; i < numVertices; i++) {
      for (int j = 0; j < numVertices; j++) {
        if (i == j) continue;
        double IJDistance = distances[i][j];
        if (IJDistance == Double.POSITIVE_INFINITY) return -1;
        maxDistance = Math.max(maxDistance, IJDistance);
      }
    }
    return maxDistance;
  }

  /** @return an array of shortest path tables for each vertex */
  private double[][] generateAllShortestPaths() {
    if (graph == null || graph.invalid) return null;
    double[][] shortestPaths = new double[graph.numVertices][];
    for (int i = 0; i < graph.numVertices; i++) {
      shortestPaths[i] = generateShortestPaths(i);
    }
    return shortestPaths;
  }

  /** @return the shortest path table generated using dijkstra's algorithm */
  private double[] generateShortestPaths(int startVertex) {
    Map<Integer, List<Edge>> adjacencies = graph.adjacencies;
    boolean[] seen = new boolean[graph.numVertices];
    double[] distanceTo = new double[graph.numVertices];
    Arrays.fill(distanceTo, Double.POSITIVE_INFINITY);
    distanceTo[startVertex] = 0;
    Queue<Integer> priorityQueue =
        new PriorityQueue<>(Comparator.comparing(vertex -> distanceTo[vertex]));
    priorityQueue.add(startVertex);
    while (!priorityQueue.isEmpty()) {
      int curr = priorityQueue.poll();
      seen[curr] = true;
      for (Edge adjacent : adjacencies.getOrDefault(curr, new ArrayList<>())) {
        int vertex = adjacent.edgeTo;
        if (!seen[vertex]) {
          double newDistance = distanceTo[curr] + adjacent.cost;
          if (newDistance < distanceTo[vertex]) distanceTo[vertex] = newDistance;
          priorityQueue.remove(vertex);
          priorityQueue.add(vertex);
        }
      }
    }
    return distanceTo;
  }

  private static class Graph {
    private Map<Integer, List<Edge>> adjacencies;
    private int numVertices;
    private boolean invalid;

    private Graph(String filename) throws IOException {
      if (filename == null || "".equals(filename)) return;
      adjacencies = new HashMap<>();
      BufferedReader bf = new BufferedReader(new FileReader(filename));
      this.numVertices = Integer.parseInt(bf.readLine());
      int numEdges = Integer.parseInt(bf.readLine());
      int i = 0;
      String line = bf.readLine();
      while (line != null) {
        Scanner scanner = new Scanner(line);
        int vertexFrom = scanner.nextInt();
        int vertexTo = scanner.nextInt();
        double cost = scanner.nextDouble();
        List<Edge> adjList = adjacencies.getOrDefault(vertexFrom, new ArrayList<>());
        adjList.add(new Edge(vertexFrom, vertexTo, cost));
        adjacencies.put(vertexFrom, adjList);
        scanner.close();
        line = bf.readLine();
        i++;
      }
      if (i != numEdges) this.invalid = true;
    }
  }

  private static class Edge {
    private int edgeFrom;
    private int edgeTo;
    double cost;

    private Edge(int edgeFrom, int edgeTo, double cost) {
      this.edgeFrom = edgeFrom;
      this.edgeTo = edgeTo;
      this.cost = cost;
    }
  }
}
