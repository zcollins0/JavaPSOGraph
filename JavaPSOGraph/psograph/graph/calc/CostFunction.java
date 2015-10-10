// This is a library to be used to represent a Graph and various measurments for a Graph
//  and to perform optimization using Particle Swarm Optimization (PSO)
//    Copyright (C) 2008, 2015 Patrick Olekas
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
package psograph.graph.calc;

import java.io.Serializable;

/**
 * Base Class of Cost Function. This to determined the cost of to connect two edges.
 * @author Patrick
 *
 */
public class CostFunction implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4911946440142911675L;

	public CostFunction() {}
	
	public double calculate (double t) throws Exception
	{
		throw new Exception ("Using BaseClass of CostFunction");
		
	}
	
	
}
