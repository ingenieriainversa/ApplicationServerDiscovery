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
	private static String wasHome;
	private static String outputFormat;
	private static CommandLineParser parser;
	private static CommandLine cmdLine;

	public static void main(String[] args) {

		// Set required options to null
		mode = null;
		wasHome = null;
		final String DEFAULT_FORMAT = "table";
		outputFormat = DEFAULT_FORMAT;

		// New instance of Options class
		Options options = new Options();

		// All options: name, alias, required and help text
		options.addOption("h", "help", false, "Print help");
		options.addOption("wasHome", true, "WAS installation path");
		options.addOption("mode", true, "Output mode");

		// Both options can not be displayed simultaneously.
		OptionGroup group = new OptionGroup();
		group.addOption(new Option("csv", "Output in CSV format"));
		group.addOption(new Option("table", "Output in table format"));
		options.addOptionGroup(group);

		try {
			parser = new DefaultParser();
			cmdLine = parser.parse(options, args);

			// Option -h or --help
			if (cmdLine.hasOption("h")) {
				new HelpFormatter().printHelp(Main.class.getCanonicalName(), options);
				return;
			}

			// Option -wasHome
			wasHome = cmdLine.getOptionValue("wasHome");
			if (wasHome == null) {
				throw new org.apache.commons.cli.ParseException("wasHome option is required");
			}

			// Option -mode
			mode = cmdLine.getOptionValue("mode");
			if (mode == null) {
				throw new org.apache.commons.cli.ParseException("mode option is required");
			}

			// Options -csv and -table for output format
			if (cmdLine.hasOption("csv")) {
				outputFormat = "csv";
			} else if (cmdLine.hasOption("table")) {
				outputFormat = "table";
			}

		} catch (org.apache.commons.cli.ParseException ex) {
			System.out.println(ex.getMessage());
			new HelpFormatter().printHelp(Main.class.getCanonicalName(), options);
			System.exit(1);
		} catch (java.lang.NumberFormatException ex) {
			new HelpFormatter().printHelp(Main.class.getCanonicalName(), options);
			System.exit(1);
		}

		// New instance of WasProductParser class
		wasProduct = new WasProductParser();
		// Parse WAS.product file
		wasProduct.parse(wasHome);
		// Get WAS product data
		wasProductData = wasProduct.getWasProduct();

		// New instance of ProfileRegistryParser class
		profileRegistryXml = new ProfileRegistryParser();
		// Parse profileRegistry.xml file
		profileRegistryXml.parse(wasHome);
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
				System.out.printf("%s;%s;%s;%s;%s;%s;%s\n",
				"Hostname", "Profile", "Cell", "Node", "Server name", "Server type", "Apps count");
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