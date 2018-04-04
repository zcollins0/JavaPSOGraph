// This is a library to be used to represent a Graph and various measurments for a Graph
//  and to perform optimization using Particle Swarm Optimization (PSO)
//    Copyright (C) 2008, 2015 
//       Patrick Olekas - polekas55@gmail.com
//       Ali Minai - minaiaa@gmail.com
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//   You should have received a copy of the GNU General Public License
//   along with this program.  If not, see <http://www.gnu.org/licenses/>.
package psograph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import psograph.graph.*;
import psograph.measurements.PercentageInLargestCluster;
import psograph.util.Util;


public class CreateGraph
{

	//This is the directory where the Seed is generated.  The Seed is the Node configuration
	//with no edges
	private File m_SeedDirectory;

	private File m_GraphDirectory;

	private Graph m_graphSeed;
	private Graph m_candidate;

	private double m_basisCost;

	int seed = 0;
	private int candidateCounter = 0;

	private Node m_centerNode;

	private CreateGraph()
	{

	}

	//This generates the Node Configuration for you to connect
	private void generateSeed() throws Exception
	{
		//Generate a new graph for as the Seed
		m_graphSeed = new Graph(GraphConstants.NUM_NODES);

		//graphSeed.printWithLocationAndWeights();
		System.out.println("--------------------------------------");
		System.out.println("Saving Graph Seed "+seed);
		// stream out seed
		m_SeedDirectory = Util.CreateSeedDirectory();
		Util.streamOutSeed(m_SeedDirectory, m_graphSeed);
		//Util.printMFile(m_graphSeed);

		m_GraphDirectory = Util.CreateCalculatedGraphDirectory(m_SeedDirectory);

		System.out.println("--------------------------------------");

		NodeLocationCalculator m_nodeLoc = new NodeLocationCalculator(m_graphSeed, false);
		m_nodeLoc.calculateResults();
	}

	private void calculateCostBasis() throws Exception
	{
		m_basisCost =0;
		CostBasis costBasis = new CostBasis(m_graphSeed);

		//System.out.println("-----------exponentialCostBasis-----------------");
		costBasis.generate(GraphConstants.MAX_CONNECTIONS);
		m_basisCost = costBasis.getCost();
		System.out.println("Total edges "+costBasis.getTotalEdges());
		Util.streamOutExponentialGraph(m_SeedDirectory, costBasis,1);

		PercentageInLargestCluster expoLCC = new PercentageInLargestCluster(costBasis);
		double valueCostBasisLCC = expoLCC.Measure();
		if(Double.compare(valueCostBasisLCC,1.0) != 0)
			System.out.println("valueExpoLCC is not equal to 1.0 : "+valueCostBasisLCC + "differ of :" + (1.0 - valueCostBasisLCC));

		CalculatedGraph calcCostBasis = new CalculatedGraph(costBasis);
		calcCostBasis.setCostBasis(m_basisCost);
		calcCostBasis.UpdateCalcuations();
		System.out.println("------------------Begin Measurements-----------");
		System.out.println("Avg Robustness Measure for Random - Percentage in LCC "+calcCostBasis.getRandomLCC());
		System.out.println("Avg Robustness Measure for Random - Diameter in LCC "+calcCostBasis.getRandomDiameter());
		System.out.println("Avg Robustness Measure for Directed - Percentage in LCC "+calcCostBasis.getDirectLCC());
		System.out.println("Avg Robustness Measure for Directed - Diameter in LCC "+calcCostBasis.getDirectDiameter());
		System.out.println("Connectivity Measure - AISPL "+ calcCostBasis.getAISPL());
		System.out.println("Cost Measure - summation weight costs "+ calcCostBasis.getCost());
		System.out.println("Cost Basis -                      "+ calcCostBasis.getCostBasis());
		double t = calcCostBasis.getCost() / calcCostBasis.getCostBasis();
		System.out.println("Cost Basis ratio - "+t );
		System.out.println("Fitness Value -  "+calcCostBasis.getFitnessValue());
		System.out.println("Diameter Value -  "+calcCostBasis.getDiameter());
		System.out.println("ClusteringCoefficient - "+calcCostBasis.getClusteringCoefficient());
		System.out.println("Per LCC -  "+calcCostBasis.getLCC());
		System.out.println("------------------End Measurements-------------");

		Util.streamOutGraphAsMFile(new FileWriter(m_GraphDirectory+"\\CostBasis"+1+".m") ,costBasis, 1);
	}

	private void connectCandidate() throws Exception
	{
		m_candidate = new Graph(m_graphSeed);

		List<Node> nodes = new ArrayList<>(m_graphSeed.getHeaderNodesMap().values());

		m_centerNode = getCenterNode(nodes);
		quarterAndConnect(m_candidate, nodes, 2);

		System.out.println("Total edges after Second connect " + m_candidate.getTotalEdges());
	}

	private Node getCenterNode(List<Node> nodes) {
		Node centerNode = nodes.get(0);

		double totalX = 0;
		double totalY = 0;

		for (Node n : nodes) {
			totalX += n.getX();
			totalY += n.getY();
		}

		double avgX = totalX/nodes.size();
		double avgY = totalY/nodes.size();

		for (Node n : nodes) {
			if (Math.abs(n.getX() - avgX) + Math.abs(n.getY() - avgY) < Math.abs(centerNode.getX() - avgX) + Math.abs(centerNode.getY() - avgY)) {
				centerNode = n;
			}
		}

		return centerNode;
	}

	private Node connectToCenterNode(Graph canditate, List<Node> nodes) throws Exception {
		Node centerNode = getCenterNode(nodes);

		for (Node n : nodes) {
			if (n != centerNode) canditate.addConnection(centerNode.getID(), n.getID());
		}

		return centerNode;
	}

