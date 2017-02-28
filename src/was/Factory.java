/*
 * Application Server Discovery v0.01
 * Factory.java
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

public class Factory {

	private String type;
	private String id;
	private String name;
	private String jndiName;
	private String description;
	private String providerType;
	private String authMechanismPreference;
	private String authDataAlias;
	private String manageCachedHandles;
	private String logMissingTransactionContext;
	private String diagnoseConnectionUsage;
	private String relationalResourceAdapter;
	private String statementCacheSize;
	private String datasourceHelperClassname;
	private String propertyURL;
	private String propertyDatabaseName;
	private String propertyDriverType;
	private String propertyServerName;
	private String propertyPortNumber;

	public Factory(String type, String id, String name, String jndiName, String description, String providerType,
			String authMechanismPreference, String authDataAlias, String manageCachedHandles,
			String logMissingTransactionContext, String diagnoseConnectionUsage, String relationalResourceAdapter,
			String statementCacheSize, String datasourceHelperClassname, String propertyURL,
			String propertyDatabaseName, String propertyDriverType, String propertyServerName,
			String propertyPortNumber) {
		setType(type);
		setId(id);
		setName(name);
		setJndiName(jndiName);
		setDescription(description);
		setProviderType(providerType);
		setAuthMechanismPreference(authMechanismPreference);
		setAuthDataAlias(authDataAlias);
		setManageCachedHandles(manageCachedHandles);
		setLogMissingTransactionContext(logMissingTransactionContext);
		setDiagnoseConnectionUsage(diagnoseConnectionUsage);
		setRelationalResourceAdapter(relationalResourceAdapter);
		setStatementCacheSize(statementCacheSize);
		setDatasourceHelperClassname(datasourceHelperClassname);
		setPropertyURL(propertyURL);
		setPropertyDatabaseName(propertyDatabaseName);
		setPropertyDriverType(propertyDriverType);
		setPropertyServerName(propertyServerName);
		setPropertyPortNumber(propertyPortNumber);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getAuthMechanismPreference() {
		return authMechanismPreference;
	}

	public void setAuthMechanismPreference(String authMechanismPreference) {
		this.authMechanismPreference = authMechanismPreference;
	}

	public String getAuthDataAlias() {
		return authDataAlias;
	}

	public void setAuthDataAlias(String authDataAlias) {
		this.authDataAlias = authDataAlias;
	}

	public String getManageCachedHandles() {
		return manageCachedHandles;
	}

	public void setManageCachedHandles(String manageCachedHandles) {
		this.manageCachedHandles = manageCachedHandles;
	}

	public String getLogMissingTransactionContext() {
		return logMissingTransactionContext;
	}

	public void setLogMissingTransactionContext(String logMissingTransactionContext) {
		this.logMissingTransactionContext = logMissingTransactionContext;
	}

	public String getDiagnoseConnectionUsage() {
		return diagnoseConnectionUsage;
	}

	public void setDiagnoseConnectionUsage(String diagnoseConnectionUsage) {
		this.diagnoseConnectionUsage = diagnoseConnectionUsage;
	}

	public String getRelationalResourceAdapter() {
		return relationalResourceAdapter;
	}

	public void setRelationalResourceAdapter(String relationalResourceAdapter) {
		this.relationalResourceAdapter = relationalResourceAdapter;
	}

	public String getStatementCacheSize() {
		return statementCacheSize;
	}

	public void setStatementCacheSize(String statementCacheSize) {
		this.statementCacheSize = statementCacheSize;
	}

	public String getDatasourceHelperClassname() {
		return datasourceHelperClassname;
	}

	public void setDatasourceHelperClassname(String datasourceHelperClassname) {
		this.datasourceHelperClassname = datasourceHelperClassname;
	}

	public String getPropertyURL() {
		return propertyURL;
	}

	public void setPropertyURL(String propertyURL) {
		this.propertyURL = propertyURL;
	}

	public String getPropertyDatabaseName() {
		return propertyDatabaseName;
	}

	public void setPropertyDatabaseName(String propertyDatabaseName) {
		this.propertyDatabaseName = propertyDatabaseName;
	}

	public String getPropertyDriverType() {
		return propertyDriverType;
	}

	public void setPropertyDriverType(String propertyDriverType) {
		this.propertyDriverType = propertyDriverType;
	}

	public String getPropertyServerName() {
		return propertyServerName;
	}

	public void setPropertyServerName(String propertyServerName) {
		this.propertyServerName = propertyServerName;
	}

	public String getPropertyPortNumber() {
		return propertyPortNumber;
	}

	public void setPropertyPortNumber(String propertyPortNumber) {
		this.propertyPortNumber = propertyPortNumber;
	}

	public String toString() {
		return type + ";" + id + ";" + name + ";" + jndiName + ";" + description + ";" + providerType + ";"
				+ authMechanismPreference + ";" + authDataAlias + ";" + manageCachedHandles + ";"
				+ logMissingTransactionContext + ";" + diagnoseConnectionUsage + ";" + relationalResourceAdapter + ";"
				+ statementCacheSize + ";" + datasourceHelperClassname + ";" + propertyURL + ";" + propertyDatabaseName
				+ ";" + propertyDriverType + ";" + propertyDatabaseName + ";" + propertyPortNumber;
	}
}
