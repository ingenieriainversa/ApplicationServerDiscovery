/*
 * Application Server Discovery v0.03
 * App.java
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

public class App {
	private String name;
	
	/* App class constructor:
	 * @name: App name.
	 */
	public App(String name) {
		setName(name);
	}
	
	public String getName() {
		String[] ear = name.split("/");
		return ear[0];
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String printData() {
		return getName();
	}
}