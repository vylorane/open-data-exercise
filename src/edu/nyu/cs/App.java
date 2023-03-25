package edu.nyu.cs;

// SEARCH UP HASH SET - TO FIND UNIQUE DATA 
// some basic java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;

import java.util.regex.Matcher;

// some imports used by the UnfoldingMap library
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.OpenStreetMap.*;
import de.fhpotsdam.unfolding.providers.MapBox;
import de.fhpotsdam.unfolding.providers.Google.*;
import de.fhpotsdam.unfolding.providers.Microsoft;
// import de.fhpotsdam.unfolding.utils.ScreenPosition;


/**
 * A program that pops open an interactive map.
 */
public class App extends PApplet {

	/****************************************************************/
	/*                  BEGIN - DON'T MODIFY THIS CODE              */
	/****************************************************************/
	UnfoldingMap map; // will be a reference to the actual map
	String mapTitle = "Title"; // will hold the title of the map
	final float SCALE_FACTOR = 0.0002f; // a factor used to scale pedestrian counts to calculate a reasonable radius for a bubble marker on the map
	final int DEFAULT_ZOOM_LEVEL = 11;
	final Location DEFAULT_LOCATION = new Location(40.7286683f, -73.997895f); // a hard-coded NYC location to start with
	String[][] data; // will hold data extracted from the CSV data file
	/****************************************************************/
	/*                    END - DON'T MODIFY THIS CODE              */
	/****************************************************************/

	/**
	 * This method is automatically called every time the user presses a key while viewing the map.
	 * The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * Complete the functions called from here, such that:
	 * 	- when the user presses the `1` key, the code calls the showMay2021MorningCounts method to show the morning counts in May 2021, with blue bubble markers on the map.
	 * 	- when the user presses the `2` key, the code calls the showMay2021EveningCounts method to show the evening counts in May 2021, with blue bubble markers on the map.
	 * 	- when the user presses the `3` key, the code calls the showMay2021EveningMorningCountsDifferencemethod to show the difference between the evening and morning counts in May 2021.  If the evening count is greater, the marker should be a green bubble, otherwise, the marker should be a red bubble.
	 * 	- when the user presses the `4` key, the code calls the showMay2021VersusMay2019Counts method to show the difference between the average of the evening and morning counts in May 2021 and the average of the evening and morning counts in May 2019.  If the counts for 2021 are greater, the marker should be a green bubble, otherwise, the marker should be a red bubble.
	 * 	- when the user presses the `5` key, the code calls the customVisualization1 method to show data of your choosing, visualized with marker types of your choosing.
	 * 	- when the user presses the `6` key, the code calls the customVisualization2 method to show data of your choosing, visualized with marker types of your choosing.
	 */
	public void keyPressed() {
		//char key = scnr.nextLine().charAt(0);
		// System.out.println("Key pressed: " + key);
		// complete this method

		if (key == '1'){
			System.out.println("Key pressed: " + key);
			showMay2021MorningCounts(data);

		} else if (key == '2'){
			showMay2021EveningCounts(data);
		} else if (key == '3'){
			showMay2021EveningMorningCountsDifference(data);
		} else if (key == '4'){
			showMay2021VersusMay2019Counts(data);
		} else if (key == '5'){
			customVisualization1(data);
		} else if (key == '6'){
			customVisualization2(data);
		}


	}

