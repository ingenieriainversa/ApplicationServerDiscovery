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

import was.Cluster;
import was.Node;
import was.Was;
import was.WasProductParser;
import was.WasProduct;
import was.Profile;
import was.ProfileRegistryParser;
import was.Resource;
import was.ResourcesXmlParser;
import was.Cell;
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
	private static ResourcesXmlParser resourcesXml;
	private static ArrayList<Jvm> jvms;
	private static ArrayList<Resource> resources;
	private static ArrayList<Cluster> clusters;
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
		Option opt_h = Option.builder("h").longOpt("help")
				.desc("Print this help.").build();

		Option opt_path = Option
				.builder("path")
				.desc("This parameter is required. Use it to specify WAS, JBoss or WebLogic "
						+ "installation path. For example:\n</opt/IBM/WebSphere/AppServer>")
				.required().hasArg().argName("install_home").build();

		Option opt_mode = Option
				.builder("mode")
				.desc("This parameter is required. Use it to specify the information to be printed. "
						+ "These are the arguments available for this option:\n"
						+ "<productData>    Print all product data.\n"
						+ "<profileList>    Print a profile list and data.\n"
						+ "<jvmList>        Print a JVM list and data.\n"
						+ "<endPointList>   Print a end point list and data.\n"
						+ "<resourcesList>  Print a resources list and data.")
				.required().hasArg().argName("argument").build();

		Option opt_csv = Option
				.builder("csv")
				.desc("This parameter is optional. Print output in CSV format.")
				.build();

		Option opt_table = Option
				.builder("table")
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
				throw new org.apache.commons.cli.ParseException(
						"path option is required.");
			}

			// Option -mode
			mode = cmdLine.getOptionValue("mode");
			if (mode == null) {
				throw new org.apache.commons.cli.ParseException(
						"mode option is required.");
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
				System.out.printf("%s;%s;%s;%s;%s;%s;%s;%s\n", "Server name",
						"Server type", "Hostname", "Profile", "Cell", "Node",
						"Apps count", "Cluster name");
			}

			// For each profile
			int profileIndex = 0;
			while (profileIndex < was.getProfiles().size()) {

				// Get the profile
				Profile profile = was.getProfiles().get(profileIndex);

				// Parse serverindex.xml and set de profile jvms
				setProfileJvms(profile);

				// Print the jvm data list
				profile.printJvmList(outputFormat);

				++profileIndex;
			}

		} else if (mode.equals("endPointList")) {
			// Print this header only if -csv option exist
			if (cmdLine.hasOption("csv")) {
				System.out.printf("%s;%s;%s;%s;%s;%s\n", "Hostname", "Server",
						"Server type", "Endpoint name", "Endpoint hostname",
						"Port");
			}

			// For each profile
			int profileIndex = 0;
			while (profileIndex < was.getProfiles().size()) {

				// Get the profile
				Profile profile = was.getProfiles().get(profileIndex);

				// Parse serverindex.xml and set de profile jvms
				setProfileJvms(profile);

				// Print the jvm data list
				profile.printJvmEndPointsData("all", outputFormat);

				++profileIndex;
			}
		} else if (mode.equals("resourcesList")) {
			// Print this header only if -csv option exist
			if (cmdLine.hasOption("csv")) {
				System.out
						.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
								"Profile", "Scope", "JDBC Provider id",
								"JDBC Provider name",
								"JDBC Provider description",
								"JDBC Provider type",
								"JDBC Provider isolated class loader",
								"JDBC Provider implementation class name",
								"XA", "Factory type", "Factory id",
								"Factory name", "Factory JNDI Name",
								"Factory Description", "Factory Provider type",
								"Factory auth mechanism preference",
								"Factory auth data alias",
								"Factory manage cached handles",
								"Factory log missing transaction context",
								"Factory diagnose connection usage",
								"Factory relational resource adapter",
								"Factory statement cache size",
								"Factory datasource helper classname", "URL");
			}

			// For each profile
			int profileIndex = 0;
			while (profileIndex < was.getProfiles().size()) {

				// Get the profile
				Profile profile = was.getProfiles().get(profileIndex);

				// New instance of ResourcesXmlParser class
				resourcesXml = new ResourcesXmlParser();
				
				
				/*
				 * Cell scope
				 */
				
				// Get Cell from profile
				Cell cell = profile.getCell();

				// Get the Cell resources.xml absolute path
				String cellResourcesXmlFile = cell.getResourcesXml();

				// Parse Cell resources.xml file
				resourcesXml.parse(cellResourcesXmlFile);

				// Get Cell resources ArrayList
				resources = resourcesXml.getResources();

				// Set the Resources ArrayList
				cell.setResources(resources);

				// Print the resources data list
				cell.printResourcesData(profile.getName(), outputFormat);
				
				
				/*
				 * Node scope
				 */
				
				// Get Node from profile
				Node node = profile.getNode();

				// Get the Node resources.xml absolute path
				String nodeResourcesXmlFile = node.getResourcesXml();

				// Parse Node resources.xml file
				resourcesXml.parse(nodeResourcesXmlFile);

				// Get Node resources ArrayList
				resources = resourcesXml.getResources();

				// Set the Resources ArrayList
				node.setResources(resources);

				// Print the resources data list
				node.printResourcesData(profile.getName(), outputFormat);
				
				
				/*
				 * Cluster scope
				 */
				
				// Parse serverindex.xml and set de profile jvms
				setProfileJvms(profile);

				// Set clusters from Cell
				profile.setCellClusters();

				// Get clusters from Cell
				clusters = cell.getClusters();

				int clustersIndex = 0;
				while (clustersIndex < clusters.size()) {

					// Get the cluster from ArrayList
					Cluster cluster = clusters.get(clustersIndex);

					// Get the cluster resources.xml absolute path
					String clusterResourcesXmlFile = cluster.getResourcesXml();

					// Parse cluster resources.xml file
					resourcesXml.parse(clusterResourcesXmlFile);

					// Get cluster resources ArrayList
					resources = resourcesXml.getResources();

					// Set the Resources ArrayList
					cluster.setResources(resources);

					// Print the resources data list
					cluster.printResourcesData(profile.getName(), outputFormat);

					++clustersIndex;
				}
				
				
				/*
				 * Server scope
				 */

				// Get jvms from profile
				jvms = profile.getJvms();

				int jvmsIndex = 0;
				while (jvmsIndex < jvms.size()) {

					// Get the Jvm from ArrayList
					Jvm jvm = jvms.get(jvmsIndex);
					
					if(!jvm.getName().equals("nodeagent")) {
						// Get the jvm resources.xml absolute path
						String jvmResourcesXmlFile = jvm.getResourcesXml();
	
						// Parse jvm resources.xml file
						resourcesXml.parse(jvmResourcesXmlFile);
	
						// Get jvm resources ArrayList
						resources = resourcesXml.getResources();
	
						// Set the Resources ArrayList
						jvm.setResources(resources);
	
						// Print the resources data list
						jvm.printResourcesData(profile.getName(), outputFormat);
					}

					++jvmsIndex;
				}

				++profileIndex;
			}
		}
	}

	public static void setProfileJvms(Profile profile) {
		// New instance of ServerindexParser class
		serverindexXml = new ServerindexParser();

		// Get the serverindex.xml absolute path
		String serverindexFile = profile.getServerindex();

		// Parse serverindex.xml file
		serverindexXml.parse(serverindexFile, profile.getNode());

		// Get Jvms ArrayList
		jvms = serverindexXml.getJvms();

		// Set the jvm ArrayList
		profile.setJvms(jvms);
	}
}
