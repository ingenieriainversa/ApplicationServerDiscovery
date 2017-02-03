/*
 * Application Server Discovery v0.01
 * EndPoint.java
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

public class EndPoint {
	private String endPointName;
	private String host;
	private String port;

	/*
	 * EndPoint class constructor:
	 * 
	 * @endPointName: EndPoint name.
	 * 
	 * @host: EndPoint host.
	 * 
	 * @port: EndPoint port.
	 */
	public EndPoint(String endPointName, String host, String port) {
		setEndPointName(endPointName);
		setHost(host);
		setPort(port);
	}

	public String getEndPointName() {
		return endPointName;
	}

	public void setEndPointName(String endPointName) {
		this.endPointName = endPointName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String printData() {
		return getEndPointName() + ";" + getHost() + ";" + getPort();
	}
}
