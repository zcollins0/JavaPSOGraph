package psograph.test;


import org.junit.Before;
import org.junit.Test;

import psograph.graph.*;

import static org.junit.Assert.*;

public class FitnessValues 
{
	
	Graph m_graph;
	
	@Before
	public void setUp() throws Exception 
	{
		m_graph = TestUtils.createTestGraph2();
	}
	
	@Test
	public void testOldFitnessValueMethod() throws Exception
	{
		
		CalculatedGraph calcGraph = new CalculatedGraph(m_graph);
		calcGraph.setCostBasis(5);
		calcGraph.UpdateCalcuations();
			
		//System.out.println("----------------Begin Measurements-------------");
		@SuppressWarnings("unused")
		
		double Rpercentage = calcGraph.getRandomLCC();
		//System.out.println("Avg Robustness Measure for Random - Percentage in LCC "+Rpercentage);
		
		@SuppressWarnings("unused")
		double Rdiameter = calcGraph.getRandomDiameter();
		//System.out.println("Avg Robustness Measure for Random - diameter "+Rdiameter);
		
		
		@SuppressWarnings("unused")
		double Dpercentage = calcGraph.getDirectLCC();
		//System.out.println("Avg Robustness Measure for Random - Percentage in LCC "+Dpercentage);
		
		@SuppressWarnings("unused")
		double Ddiameter = calcGraph.getDirectDiameter();
	//	System.out.println("Avg Robustness Measure for Random - diameter "+Ddiameter);
		
		double aispl=0;
		aispl = calcGraph.getAISPL();
		
		//System.out.println("Connectivity Measure - AISPL "+ aispl);


		assertTrue("APISL  is " + aispl,  
				Double.compare(aispl, 0.3371212121212121) ==0);

		
		double cost = m_graph.SumAllWeights()/2;
		
		//m_graph.printWithLocationAndWeights();
		
		//System.out.println("Cost Measure - summation weight costs "+ cost);	
		assertTrue("cost should be 52, but is " + cost, cost == 52);
		//System.out.println("------------------End Measurements-------------");
		
		//System.out.println("Fitness Value - Ran "+copy.calcRFitnessValue());
		//System.out.println("Fitness Value - Dir "+copy.calDRFitnessValue());
		
		
		//copy.printWithWeights();
	}
	
	
}
