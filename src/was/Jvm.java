/*
 * Application Server Discovery v0.03
 * Jvm.java
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

public class Jvm {
	private String hostName;
	private String name;
	private String path;
	private String type;
	private String serverXmlFile;
	private String resourcesXml;
	private ServerXmlParser serverXml;
	private String scope;
	private String clusterName;
	private boolean memberOfCluster;
	private ArrayList<EndPoint> endPoints;
	private ArrayList<App> apps;
	private int countApps;
	private ArrayList<Resource> resources;

	/*
	 * Jvm class constructor:
	 * 
	 * @hostName: Jvm hostName.
	 * 
	 * @name: Jvm name.
	 * 
	 * @nodePath: Jvm node path
	 * 
	 * @type: Jvm type.
	 * 
	 * @apps: Jvm apps array list.
	 * 
	 * @endPoints: Jvm endPoints array list.
	 */
	public Jvm(String hostName, String name, String nodePath, String type,
			ArrayList<App> apps, ArrayList<EndPoint> endPoints) {
		setHostName(hostName);
		setName(name);
		setPath(nodePath);
		setType(type);
		setServerXmlFile(path);
		setResourcesXml();
		setScope();
		setClusterName();
		setMemberOfCluster();
		setApps(apps);
		setCountApps(apps);
		setEndPoints(endPoints);
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
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

	public void setPath(String nodePath) {
		path = nodePath + "/servers/" + name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServerXmlFile() {
		return serverXmlFile;
	}

	public void setServerXmlFile(String serverPath) {
		serverXmlFile = serverPath + "/server.xml";
	}
	
	public String getResourcesXml() {
		return resourcesXml;
	}

	public void setResourcesXml() {
		if(!name.equals("nodeagent")) {
			resourcesXml = path +"/resources.xml";
		} else {
			resourcesXml = null;
		}
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName() {

		// New instance of server.xml class
		serverXml = new ServerXmlParser();

		// Parse server.xml file
		serverXml.parse(serverXmlFile);

		// Set clusterName
		clusterName = serverXml.getClusterName();
	}
	
	public String getScope() {
		return scope;
	}

	public void setScope() {
		scope = "Server: "+ getName();
	}

	public boolean isMemberOfCluster() {
		return memberOfCluster;
	}

	public void setMemberOfCluster() {

		// Set to true only if cluster name is not null
		if (getClusterName() != null) {
			memberOfCluster = true;
		} else {
			memberOfCluster = false;
		}
	}

	public ArrayList<App> getApps() {
		return apps;
	}

	public void setApps(ArrayList<App> apps) {
		this.apps = apps;
	}

	public int getCountApps() {
		return countApps;
	}

	public void setCountApps(ArrayList<App> apps) {
		countApps = apps.size();
	}

	public ArrayList<EndPoint> getEndPoints() {
		return endPoints;
	}

	public void setEndPoints(ArrayList<EndPoint> endPoints) {
		this.endPoints = endPoints;
	}
	
	public void printJvmData(String profile, Cell cell, Node node,
			String outputFormat) {
		if (outputFormat.equals("csv")) {
			System.out.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", hostName, name, type,
					profile, cell.getName(), node.getName(),
					countApps, memberOfCluster, clusterName);
		} else if (outputFormat.equals("table")) {
			System.out.printf("| %-11.11s %-22.22s %-20.20s %-25.25s %-17.17s %-24.24s %-12.12s %-19.19s %-17.17s |\n", hostName, name, type,
					profile, cell.getName(), node.getName(),
					countApps, memberOfCluster, clusterName);
		}
	}

	public void endPointData(String endPointData, String outputFormat) {

		// Print the complete data line for endPoint
		if (outputFormat.equals("csv")) {
			System.out.printf("%s;%s;%s;%s\n", hostName, name, type,
					endPointData);
		} else if (outputFormat.equals("table")) {

			// Fix this!
			System.out.printf("| %-11.11s %-22.22s %-20.20s %s\n", hostName, name, type,
					endPointData);
		}
	}

	public void printEndPointsData(String endPointName, String outputFormat) {

		// EndPoints array iteration
		int index = 0;
		while (index < getEndPoints().size()) {
			EndPoint endPoint = getEndPoints().get(index);

			// Before "endPointName.isEmpty()"
			if (endPointName.equals("all")) {

				// Print jvm data without filter
				endPointData(endPoint.printData(outputFormat), outputFormat);

			} else if (endPoint.getName().equals(endPointName)) {

				// Print jvm data filtered by endPointName
				endPointData(endPoint.printData(outputFormat), outputFormat);
			}
			++index;
		}
	}

	public void printAppsData(String outputFormat) {
		// Apps array iteration
		int appIndex = 0;
		while (appIndex < getApps().size()) {
			App app = getApps().get(appIndex);

			// EndPoints array iteration
			int endPointIndex = 0;
			while (endPointIndex < getEndPoints().size()) {
				EndPoint endPoint = getEndPoints().get(endPointIndex);

				// Print jvm data
				System.out.printf("%s;%s;%s;%s;%s\n", hostName, name, type,
						endPoint.printData(outputFormat), app.getName());
				++endPointIndex;
			}
			++appIndex;
		}
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
