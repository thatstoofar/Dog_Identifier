package jonathan.dogidentifer.expert;

import java.util.List;
/**
 * 
 * 
 * @author Jonathan
 *
 */
public interface Expert {
	
	public String getPrompt();
	public List<String> getPossibleAnswers();
	
	public void postQuestion(String question);
	public List<String> getPotentialHit();
	
	
}
