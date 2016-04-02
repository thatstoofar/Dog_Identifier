package jonathan.dogidentifer.expert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EarExpert implements Expert {
	private String prompt = "Ear Type";
	private List<String> possibleAnswerArr;
	private List<String> floppyEarDogs = new ArrayList<String>();
	private List<String> foldedEarDogs = new ArrayList<String>();
	private List<String> pointyEarDogs = new ArrayList<String>();
	private String question = null;

	public EarExpert() {
		possibleAnswerArr = new ArrayList<String>();
		possibleAnswerArr.add("Floppy");
		possibleAnswerArr.add("Folded");
		possibleAnswerArr.add("Pointy");

		
		Properties props = new Properties();
		InputStream in = getClass().getResourceAsStream("dog.properties");
		try {
			props.load(in);
			String[] dogtype = {"floppyEarDogs", "foldedEarDogs", "pointyEarDogs"};
			for (int i=0; i < dogtype.length; i++) {
				String dog = props.getProperty(dogtype[i]);
				String[] dogs = dog.split(",");
				List<String> d = null;
				if (dogtype[i].equals("floppyEarDogs")) {
					d = floppyEarDogs;
				} else if (dogtype[i].equals("foldedEarDogs")) {
					d =foldedEarDogs;
				} else if (dogtype[i].equals("pointyEarDogs")) {
					d = pointyEarDogs;
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
		// TODO Auto-generated method stub
		if (question != null) {
			if ("Floppy".equals(question)) {
				return floppyEarDogs;
			} else if ("Folded".equals(question)) {
				return foldedEarDogs;
			} else if ("Pointy".equals(question)) {
				return pointyEarDogs;
			} 
		}
		return null;
	}

}
