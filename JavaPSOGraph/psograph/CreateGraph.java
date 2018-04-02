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
import java.util.Random;
import java.util.Vector;
import java.lang.Math;

import psograph.graph.*;
import psograph.measurements.PercentageInLargestCluster;
import psograph.util.Util;


public class CreateGraph
{

	//This is the directory where the Seed is generated.  The Seed is the Node configuration
	//with no edges
	File m_SeedDirectory;

	File m_GraphDirectory;

	Graph m_graphSeed;
	Graph canditate;

	double m_basisCost;
	NodeLocationCalculator m_nodeLoc;

	int seed = 0;
	int candidateCounter = 0;

	public CreateGraph()
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

		m_nodeLoc = new NodeLocationCalculator(m_graphSeed, false);
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
		canditate = new Graph(m_graphSeed);

		Vector<Node> v_Nodes = new Vector<>(m_graphSeed.getHeaderNodesMap().values());
		int jj;

		int num_of_nodes = v_Nodes.size();
		//Add in NumNodes edges that connect to close Nodes
		Node middlest = v_Nodes.get(0);
		int mididx = 0;

		double totalX = 0;
		double totalY = 0;

		for (jj = 0; jj < num_of_nodes; jj++) {
			Node n = v_Nodes.get(jj);
			totalX += n.getX();
			totalY += n.getY();
		}

		double avgX = totalX/num_of_nodes;
		double avgY = totalY/num_of_nodes;

		System.out.printf("Average (x, y) = (%f, %f))\n", avgX, avgY);

		for (jj = 0; jj < num_of_nodes; jj++) {
			Node n = v_Nodes.get(jj);
			if (Math.abs(n.getX() - avgX) + Math.abs(n.getY() - avgY) < Math.abs(middlest.getX() - avgX) + Math.abs(middlest.getY() - avgY)) {
				middlest = n;
				mididx = jj;
			}
		}

		for (int i = 0; i < 4; i++) {

		}

		for (jj = 0; jj < num_of_nodes; jj++) {
			if (jj != mididx)
				canditate.addConnection(middlest.getID(), v_Nodes.get(jj).getID());
		}

		System.out.println("Total edges after Second connect "+canditate.getTotalEdges());
	}

	private void quadrantizeAndConnect(Graph candidate, Vector<Node> nodes) {

	}

	public void doWork() throws Exception
	{
		try
		{
			for( seed=0; seed < 1; seed++)
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

		CalculatedGraph calculatedCanditate = new CalculatedGraph(canditate);
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
	 * @param args
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