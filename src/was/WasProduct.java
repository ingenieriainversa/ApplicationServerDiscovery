/*
 * Application Server Discovery v0.01
 * WasProduct.java
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

public class WasProduct {
	private String name;
	private String id;
	private String version;
	private String date;
	private String level;

	/* WasProduct class constructor:
	 * @name: Was name.
	 * @id: Was id.
	 * @version: Was version.
	 * @date: Was build date.
	 * @level: Was build level.
	 */
	public WasProduct(String name, String id, String version, String date,
			String level) {
		setName(name);
		setId(id);
		setVersion(version);
		setDate(date);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void printWasData(String outputFormat) {
		if (outputFormat.equals("csv")) {
			System.out.printf("%s;%s;%s;%s;%s\n%s;%s;%s;%s;%s\n", "Name", "ID",
					"Version", "Date", "Level", getName(), getId(),
					getVersion(), getDate(), getLevel());
		} else if (outputFormat.equals("table")) {
			String width = "%-9.9s";
			System.out.printf(width + "%s\n" + width + "%s\n" + width + "%s\n"
					+ width + "%s\n" + width + "%s\n", "Name:", getName(),
					"ID:", getId(), "Version:", getVersion(), "Date:",
					getDate(), "Level:", getLevel());
		}
	}
}