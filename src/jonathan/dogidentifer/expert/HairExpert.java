package jonathan.dogidentifer.expert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HairExpert implements Expert {
	private String prompt = "Hair Type";
	private List<String> possibleAnswerArr;
	private List<String> longhairDogs = new ArrayList<String>();
	private List<String> mediumhairDogs = new ArrayList<String>();
	private List<String> shorthairDogs = new ArrayList<String>();
	private String question = null;

	public HairExpert() {
		possibleAnswerArr = new ArrayList<String>();
		possibleAnswerArr.add("Long");
		possibleAnswerArr.add("Medium");
		possibleAnswerArr.add("Short");
		Properties props = new Properties();
		InputStream in = getClass().getResourceAsStream("dog.properties");
		try {
			props.load(in);
			String[] dogtype = {"longhairDogs", "mediumhairDogs", "shorthairDogs" };
			for (int i=0; i < dogtype.length; i++) {
				String dog = props.getProperty(dogtype[i]);
				String[] dogs = dog.split(",");
				List<String> d = null;
				if (dogtype[i].equals("longhairDogs")) {
					d = longhairDogs;
				} else if (dogtype[i].equals("mediumhairDogs")) {
					d = mediumhairDogs;
				} else if (dogtype[i].equals("shorthairDogs")) {
					d = shorthairDogs;
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
			if ("Long".equals(question)) {
				return longhairDogs;
			} else if ("Medium".equals(question)) {
				return mediumhairDogs;
			} else if ("Short".equals(question)) {
				return shorthairDogs;
			}

		}
		return null;
	}

}
