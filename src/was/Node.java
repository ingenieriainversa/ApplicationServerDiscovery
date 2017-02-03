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
