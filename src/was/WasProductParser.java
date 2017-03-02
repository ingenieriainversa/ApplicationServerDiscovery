/*
 * Application Server Discovery v0.03
 * WasProductParser.java
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

public class WasProductParser {
	private WasProduct wasProduct;
	private String id;
	private String version;
	private String date;
	private String level;

	public void parse(String was_home) {
		try {
			// Create a new DocumentBuilderFactory instance
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			// Set to false load external DTD
			dbf.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);

			// Create a new DocumentBuilder instance
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

			// Process the XML file and obtain the Document object
			Document doc = documentBuilder.parse(new InputSource(
					new FileInputStream(was_home
							+ "/properties/version/WAS.product")));

			// Get product root node
			Element productNode = doc.getDocumentElement();

			// Get name attribute from product node
			String name = productNode.getAttributes().getNamedItem("name")
					.getTextContent();

			// product children nodes iteration
			NodeList productChildNodes = productNode.getChildNodes();

			for (int i = 0; i < productChildNodes.getLength(); i++) {

				Node productChildNode = productChildNodes.item(i);

				if (productChildNode instanceof Element) {

					// Get child node name
					String productChildNodeName = productChildNode
							.getNodeName();

					if (productChildNodeName.equals("id")) {

						// Get text that id node contains
						id = productChildNode.getTextContent();

					}
					if (productChildNodeName.equals("version")) {

						// Get text that version node contains
						version = productChildNode.getTextContent();

					}
					if (productChildNodeName.equals("build-info")) {

						// Get text that version node contains
						date = productChildNode.getAttributes()
								.getNamedItem("date").getTextContent();

						// Get text that version node contains
						level = productChildNode.getAttributes()
								.getNamedItem("level").getTextContent();
					}

					wasProduct = new WasProduct(name, id, version, date, level);
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

	public WasProduct getWasProduct() {
		return wasProduct;
	}
}
