import org.hamcrest.collection.IsMapContaining;
import org.hua.datastructures.graph.AdjacencyListGraph;
import org.hua.datastructures.graph.CSRGraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraphTests {

    @Test
    @DisplayName("Test if an adjacency list graph is created correctly")
    public void testAdjacencyListGraph() throws IOException {

        var path = getClass().getResource("doublebfs.txt").getFile();
        var graph = new AdjacencyListGraph(path);

        var normalGraph = graph.getNormalGraph();
        //Be careful as changing the order in lists breaks the test
        //If you wonder why, remember how the hashcode of a list is created
        assertEquals(normalGraph.size(), 12);

        assertThat(normalGraph, IsMapContaining.hasEntry(1L, List.of(2L,3L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(2L, List.of(4L,5L,6L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(4L, List.of(7L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(5L, List.of(7L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(6L, List.of(9L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(7L, List.of(8L,1L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(9L, List.of(11L,10L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(10L, List.of(12L,13L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(12L, List.of(14L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(11L, List.of(27L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(13L, List.of(27L)));
        assertThat(normalGraph, IsMapContaining.hasEntry(26L, List.of(10L)));

        var invertedGraph = graph.getInvertedGraph();
        assertEquals(invertedGraph.size(), 15);

        assertThat(invertedGraph, IsMapContaining.hasEntry(1L, List.of(7L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(2L, List.of(1L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(3L, List.of(1L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(4L, List.of(2L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(5L, List.of(2L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(6L, List.of(2L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(7L, List.of(4L, 5L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(8L, List.of(7L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(9L, List.of(6L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(10L, List.of(9L, 26L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(11L, List.of(9L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(12L, List.of(10L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(13L, List.of(10L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(14L, List.of(12L)));
        assertThat(invertedGraph, IsMapContaining.hasEntry(27L, List.of(11L, 13L)));

    }

    @Test
    @DisplayName("Test if an CSR graph is created correctly")
    void testCSRGraph() {

        var graph = Map.ofEntries(
                Map.entry(1L, List.of(2L, 3L)),
                Map.entry(2L, List.of(4L, 5L, 6L)),
                Map.entry(4L, List.of(7L)),
                Map.entry(6L, List.of(9L)),
                Map.entry(7L, List.of(8L, 1L)),
                Map.entry(9L, List.of(11L, 10L)),
                Map.entry(10L, List.of(12L, 13L)),
                Map.entry(12L, List.of(14L)),
                Map.entry(11L, List.of(27L)),
                Map.entry(13L, List.of(27L)),
                Map.entry(26L, List.of(10L))
        );

        var csrGraph = new CSRGraph(graph);
        //Be careful as changing the order in lists breaks the test
        //If you wonder why, remember how the hashcode of a list is created
        assertFalse(csrGraph.vertexExists(15L));
        assertEquals(csrGraph.getNeighbors(15L), List.of()); //Test non-existing vertex

        assertTrue(csrGraph.vertexExists(1L));
        assertEquals(csrGraph.getNeighbors(1L), List.of(2L, 3L));

        assertTrue(csrGraph.vertexExists(2L));
        assertEquals(csrGraph.getNeighbors(2L), List.of(4L, 5L, 6L));

        assertTrue(csrGraph.vertexExists(4L));
        assertEquals(csrGraph.getNeighbors(4L), List.of(7L));

        assertTrue(csrGraph.vertexExists(6L));
        assertEquals(csrGraph.getNeighbors(6L), List.of(9L));

        assertTrue(csrGraph.vertexExists(7L));
        assertEquals(csrGraph.getNeighbors(7L), List.of(8L, 1L));

        assertTrue(csrGraph.vertexExists(9L));
        assertEquals(csrGraph.getNeighbors(9L), List.of(11L, 10L));

        assertTrue(csrGraph.vertexExists(10L));
        assertEquals(csrGraph.getNeighbors(10L), List.of(12L, 13L));

        assertTrue(csrGraph.vertexExists(12L));
        assertEquals(csrGraph.getNeighbors(12L), List.of(14L));

        assertTrue(csrGraph.vertexExists(11L));
        assertEquals(csrGraph.getNeighbors(11L), List.of(27L));

        assertTrue(csrGraph.vertexExists(13L));
        assertEquals(csrGraph.getNeighbors(13L), List.of(27L));

        assertTrue(csrGraph.vertexExists(26L));
        assertEquals(csrGraph.getNeighbors(26L), List.of(10L));
    }

}