	/**
	 * Adds markers to the map for the morning pedestrian counts in May 2021.
	 * These counts are in the second-to-last field in the CSV data file.  So we look at the second-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021MorningCounts(String[][] data) {
		//clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Morning Pedestrian Counts";

		// complete this method - DELETE THE EXAMPLE CODE BELOW
		
		for (int i=0; i< data.length; i++){
			String strLat = data[i][0]; 
			float fltLat = Float.parseFloat(strLat);
			String strLng = data[i][1]; // longitude of a location of interest
			float fltLng = Float.parseFloat(strLng);
			Location markerLocation = new Location(fltLat, fltLng); // create a Location object
			String strPedCount = data[i][data[i].length-3];
			int pedestrianCount = Integer.parseInt(strPedCount); // an example pedestrian count (in reality, you will get these from a file)
			float markerRadius = pedestrianCount * SCALE_FACTOR; // scale down the marker radius to look better on the map
			float[] markerColor = {255, 0, 0, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
			MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); // don't worry about the `this` keyword for now... just make sure it's there.
			map.addMarker(marker);
			System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
		}


	}
		

	/**
	 * Adds markers to the map for the evening pedestrian counts in May 2021.
	 * These counts are in the second-to-last field in the CSV data file.  So we look at the second-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningCounts(String[][] data) {
		//clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Evening Pedestrian Counts";
		// complete this method
		for (int i=0; i< data.length; i++){
			String strLat = data[i][0]; 
			float fltLat = Float.parseFloat(strLat);
			String strLng = data[i][1]; // longitude of a location of interest
			float fltLng = Float.parseFloat(strLng);
			Location markerLocation = new Location(fltLat, fltLng); // create a Location object
			String strPedCount = data[i][data[i].length-3];
			int pedestrianCount = Integer.parseInt(strPedCount); // an example pedestrian count (in reality, you will get these from a file)
			float markerRadius = pedestrianCount * SCALE_FACTOR; // scale down the marker radius to look better on the map
			float[] markerColor = {100, 149, 237, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
			MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); // don't worry about the `this` keyword for now... just make sure it's there.
			map.addMarker(marker);
			System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
		}

	}

	/**
	 * Adds markers to the map for the difference between evening and morning pedestrian counts in May 2021.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningMorningCountsDifference(String[][] data) {
		//clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 Evening and Morning Pedestrian Counts";

		for (int i =0; i<data.length; i++){
			String strLat = data[i][0]; 
			float fltLat = Float.parseFloat(strLat);
			String strLng = data[i][1]; // longitude of a location of interest
			float fltLng = Float.parseFloat(strLng);
			Location markerLocation = new Location(fltLat, fltLng);

			String mornPop = data[i][data[i].length-3];
			int intMornPop = Integer.parseInt(mornPop);
			String nightPop = data[i][data[i].length-2];
			int intNightPop = Integer.parseInt(nightPop);

			int difference = 0;
			if (intMornPop > intNightPop){
				difference = intMornPop - intNightPop;
				int pedestrianCount = difference;
				float markerRadius = pedestrianCount * SCALE_FACTOR; 
				float[] markerColor = {0, 0, 139, 127}; 
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
				map.addMarker(marker);
				System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
			} else{
				difference = intNightPop - intMornPop;
				int pedestrianCount = difference;
				float markerRadius = pedestrianCount * SCALE_FACTOR; 
				float[] markerColor = {128, 0, 0, 127}; 
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
				map.addMarker(marker);
				System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
			}
		}
	}
		// complete this method

	/**
	 * Adds markers to the map for the difference between the average pedestrian count in May 2021 and the average pedestrian count in May 2019.
	 * Note that some pedestrian counts were not done in May 2019, and the data is blank.  
	 * Do not put a marker at those locations with missing data.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021VersusMay2019Counts(String[][] data) {
		//clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 and May 2019 Pedestrian Counts";
		for (int i = 0; i < data.length; i++){
			if (!data[i][data[i].length-1].contains(" ") && !data[i][data[i].length-2].contains(" ") && !data[i][data[i].length-3].contains(" ") && !data[i][data[i].length-9].contains(" ") && !data[i][data[i].length-8].contains(" ") && !data[i][data[i].length-7].contains(" ")){
				String strLat = data[i][0]; 
				float fltLat = Float.parseFloat(strLat);
				String strLng = data[i][1]; 
				float fltLng = Float.parseFloat(strLng);
				Location markerLocation = new Location(fltLat, fltLng);
				
				String strMorn21 = data[i][data[i].length-3];
				String strNight21 = data[i][data[i].length-2];
				String strWeekend21 = data[i][data[i].length-1];
				float fltMorn21 = Float.parseFloat(strMorn21);
				float fltNight21 = Float.parseFloat(strNight21);
				float fltWeekend21 = Float.parseFloat(strWeekend21);
				float avg21 = fltMorn21 + fltNight21 + fltWeekend21 / 3;

				String strMorn19 = data[i][data[i].length-9];
				String strNight19 = data[i][data[i].length-8];
				String strWeekend19 = data[i][data[i].length-7];
				float fltMorn19 = Float.parseFloat(strMorn19);
				float fltNight19 = Float.parseFloat(strNight19);
				float fltWeekend19 = Float.parseFloat(strWeekend19);
				float avg19 = fltMorn19 + fltNight19 + fltWeekend19 / 3;

				float difference = 0;
				if (avg21 > avg19){
					difference = avg21 - avg19;
					int pedestrianCount = (int) difference;
					float markerRadius = pedestrianCount * SCALE_FACTOR; 
					float[] markerColor = {250, 128, 114, 127}; 
					MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
					map.addMarker(marker);
					System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
				}else{
					difference = avg19 - avg21;
					int pedestrianCount = (int) difference;
					float markerRadius = pedestrianCount * SCALE_FACTOR; 
					float[] markerColor = {205, 113, 63, 127}; 
					MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
					map.addMarker(marker);
					System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
				}


			}
		}
	
	}


	private int extracted() {
		return 0;
	}

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization1(String[][] data) {
		//clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2018 and Sept 2018 Pedestrian Counts";
		for (int i = 0; i < data.length; i++){
			if (!data[i][data[i].length-1].contains(" ") && !data[i][data[i].length-2].contains(" ") && !data[i][data[i].length-3].contains(" ") && !data[i][data[i].length-9].contains(" ") && !data[i][data[i].length-8].contains(" ") && !data[i][data[i].length-7].contains(" ")){
				String strLat = data[i][0]; 
				float fltLat = Float.parseFloat(strLat);
				String strLng = data[i][1]; 
				float fltLng = Float.parseFloat(strLng);
				Location markerLocation = new Location(fltLat, fltLng);
				
				String strSeptMorn = data[i][data[i].length-12];
				String strSeptNight = data[i][data[i].length-11];
				String strSeptWeekend = data[i][data[i].length-10];
				float fltSeptMorn = Float.parseFloat(strSeptMorn);
				float fltSeptNight = Float.parseFloat(strSeptNight);
				float fltSeptWeekend = Float.parseFloat(strSeptWeekend);
				float avgSept = fltSeptMorn + fltSeptNight + fltSeptWeekend / 3;

				String strMayMorn = data[i][data[i].length-15];
				String strMayNight = data[i][data[i].length-14];
				String strMayWeekend = data[i][data[i].length-13];
				float fltMayMorn = Float.parseFloat(strMayMorn);
				float fltMayNight = Float.parseFloat(strMayNight);
				float fltMayWeekend = Float.parseFloat(strMayWeekend);
				float avgMay = fltMayMorn+ fltMayNight + fltMayWeekend / 3;

				float difference = 0;
				if (avgSept > avgMay){
					difference = avgSept - avgMay;
					int pedestrianCount = (int) difference;
					float markerRadius = pedestrianCount * SCALE_FACTOR; 
					float[] markerColor = {119, 21, 113, 127}; 
					MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
					map.addMarker(marker);
					System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
				}else{
					difference = avgMay - avgSept;
					int pedestrianCount = (int) difference;
					float markerRadius = pedestrianCount * SCALE_FACTOR; 
					float[] markerColor = {72, 61, 139, 127}; 
					MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
					map.addMarker(marker);
					System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
				}


			}
		}	
	}

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization2(String[][] data) {
		//clearMap(); // clear any markers previously placed on the map
		mapTitle = "Brooklyn May 2019 vs May 2021";
		for (int i=0; i< data.length; i++){
			if (data[i][2].equals("Brooklyn")){
				String strLat = data[i][0]; 
				float fltLat = Float.parseFloat(strLat);
				String strLng = data[i][1]; 
				float fltLng = Float.parseFloat(strLng);
				Location markerLocation = new Location(fltLat, fltLng); 

				String mayMorn21 = data[i][data[i].length-3];
				String mayNight21 = data[i][data[i].length-2];
				String mayWeekend21 = data[i][data[i].length-1];

				float fltMayMorn21 = Float.parseFloat(mayMorn21);
				float fltMayNight21 = Float.parseFloat(mayNight21);
				float fltMayWeekend21 = Float.parseFloat(mayWeekend21);

				float mayAvg21 = fltMayMorn21 + fltMayNight21 + fltMayWeekend21 / 3 ;

				String mayMorn19 = data[i][data[i].length-9];
				String mayNight19 = data[i][data[i].length-8];
				String mayWeekend19 = data[i][data[i].length-7];

				float fltMayMorn19 = Float.parseFloat(mayMorn19);
				float fltMayNight19 = Float.parseFloat(mayNight19);
				float fltMayWeekend19 = Float.parseFloat(mayWeekend19);

				float mayAvg19 = fltMayMorn19 + fltMayNight19 + fltMayWeekend19 / 3 ;

				if (mayAvg21 > mayAvg19){
					int pedestrianCount = (int) mayAvg21; 
					float markerRadius = pedestrianCount * SCALE_FACTOR; 
					float[] markerColor = {255, 0, 0, 127}; 
					MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
					map.addMarker(marker);
					System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
				}else{
					int pedestrianCount = (int) mayAvg21; 
					float markerRadius = pedestrianCount * SCALE_FACTOR; 
					float[] markerColor = {255, 0, 0, 127}; 
					MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); 
					map.addMarker(marker);
					System.out.printf("(%f,%f) => %f", fltLat, fltLng, pedestrianCount);
				}

			}
			
		}
	}

	/**
	 * Opens a file and returns an array of the lines within the file, as Strings with their line breaks removed.
	 * 
	 * @param filepath The filepath to open
	 * @return A String array, where each String contains the text of a line of the file, with its line break removed.
	 * @throws FileNotFoundException
	 */
	public String[] getLinesFromFile(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		Scanner scnr = new Scanner(file);
		StringBuffer buffTheStrings = new StringBuffer();
		while (scnr.hasNext()){
			buffTheStrings.append(scnr.nextLine());
			buffTheStrings.append("\n");
		}
		scnr.close();

		String stuff = buffTheStrings.toString();
		String[] linesss = stuff.split("\n");
		String[] lines = Arrays.copyOfRange(linesss, 1, linesss.length);

		//System.out.println(Arrays.toString(lines));

		return lines;

		// delete the two lines above... they are placeholder only
		// complete this method
	}

