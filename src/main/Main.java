/*
 * Application Server Discovery v0.01
 * Main.java
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

package main;

import java.util.ArrayList;

import was.Was;
import was.WasProductParser;
import was.WasProduct;
import was.Profile;
import was.ProfileRegistryParser;
import was.Jvm;
import was.ServerindexParser;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionGroup;

public class Main {
	private static Was was;
	private static WasProductParser wasProduct;
	private static WasProduct wasProductData;
	private static ProfileRegistryParser profileRegistryXml;
	private static ArrayList<Profile> profiles;
	private static ServerindexParser serverindexXml;
	private static ArrayList<Jvm> jvms;
	private static String mode;
	private static String path;
	private static String outputFormat;
	private static CommandLineParser parser;
	private static CommandLine cmdLine;

	public static void main(String[] args) {

		// Set required options to null
		mode = null;
		path = null;
		
		// Constant to set table as default value
		final String DEFAULT_FORMAT = "table";
		outputFormat = DEFAULT_FORMAT;

		// New instance of HelpFormatter class
		HelpFormatter formatter = new HelpFormatter();

		/*
		 * The formatter will skip sorting, and the arguments will be printed in
		 * the same order they were added.
		 */
		formatter.setOptionComparator(null);

		// All options: name, alias, required and help text
		Option opt_h = Option.builder("h").longOpt("help").desc("Print this help.").build();

		Option opt_path = Option.builder("path")
				.desc("This parameter is required. Use it to specify WAS, JBoss or WebLogic "
						+ "installation path. For example:\n</opt/IBM/WebSphere/AppServer>")
				.required().hasArg().argName("install_home").build();

		Option opt_mode = Option.builder("mode")
				.desc("This parameter is required. Use it to specify the information to be printed. "
						+ "These are the arguments available for this option:\n"
						+ "<productData>   Print all product data.\n"
						+ "<profileList>   Print a profile list and data.\n"
						+ "<jvmList>       Print a JVM list and data.")
				.required().hasArg().argName("argument").build();

		Option opt_csv = Option.builder("csv").desc("This parameter is optional. Print output in CSV format.").build();

		Option opt_table = Option.builder("table")
				.desc("This parameter is optional and set by default if you don't specify the "
						+ "ouput format. Print output in table format.")
				.build();

		// New instance of Options class
		Options options = new Options();
		
		// Add options to options object in order
		options.addOption(opt_h);
		options.addOption(opt_path);
		options.addOption(opt_mode);

		// New instance of OptionGroup class
		OptionGroup group = new OptionGroup();
		
		// Add options to group object in order
		group.addOption(opt_table);
		group.addOption(opt_csv);
		
		// Both options can not be displayed simultaneously.
		options.addOptionGroup(group);

		try {
			parser = new DefaultParser();
			cmdLine = parser.parse(options, args);

			// Option -h or --help
			if (cmdLine.hasOption("h")) {
				formatter.printHelp(Main.class.getCanonicalName(), options);
				return;
			}

			// Option -path
			path = cmdLine.getOptionValue("path");
			if (path == null) {
				throw new org.apache.commons.cli.ParseException("path option is required.");
			}

			// Option -mode
			mode = cmdLine.getOptionValue("mode");
			if (mode == null) {
				throw new org.apache.commons.cli.ParseException("mode option is required.");
			}

			// Options -csv and -table for output format
			if (cmdLine.hasOption("csv")) {
				outputFormat = "csv";
			} else if (cmdLine.hasOption("table")) {
				outputFormat = "table";
			}

		} catch (org.apache.commons.cli.ParseException ex) {
			System.out.println(ex.getMessage());
			formatter.printHelp(Main.class.getCanonicalName(), options);
			System.exit(1);
		} catch (java.lang.NumberFormatException ex) {
			formatter.printHelp(Main.class.getCanonicalName(), options);
			System.exit(1);
		}

		// New instance of WasProductParser class
		wasProduct = new WasProductParser();
		
		// Parse WAS.product file
		wasProduct.parse(path);
		
		// Get WAS product data
		wasProductData = wasProduct.getWasProduct();

		// New instance of ProfileRegistryParser class
		profileRegistryXml = new ProfileRegistryParser();
		
		// Parse profileRegistry.xml file
		profileRegistryXml.parse(path);
		
		// Get Profiles ArrayList
		profiles = profileRegistryXml.getProfiles();

		// New instance of Was class
		was = new Was(wasProductData, profiles);

		if (mode.equals("productData")) {

			// Print all product data
			was.printWasProductData(outputFormat);

		} else if (mode.equals("profileList")) {

			// Print a profile list
			was.printProfileList(outputFormat);

		} else if (mode.equals("jvmList")) {

			// Print this header only if -csv option exist
			if (cmdLine.hasOption("csv")) {
				System.out.printf("%s;%s;%s;%s;%s;%s;%s\n", "Hostname", "Profile", "Cell", "Node", "Server name",
						"Server type", "Apps count");
			}

			int profileIndex = 0;
			while (profileIndex < was.getProfiles().size()) {

				// Get the profile
				Profile profile = was.getProfiles().get(profileIndex);

				// New instance of ServerindexParser class
				serverindexXml = new ServerindexParser();

				// Get the serverindex.xml absolute path
				String serverindexFile = profile.getServerindex();

				// Parse serverindex.xml file
				serverindexXml.parse(serverindexFile);

				// Get Jvms ArrayList
				jvms = serverindexXml.getJvms();

				// For each profile set the jvm ArrayList
				profile.setJvms(jvms);

				// Print the jvm data list
				profile.printJvmList(outputFormat);

				++profileIndex;
			}

		}
	}
}