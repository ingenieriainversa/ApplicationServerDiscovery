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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ResourcesXmlParser {
	private static ArrayList<Resource> resources;
	private static ArrayList<JDBCProvider> jdbcProviders;
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
					new FileInputStream("resources.xml")));

			// Get serverindex root node
			Element resourcesXmlNode = doc.getDocumentElement();

			// resourcesXml children nodes iteration
			NodeList resourcesXmlChildNodes = resourcesXmlNode.getChildNodes();
			for (int a = 0; a < resourcesXmlChildNodes.getLength(); a++) {

				jdbcProviders = new ArrayList<JDBCProvider>();
				classpaths = new ArrayList<String>();
				nativeptahs = new ArrayList<String>();
				factories = new ArrayList<Factory>();

				Node resourcesNode = resourcesXmlChildNodes.item(a);
				if (resourcesNode instanceof Element) {
					
					// Get child node name
					String resourcesNodeName = resourcesNode.getNodeName();
					
					// Get id attribute from resources node
					String id = resourcesNode.getAttributes()
							.getNamedItem("id").getTextContent();

					// Get name attribute from resources node
					String name = resourcesNode.getAttributes()
							.getNamedItem("name").getTextContent();

					// resources children nodes iteration
					NodeList resourcesChildNodes = resourcesNode
							.getChildNodes();
//					for (int b = 0; b < resourcesChildNodes.getLength(); b++) {
//
//						Node childNode = resourcesChildNodes.item(b);
//						if (childNode instanceof Element) {
//
//							// Get child node name
//							String childNodeName = childNode.getNodeName();
//
//							if (childNodeName.equals("deployedApplications")) {
//
//								// Get text that deployedApplications node
//								// contains
//								String deployedApplication = childNode
//										.getTextContent();
//
//								// Add app to ArrayList<App>
//								apps.add(new App(deployedApplication));
//
//							} else if (childNodeName.equals("specialEndpoints")) {
//
//								// Get endPointName attribute from
//								// specialEndpoints node
//								String endpointName = childNode.getAttributes()
//										.getNamedItem("endPointName")
//										.getTextContent();
//
//								// specialEndpoints children nodes iteration
//								NodeList specialEndpointsChildNodes = childNode
//										.getChildNodes();
//								for (int c = 0; c < specialEndpointsChildNodes
//										.getLength(); c++) {
//									Node endPoint = specialEndpointsChildNodes
//											.item(c);
//									if (endPoint instanceof Element) {
//
//										// Get host attribute from endPoint node
//										String host = endPoint.getAttributes()
//												.getNamedItem("host")
//												.getTextContent();
//
//										// Get port attribute from endPoint node
//										String port = endPoint.getAttributes()
//												.getNamedItem("port")
//												.getTextContent();
//
//										// Add endPoint to ArrayList<EndPoint>
//										endPoints.add(new EndPoint(
//												endpointName, host, port));
//									}
//								}
//							}
//						}
//					}

					// Add resource to ArrayList<Resource>
					resources.add(new Resource(id, name));
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