	/**
	 * Takes an array of lines of text in comma-separated values (CSV) format and splits each line into a sub-array of data fields.
	 * For example, an argument such as {"1,2,3", "100,200,300"} could result in a returned array { {"1", "2", "3"}, {"100", "200", "300"} }
	 * This method must skip any lines that don't contain mappable data (i.e. don't have any geospatial data in them) 
	 * and do any other cleanup of the data necessary for it to be easily mapped by other code in the program.
	 *
	 * @param lines A String array of lines of text, where each line is in comma-separated values (CSV) format.
	 * @return A two-dimensional String array, where each inner array contains the data from one of the lines, split by commas.
	 */
	public String[][] getDataFromLines(String[] lines) {
		// complete this method - DELETE THE EXAMPLE CODE BELOW
		String[] firstEdit = new String[lines.length];
			for(int i = 0; i< lines.length;i++){
				String currentRow = lines[i];
				String[] rowComponents = currentRow.replaceAll("\"","").split(",");
				String[] latAndLongOnly = rowComponents[0].replace("POINT (", "").replace(")", "").split(" ");
				String finalPoints = String.join(",", latAndLongOnly);
				rowComponents[0] = finalPoints;
				firstEdit[i] = String.join(",", rowComponents);

			}
	
			//make multidimentional array idk
			String[][] final2DArray = new String[firstEdit.length][];
			for (int i = 0; i < firstEdit.length; i++){
				final2DArray[i] = firstEdit[i].split(",");
			}
			//System.out.println(Arrays.deepToString(final2DArray));

	return final2DArray;
	}
				
		
		// hint: ultimately, you want this function to return data structured something like the following (you can structure your array differently if you prefer)
		// in this example, the geospatial Point data (latitude and longitude), which is one field in the original CSV file, has been broken up into two fields... you would be wise to do this too.
		String[][] allLines = { 
			// the first two values are the latitude and longitude... these were a single field in the original CSV file
			{"-73.90459140730678","40.87919896648574","Bronx","1","1","Broadway","West 231st Street","Naples Terrace","N","1189","4094","2508","734","2646","2939","802","4015","2631","1125","4310","3420","1001","3475","2832","991","4262","2469","1010","3609","3128","863","4119","2217","997","4440","2687","1328","3820","2428","1288","3328","3365","1268","4315","2276","1210","4710","3825","1206","4590","3008","1220","4384","2641","1450","4646","2996","1788","4980","3033","1204","4520","2999","1246","4531","2686","1309","3642","2830","1916","5893","2776","1111","4044","2731","1271","4502","2899","1708","4464","2967","","","","486","2843","1754","630","3262","4710"},
			{"-73.92188432870218","40.82662794123294","Bronx","2","2","East 161st Street","Grand Concourse","Sheridan Avenue","Y","1511","3184","1971","1855","3754","1183","1136","2638","1522","1939","3283","1383","1351","3111","1304","1227","3137","2762","2077","3283","1409","1007","3069","1477","1734","3333","1772","2051","3525","1752","1233","1875","1912","2113","4099","1970","2278","4215","2288","2071","3890","1832","2206","4363","2315","1949","4435","2388","2318","4589","2483","2005","4790","2512","2053","4721","2311","2109","5485","2548","1848","4920","2143","2389","5952","2832","1749","5148","2156","2006","4723","1604","1702","4347","1576","780","1892","1287","1405","2097","8410"},
			{"-73.92785197149036","40.80034506063933","Harlem River Bridges","113","113","Triborough Bridge (Manhattan span)","midpoint","","N","17","35","34","11","44","24","30","44","16","30","200","23","37","44","23","20","174","66","12","39","55","36","205","64","10","45","11","7","119","39","26","21","49","6","33","15","12","42","16","13","31","40","14","32","10","21","42","20","19","36","14","17","40","28","10","18","8","21","43","21","7","19","5","16","38","24","6","14","4","12","15","6","","","","","","","23","52","6437"},
			{"-73.93686603590555","40.78611224350854","Harlem River Bridges","114","114","Wards Island Bridge","midpoint","","N","57","207","71","63","186","149","45","203","113","80","190","120","33","213","324","43","151","173","37","169","674","77","205","913","32","66","70","62","189","936","78","249","439","102","460","569","191","455","435","92","514","594","164","527","312","123","458","564","189","539","312","117","424","581","160","484","300","159","490","587","169","493","312","178","519","608","187","543","351","213","490","263","","","","","","","237","405","6353"}
		};

	

