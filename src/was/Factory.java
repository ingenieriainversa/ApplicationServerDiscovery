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
	private String connectionPoolId;
	private String connectionPoolConnectionTimeout;
	private String connectionPoolMaxConnections;
	private String connectionPoolMinConnections;
	private String connectionPoolReapTime;
	private String connectionPoolUnusedTimeout;
	private String connectionPoolAgedTimeout;
	private String connectionPoolPurgePolicy;
	private String connectionPoolNumberOfSharedPoolPartitions;
	private String connectionPoolNumberOfUnsharedPoolPartitions;
	private String connectionPoolNumberOfFreePoolPartitions;
	private String connectionPoolFreePoolDistributionTableSize;
	private String connectionPoolSurgeThreshold;
	private String connectionPoolSurgeCreationInterval;
	private String connectionPoolTestConnection;
	private String connectionPoolTestConnectionInterval;
	private String connectionPoolStuckTimerTime;
	private String connectionPoolStuckTime;
	private String connectionPoolStuckThreshold;

	public Factory(String type, String id, String name, String jndiName,
			String description, String providerType,
			String authMechanismPreference, String authDataAlias,
			String manageCachedHandles, String logMissingTransactionContext,
			String diagnoseConnectionUsage, String relationalResourceAdapter,
			String statementCacheSize, String datasourceHelperClassname,
			String propertyURL, String propertyDatabaseName,
			String propertyDriverType, String propertyServerName,
			String propertyPortNumber, String connectionPoolId,
			String connectionPoolConnectionTimeout,
			String connectionPoolMaxConnections,
			String connectionPoolMinConnections, String connectionPoolReapTime,
			String connectionPoolUnusedTimeout,
			String connectionPoolAgedTimeout, String connectionPoolPurgePolicy,
			String connectionPoolNumberOfSharedPoolPartitions,
			String connectionPoolNumberOfUnsharedPoolPartitions,
			String connectionPoolNumberOfFreePoolPartitions,
			String connectionPoolFreePoolDistributionTableSize,
			String connectionPoolSurgeThreshold,
			String connectionPoolSurgeCreationInterval,
			String connectionPoolTestConnection,
			String connectionPoolTestConnectionInterval,
			String connectionPoolStuckTimerTime,
			String connectionPoolStuckTime, String connectionPoolStuckThreshold) {
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
		setConnectionPoolId(connectionPoolId);
		setConnectionPoolConnectionTimeout(connectionPoolConnectionTimeout);
		setConnectionPoolMaxConnections(connectionPoolMaxConnections);
		setConnectionPoolMinConnections(connectionPoolMinConnections);
		setConnectionPoolReapTime(connectionPoolReapTime);
		setConnectionPoolUnusedTimeout(connectionPoolUnusedTimeout);
		setConnectionPoolAgedTimeout(connectionPoolAgedTimeout);
		setConnectionPoolPurgePolicy(connectionPoolPurgePolicy);
		setConnectionPoolNumberOfSharedPoolPartitions(connectionPoolNumberOfSharedPoolPartitions);
		setConnectionPoolNumberOfUnsharedPoolPartitions(connectionPoolNumberOfUnsharedPoolPartitions);
		setConnectionPoolNumberOfFreePoolPartitions(connectionPoolNumberOfFreePoolPartitions);
		setConnectionPoolFreePoolDistributionTableSize(connectionPoolFreePoolDistributionTableSize);
		setConnectionPoolSurgeThreshold(connectionPoolSurgeThreshold);
		setConnectionPoolSurgeCreationInterval(connectionPoolSurgeCreationInterval);
		setConnectionPoolTestConnection(connectionPoolTestConnection);
		setConnectionPoolTestConnectionInterval(connectionPoolTestConnectionInterval);
		setConnectionPoolStuckTimerTime(connectionPoolStuckTimerTime);
		setConnectionPoolStuckTime(connectionPoolStuckTime);
		setConnectionPoolStuckThreshold(connectionPoolStuckThreshold);
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

	public void setLogMissingTransactionContext(
			String logMissingTransactionContext) {
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

	public String getConnectionPoolId() {
		return connectionPoolId;
	}

	public void setConnectionPoolId(String connectionPoolId) {
		this.connectionPoolId = connectionPoolId;
	}

	public String getConnectionPoolConnectionTimeout() {
		return connectionPoolConnectionTimeout;
	}

	public void setConnectionPoolConnectionTimeout(
			String connectionPoolConnectionTimeout) {
		this.connectionPoolConnectionTimeout = connectionPoolConnectionTimeout;
	}

	public String getConnectionPoolMaxConnections() {
		return connectionPoolMaxConnections;
	}

	public void setConnectionPoolMaxConnections(
			String connectionPoolMaxConnections) {
		this.connectionPoolMaxConnections = connectionPoolMaxConnections;
	}

	public String getConnectionPoolMinConnections() {
		return connectionPoolMinConnections;
	}

	public void setConnectionPoolMinConnections(
			String connectionPoolMinConnections) {
		this.connectionPoolMinConnections = connectionPoolMinConnections;
	}

	public String getConnectionPoolReapTime() {
		return connectionPoolReapTime;
	}

	public void setConnectionPoolReapTime(String connectionPoolReapTime) {
		this.connectionPoolReapTime = connectionPoolReapTime;
	}

	public String getConnectionPoolUnusedTimeout() {
		return connectionPoolUnusedTimeout;
	}

	public void setConnectionPoolUnusedTimeout(
			String connectionPoolUnusedTimeout) {
		this.connectionPoolUnusedTimeout = connectionPoolUnusedTimeout;
	}

	public String getConnectionPoolAgedTimeout() {
		return connectionPoolAgedTimeout;
	}

	public void setConnectionPoolAgedTimeout(String connectionPoolAgedTimeout) {
		this.connectionPoolAgedTimeout = connectionPoolAgedTimeout;
	}

	public String getConnectionPoolPurgePolicy() {
		return connectionPoolPurgePolicy;
	}

	public void setConnectionPoolPurgePolicy(String connectionPoolPurgePolicy) {
		this.connectionPoolPurgePolicy = connectionPoolPurgePolicy;
	}

	public String getConnectionPoolNumberOfSharedPoolPartitions() {
		return connectionPoolNumberOfSharedPoolPartitions;
	}

	public void setConnectionPoolNumberOfSharedPoolPartitions(
			String connectionPoolNumberOfSharedPoolPartitions) {
		this.connectionPoolNumberOfSharedPoolPartitions = connectionPoolNumberOfSharedPoolPartitions;
	}

	public String getConnectionPoolNumberOfUnsharedPoolPartitions() {
		return connectionPoolNumberOfUnsharedPoolPartitions;
	}

	public void setConnectionPoolNumberOfUnsharedPoolPartitions(
			String connectionPoolNumberOfUnsharedPoolPartitions) {
		this.connectionPoolNumberOfUnsharedPoolPartitions = connectionPoolNumberOfUnsharedPoolPartitions;
	}

	public String getConnectionPoolNumberOfFreePoolPartitions() {
		return connectionPoolNumberOfFreePoolPartitions;
	}

	public void setConnectionPoolNumberOfFreePoolPartitions(
			String connectionPoolNumberOfFreePoolPartitions) {
		this.connectionPoolNumberOfFreePoolPartitions = connectionPoolNumberOfFreePoolPartitions;
	}

	public String getConnectionPoolFreePoolDistributionTableSize() {
		return connectionPoolFreePoolDistributionTableSize;
	}

	public void setConnectionPoolFreePoolDistributionTableSize(
			String connectionPoolFreePoolDistributionTableSize) {
		this.connectionPoolFreePoolDistributionTableSize = connectionPoolFreePoolDistributionTableSize;
	}

	public String getConnectionPoolSurgeThreshold() {
		return connectionPoolSurgeThreshold;
	}

	public void setConnectionPoolSurgeThreshold(
			String connectionPoolSurgeThreshold) {
		this.connectionPoolSurgeThreshold = connectionPoolSurgeThreshold;
	}

	public String getConnectionPoolSurgeCreationInterval() {
		return connectionPoolSurgeCreationInterval;
	}

	public void setConnectionPoolSurgeCreationInterval(
			String connectionPoolSurgeCreationInterval) {
		this.connectionPoolSurgeCreationInterval = connectionPoolSurgeCreationInterval;
	}

	public String getConnectionPoolTestConnection() {
		return connectionPoolTestConnection;
	}

	public void setConnectionPoolTestConnection(
			String connectionPoolTestConnection) {
		this.connectionPoolTestConnection = connectionPoolTestConnection;
	}

	public String getConnectionPoolTestConnectionInterval() {
		return connectionPoolTestConnectionInterval;
	}

	public void setConnectionPoolTestConnectionInterval(
			String connectionPoolTestConnectionInterval) {
		this.connectionPoolTestConnectionInterval = connectionPoolTestConnectionInterval;
	}

	public String getConnectionPoolStuckTimerTime() {
		return connectionPoolStuckTimerTime;
	}

	public void setConnectionPoolStuckTimerTime(
			String connectionPoolStuckTimerTime) {
		this.connectionPoolStuckTimerTime = connectionPoolStuckTimerTime;
	}

	public String getConnectionPoolStuckTime() {
		return connectionPoolStuckTime;
	}

	public void setConnectionPoolStuckTime(String connectionPoolStuckTime) {
		this.connectionPoolStuckTime = connectionPoolStuckTime;
	}

	public String getConnectionPoolStuckThreshold() {
		return connectionPoolStuckThreshold;
	}

	public void setConnectionPoolStuckThreshold(
			String connectionPoolStuckThreshold) {
		this.connectionPoolStuckThreshold = connectionPoolStuckThreshold;
	}

	public String toString() {
		return type + ";" + id + ";" + name + ";" + jndiName + ";"
				+ description + ";" + providerType + ";"
				+ authMechanismPreference + ";" + authDataAlias + ";"
				+ manageCachedHandles + ";" + logMissingTransactionContext
				+ ";" + diagnoseConnectionUsage + ";"
				+ relationalResourceAdapter + ";" + statementCacheSize + ";"
				+ datasourceHelperClassname + ";" + propertyURL + ";"
				+ propertyDatabaseName + ";" + propertyDriverType + ";"
				+ propertyDatabaseName + ";" + propertyPortNumber + ";"
				+ connectionPoolId + ";" + connectionPoolConnectionTimeout
				+ ";" + connectionPoolMaxConnections + ";"
				+ connectionPoolMinConnections + ";" + connectionPoolReapTime
				+ ";" + connectionPoolUnusedTimeout + ";"
				+ connectionPoolAgedTimeout + ";" + connectionPoolPurgePolicy
				+ ";" + connectionPoolNumberOfSharedPoolPartitions + ";"
				+ connectionPoolNumberOfUnsharedPoolPartitions + ";"
				+ connectionPoolNumberOfFreePoolPartitions + ";"
				+ connectionPoolFreePoolDistributionTableSize + ";"
				+ connectionPoolSurgeThreshold + ";"
				+ connectionPoolSurgeCreationInterval + ";"
				+ connectionPoolTestConnection + ";"
				+ connectionPoolTestConnectionInterval + ";"
				+ connectionPoolStuckTimerTime + ";" + connectionPoolStuckTime
				+ ";" + connectionPoolStuckThreshold;
	}
}
