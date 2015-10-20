import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FPGrowthTest {

	public static final double minSupport = .3;
	public static int a,c,e,k,l,m,capitalGainMedian,capitalLossMedian,count;
	public static String container,storage,b,d,f,g,h,i,n,j,o,aholder,cholder,kholder,lholder,mholder;
	public static String[] holder;
	public static Map<Double,String> candSetSort = new TreeMap<Double,String>();
	public static Map<Integer,String> candSort = new HashMap<Integer,String>();
	public static Map<String, Double> candSet = new HashMap<String,Double>();
	public static List<Map<String,Integer>> theLists = new ArrayList<>();
	public static Map<Integer,Person> database = new HashMap<Integer,Person>();
	public static Map<String,Integer> supportAge = new HashMap<String,Integer>();
	public static Map<String,Integer> supportWorkClass = new HashMap<String,Integer>();
	public static Map<String,Integer> supportEducation = new HashMap<String,Integer>();
	public static Map<String,Integer> supportMaritalStatus = new HashMap<String,Integer>();
	public static Map<String,Integer> supportOccupation = new HashMap<String,Integer>();
	public static Map<String,Integer> supportRelationship = new HashMap<String,Integer>();
	public static Map<String,Integer> supportRace = new HashMap<String,Integer>();
	public static Map<String,Integer> supportSex = new HashMap<String,Integer>();
	public static ArrayList<Integer> capitalGainValues = new ArrayList<Integer>();
	public static ArrayList<Integer> capitalGainNonZero = new ArrayList<Integer>();
	public static Map<String,Integer> supportCapitalGain = new HashMap<String,Integer>();
	public static ArrayList<Integer> capitalLossNonZero = new ArrayList<Integer>();
	public static ArrayList<Integer> capitalLossValues = new ArrayList<Integer>();
	public static Map<String,Integer> supportCapitalLoss = new HashMap<String,Integer>();
	public static Map<String,Integer> supportHoursPerWeek = new HashMap<String,Integer>();
	public static Map<String,Integer> supportNativeCountry = new HashMap<String,Integer>();
	public static Map<String,Integer> supportIncome = new HashMap<String,Integer>();
	
	public static void main(String[] args) throws Exception {
		
		//start the timer
		long startTime = System.currentTimeMillis();
		
		String testFile = "adult_data.txt";
		
		//create try...catch for reading in data through buffered reader - like so many projects before
		try{
			//create buffered reader
			BufferedReader read = new BufferedReader(new FileReader(testFile));
			
			//initializeCount
			count = 0;
			
			//read in first line
			container = read.readLine();
			
			//while the file has another line, continue the while... loop
			while(container != null){
				
				//remove all blank spaces
				storage = container.replaceAll(" ", "");
				
				//split on ,
				holder = storage.split(",");
				
				//age variable
				a = Integer.parseInt(holder[0]);
				
				//classify the integers into intervals to calculate support
				//4 categories... <=25 (young), 25 to 46 (middle-aged), 46 to 65 (getting ready to retire), 65+ (retired)
				if(a <= 25){
					aholder = "Group<=25";
					if(supportAge.containsKey("Group<=25")){
						int value = supportAge.get("Group<=25");
						supportAge.replace("Group<=25", value + 1);
					} else {
						supportAge.put("Group<=25", 1);
					}
				} else if(a <= 45){
					aholder = "Group26to45";
					if(supportAge.containsKey("Group26to45")){
						int value = supportAge.get("Group26to45");
						supportAge.replace("Group26to45", value + 1);
					} else {
						supportAge.put("Group26to45", 1);
					}
				} else if(a <= 65){
					aholder = "Group46to65";
					if(supportAge.containsKey("Group46to65")){
						int value = supportAge.get("Group46to65");
						supportAge.replace("Group46to65", value + 1);
					} else {
						supportAge.put("Group46to65", 1);
					}
				} else if (a > 65){
					aholder = "Group>=65";
					if(supportAge.containsKey("Group>=65")){
						int value = supportAge.get("Group>=65");
						supportAge.replace("Group>=65", value + 1);
					} else {
						supportAge.put("Group>=65", 1);
					}
				} else {
					aholder = "MissingDataPoint";
					if(supportAge.containsKey("MissingDataPoint")){
						int value = supportAge.get("MissingDataPoint");
						supportAge.replace("MissingDataPoint", value + 1);
					}
				}
				
				//workclass variable
				b = holder[1];
				
				//create appropriate arraylist and hashmap for values
				if(supportWorkClass.containsKey(holder[1])){
					int value = supportWorkClass.get(holder[1]);
					supportWorkClass.replace(holder[1], value + 1);
				} else {
					supportWorkClass.put(holder[1], 1);
				}
				
				//fnlwgt variable ... sample weight, unimportant to algorithm
				//c = Integer.parseInt(holder[2]);
				
				//education variable
				d = holder[3];
				
				if(supportEducation.containsKey(holder[3])){
					int value = supportEducation.get(holder[3]);
					supportEducation.replace(holder[3], value + 1);
				} else {
					supportEducation.put(holder[3], 1);
				}
				
				//education - num ... redundant variable from education
				//e = Integer.parseInt(holder[4]);
				
				//marital status variable
				f = holder[5];
				
				if(supportMaritalStatus.containsKey(holder[5])){
					int value = supportMaritalStatus.get(holder[5]);
					supportMaritalStatus.replace(holder[5], value + 1);
				} else {
					supportMaritalStatus.put(holder[5], 1);
				}
				
				//occupation variable
				g = holder[6];
				
				if(supportOccupation.containsKey(holder[6])){
					int value = supportOccupation.get(holder[6]);
					supportOccupation.replace(holder[6], value + 1);
				} else {
					supportOccupation.put(holder[6], 1);
				}
				
				//relationship variable
				h = holder[7];
				
				if(supportRelationship.containsKey(holder[7])){
					int value = supportRelationship.get(holder[7]);
					supportRelationship.replace(holder[7], value + 1);
				} else {
					supportRelationship.put(holder[7], 1);
				}
				
				//race variable
				i = holder[8];
				
				if(supportRace.containsKey(holder[8])){
					int value = supportRace.get(holder[8]);
					supportRace.replace(holder[8], value + 1);
				} else {
					supportRace.put(holder[8], 1);
				}
				
				//sex variable
				j = holder[9];
				
				if(supportSex.containsKey(holder[9])){
					int value = supportSex.get(holder[9]);
					supportSex.replace(holder[9], value + 1);
				} else {
					supportSex.put(holder[9], 1);
				}
				
				//capital-gain variable
				k = Integer.parseInt(holder[10]);
				
				capitalGainValues.add(k);
				
				if(k != 0){
					capitalGainNonZero.add(k);
				}
				
				//capital-loss variable
				l = Integer.parseInt(holder[11]);
				
				capitalLossValues.add(l);
				
				if(l != 0){
					capitalLossNonZero.add(l);
				}
				
				//hours-per-week variable
				m = Integer.parseInt(holder[12]);
				
				//4 categories - Part-time (<=25), Full-time (26 to 40), Overtime (41 to 60), and the Over-Overtimes (60+)
				if(m <= 25){
					mholder = "Part-time";
					if(supportHoursPerWeek.containsKey("Part-time")){
						int value = supportHoursPerWeek.get("Part-time");
						supportHoursPerWeek.replace("Part-time", value + 1);
					} else {
						supportHoursPerWeek.put("Part-time", 1);
					}
				} else if(m <= 40){
					mholder = "Full-time";
					if(supportHoursPerWeek.containsKey("Full-time")){
						int value = supportHoursPerWeek.get("Full-time");
						supportHoursPerWeek.replace("Full-time", value + 1);
					} else {
						supportHoursPerWeek.put("Full-time", 1);
					}
				} else if(m <= 60){
					mholder = "Overtime";
					if(supportHoursPerWeek.containsKey("Overtime")){
						int value = supportHoursPerWeek.get("Overtime");
						supportHoursPerWeek.replace("Overtime", value + 1);
					} else {
						supportHoursPerWeek.put("Overtime", 1);
					}
				} else if (m > 60){
					mholder = "60+hours";
					if(supportHoursPerWeek.containsKey("60+hours")){
						int value = supportHoursPerWeek.get("60+hours");
						supportHoursPerWeek.replace("60+hours", value + 1);
					} else {
						supportHoursPerWeek.put("60+hours", 1);
					}
				} else {
					mholder = "MissingDataValue";
					if(supportHoursPerWeek.containsKey("MissingDataValue")){
						int value = supportHoursPerWeek.get("MissingDataValue");
						supportHoursPerWeek.replace("MissingDataValue", value + 1);
					} else {
						supportHoursPerWeek.put("MissingDataValue", 1);
					}
				}
				
				//native-country variable
				n = holder[13];
				
				if(supportNativeCountry.containsKey(holder[13])){
					int value = supportNativeCountry.get(holder[13]);
					supportNativeCountry.replace(holder[13], value + 1);
				} else {
					supportNativeCountry.put(holder[13], 1);
				}
				
				//income variable
				o = holder[14];
				
				if(supportIncome.containsKey(holder[14])){
					int value = supportIncome.get(holder[14]);
					supportIncome.replace(holder[14], value + 1);
				} else {
					supportIncome.put(holder[14], 1);
				}
				
				//create person variable to store information for further run through database
				Person person = new Person(aholder,b,d,f,g,h,i,j,k,l,mholder,n,o);
				database.put(count, person);
				
				//increment count and read the next line if one
				count++;
				container = read.readLine();
			}
			//close the buffered reader
			read.close();
			

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + testFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}

		//calculate capital gain non zero median
		Collections.sort(capitalGainNonZero);
				
		if(capitalGainNonZero.size() % 2 == 0){
			capitalGainMedian = (capitalGainNonZero.get(capitalGainNonZero.size()/2) + 
					capitalGainNonZero.get(capitalGainNonZero.size()/2 + 1)) / 2;
		} else {
			capitalGainMedian = capitalGainNonZero.get(capitalGainNonZero.size() / 2 + 1);
		}
		
		//sort values into their correct categories based on median calculation
		for(int i = 0; i < capitalGainValues.size(); i++){
			if(capitalGainValues.get(i).equals(0)){
				if(database.get(i).capitalGainInt == 0)
					database.get(i).capitalGain = "0CapitalGain";
				if(supportCapitalGain.containsKey("0CapitalGain")){
					int value = supportCapitalGain.get("0CapitalGain");
					supportCapitalGain.replace("0CapitalGain", value + 1);
				} else {
					supportCapitalGain.put("0CapitalGain", 1);
				}
			} else if(capitalGainValues.get(i).compareTo(capitalGainMedian) < 0){
				if(database.get(i).capitalGainInt < capitalGainMedian)
					database.get(i).capitalGain = "LessThanMedianCapitalGain";
				if(supportCapitalGain.containsKey("LessThanMedianCapitalGain")){
					int value = supportCapitalGain.get("LessThanMedianCapitalGain");
					supportCapitalGain.replace("LessThanMedianCapitalGain", value + 1);
				} else {
					supportCapitalGain.put("LessThanMedianCapitalGain", 1);
				}
			} else {
				if(database.get(i).capitalGainInt >= capitalGainMedian)
					database.get(i).capitalGain = "GreaterThanOrEqualToMedianCapitalGain";
				if(supportCapitalGain.containsKey("GreaterThanOrEqualToMedianCapitalGain")){
					int value = supportCapitalGain.get("GreaterThanOrEqualToMedianCapitalGain");
					supportCapitalGain.replace("GreaterThanOrEqualToMedianCapitalGain", value + 1);
				} else {
					supportCapitalGain.put("GreaterThanOrEqualToMedianCapitalGain", 1);
				}
			}
		}
		
		//calculate capital loss non zero median
		Collections.sort(capitalLossNonZero);
		
		if(capitalLossNonZero.size() % 2 == 0){
			capitalLossMedian = (capitalLossNonZero.get(capitalLossNonZero.size()/2) + 
					capitalLossNonZero.get(capitalLossNonZero.size()/2 + 1)) / 2;
		} else {
			capitalLossMedian = capitalLossNonZero.get(capitalLossNonZero.size() / 2 + 1);
		}
		
		//sort values into their correct categories based on median calculation and initialize the ArrayList of strings to use later
		for(int i = 0; i < capitalLossValues.size(); i++){
			if(capitalLossValues.get(i).equals(0)){
				if(database.get(i).capitalLossInt == 0)
					database.get(i).capitalLoss = "0CapitalLoss";
				database.get(i).initializeArrayList();
				if(supportCapitalLoss.containsKey("0CapitalLoss")){
					int value = supportCapitalLoss.get("0CapitalLoss");
					supportCapitalLoss.replace("0CapitalLoss", value + 1);
				} else {
					supportCapitalLoss.put("0CapitalLoss", 1);
				}
			} else if(capitalLossValues.get(i).compareTo(capitalLossMedian) < 0){
				if(database.get(i).capitalLossInt < capitalLossMedian)
					database.get(i).capitalLoss = "LessThanMedianCapitalLoss";
				database.get(i).initializeArrayList();
				if(supportCapitalLoss.containsKey("LessThanMedianCapitalLoss")){
					int value = supportCapitalLoss.get("LessThanMedianCapitalLoss");
					supportCapitalLoss.replace("LessThanMedianCapitalLoss", value + 1);
				} else {
					supportCapitalLoss.put("LessThanMedianCapitalLoss", 1);
				}
			} else {
				if(database.get(i).capitalLossInt >= capitalLossMedian)
					database.get(i).capitalLoss = "GreaterThanOrEqualToMedianCapitalLoss";
				database.get(i).initializeArrayList();
				if(supportCapitalLoss.containsKey("GreaterThanOrEqualToMedianCapitalLoss")){
					int value = supportCapitalLoss.get("GreaterThanOrEqualToMedianCapitalLoss");
					supportCapitalLoss.replace("GreaterThanOrEqualToMedianCapitalLoss", value + 1);
				} else {
					supportCapitalLoss.put("GreaterThanOrEqualToMedianCapitalLoss", 1);
				}
			}
		}
		
		//add all the ArrayLists to a bigger array to iterate through
		theLists.add(supportAge);
		theLists.add(supportWorkClass);
		theLists.add(supportEducation);
		theLists.add(supportMaritalStatus);
		theLists.add(supportOccupation);
		theLists.add(supportRelationship);
		theLists.add(supportRace);
		theLists.add(supportSex);
		theLists.add(supportCapitalGain);
		theLists.add(supportCapitalLoss);
		theLists.add(supportHoursPerWeek);
		theLists.add(supportNativeCountry);
		theLists.add(supportIncome);
		
		//iterate through and calculate support
		for(int i = 0; i < theLists.size();i++){
			//learned iterator from... http://java67.blogspot.com/2013/08/best-way-to-iterate-over-each-entry-in.html
			for(Map.Entry<String, Integer> entry : theLists.get(i).entrySet()){
				double support = support(entry.getValue());
				if(!entry.getKey().equals("?") && support > minSupport){
					candSetSort.put(support,entry.getKey());
					candSet.put(entry.getKey(), support);
				}
		    }
		}
		
		int length = candSet.size() - 1;
		
		//sort the variables into most ordering and store in the rankings array
		ArrayList<String> rankings = new ArrayList<String>();
		
		for(Map.Entry<Double, String> entry : candSetSort.entrySet()){
			rankings.add(0,entry.getValue());
			candSort.put(length,entry.getValue());
			length--;
		}
		
		//create new tree for all the frequent data attributes
		ImplementFPGrowth<String> tree = new ImplementFPGrowth<String>();
		//create map to store all the node-links, passed back after the implementation of the tree
		Map<String,ArrayList<TreeNode<String>>> nodes = new HashMap<String,ArrayList<TreeNode<String>>>();
		
		//insert each item in the database into the tree
		for(int i = 0; i < database.size(); i++){
			nodes = tree.insertPrep(database.get(i).attributes, rankings);
		}
		
		//calculate the minimum number of observations to meet the minimum support threshold
		int minCount = (int) (minSupport*count);
		
		//for each of the rankings, start with the lowest frequency
		for(int i = rankings.size() - 1; i >= 0; i--){
			Map<ArrayList<String>,Integer> condFPtree = new HashMap<ArrayList<String>,Integer>();
			//if the nodes contain the frequent item
			if(nodes.containsKey(rankings.get(i))){
				//sum calculates the total for each of the node sets...if 2 I1s, calculate the sum and see if frequent
				int sum = 0;
				//for each of the nodes
				for(int j = 0; j < nodes.get(rankings.get(i)).size(); j++){
					//stores the path
					ArrayList<String> thePath = new ArrayList<String>();
					//increase sum
					sum += nodes.get(rankings.get(i)).get(j).count;
					//set treeNode to parent to get path in order to construct the tree with the conditional pattern base
					TreeNode<String> current = nodes.get(rankings.get(i)).get(j).parent;
					//while not the root of the tree, go up the tree and get the path
					while(!current.name.equals("root")){
						thePath.add(0,current.name);
						current = current.parent;
					}
					//add to conditional pattern base
					condFPtree.put(thePath, nodes.get(rankings.get(i)).get(j).count);
				}
				//if the sum is greater than the minimum threshold do
				//if not, then it is not frequent, so there is no reason to construct the tree
				if(sum >= minCount){
					System.out.println("\n----Frequent Patterns for Item: " + rankings.get(i) + "----" + "\n");
					
					//create new tree and return the nodeLinks
					ImplementFPGrowth<String> tree2 = new ImplementFPGrowth<String>();			
					Map<String,ArrayList<TreeNode<String>>> conditionalFPtree = tree2.constructFPTree(condFPtree);
					
					//for each in the conditional pattern base
					for(Map.Entry<String,ArrayList<TreeNode<String>>> entry: conditionalFPtree.entrySet()){
						//keep total the same way as sum before... for each of the nodeLinks
						int total = 0;
						//for each of the treeNodes
						for(int k = 0; k < entry.getValue().size(); k++){
							//increase total
							total += entry.getValue().get(k).count;
							//if that individual nodeLink has a count greater than minCount, then its path is a frequent set
							if(entry.getValue().get(k).count >= minCount){
								//generate path same as before
								ArrayList<String> nodePath = new ArrayList<String>();
								nodePath.add(entry.getValue().get(k).name);
								TreeNode<String> current = entry.getValue().get(k).parent;
								while(!current.name.equals("root")){
									nodePath.add(current.name);
									current = current.parent;
								}
								
								//print out path and support level
								for(int m = 0; m < nodePath.size(); m++){
									System.out.print(nodePath.get(m) + " ");
								}
								System.out.print(rankings.get(i) + " " + (double) entry.getValue().get(k).count/count +"\n");
							}						
						}
						//if the nodeLink has a size greater than 1 (avoid redundancies) and total is greater than minCount...
						//...print out the set and its support
						if(entry.getValue().size() >1 && total >= minCount){
							System.out.println(entry.getKey() + " " + rankings.get(i) + ": " + (double) total/count);
						}
					}
					//know the variable is frequent so print out its frequency
					System.out.println(rankings.get(i) + ": " + (double) sum/count);
				}	
			}
		}
		
		//print out the time it took to run the algorithm
		long finishTime = System.currentTimeMillis();
		long elapsedTime = finishTime - startTime;
			
		System.out.println("\nThe FP-Growth Algorithm took " + elapsedTime + " milliseconds for a minimum support of " + minSupport);
		
	}
	
	//calculate the support... # of observations that contain the attribute(s) / the total # of observations
	public static double support(int x){
		return (double) x / (double) count;
	}
}
