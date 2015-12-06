package tp9.model;

import java.util.ArrayList;
import java.util.List;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.User;

public class Twit {

	public final String text;
	public final String userName;
	public final String avatar;
	public final List<String> images = new ArrayList<>();

	public Twit() {
		text = "<no text>";
		userName = "<no user>";
		avatar = "";
	}

	public Twit(Status status) {
		text = status.getText();
		User u = status.getUser();
		userName = u.getName();
		avatar = u.getProfileImageURL();
		for (MediaEntity me : status.getMediaEntities())
			if (me.getType().equals("photo") || me.getType().equals("animated_gif"))
				images.add(me.getMediaURL());
	}
}
