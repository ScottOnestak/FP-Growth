import java.util.ArrayList;


public class Person {

	public int capitalGainInt, capitalLossInt;
	public String age, workclass, education, maritalStatus, occupation, relationship, race, hoursPerWeek, nativeCountry, capitalGain,
		capitalLoss, sex, income;
	public ArrayList<String> attributes = new ArrayList<String>();
	
	public Person(String age, String workclass, String education, String maritalStatus, String occupation, String relationship, 
			String race, String sex, int capitalGainInt, int capitalLossInt, String hoursPerWeek, String nativeCountry, String income){
		this.age = age;
		this.workclass = workclass;
		this.education = education;
		this.maritalStatus = maritalStatus;
		this.occupation = occupation;
		this.relationship = relationship;
		this.race = race;
		this.sex = sex;
		this.capitalGainInt = capitalGainInt;
		this.capitalGain = "";
		this.capitalLossInt = capitalLossInt;
		this.capitalLoss = "";
		this.hoursPerWeek = hoursPerWeek;
		this.nativeCountry = nativeCountry;
		this.income = income;
		}
	
	public void initializeArrayList(){
		attributes.add(age);
		attributes.add(workclass);
		attributes.add(education);
		attributes.add(maritalStatus);
		attributes.add(occupation);
		attributes.add(relationship);
		attributes.add(race);
		attributes.add(sex);
		attributes.add(capitalGain);
		attributes.add(capitalLoss);
		attributes.add(hoursPerWeek);
		attributes.add(nativeCountry);
		attributes.add(income);
	}
	
	public void printAttributes(){
		System.out.println(age + " " + workclass+ " " +education+ " " +maritalStatus+ " " +occupation+ " " +relationship+ " " +race+ 
				" " +sex+ " " +capitalGain+ " " +capitalLoss+ " " +hoursPerWeek+ " " +nativeCountry+ " " +income);
	}
	
}
