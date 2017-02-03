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
