package jonathan.dogidentifer.expert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SizeExpert implements Expert {

	private String prompt = "Size";
	private List<String> possibleAnswerArr;
	private List<String> gaintDogs = new ArrayList<String>();
	private List<String> largeDogs = new ArrayList<String>();
	private List<String> mediumDogs = new ArrayList<String>();
	private List<String> smallDogs = new ArrayList<String>();
	private String question = null;
	
	public SizeExpert() {
		possibleAnswerArr = new ArrayList<String>();
		possibleAnswerArr.add("Gaint");
		possibleAnswerArr.add("Large");
		possibleAnswerArr.add("Medium");
		possibleAnswerArr.add("Small");
		Properties props = new Properties();
		InputStream in = getClass().getResourceAsStream("dog.properties");
		try {
			props.load(in);
			String[] dogtype = {"giantDogs", "largeDogs", "mediumDogs", "smallDogs" };
			for (int i=0; i < dogtype.length; i++) {
				String dog = props.getProperty(dogtype[i]);
				String[] dogs = dog.split(",");
				List<String> d = null;
				if (dogtype[i].equals("giantDogs")) {
					d = gaintDogs;
				} else if (dogtype[i].equals("largeDogs")) {
					d = largeDogs;
				} else if (dogtype[i].equals("mediumDogs")) {
					d = mediumDogs;
				} else if (dogtype[i].equals("smallDogs")) {
					d = smallDogs;
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
		return prompt;
	}

	@Override
	public List<String> getPossibleAnswers() {
		return possibleAnswerArr;
	}

	@Override
	public void postQuestion(String question) {
		this.question = question;

	}

	@Override
	public List<String> getPotentialHit() {
		if (question != null) {
			if ("Gaint".equals(question)) {
				return gaintDogs;
			} else if ("Large".equals(question)) {
				return largeDogs;
			} else if ("Medium".equals(question)) {
				return mediumDogs;
			} else if ("Small".equals(question)) {
				return smallDogs;
			}
		}
		return null;
	}

}