	private Node quarterAndConnect(Graph candidate, List<Node> nodes, int quarteringLevel) throws Exception {
		if (quarteringLevel == 0) return null;
		Node centerNode = getCenterNode(nodes);

		List<Node> upperLeft = new ArrayList<>();
		List<Node> upperRight = new ArrayList<>();
		List<Node> lowerLeft = new ArrayList<>();
		List<Node> lowerRight = new ArrayList<>();

		for (Node n: nodes) {
			if (n == centerNode) continue;

			if (n.getY() > centerNode.getY() && n.getX() > centerNode.getX()) {
				upperRight.add(n);
			} else if (n.getY() > centerNode.getY() && n.getX() <= centerNode.getX()) {
				upperLeft.add(n);
			} else if (n.getY() <= centerNode.getY() && n.getX() > centerNode.getX()) {
				lowerRight.add(n);
			} else {
				lowerLeft.add(n);
			}
		}

		if (quarteringLevel > 1) {
			candidate.addConnection(quarterAndConnect(candidate, upperLeft, quarteringLevel - 1).getID(), centerNode.getID());
			candidate.addConnection(quarterAndConnect(candidate, upperRight, quarteringLevel - 1).getID(), centerNode.getID());
			candidate.addConnection(quarterAndConnect(candidate, lowerLeft, quarteringLevel - 1).getID(), centerNode.getID());
			candidate.addConnection(quarterAndConnect(candidate, lowerRight, quarteringLevel - 1).getID(), centerNode.getID());
		} else {
			for (int i = 0; i < 4; i++) {
				List<Node> nodesl;
				if (i == 0) nodesl = upperLeft;
				else if (i == 1) nodesl = upperRight;
				else if (i == 2) nodesl = lowerLeft;
				else nodesl = lowerRight;
				Node n = connectToCenterNode(candidate, nodesl);
				// n: center node of a quadrant
				// centerNode: node in between the four quadrants
				// m_centerNode: overall center node
				// our two options are: connect n to centerNode OR connect n to m_centerNode
				// we want to choose the one that adds the least access cost to the nodes we just connected
				double twoStepLength = Math.hypot(n.getX()-centerNode.getX(), n.getY()-centerNode.getY()) +
						Math.hypot(centerNode.getX()-m_centerNode.getX(), centerNode.getY()-m_centerNode.getY());
				double singleStepLength = Math.hypot(n.getX()-m_centerNode.getX(), n.getY()-m_centerNode.getY());
				// ratio of two-step distance to one-step distance
				// if two-step distance is much larger, connect to use one step instead
				double ratio = twoStepLength/singleStepLength;
				if (ratio > 1.3) {
					candidate.addConnection(n.getID(), m_centerNode.getID());
				} else {
					candidate.addConnection(n.getID(), centerNode.getID());
				}
			}
		}

		return centerNode;
	}

	private void doWork() throws Exception
	{
		try
		{
			for(seed=0; seed < 1; seed++)
			{
				//The NodeLocation
				generateSeed();

				//Now to make some graphs to be used for normalizing the cost
				calculateCostBasis();

				double fitness = 0;

				for(candidateCounter=0; candidateCounter < 1; candidateCounter++)
				{
					connectCandidate();
					fitness = measureAndOutputCandidate();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	private double measureAndOutputCandidate() throws Exception
	{
		CalculatedGraph calculatedCanditate = new CalculatedGraph(m_candidate);
		calculatedCanditate.setCostBasis(m_basisCost);
		calculatedCanditate.UpdateCalcuations();
		calculatedCanditate.UpdatePSOCalculations();

		System.out.println("------------------Begin Measurements-----------");
		System.out.println("Avg Robustness Measure for Random - Percentage in LCC "+calculatedCanditate.getRandomLCC());
		System.out.println("Avg Robustness Measure for Random - Diameter in LCC "+calculatedCanditate.getRandomDiameter());
		System.out.println("Avg Robustness Measure for Directed - Percentage in LCC "+calculatedCanditate.getDirectLCC());
		System.out.println("Avg Robustness Measure for Directed - Diameter in LCC "+calculatedCanditate.getDirectDiameter());
		System.out.println("Connectivity Measure - AISPL "+ calculatedCanditate.getAISPL());
		System.out.println("Cost Measure - summation weight costs "+ calculatedCanditate.getCost());
		System.out.println("Cost Basis -                      "+ calculatedCanditate.getCostBasis());
		double t = calculatedCanditate.getCost() / calculatedCanditate.getCostBasis();
		System.out.println("Cost Basis ratio - "+t );
		System.out.println("Fitness Value -  "+calculatedCanditate.getFitnessValue());
		System.out.println("Diameter Value -  "+calculatedCanditate.getDiameter());
		System.out.println("ClusteringCoefficient - "+calculatedCanditate.getClusteringCoefficient());
		System.out.println("Per LCC -  "+calculatedCanditate.getLCC());
		//printMFile(canditate);
		System.out.println("------------------End Measurements-------------");
		//calculatedCanditate.printWithLocationAndWeights();

		Util.streamOutCalculatedGraph(m_GraphDirectory, candidateCounter, calculatedCanditate);
		Util.streamOutGraphAsMFile(new FileWriter(m_GraphDirectory+"\\Graph"+1+".m") ,calculatedCanditate, 1);

		return calculatedCanditate.getFitnessValue();
	}

	/**
	 * @param args the command line input to the program
	 */
	public static void main(String[] args) throws Exception
	{
		try
		{
			CreateGraph createGraph = new CreateGraph();
			createGraph.doWork();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

}