/*
 * Application Server Discovery v0.01
 * Node.java
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

public class Node {
	private String nodeName;
	private String nodePath;
	private String resourcesXml;

	/*
	 * Node class constructor:
	 * 
	 * @profilePath: Profile path.
	 * 
	 * @cell: Cell object.
	 * 
	 * @nodeName: Node name.
	 */
	public Node(String profilePath, Cell cell, String nodeName) {
		setNodeName(nodeName);
		setNodePath(profilePath, cell);
		setResourcesXml();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String profilePath, Cell cell) {
		nodePath = profilePath + "/config/cells/" + cell.getCellName() + "nodes/"+ nodeName;
	}
	
	public String getResourcesXml() {
		return resourcesXml;
	}

	public void setResourcesXml() {
		resourcesXml = nodePath +"/resources.xml";
	}
}
