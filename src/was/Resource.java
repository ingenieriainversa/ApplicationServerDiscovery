package was;

import java.util.ArrayList;

public class Resource {

	private String resourceId;
	private String resourceName;
	private ArrayList<JDBCProvider> jdbcProviders;

	public Resource(String resourceId, String resourceName) {
		setResourceId(resourceId);
		setResourceName(resourceName);
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
