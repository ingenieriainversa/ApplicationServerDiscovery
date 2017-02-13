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
	private String width;

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
	 * @implementationClassName: JDBC Provider implementation class name.
	 * 
	 * @xa: JDBC Provider connection type.
	 */
	public JDBCProvider(String id, String name, String description,
			String providerType, String implementationClassName, String xa,
			ArrayList<String> classpaths, ArrayList<String> nativeptahs,
			ArrayList<Factory> factories) {
		super(id, name);
		setDescription(description);
		setProviderType(providerType);
		setIsolatedClassLoader(isolatedClassLoader);
		setImplementationClassName(implementationClassName);
		setXa(xa);
		setClasspaths(classpaths);
		setNativeptahs(nativeptahs);
		setFactories(factories);
		width = "%-26.26s";
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

	public String toString() {
		return getId() + ";" + getName() + ";" + description + ";"
				+ providerType + ";" + isolatedClassLoader + ";"
				+ implementationClassName + ";" + xa;
	}

	public void printData(String outputFormat) {
		if (outputFormat.equals("csv")) {
			System.out.printf("%s\n", toString());
		} else if (outputFormat.equals("table")) {
			String width = "%-13.13s";
			System.out.printf(width + "%s\n" + width + "%s\n\n", "Id:",
					getId(), "Name:", getName());
		}
	}

	public void printResourceClassPathData(String outputFormat) {
		// ClassPath array iteration
		int index = 0;
		while (index < classpaths.size()) {
			String classpath = classpaths.get(index);

			// For each ClassPath print data
			if (outputFormat.equals("csv")) {
				System.out.printf("%s", classpath);
			} else if (outputFormat.equals("table")) {
				if (index == 0) {
					System.out.printf(width + "%s\n", "ClassPath:", classpath);
				} else {
					System.out.printf(width + "%s\n", "", classpath);
				}
			}
			++index;
		}
	}

	public void printResourceNativePathData(String outputFormat) {
		// NativePath array iteration
		int index = 0;
		while (index < nativeptahs.size()) {
			String nativepath = nativeptahs.get(index);

			// For each NativePath print data
			if (outputFormat.equals("csv")) {
				System.out.printf("%s\n", nativepath);
			} else if (outputFormat.equals("table")) {

				if (index == 0) {
					System.out
							.printf(width + "%s\n", "NativePath:", nativepath);
				} else {
					System.out.printf(width + "%s\n", "", nativepath);
				}
			}
			++index;
		}
	}

	public void printResourceFactoriesData(String profileName, String scope, String outputFormat) {
		// Factories array iteration
		int index = 0;
		while (index < factories.size()) {
			Factory factory = factories.get(index);

			// For each Factory print data
			if (outputFormat.equals("csv")) {
				System.out.printf("%s;%s;%s;%s\n", profileName, scope, toString(), factory.toString());
			} else if (outputFormat.equals("table")) {
//				if (index == 0) {
//					System.out.printf(width + "%s\n", "Factory:",
//							factory.getName());
//				} else {
//					System.out.printf(width + "%s\n", "", factory.getName());
//				}
			}
			++index;
		}
	}

	@Override
	public void printResourceData(String profileName, String scope, String outputFormat) {
		if (outputFormat.equals("csv")) {
			printResourceFactoriesData(profileName, scope, outputFormat);
		} else if (outputFormat.equals("table")) {
			// System.out.printf(width + "%s\n" + width + "%s\n" + width +
			// "%s\n"
			// + width + "%s\n" + width + "%s\n" + width + "%s\n",
			// "Id:", getId(), "Name:", getName(), "Description:",
			// getDescription(), "Provider type:", getProviderType(),
			// "Implementation Class Name", getImplementationClassName(),
			// "XA:", getXa());
			// printResourceClassPathData(outputFormat);
			// printResourceNativePathData(outputFormat);
			// printResourceFactoriesData(outputFormat);
		}
	}

}
