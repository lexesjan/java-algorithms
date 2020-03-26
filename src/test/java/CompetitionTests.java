import java.io.File;
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
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 26);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-A.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 2728);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-B.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 9091);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-C.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 3637);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-D.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 34);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-E.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 26);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-F.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 2555);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-G.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 7575);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-H.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), -1);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-I.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 219);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-J.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 1);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-K.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 291);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-L.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 146);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-M.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 273);
    competitionDijkstra = new CompetitionDijkstra("input/competition/input-N.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 146);
    competitionDijkstra = new CompetitionDijkstra("input/competition/tinyEWD.txt", 55, 60, 75);
    assertEquals(competitionDijkstra.timeRequiredforCompetition(), 34);
  }

  @Test
  public void testFWConstructor() {
    // TODO
  }

  public static void main(String[] args) {
    String dirString = "input/competition";
    File inputDir = new File(dirString);
    File[] inputs = inputDir.listFiles();
    for (File input : inputs) {
      System.out.print(input.toString() + " ");
      CompetitionDijkstra competitionDijkstra =
          new CompetitionDijkstra(input.toString(), 55, 60, 75);
      System.out.println(competitionDijkstra.timeRequiredforCompetition());
    }
  }

}
