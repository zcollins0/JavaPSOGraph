/**
 * 
 */
package psograph.test;

import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.Vector;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import psograph.graph.Edge;
import psograph.graph.Node;

/**
 * @author Patrick
 *
 */
public class NodeTest 
{

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
	 * Test method for {@link psograph.graph.Node#Node(int, double, double)}.
	 */
	@Test
	public void testNodeIntDoubleDouble() 
	{
		Node n = new Node(1, .5, .3);
		
		assertTrue(n.getID() == 1);
		
		assertTrue(Double.compare(.5, n.getX()) == 0);
		
		assertTrue(Double.compare(.3, n.getY()) == 0);
		
		assertTrue(n.getDegree() == 0);
		
		assertTrue(n.getDepth() == -1);
		
		assertTrue(n.getLPLength()==-1);
		
		assertTrue(n.getMeanEdgeCost() == 0);
		
		assertTrue(n.getNeighborDistribution() == null);
		
		assertTrue(n.getNeighbors() == null);
		
		assertTrue(n.getPaths().size() == 0);
		
		assertTrue(n.getSPLength() == -1);
		
		assertTrue(n.getTotalEdgeCost() == 0);
		
		assertTrue(n.getVisited()==false);
		
	}

	/**
	 * Test method for {@link psograph.graph.Node#Node(int, double, double, int[])}.
	 */
	@Test
	public void testNodeIntDoubleDoubleIntArray() throws Exception
	{
		int connectedTo[] = null;
		
		Node n = new Node(1, .5, .3, connectedTo);
		
		assertTrue(n.getID() == 1);
		
		assertTrue(Double.compare(.5, n.getX()) == 0);
		
		assertTrue(Double.compare(.3, n.getY()) == 0);
		
		assertTrue(n.getDegree() == 0);
		
		assertTrue(n.getDepth() == -1);
		
		assertTrue(n.getLPLength()==-1);
		
		assertTrue(n.getMeanEdgeCost() == 0);
		
		assertTrue(n.getNeighborDistribution() == null);
		
		assertTrue(n.getNeighbors() == null);
		
		assertTrue(n.getPaths().size() == 0);
		
		assertTrue(n.getSPLength() == -1);
		
		assertTrue(n.getTotalEdgeCost() == 0);
		
		assertTrue(n.getVisited()==false);
	}		

	/**
	 * Test method for {@link psograph.graph.Node#Node(int, double, double, int[])}.
	 */
	@Test
	public void testNodeIntDoubleDoubleIntArray2() throws Exception
	{
		int connectedTo[] = {1,2,3,4};
		try
		{
			@SuppressWarnings("unused")
			Node n = new Node(1, .5, .3, connectedTo);
			fail("Test shoudl have thrown exception");
		}
		catch(Exception e)
		{
			
			//Good as expected
		}

	}		
	
	/**
	 * Test method for {@link psograph.graph.Node#Node(int, double, double, int[])}.
	 */
	@Test
	public void testNodeIntDoubleDoubleIntArray3() throws Exception
	{
		int connectedTo[] = {2,3,4};
		
		Node n = new Node(1, .5, .3, connectedTo);
		
		assertTrue(n.getID() == 1);
		
		assertTrue(Double.compare(.5, n.getX()) == 0);
		
		assertTrue(Double.compare(.3, n.getY()) == 0);
		
		assertTrue(n.getDegree() == 3);
		
		assertTrue(n.getDepth() == -1);
		
		assertTrue(n.getLPLength()==-1);
		
		assertTrue(n.getMeanEdgeCost() == 1);
		
		TreeMap<Double,Vector<Integer>> neighborDist = n.getNeighborDistribution();
		
		assertTrue(neighborDist != null);
		
		assertTrue(neighborDist.keySet().size() == 1);
		
		Vector<Integer> nodeIds = neighborDist.get(1.0);
		
		assertTrue(nodeIds.contains(2)== true);
		assertTrue(nodeIds.contains(3)== true);
		assertTrue(nodeIds.contains(4)== true);
	
		TreeMap<Integer, Edge> neighbors = n.getNeighbors();
		
		assertTrue(neighbors.size() == 3);
		
		assertTrue(neighbors.get(2).getWeight() == 1);
		assertTrue(neighbors.get(3).getWeight() == 1);
		assertTrue(neighbors.get(4).getWeight() == 1);
		
		assertTrue(n.getPaths().size() == 0);
		
		assertTrue(n.getSPLength() == -1);
		
		assertTrue(n.getTotalEdgeCost() == 3);
		
		assertTrue(n.getVisited()==false);
	}		
	
	/**
	 * Test method for {@link psograph.graph.Node#Node(psograph.graph.Node)}.
	 */
	@Test
	public void testNodeNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#addConnection(int, double)}.
	 */
	@Test
	public void testAddConnection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#addPath(psograph.graph.Path)}.
	 */
	@Test
	public void testAddPath() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getEdgeInfo(int)}.
	 */
	@Test
	public void testGetConnectionInfoInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getConnectionInfo(psograph.graph.Node)}.
	 */
	@Test
	public void testGetConnectionInfoNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getDegree()}.
	 */
	@Test
	public void testGetDegree() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getDepth()}.
	 */
	@Test
	public void testGetDepth() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getID()}.
	 */
	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getPaths()}.
	 */
	@Test
	public void testGetPaths() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getLPLength()}.
	 */
	@Test
	public void testGetLPLength() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getMeanEdgeCost()}.
	 */
	@Test
	public void testGetMeanEdgeCost() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getNeighborDistribution()}.
	 * @throws Exception 
	 */
	@Test
	public void testGetNeighborDistribution() throws Exception 
	{
		int connectedTo[] = {2,3,4};
		
		Node n = new Node(1, .5, .3, connectedTo);
		
		TreeMap<Double,Vector<Integer>> neighborDist = n.getNeighborDistribution();
		
		assertTrue(neighborDist != null);
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getNeighbors()}.
	 */
	@Test
	public void testGetNeighbors() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getSPLength()}.
	 */
	@Test
	public void testGetSPLength() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getTotalEdgeCost()}.
	 */
	@Test
	public void testGetTotalEdgeCost() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getVisited()}.
	 */
	@Test
	public void testGetVisited() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getX()}.
	 */
	@Test
	public void testGetX() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#getY()}.
	 */
	@Test
	public void testGetY() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#hasSPLTo(int)}.
	 */
	@Test
	public void testHasSPLTo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#isConnectedTo(int)}.
	 */
	@Test
	public void testIsConnectedToInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#isConnectedTo(psograph.graph.Node)}.
	 */
	@Test
	public void testIsConnectedToNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#numberOfSPLNodeParticipatesIn(int, psograph.graph.Node)}.
	 */
	@Test
	public void testNumberOfSPLNodeParticipatesIn() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#numOfSPLength()}.
	 */
	@Test
	public void testNumOfSPLength() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#removeConnection(int)}.
	 */
	@Test
	public void testRemoveConnection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#setDepth(int)}.
	 */
	@Test
	public void testSetDepth() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#setPath(java.util.Vector)}.
	 */
	@Test
	public void testSetPath() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link psograph.graph.Node#setVisited(boolean)}.
	 */
	@Test
	public void testSetVisited() {
		fail("Not yet implemented");
	}

}