		public void setuptext()  throws FileNotFoundException{
			String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
			String path = Paths.get(cwd, "data", "PedCountLocationsMay2015.csv").toString(); // e.g "data/PedCountLocationsMay2015.csv" on Mac/Unix vs. "data\PedCountLocationsMay2015.csv" on Windows
			String[] lines = getLinesFromFile(path); // get an array of the lines from the file.
			data = getDataFromLines(lines); // get a two-dimensional array of the data in these lines; complete the getDataFromLines method so the data from the file is returned appropriately
			// System.out.println(Arrays.deepToString(data)); // for debugging

		}

	/****************************************************************/
	/**** YOU WILL MOST LIKELY NOT NEED TO EDIT ANYTHING BELOW HERE */
	/****               Proceed at your own peril!                  */
	/****************************************************************/

	/**
	 * This method will be automatically called when the program runs
	 * Put any initial setup of the window, the map, and markers here.
	 */
	public void setup() {
		size(1200, 800, P2D); // set the map window size, using the OpenGL 2D rendering engine
		// size(1200, 800); // set the map window size, using Java's default rendering engine (try this if the OpenGL doesn't work for you)
		map = getMap(); // create the map and store it in the global-ish map variable

		// load the data from the file... you will have to complete the functions called to make sure this works
		try {
			setuptext();
			// automatically zoom and pan into the New York City location
			map.zoomAndPanTo(DEFAULT_ZOOM_LEVEL, DEFAULT_LOCATION);

			// by default, show markers for the morning counts in May 2021 (the third-to-last field in the CSV file)
			showMay2021MorningCounts(data);

			// various ways to zoom in / out
			// map.zoomLevelOut();
			// map.zoomLevelIn();
			// map.zoomIn();
			// map.zoomOut();

			// it's possible to pan to a location or a position on the screen
			// map.panTo(nycLocation); // pan to NYC
			// ScreenPosition screenPosition = new ScreenPosition(100, 100);
			// map.panTo(screenPosition); // pan to a position on the screen

			// zoom and pan into a location
			// int zoomLevel = 10;
			// map.zoomAndPanTo(zoomLevel, nycLocation);

			// it is possible to restrict zooming and panning
			// float maxPanningDistance = 30; // in km
			// map.setPanningRestriction(nycLocation, maxPanningDistance);
			// map.setZoomRange(5, 22);


		}
		catch (Exception e) {
			System.out.println("Error: could not load data from file: " + e);
		}

	} // setup

