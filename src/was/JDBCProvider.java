/*
 * Application Server Discovery v0.01
 * JDBCProvider.java
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

public class JDBCProvider extends Resource {

	private String description;
	private String providerType;
	private String isolatedClassLoader;
	private String implementationClassName;
	private String xa;
	private ArrayList<String> classpaths;
	private ArrayList<String> nativeptahs;
	private ArrayList<Factory> factories;
	
	/*
	 * Node class constructor:
	 * 
	 * @id: JDBC Provider id.
	 * 
	 * @name: JDBC Provider name.
	 * 
	 * @description: JDBC Provider description.
	 * 
	 * @providerType: JDBC Provider type.
	 * 
	 * @isolatedClassLoader: JDBC Provider isolated class loader.
	 * 
	 * @implementationClassName: JDBC Provider implementation class name.
	 * 
	 * @xa: JDBC Provider connection type.
	 */
	public JDBCProvider(String id, String name, String description, String providerType, String isolatedClassLoader,
			String implementationClassName, String xa) {
		super(id, name);
		setDescription(description);
		setProviderType(providerType);
		setIsolatedClassLoader(isolatedClassLoader);
		setImplementationClassName(implementationClassName);
		setXa(xa);
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

	public String getIsolatedClassLoader() {
		return isolatedClassLoader;
	}

	public void setIsolatedClassLoader(String isolatedClassLoader) {
		this.isolatedClassLoader = isolatedClassLoader;
	}

	public String getImplementationClassName() {
		return implementationClassName;
	}

	public void setImplementationClassName(String implementationClassName) {
		this.implementationClassName = implementationClassName;
	}

	public String getXa() {
		return xa;
	}

	public void setXa(String xa) {
		this.xa = xa;
	}

	public ArrayList<String> getClasspaths() {
		return classpaths;
	}

	public void setClasspaths(ArrayList<String> classpaths) {
		this.classpaths = classpaths;
	}

	public ArrayList<String> getNativeptahs() {
		return nativeptahs;
	}

	public void setNativeptahs(ArrayList<String> nativeptahs) {
		this.nativeptahs = nativeptahs;
	}

	public ArrayList<Factory> getFactories() {
		return factories;
	}

	public void setFactories(ArrayList<Factory> factories) {
		this.factories = factories;
	}

}
