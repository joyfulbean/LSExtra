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
	
	public void run(String[] args) throws IOException {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}
		}
		

		System.out.println("This is the input path(including the name of dicrectory that you want to search): "+input);
		
		File a = new File(input);
		ArrayList<String> filenames = new ArrayList<String>();
		
		int i = 0;
		for(File file: a.listFiles()) {
			if(file.isFile()) {
				filenames.add(file.getName());
				System.out.println(filenames.get(i));
				 i++;
			}
		}

		
		   // String tempFileName = toGet.getName();
		   // fileNames.add(tempFileName);
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
		
		
		
		if (size) {
			System.out.println(input+" ls '-h' or 'size' option");
			System.out.println(" ");
			//contians 등등!!!!!
			i = 0;
			for(File file: a.listFiles()) {
				System.out.println("The size of"+file.getName()+":"+ file.length());
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






