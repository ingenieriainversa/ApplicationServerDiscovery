/* Application Server Discovery v0.03
 * ServerXmlParser.java
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ServerXmlParser {
	private static String clusterName;

	public void parse(String serverXmlFile) {
		clusterName = null;
		
		try {

			// Create a new DocumentBuilder instance
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();

			// Process the XML file and obtain the Document object
			Document doc = documentBuilder.parse(new InputSource(
					new FileInputStream(serverXmlFile)));

			// Get server.xml root node
			Element serverXMLNode = doc.getDocumentElement();
			
			NamedNodeMap serverXMLNodeAttrs = serverXMLNode
					.getAttributes();
			
			for (int i = 0; i < serverXMLNodeAttrs.getLength(); i++) {
				Attr attribute = (Attr) serverXMLNodeAttrs.item(i);
				
				// Get clusterName attribute from server.xml root node
				if (attribute.getName().equals("clusterName")) {
					clusterName = attribute.getValue();
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

	public String getClusterName() {
		return clusterName;
	}

}
