/*
 * Application Server Discovery v0.01
 * ProfileRegistryParser.java
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

public class ProfileRegistryParser {
	private static ArrayList<Profile> profiles;
	
	public void parse(String was_home) {
		profiles = new ArrayList<Profile>();
		
		try {
			// Create a new DocumentBuilder instance
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			// Process the XML file and obtain the Document object
			Document doc = documentBuilder.parse(new InputSource(new FileInputStream(was_home+"/properties/profileRegistry.xml")));
			
			// Get profiles root node
			Element profilesNode = doc.getDocumentElement();
			
			// profiles children nodes iteration
			NodeList profilesChildNodes = profilesNode.getChildNodes();
			for (int i = 0; i < profilesChildNodes.getLength(); i++) {
				Node profile = profilesChildNodes.item(i);
				if (profile instanceof Element) {
					// Get isAReservationTicket attribute from profile node
					String isAReservationTicket = profile.getAttributes().getNamedItem("isAReservationTicket").getTextContent();
					
					// Get isDefault attribute from profile node
					String isDefault = profile.getAttributes().getNamedItem("isDefault").getTextContent();
					
					// Get name attribute from profile node
					String name = profile.getAttributes().getNamedItem("name").getTextContent();
					
					// Get path attribute from profile node
					String path = profile.getAttributes().getNamedItem("path").getTextContent();
					
					// Get template attribute from profile node
					String template = profile.getAttributes().getNamedItem("template").getTextContent();
					
					// Add profile to ArrayList<Profile>
					profiles.add(new Profile(isAReservationTicket, isDefault, name, path, template));
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
	
	public ArrayList<Profile> getProfiles() {
		return profiles;
	}
}