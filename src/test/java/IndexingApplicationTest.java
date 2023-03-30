import org.gorbatko.indexing.Port;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class IndexingApplicationTest {
    private final String[] indexes = {"1,3-5", "2", "3-4"};
    private final Port port = new Port(indexes);

    @Test
    public void testConvertIndexes() {
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 3, 4, 5),
                Arrays.asList(2),
                Arrays.asList(3, 4)
        );
        List<List<Integer>> actual = port.convertIndexes();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGenerateCombinations() {
        List<List<Integer>> sequences = Arrays.asList(
                Arrays.asList(1, 3, 4, 5),
                Arrays.asList(2),
                Arrays.asList(3, 4)
        );
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 4),
                Arrays.asList(3, 2, 3),
                Arrays.asList(3, 2, 4),
                Arrays.asList(4, 2, 3),
                Arrays.asList(4, 2, 4),
                Arrays.asList(5, 2, 3),
                Arrays.asList(5, 2, 4)
        );
        List<List<Integer>> actual = port.generateCombinations(sequences);
        Assertions.assertEquals(expected, actual);
    }
}
