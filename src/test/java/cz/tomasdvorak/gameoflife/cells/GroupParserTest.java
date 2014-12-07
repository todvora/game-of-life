package cz.tomasdvorak.gameoflife.cells;

import junit.framework.TestCase;
import org.junit.Assert;

public class GroupParserTest extends TestCase {

    public void testParseCorner() throws Exception {
        final Cell[][] map = new Cell[][]{
                new Cell[]{CellType.ALIVE, CellType.ALIVE, CellType.ALIVE, CellType.ALIVE},
                new Cell[]{CellType.DEAD, CellType.DEAD, CellType.DEAD, CellType.DEAD},
                new Cell[]{CellType.DEAD, CellType.DEAD, CellType.DEAD, CellType.DEAD},
                new Cell[]{CellType.DEAD, CellType.DEAD, CellType.DEAD, CellType.DEAD}
        };

        final CellGroup actual = GroupParser.parse(new MatrixMap(map), 0, 0);
        Assert.assertEquals(CellType.ALIVE, actual.getCenter());
        Assert.assertEquals(1, actual.getSum());
    }

    public void testParseCenter() throws Exception {
        final Cell[][] map = new Cell[][]{
                new Cell[]{CellType.ALIVE, CellType.ALIVE, CellType.ALIVE, CellType.ALIVE},
                new Cell[]{CellType.DEAD,  CellType.DEAD,  CellType.DEAD,  CellType.DEAD},
                new Cell[]{CellType.DEAD,  CellType.ALIVE,  CellType.DEAD,  CellType.DEAD},
                new Cell[]{CellType.DEAD,  CellType.DEAD,  CellType.DEAD,  CellType.DEAD}
        };

        final CellGroup actual = GroupParser.parse(new MatrixMap(map),1,1);
        Assert.assertEquals(CellType.DEAD, actual.getCenter());
        Assert.assertEquals(4, actual.getSum());
    }
}