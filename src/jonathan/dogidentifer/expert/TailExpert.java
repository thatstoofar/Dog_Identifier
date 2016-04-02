package jonathan.dogidentifer.expert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TailExpert implements Expert {
	
	private String prompt = "Tail Type";
	private List<String> possibleAnswerArr;
	private List<String> longTailDogs = new ArrayList<String>();
	private List<String>  shortTailDogs= new ArrayList<String>();
	private List<String> noneTailDogs = new ArrayList<String>();

	
	private String question = null;
	
	public TailExpert() {
		possibleAnswerArr = new ArrayList<String>();
		possibleAnswerArr.add("Long");
		possibleAnswerArr.add("Short");
		possibleAnswerArr.add("None");

		
		Properties props = new Properties();
		InputStream in = getClass().getResourceAsStream("dog.properties");
		try {
			props.load(in);
			String[] dogtype = {"longTailDogs", "shortTailDogs", "noneTailDogs"};
			for (int i=0; i < dogtype.length; i++) {
				String dog = props.getProperty(dogtype[i]);
				String[] dogs = dog.split(",");
				List<String> d = null;
				if (dogtype[i].equals("longTailDogs")) {
					d = longTailDogs;
				} else if (dogtype[i].equals("shortTailDogs")) {
					d =shortTailDogs;
				} else if (dogtype[i].equals("noneTailDogs")) {
					d = noneTailDogs;
				} 
				for (int j=0; j < dogs.length; j++) {
					d.add(dogs[j].trim());
				}
			}
				
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

	@Override
	public String getPrompt() {
		// TODO Auto-generated method stub
		return prompt;
	}

	@Override
	public List<String> getPossibleAnswers() {
		// TODO Auto-generated method stub
		return possibleAnswerArr;
	}

	@Override
	public void postQuestion(String question) {
		// TODO Auto-generated method stub
		this.question = question;


	}

	@Override
	public List<String> getPotentialHit() {
		if (question != null) {
			if ("Long".equals(question)) {
				return longTailDogs;
			} else if ("Short".equals(question)) {
				return shortTailDogs;
			} else if ("None".equals(question)) {
				return noneTailDogs;
			} 
		}
		return null;
	}

}
