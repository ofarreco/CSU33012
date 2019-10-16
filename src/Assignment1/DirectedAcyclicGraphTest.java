package Assignment1;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class DirectedAcyclicGraphTest {

	@Test
	public void testDirectedAcyclicGraph()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(4, 6);
		graph.addEdge(4, 7);
		
		assertEquals(1, graph.indeg(4));
		assertEquals(2, graph.outdeg(4));
		assertEquals(5, graph.edge());
		assertEquals(10, graph.X());
		String adjacent = "[6, 7]";
		assertEquals(adjacent, graph.adjacent(4).toString());
	}
}