	/**
	 * Create a map using a publicly-available map provider.
	 * There will usually not be a need to modify this method. However, in some cases, you may need to adjust the assignment of the `map` variable.
	 * If there are error messages related to the Map Provider or with loading the map tile image files, try all of the other commented-out map providers to see if one works.
	 * 
	 * @return A map object.
	 */
	private UnfoldingMap getMap() {
		// not all map providers work on all computers.
		// if you have trouble with the one selected, try the others one-by-one to see which one works for you.
		map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		// map = new UnfoldingMap(this, new Microsoft.AerialProvider());
		// map = new UnfoldingMap(this, new GoogleMapProvider());
		// map = new UnfoldingMap(this);
		// map = new UnfoldingMap(this, new OpenStreetMapProvider());

		// enable some interactive behaviors
		MapUtils.createDefaultEventDispatcher(this, map);
		map.setTweening(true);
		map.zoomToLevel(DEFAULT_ZOOM_LEVEL);

		return map;
	}
	
	/**
	 * This method is called automatically to draw the map.
	 * This method is given to you.
	 * There is no need to modify this method.
	 */
	public void draw() {
		background(0);
		map.draw();
		drawTitle();
	}

	/**
	 * Clear the map of all markers.
	 * This method is given to you.
	 * There is no need to modify this method.
	 */
	public void clearMap() {
		map.getMarkers().clear();
	}

	/**
	 * Draw the title of the map at the bottom of the screen
	 */
	public void drawTitle() {
		fill(0);
		noStroke();
		rect(0, height-40, width, height-40); // black rectangle
		textAlign(CENTER);
		fill(255);
		text(mapTitle, width/2, height-15); // white centered text
	}

	/**
	 * The main method is automatically called when the program runs.
	 * This method is given to you.
	 * There is no need to modify this method.
	 * @param args A String array of command-line arguments.
	 */
	public static void main(String[] args) {
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			//PApplet.main("edu.nyu.cs.App"); // do not modify this!

			App app = new App();
			try {app.setuptext();}
			catch(Exception e) {
				System.out.println(e);
			}
			app.showMay2021EveningCounts(app.data);

		}
	}

}
