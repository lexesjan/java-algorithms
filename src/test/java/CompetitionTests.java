import org.junit.Test;
import static org.junit.Assert.*;

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
        new CompetitionDijkstra("input/competition/1000EWD.txt", 55, 60, 75);
    assertEquals(26, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-A.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-B.txt", 55, 60, 75);
    assertEquals(9091, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-C.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-D.txt", 55, 60, 75);
    assertEquals(34, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-E.txt", 55, 60, 75);
    assertEquals(26, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-F.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-G.txt", 55, 60, 75);
    assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    //    competitionDijkstra = new CompetitionDijkstra("input/competition/input-H.txt", 55, 60,
    // 75);
    //    assertEquals( -1, competitionDijkstra.timeRequiredforCompetition())
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
        new CompetitionFloydWarshall("input/competition/1000EWD.txt", 55, 60, 75);
    assertEquals(26, competitionFloydWarshall.timeRequiredforCompetition());
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
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-E.txt", 55, 60, 75);
    assertEquals(26, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-F.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    competitionFloydWarshall =
        new CompetitionFloydWarshall("input/competition/input-G.txt", 55, 60, 75);
    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    //    competitionFloydWarshall =
    //        new CompetitionFloydWarshall("input/competition/input-H.txt", 55, 60, 75);
    //    assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
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
