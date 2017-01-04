/*
 * Application Server Discovery v0.01
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
	private String serverName;
	private String serverType;
	private ArrayList<EndPoint> endPoints;
	private ArrayList<App> apps;
	private int countApps;

	/*
	 * Jvm class constructor:
	 * 
	 * @hostName: Jvm hostName.
	 * 
	 * @serverName: Jvm serverName.
	 * 
	 * @serverType: Jvm serverType.
	 * 
	 * @apps: Jvm apps array list.
	 * 
	 * @endPoints: Jvm endPoints array list.
	 */
	public Jvm(String hostName, String serverName, String serverType, ArrayList<App> apps,
			ArrayList<EndPoint> endPoints) {
		setHostName(hostName);
		setServerName(serverName);
		setServerType(serverType);
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

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
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

	public void printJvmData(String profile, String cell, String node, String outputFormat) {
		if (outputFormat.equals("csv")) {
			System.out.printf("%s;%s;%s;%s;%s;%s;%s\n", getServerName(), getServerType(), getHostName(), profile, cell,
					node, getCountApps());
		} else if (outputFormat.equals("table")) {
			String width = "%-13.13s";
			System.out.printf(
					width + "%s\n" + width + "%s\n" + width + "%s\n" + width + "%s\n" + width + "%s\n" + width + "%s\n"
							+ width + "%d\n\n",
					"Server name:", getServerName(), "Server type:", getServerType(), "Hostname:", getHostName(),
					"Profile:", profile, "Cell:", cell, "Node:", node, "Apps count:", getCountApps());
		}
	}

	public void endPointData(String endPointData, String outputFormat) {

		// Print the complete data line for endPoint
		if (outputFormat.equals("csv")) {
			System.out.printf("%s;%s;%s;%s\n", getHostName(), getServerName(), getServerType(), endPointData);
		} else if (outputFormat.equals("table")) {

			// Fix this!
			System.out.printf("%s;%s;%s;%s\n", getHostName(), getServerName(), getServerType(), endPointData);
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
				endPointData(endPoint.printData(), outputFormat);

			} else if (endPoint.getEndPointName().equals(endPointName)) {

				// Print jvm data filtered by endPointName
				endPointData(endPoint.printData(), outputFormat);
			}
			++index;
		}
	}

	public void printAppsData() {
		// Apps array iteration
		int appIndex = 0;
		while (appIndex < getApps().size()) {
			App app = getApps().get(appIndex);

			// EndPoints array iteration
			int endPointIndex = 0;
			while (endPointIndex < getEndPoints().size()) {
				EndPoint endPoint = getEndPoints().get(endPointIndex);

				// Print jvm data
				System.out.printf("%s;%s;%s;%s;%s\n", getHostName(), getServerName(), getServerType(),
						endPoint.printData(), app.getName());
				++endPointIndex;
			}
			++appIndex;
		}
	}
}