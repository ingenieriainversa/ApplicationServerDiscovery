/*
 * Application Server Discovery v0.01
 * Profile.java
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

import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Profile {

	// New instance of Properties class
	private Properties propertiesFile = new Properties();

	private String isAReservationTicket;
	private String isDefault;
	private String name;
	private String path;
	private String template;
	private String cell;
	private String node;
	private String serverindex;
	private ArrayList<Jvm> jvms;

	/* Jvm class constructor:
	 * @isAReservationTicket: Profile isAReservationTicket.
	 * @isDefault: Profile isDefault.
	 * @name: Profile name.
	 * @path: Profile path.
	 * @template: Profile template.
	 */
	public Profile(String isAReservationTicket, String isDefault, String name,
			String path, String template) throws IOException {
		setIsAReservationTicket(isAReservationTicket);
		setIsDefault(isDefault);
		setName(name);
		setPath(path);
		setTemplate(template);

		// Load properties file
		propertiesFile.load(new FileInputStream(path + "/bin/setupCmdLine.sh"));

		// Set atributtes with propertiesFile properties values
		setCell(propertiesFile.getProperty("WAS_CELL"));
		setNode(propertiesFile.getProperty("WAS_NODE"));

		// Set serverindex attribute with serverindex.xml absolute path
		setServerindex(path, cell, node);

		setJvms(null);
	}

	public String getIsAReservationTicket() {
		return isAReservationTicket;
	}

	public void setIsAReservationTicket(String isAReservationTicket) {
		this.isAReservationTicket = isAReservationTicket;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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

	public void setPath(String path) {
		this.path = path;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getServerindex() {
		return serverindex;
	}

	public void setServerindex(String path, String cell, String node) {
		serverindex = path + "/config/cells/" + cell + "/nodes/" + node
				+ "/serverindex.xml";
	}

	public ArrayList<Jvm> getJvms() {
		return jvms;
	}

	public void setJvms(ArrayList<Jvm> jvms) {
		this.jvms = jvms;
	}

	public void printProfileData(String outputFormat) {
		if (outputFormat.equals("csv")) {
			System.out.printf("%s;%s;%s;%s;%s;%s;%s;%s\n",
					getIsAReservationTicket(), getIsDefault(), getName(),
					getPath(), getTemplate(), getCell(), getNode(),
					getServerindex());
		} else if (outputFormat.equals("table")) {
			String width = "%-25.25s";
			System.out.printf(width + "%s\n" + width + "%s\n" + width + "%s\n"
					+ width + "%s\n" + width + "%s\n" + width + "%s\n" + width
					+ "%s\n" + width + "%s\n\n", "Is a reservation ticket:",
					getIsAReservationTicket(), "Default:", getIsDefault(),
					"Name:", getName(), "Path:", getPath(), "Template:",
					getTemplate(), "Cell:", getCell(), "Node:", getNode(),
					"Serverindex:", getServerindex());
		}
	}

	// Prints Jvm data list
	public void printJvmList(String outputFormat) {
		// Jvms array iteration
		int index = 0;
		while (index < jvms.size()) {
			Jvm jvm = jvms.get(index);

			// For each Jvm print data
			jvm.printJvmData(getName(), getCell(), getNode(), outputFormat);

			++index;
		}
	}

	/* Method that prints a Jvm list filtered by endPointName:
	 * @endPointName: The filter.
	 */
	public void printJvmListFilteredByEndPointName(String endPointName) {
		// Jvms array iteration
		int index = 0;
		while (index < jvms.size()) {
			Jvm jvm = jvms.get(index);

			// For each Jvm print data
			jvm.printEndPointsData(endPointName);

			++index;
		}
	}

	// Prints a Jvm apps list
	public void printJvmAppList() {
		// Jvms array iteration
		int index = 0;
		while (index < jvms.size()) {
			Jvm jvm = jvms.get(index);

			// For each Jvm print data
			jvm.printAppsData();

			++index;
		}
	}
}