import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Test class for CompetitionDijkstra.java and CompetitionFloydWarshall.java
 *
 * @author Lexes Jan Mantiquilla
 * @version 18/09/18 12:21:26
 */
public class CompetitionTests {

  @Test
  public void testDijkstraConstructor() {
    CompetitionDijkstra competitionDijkstra =
        new CompetitionDijkstra("input/competition/input-A.txt", 55, 60, 75);
    System.out.println(competitionDijkstra.timeRequiredforCompetition());
  }

  @Test
  public void testFWConstructor() {
    // TODO
  }
}
