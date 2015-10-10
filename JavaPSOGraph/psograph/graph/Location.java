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
package psograph.graph;

import java.io.Serializable;
/**
 * Represents a Location of Node.  This is here to make it easier to 
 * check for coincident Nodes.
 * @author Patrick
 *
 */
public class Location implements Serializable
{
	
	static final long serialVersionUID = 245L;
	private double m_x;
	
	private double m_y;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	Location(double x, double y)
	{
		m_x = x;
		m_y = y;
	}
	
	/**
	 * Tells if two locations are equal
	 */
	public boolean equals (Object obj)
	{
		boolean ret = false;
	

		Location l = (Location)obj;
		if((Double.compare(l.getX(), m_x) == 0) && (Double.compare( l.getY(), m_y) == 0))
			ret = true;
			
		return ret;
	}
	
	/** Returns X coordinate
	 * 
	 * @return
	 */
	public double getX() {return m_x;}
	/** Returns Y coordinate
	 * 
	 * @return
	 */
	public double getY() {return m_y;}
	
}
