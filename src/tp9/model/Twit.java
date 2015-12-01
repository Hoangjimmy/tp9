package tp9.model;

import twitter4j.Status;

public class Twit {
	public String text;
	
	public Twit(Status status) {
		text = status.getText();
	}
}
