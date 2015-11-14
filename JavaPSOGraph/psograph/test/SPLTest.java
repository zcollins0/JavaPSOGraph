/**
 * 
 */
package psograph.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import psograph.graph.CalculatedGraph;
import psograph.graph.Graph;
import psograph.graph.Node;
import psograph.graph.Path;
import psograph.measurements.SPL;

/**
 * @author Patrick
 *
 */
public class SPLTest 
{
	Graph m_graph;
	CalculatedGraph m_calcGraph;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link psograph.measurements.SPL#SPL(psograph.graph.Graph)}.
	 */
	@Test
	public void testSPL() throws Exception
	{
		m_graph = TestUtils.createTestGraph17();
		m_calcGraph = new CalculatedGraph(m_graph);
		m_calcGraph.setCostBasis(5);
		m_calcGraph.UpdateCalcuations();
		
		SPL spl = new SPL(m_calcGraph);
		Graph graphs[] = spl.measure();
		
		m_calcGraph.print();
		
		spl.print();

		//Node 0
		Graph node0Graph = graphs[0];
		Node node0 = node0Graph.getNode(0);
		Vector<Path> vPaths = node0.getPaths();
		assertTrue(vPaths.size()==0);
/*		
		Number of paths from 0 to 0 is 1
		start of path 0 : Path Length 0
		Number of paths from 0 to 1 is 1
		start of path 0  1: Path Length 1
		Number of paths from 0 to 2 is 1
		start of path 0  2: Path Length 1
		Number of paths from 0 to 3 is 1
		start of path 0  3: Path Length 1
		Number of paths from 0 to 4 is 2
		start of path 0  1 7 4: Path Length 3
		start of path 0  2 7 4: Path Length 3
		Number of paths from 0 to 5 is 1
		start of path 0  3 5: Path Length 2
		Number of paths from 0 to 6 is 3
		start of path 0  1 7 6: Path Length 3
		start of path 0  2 7 6: Path Length 3
		start of path 0  3 5 6: Path Length 3
		Number of paths from 0 to 7 is 2
		start of path 0  1 7: Path Length 2
		start of path 0  2 7: Path Length 2
		*/
		/*
		for(int iGraph = 0; iGraph < m_solns.length; iGraph++)
		{
		    System.out.println("");
			for(int i =0; i < m_solns[iGraph].getNumberOfNodes() ; i++)
			{
				Node n = m_solns[iGraph].getNode(i);
				Vector<Path> vPaths = n.getPaths();
				System.out.println("Number of paths from "+ iGraph +" to "+n.getID()+" is " + vPaths.size());
				for(int j = 0; j < vPaths.size(); j++)
				{
					Path p = vPaths.get(j);
		*/
		
	}


	/**
	 * Test method for {@link psograph.measurements.SPL#determineSPLs()}.
	 */
	@Test
	public void testDetermineSPLs() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.measurements.SPL#Measure()}.
	 */
	@Test
	public void testMeasure() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.measurements.SPL#evaulateNodeWithBFS(int, psograph.graph.Graph)}.
	 */
	@Test
	public void testEvaulateNodeWithBFS() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.measurements.SPL#isCalculated()}.
	 */
	@Test
	public void testIsCalculated() {
		fail("Not yet implemented");
	}

}
