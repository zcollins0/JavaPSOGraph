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
		Graph graphs[] = spl.Measure();
		
		m_calcGraph.print();
		
		for(int iGraph = 0; iGraph < graphs.length; iGraph++)
		{
			
		    System.out.println("");
			for(int i =0; i < graphs[iGraph].getNumberOfNodes() ; i++)
			{
				Node n = graphs[iGraph].getNode(i);
				Vector<Path> vPaths = n.getPaths();
				

				//System.out.println("Number of paths from "+ iGraph +" to "+n.getID()+" is " + vPaths.size());

				for(int j = 0; j < vPaths.size(); j++)
				{
					Path p = vPaths.get(j);

					System.out.print("start of path "+ p.getStart().getID()+" ");
					for(int k =0; k < p.getLength(); k++)
					{
						Node n2 = p.getPath().get(k);
						int id = n2.getID();
						System.out.print(" " + id);
					}
					System.out.println(": Path Length " + p.getLength());					
				}
			}
		}
		
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
