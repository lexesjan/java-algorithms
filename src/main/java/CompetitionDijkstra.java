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

  private static class Graph {
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
        vertices[vertexFrom].connections.add(vertices[vertexTo]);
        vertices[vertexFrom].costs[vertexTo] = distance;
      }
      scanner.close();
    }

    private class Vertex {
      int label;
      double[] costs;
      List<Vertex> connections;

      private Vertex(int label) {
        this.label = label;
        this.costs = new double[Graph.this.vertices.length];
        this.connections = new ArrayList<>();
      }
    }
  }
}
