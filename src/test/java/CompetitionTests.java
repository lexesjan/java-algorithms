import org.junit.Test;
import static org.junit.Assert.*;

// -------------------------------------------------------------------------
/**
 * Test class for CompetitionDijkstra.java and CompetitionFloydWarshall.java
 *
 * @author Lexes Jan Mantiquilla
 * @version 18/09/18 12:21:26
 */

// ~ Questions --------------------------------------------------------------
/*
 * 1. Discuss the difference in implementation between Dijkstra and Floyd Warshall
 *    version
 *    --
 *    The Dijkstra version is implemented using an inner class called Graph this
 *    contains all the information needed to run Dijkstra's algorithm. The Graph
 *    class uses a hash map which maps an Integer to a array list of Edges. The
 *    Edges contain the costs (distance from one node to another). The Floyd Warshall
 *    version does not use any inner classes. Instead to store the information
 *    needed to run Floyd Warshalls' algorithm an 2d array is used. To process
 *    the graph in the Dijkstra version a priority queue is used. In the Floyd
 *    Warshall version no additional data structures are used. Three for loops
 *    are used to process the graph.
 *
 * 2. Discuss the difference in performance between Dijkstra and Floyd Warshall
 *    version
 *    --
 *    In the Dijkstra version, the run time complexity is O(V^2 + V * E * log(V)) amortized.
 *    This is amortized due the use of hash maps. Dijkstra's algorithm worst time
 *    complexity is O(E * log(V)), however since I run Dijkstra's algorithm for every
 *    vertex the run time complexity for repeated Dijkstra's algorithm is O(V * E * log(V))
 *    I use two for loops to find the maximum distance between any two nodes which explains
 *    the final total run time complexity.
 *
 *    Floyd Warshall's worst run time complexity is O(V^3). You would use the Dijkstra
 *    version if the graph is sparse i.e. it has a small amount of edges. The Floyd
 *    The Floyd Warshall version would be used if graph is very dense. i.e. has a
 *    lot of edges. If the E constant is bigger than V, the Floyd Warshall version
 *    would finish faster. The Dijkstra version does not work graphs with negative
 *    edge costs. The Floyd Warshall version does not suffer from this problem.
 */
public class CompetitionTests {

  @Test
  public void testDijkstraConstructor() {
    CompetitionDijkstra competitionDijkstra = new CompetitionDijkstra(null, 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("doesn't exist", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    /*
    competitionDijkstra = new CompetitionDijkstra("input/competition/1000EWD.txt", 55, 60, 75);
    assertEquals(26, competitionDijkstra.timeRequiredforCompetition());
     */
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-A.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-B.txt", 55, 60, 75);
    assertEquals(9091, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-C.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-D.txt", 55, 60, 75);
    assertEquals(34, competitionDijkstra.timeRequiredforCompetition());
    /*
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-E.txt", 55, 60, 75);
    assertEquals(26, competitionDijkstra.timeRequiredforCompetition());
     */
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-F.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-G.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    /*
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-H.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
     */
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-I.txt", 55, 60, 75);
    assertEquals(219, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-J.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-K.txt", 55, 60, 75);
    assertEquals(291, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-L.txt", 55, 60, 75);
    assertEquals(146, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-M.txt", 55, 60, 75);
    assertEquals(273, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-N.txt", 55, 60, 75);
    assertEquals(146, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/tinyEWD.txt", 55, 60, 75);
    assertEquals(34, competitionDijkstra.timeRequiredforCompetition());
  }

  @Test
  public void testFWConstructor() {
    CompetitionFloydWarshall competitionFloydWarshall =
        new CompetitionFloydWarshall(null, 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall = new CompetitionFloydWarshall("doesn't exist", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    /*
    competitionFloydWarshall = new CompetitionFloydWarshall("input/competition/1000EWD.txt", 55, 60, 75);
    assertEquals(26, competitionFloydWarshall.timeRequiredforCompetition());
     */
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-A.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-B.txt", 55, 60, 75);
    assertEquals(9091, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-C.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-D.txt", 55, 60, 75);
    assertEquals(34, competitionFloydWarshall.timeRequiredforCompetition());
    /*
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-E.txt", 55, 60, 75);
    assertEquals(26, competitionFloydWarshall.timeRequiredforCompetition());
     */
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-F.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-G.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    /*
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-H.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
     */
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-I.txt", 55, 60, 75);
    assertEquals(219, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-J.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-K.txt", 55, 60, 75);
    assertEquals(291, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-L.txt", 55, 60, 75);
    assertEquals(146, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-M.txt", 55, 60, 75);
    assertEquals(273, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-N.txt", 55, 60, 75);
    assertEquals(146, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/tinyEWD.txt", 55, 60, 75);
    assertEquals(34, competitionFloydWarshall.timeRequiredforCompetition());
  }
}
