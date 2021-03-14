import org.hua.datastructures.bfs.BidirectionalBFS;
import org.hua.datastructures.graph.CSRGraph;
import org.hua.datastructures.graph.VertexPair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BFSTests {

    @Test
    @DisplayName("Test if BFS works")
    void testBidirectionalBFS(){

        CSRGraph normalGraph = mock(CSRGraph.class);

        when(normalGraph.vertexExists(anyLong())).thenReturn(false);
        when(normalGraph.vertexExists(argThat(List.of(1L,2L,4L,6L,7L,9L,10L,12L,11L,13L,26L)::contains))).thenReturn(true);

        when(normalGraph.getNeighbors(anyLong())).thenReturn(new LinkedList<>());
        when(normalGraph.getNeighbors(1L)).thenReturn(new LinkedList<>(Arrays.asList(2L,3L)));
        when(normalGraph.getNeighbors(2L)).thenReturn(new LinkedList<>(Arrays.asList(4L,5L,6L)));
        when(normalGraph.getNeighbors(4L)).thenReturn(new LinkedList<>(Arrays.asList(7L)));
        when(normalGraph.getNeighbors(6L)).thenReturn(new LinkedList<>(Arrays.asList(9L)));
        when(normalGraph.getNeighbors(7L)).thenReturn(new LinkedList<>(Arrays.asList(8L,1L)));
        when(normalGraph.getNeighbors(9L)).thenReturn(new LinkedList<>(Arrays.asList(11L,10L)));
        when(normalGraph.getNeighbors(10L)).thenReturn(new LinkedList<>(Arrays.asList(12L,13L)));
        when(normalGraph.getNeighbors(12L)).thenReturn(new LinkedList<>(Arrays.asList(14L)));
        when(normalGraph.getNeighbors(11L)).thenReturn(new LinkedList<>(Arrays.asList(27L)));
        when(normalGraph.getNeighbors(13L)).thenReturn(new LinkedList<>(Arrays.asList(27L)));
        when(normalGraph.getNeighbors(26L)).thenReturn(new LinkedList<>(Arrays.asList(10L)));


        CSRGraph invertedGraph = mock(CSRGraph.class);

        when(invertedGraph.vertexExists(anyLong())).thenReturn(false);
        when(invertedGraph.vertexExists(argThat(List.of(2L,3L,4L,5L,6L,7L,8L,9L,10L,11L,12L,13L,27L)::contains))).thenReturn(true);

        when(invertedGraph.getNeighbors(anyLong())).thenReturn(new LinkedList<>());
        when(invertedGraph.getNeighbors(2L)).thenReturn(new LinkedList<>(Arrays.asList(1L)));
        when(invertedGraph.getNeighbors(3L)).thenReturn(new LinkedList<>(Arrays.asList(1L)));
        when(invertedGraph.getNeighbors(4L)).thenReturn(new LinkedList<>(Arrays.asList(2L)));
        when(invertedGraph.getNeighbors(5L)).thenReturn(new LinkedList<>(Arrays.asList(2L)));
        when(invertedGraph.getNeighbors(6L)).thenReturn(new LinkedList<>(Arrays.asList(2L)));
        when(invertedGraph.getNeighbors(7L)).thenReturn(new LinkedList<>(Arrays.asList(4L,5L)));
        when(invertedGraph.getNeighbors(8L)).thenReturn(new LinkedList<>(Arrays.asList(7L)));
        when(invertedGraph.getNeighbors(9L)).thenReturn(new LinkedList<>(Arrays.asList(6L)));
        when(invertedGraph.getNeighbors(10L)).thenReturn(new LinkedList<>(Arrays.asList(9L,26L)));
        when(invertedGraph.getNeighbors(11L)).thenReturn(new LinkedList<>(Arrays.asList(9L)));
        when(invertedGraph.getNeighbors(12L)).thenReturn(new LinkedList<>(Arrays.asList(10L)));
        when(invertedGraph.getNeighbors(13L)).thenReturn(new LinkedList<>(Arrays.asList(10L)));
        when(invertedGraph.getNeighbors(27L)).thenReturn(new LinkedList<>(Arrays.asList(11L,13L)));


        BidirectionalBFS bfs = new BidirectionalBFS(normalGraph,invertedGraph);

        var result1 = bfs.query(new VertexPair(1,27));

        // 1 -> 27 are connected with a distance of 5
        assertAll(
                () -> assertTrue(result1.sourceExists()),
                () -> assertTrue(result1.targetExists()),
                () -> assertEquals(1, result1.getSourceId()),
                () -> assertEquals(27,result1.getTargetId()),
                () -> assertTrue(result1.areConnected()),
                () -> assertEquals(5, result1.getDistance())
        );

        var result2 = bfs.query(new VertexPair(1,9));

        // 1 -> 9 are not connected but both exist
        assertAll(
                () -> assertTrue(result2.sourceExists()),
                () -> assertTrue(result2.targetExists()),
                () -> assertEquals(1, result2.getSourceId()),
                () -> assertEquals(9,result2.getTargetId()),
                () -> assertFalse(result2.areConnected()),
                () -> assertNull(result2.getDistance())
        );

        var result3 = bfs.query(new VertexPair(100,10));

        // 100 -> 10 100 doesn't exist as a node but 10 does
        assertAll(
                () -> assertFalse(result3.sourceExists()),
                () -> assertTrue(result3.targetExists()),
                () -> assertEquals(100, result3.getSourceId()),
                () -> assertEquals(10,result3.getTargetId()),
                () -> assertFalse(result3.areConnected()),
                () -> assertNull(result3.getDistance())
        );

    }

}
