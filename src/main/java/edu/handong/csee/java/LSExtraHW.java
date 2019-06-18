package edu.handong.csee.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class LSExtraHW {
	String input;
	boolean lisFiles;
	boolean DoNotSort;
	boolean size;
	boolean help;
	boolean modificationTime;
	
	public void run(String[] args) throws IOException {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}
		}

			System.out.println("This is the input path(including the name of dicrectory that you want to search): "+input);
			System.out.println(" ");
			//error!! 
			
			if(input == null) {
				if (help) {
					printHelp(options);
				}
				System.out.println("Please fill out the option '-i'. It is required option!");
				System.exit(0);
			}
			
			File a = new File(input);
			ArrayList<String> filenames = new ArrayList<String>();
			ArrayList<String> filenames2 = new ArrayList<String>();
			ArrayList<Integer> filelength = new ArrayList<Integer>();
			ArrayList<String> time = new ArrayList<String>();
			ArrayList<String> start = new ArrayList<String>();
		
			//stupid..!! 
			int i = 0;
			for(File file: a.listFiles()) {
				if(file.isFile()) {
					filenames.add(file.getName());
					//System.out.println(filenames.get(i));
					i++;
				}
			}
		
		   
			if (DoNotSort) {
				System.out.println(input+" ls '-f' or 'DoNotSort' option");
				System.out.println(".");
				System.out.println("..");

				i = 0;
				for(File file: a.listFiles()) {
					if(file.isFile()) {
						filenames.add(file.getName());
						System.out.println(filenames.get(i));
						i++;
					}
				}
			
				System.out.println(" ");

			} 
		
		
			if (lisFiles) {
				System.out.println(input+" ls '-a' or 'lisFiles' option");
				System.out.println(".");
				System.out.println("..");
			
				Collections.sort(filenames);

				for(int j=0; j<filenames.size(); j++) {
					if(j%2==0) {
						System.out.println(filenames.get(j));
					}
				}
			

				System.out.println(" ");

			} 
		
			//five options -t sort the list of files by modification time. file.getmodified time. time 별로 sort
			if (modificationTime) {
				System.out.println(input+" ls '-t' or 'modification' option");
			
				//store the time as string to use collections
				int k=0;
				for (File file : a.listFiles()) {
					long modifiedtime = file.lastModified();
					start.add(Long.toString(modifiedtime));
					time.add(start.get(k));
					k++;
				}
			
				Collections.sort(time);

				for (int j = time.size()-1; j >= 0; j--) {
					for (File file : a.listFiles()) {
						long modifiedtime2 = file.lastModified();
						if(Long.parseLong(time.get(j)) == modifiedtime2) {
							System.out.println(file.getName());
						}
					}
				}
			
			System.out.println(" ");
			}
		
		
			if (size) {
				System.out.println(input+" ls '-h' or 'size' option");
				System.out.println(" ");
			
				Collections.sort(filenames);
			
			
				for(int j=0; j<filenames.size(); j = j+2) {
					for(File file: a.listFiles()) {
						if(file.getName().equals(filenames.get(j))) {
							filenames2.add(filenames.get(j));	
							filelength.add((int) file.length());
						}
					}
				}
			
				for(int j=0; j<filenames.size()/2; j++) {
					System.out.println("The size of "+filenames2.get(j)+":"+ filelength.get(j));
				}
			
				System.out.println(" ");
			}
			}
	
		
		
		
		

	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
			//input path of file
			input = cmd.getOptionValue("i");
			
			//-a lists all files in the given directory, including those whose names start with "." (which are hidden files in Unix).
			//By default, these files are excluded from the list.
			lisFiles = cmd.hasOption("a");
			
			//-f do not sort. Useful for directories containing large numbers of files.
			DoNotSort = cmd.hasOption("f");
			
			//-h print sizes in human readable format. 
			size = cmd.hasOption("h");
			
			//-H help
			help = cmd.hasOption("H");		
			
			//-t modification time 
			modificationTime = cmd.hasOption("t");		

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}



	//Definition Stage
		private Options createOptions() {
			Options options = new Options();
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("i").longOpt("input")
					.desc("Set an input file path")
					.hasArg()
					.argName("input path")
					.required()
					.build());

			// add options by using OptionBuilder
			options.addOption(Option.builder("a").longOpt("lisFiles")
					.desc("list all the files")
					.argName("lisFiles")
					.build());
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("f").longOpt("DoNotSort")
					.desc("do not sort")
					.argName("DoNotSort")
					.build());
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("h").longOpt("size")
					.desc("print sizes in human readable format")
					.argName("size")
					.build());
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("t").longOpt("modificationTime")
					.desc("psort the list of files by modification time.")
					.argName("modificationTime")
					.build());
						
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("H").longOpt("help")
			        .desc("Help")
			        .build());
			
			return options;
		}


	private void printHelp(Options options) {
			// automatically generate the help statement
			HelpFormatter formatter = new HelpFormatter();
			String header = "LSExtraHW";
			String footer = "" ; // Leave this empty.
			formatter.printHelp("LSExtraHW", header, options, footer, true);
	}

}






