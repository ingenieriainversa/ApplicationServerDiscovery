/*
 * Application Server Discovery v0.01
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
	private String cellName;
	private String cellPath;
	private String resourcesXml;
	private ArrayList<Resource> resources;

	/*
	 * Cell class constructor:
	 * 
	 * @cellName: Cell name.
	 */
	public Cell(String profilePath, String cellName) {
		setCellName(cellName);
		setCellPath(profilePath);
		setResourcesXml();
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCellPath() {
		return cellPath;
	}

	public void setCellPath(String profilePath) {
		cellPath = profilePath + "/config/cells/" + cellName;
	}

	public String getResourcesXml() {
		return resourcesXml;
	}

	public void setResourcesXml() {
		resourcesXml = cellPath + "/resources.xml";
	}
}
