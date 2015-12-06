package tp9;

import tp9.model.TwitterModel;
import tp9.swingView.SwingTwitterView;
import twitter4j.TwitterException;

public class Tp9 {

	public static void main(String[] args) throws TwitterException {
		final ITwitterView view = new SwingTwitterView();
		final TwitterModel model = new TwitterModel();
		final TwitterController ctrl = new TwitterController(model, view);		
		view.run();
	}
}
