package tp9.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import tp9.swingView.ImageLoader;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.User;

public class Twit {

	public final String text;
	public final String userName;
	public final ImageIcon avatar;
	public final List<ImageIcon> images = new ArrayList<>();

	public Twit(Status status) {
		text = status.getText();
		User u = status.getUser();
		userName = u.getName();
		avatar = ImageLoader.load(u.getProfileImageURL());
		for (MediaEntity me : status.getMediaEntities())
			if (me.getType().equals("photo") || me.getType().equals("animated_gif"))
				images.add(ImageLoader.load(me.getMediaURL()));
	}
}
