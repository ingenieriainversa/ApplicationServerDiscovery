/*
 * Application Server Discovery v0.03
 * Cell.java
 * Copyleft - 2016  Javier Dominguez Gomez
 * Written by Javier Dominguez Gomez <jdg@member.fsf.org>
 * GnuPG Key: 6ECD1616
 * Madrid, Spain
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package was;

import java.util.ArrayList;

public class Cell {
	private String name;
	private String path;
	private String resourcesXml;
	private String scope;
	private ArrayList<Resource> resources;
	private ArrayList<Cluster> clusters;

	/*
	 * Cell class constructor:
	 * 
	 * @name: Cell name.
	 * 
	 * @path: Cell path.
	 */
	public Cell(String name, String profilePath) {
		setName(name);
		setPath(profilePath);
		setResourcesXml();
		setScope();

		clusters = new ArrayList<Cluster>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String profilePath) {
		path = profilePath + "/config/cells/" + name;
	}

	public String getResourcesXml() {
		return resourcesXml;
	}

	public void setResourcesXml() {
		resourcesXml = path + "/resources.xml";
	}

	public String getScope() {
		return scope;
	}

	public void setScope() {
		scope = "Cell: " + name;
	}

	public ArrayList<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(ArrayList<Jvm> jvms) {

		// Jvms array iteration
		int index = 0;
		while (index < jvms.size()) {
			Jvm jvm = jvms.get(index);

			// Add cluster to ArrayList only if jvm is member of a cluster
			if (jvm.isMemberOfCluster()) {
				// For each Jvm get the cluster name and add to clusters
				// ArrayList
				clusters.add(new Cluster(jvm.getClusterName(), path, jvms));
			}

			++index;
		}
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}

	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	/*
	 * Method that prints a Resources list:
	 * 
	 * @outputFormat: Can be csv or table.
	 */
	public void printResourcesData(String profileName, String outputFormat) {
		// Resources array iteration
		int index = 0;
		while (index < resources.size()) {
			Resource resource = resources.get(index);

			// For each Resource print data
			resource.printResourceData(profileName, scope, outputFormat);
			++index;
		}
	}
}
