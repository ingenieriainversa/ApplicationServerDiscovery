/*
 * Application Server Discovery v0.01
 * ResourcesXmlParser.java
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ResourcesXmlParser {
	private static ArrayList<Resource> resources;
	private static ArrayList<String> classpaths;
	private static ArrayList<String> nativeptahs;
	private static ArrayList<Factory> factories;

	public void parse(String resourcesXmlFile) {

		resources = new ArrayList<Resource>();

		try {

			// Create a new DocumentBuilder instance
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();

			// Process the XML file and obtain the Document object
			Document doc = documentBuilder.parse(new InputSource(
					new FileInputStream(resourcesXmlFile)));

			// Get serverindex root node
			Element resourcesXmlNode = doc.getDocumentElement();

			// resourcesXml children nodes iteration
			NodeList resourcesXmlChildNodes = resourcesXmlNode.getChildNodes();
			for (int a = 0; a < resourcesXmlChildNodes.getLength(); a++) {

				classpaths = new ArrayList<String>();
				nativeptahs = new ArrayList<String>();
				factories = new ArrayList<Factory>();

				Node resourcesNode = resourcesXmlChildNodes.item(a);
				if (resourcesNode instanceof Element) {

					// Get child node name
					String resourcesNodeName = resourcesNode.getNodeName();

					// Get id attribute from resources node
					String id = resourcesNode.getAttributes()
							.getNamedItem("xmi:id").getTextContent();

					// Get name attribute from resources node
					String name = resourcesNode.getAttributes()
							.getNamedItem("name").getTextContent();

					NamedNodeMap resourcesNodeAttrs = resourcesNode
							.getAttributes();

					String description = null;
					String providerType = null;
					String isolatedClassLoader = null;
					String xa = null;

					for (int i = 0; i < resourcesNodeAttrs.getLength(); i++) {
						Attr attribute = (Attr) resourcesNodeAttrs.item(i);

						// Get description attribute from resources node
						if (attribute.getName().equals("description")) {
							description = attribute.getValue();
						}

						// Get providerType attribute from resources node
						if (attribute.getName().equals("providerType")) {
							providerType = attribute.getValue();
						}

						// Get isolatedClassLoader attribute from resources
						// node
						if (attribute.getName().equals("isolatedClassLoader")) {
							isolatedClassLoader = attribute.getValue();
						}

						// Get xa attribute from resources node
						if (attribute.getName().equals("xa")) {
							xa = attribute.getValue();
						}
					}

					if (resourcesNodeName.equals("resources.jdbc:JDBCProvider")) {

						// Get implementationClassName attribute from resources
						// node
						String implementationClassName = resourcesNode
								.getAttributes()
								.getNamedItem("implementationClassName")
								.getTextContent();

						// resourcesNode children nodes iteration
						NodeList resourcesChildNodes = resourcesNode
								.getChildNodes();
						for (int b = 0; b < resourcesChildNodes.getLength(); b++) {

							Node resourcesChildNode = resourcesChildNodes
									.item(b);
							if (resourcesChildNode instanceof Element) {

								NamedNodeMap resourcesChildNodeAttrs = resourcesChildNode
										.getAttributes();

								String factoryProviderType = null;
								String factoryAuthDataAlias = null;
								String factoryManageCachedHandles = null;
								String factoryLogMissingTransactionContext = null;
								String factoryDiagnoseConnectionUsage = null;

								for (int i = 0; i < resourcesChildNodeAttrs
										.getLength(); i++) {
									Attr attribute = (Attr) resourcesChildNodeAttrs
											.item(i);

									// Get providerType attribute from factories
									// node
									if (attribute.getName().equals(
											"providerType")) {
										factoryProviderType = attribute
												.getValue();
									}

									// Get authDataAlias attribute from
									// factories node
									if (attribute.getName().equals(
											"authDataAlias")) {
										factoryAuthDataAlias = attribute
												.getValue();
									}

									// Get manageCachedHandles attribute from
									// factories node
									if (attribute.getName().equals(
											"manageCachedHandles")) {
										factoryManageCachedHandles = attribute
												.getValue();
									}

									// Get logMissingTransactionContext
									// attribute from factories node
									if (attribute.getName().equals(
											"logMissingTransactionContext")) {
										factoryLogMissingTransactionContext = attribute
												.getValue();
									}

									// Get diagnoseConnectionUsage attribute
									// from factories node
									if (attribute.getName().equals(
											"diagnoseConnectionUsage")) {
										factoryDiagnoseConnectionUsage = attribute
												.getValue();
									}
								}

								// Get child node name
								String childNodeName = resourcesChildNode
										.getNodeName();

								if (childNodeName.equals("classpath")) {

									// Get text that classpath node contains
									String classpath = resourcesChildNode
											.getTextContent();

									// Add classpath to ArrayList<String>
									classpaths.add(new String(classpath));

								} else if (childNodeName.equals("nativepath")) {

									// Get text that nativeptah node contains
									String nativeptah = resourcesChildNode
											.getTextContent();

									// Add nativeptah to ArrayList<String>
									nativeptahs.add(new String(nativeptah));

								} else if (childNodeName.equals("factories")) {

									String factoryPropertyURL = null;

									// Get xmi:type attribute from factories
									// node
									String factoryType = resourcesChildNode
											.getAttributes()
											.getNamedItem("xmi:type")
											.getTextContent();

									// Get xmi:id attribute from factories node
									String factoryId = resourcesChildNode
											.getAttributes()
											.getNamedItem("xmi:id")
											.getTextContent();

									// Get name attribute from factories node
									String factoryName = resourcesChildNode
											.getAttributes()
											.getNamedItem("name")
											.getTextContent();

									// Get jndiName attribute from factories
									// node
									String factoryJndiName = resourcesChildNode
											.getAttributes()
											.getNamedItem("jndiName")
											.getTextContent();

									// Get description attribute from factories
									// node
									String factoryDescription = resourcesChildNode
											.getAttributes()
											.getNamedItem("description")
											.getTextContent();

									// Get authMechanismPreference attribute
									// from factories node
									String factoryAuthMechanismPreference = resourcesChildNode
											.getAttributes()
											.getNamedItem(
													"authMechanismPreference")
											.getTextContent();

									// Get relationalResourceAdapter attribute
									// from factories node
									String factoryRelationalResourceAdapter = resourcesChildNode
											.getAttributes()
											.getNamedItem(
													"relationalResourceAdapter")
											.getTextContent();

									// Get statementCacheSize attribute from
									// factories node
									String factoryStatementCacheSize = resourcesChildNode
											.getAttributes()
											.getNamedItem("statementCacheSize")
											.getTextContent();

									// Get datasourceHelperClassname attribute
									// from factories node
									String factoryDatasourceHelperClassname = resourcesChildNode
											.getAttributes()
											.getNamedItem(
													"datasourceHelperClassname")
											.getTextContent();

									// factories children nodes iteration
									NodeList factoriesChildrenNodes = resourcesChildNode
											.getChildNodes();
									for (int c = 0; c < factoriesChildrenNodes
											.getLength(); c++) {

										Node factoriesChildNode = factoriesChildrenNodes
												.item(c);
										if (factoriesChildNode instanceof Element) {

											// Get child node name
											String factoriesChildNodeName = factoriesChildNode
													.getNodeName();

											if (factoriesChildNodeName
													.equals("propertySet")) {
												// propertySet children nodes
												// iteration
												NodeList propertySetChildrenNodes = factoriesChildNode
														.getChildNodes();
												for (int d = 0; d < propertySetChildrenNodes
														.getLength(); d++) {

													Node propertySetChildNode = propertySetChildrenNodes
															.item(d);
													if (propertySetChildNode instanceof Element) {

														// Get name attribute
														// from propertySet node
														String factoryPropertyName = propertySetChildNode
																.getAttributes()
																.getNamedItem(
																		"name")
																.getTextContent();

														if (factoryPropertyName
																.equals("URL")) {
															// Get value
															// attribute
															// from propertySet
															// node
															// ans set it
															factoryPropertyURL = propertySetChildNode
																	.getAttributes()
																	.getNamedItem(
																			"value")
																	.getTextContent();
														}
													}
												}
											} else if (factoriesChildNodeName
													.equals("connectionPool")) {
											}
										}
									}

									// Add factories to ArrayList<Factory>
									factories
											.add(new Factory(
													factoryType,
													factoryId,
													factoryName,
													factoryJndiName,
													factoryDescription,
													factoryProviderType,
													factoryAuthMechanismPreference,
													factoryAuthDataAlias,
													factoryManageCachedHandles,
													factoryLogMissingTransactionContext,
													factoryDiagnoseConnectionUsage,
													factoryRelationalResourceAdapter,
													factoryStatementCacheSize,
													factoryDatasourceHelperClassname,
													factoryPropertyURL));

								}
							}
						}

						resources.add(new JDBCProvider(id, name, description,
								providerType, isolatedClassLoader, implementationClassName, xa,
								classpaths, nativeptahs, factories));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}
}
