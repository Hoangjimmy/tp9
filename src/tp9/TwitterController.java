package tp9;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import tp9.model.TwitterModel;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterController implements Observer {

	private final TwitterModel _model;
	private final ITwitterView _view;
	private final Twitter _twitter;

	public TwitterController(TwitterModel model, ITwitterView view) throws TwitterException {
		_model = model;
		_view = view;

		_model.addObserver(this);
		_view.setController(this);

		_twitter = new TwitterFactory("hP49CWN0KuutGM6PCn7pfVk7P", "vQXz8BZ1tKBiSx1YTRwlkzT0Tfqf72hTdTSHv59hw5T1WFc0V8").createTwitter();
	}

	@Override
	public void update(Observable o, Object arg) {
		assert o instanceof TwitterModel;
		
		_view.notifyModelChanged(_model);
	}

	public void loadTwits(List<String> users, List<String> hashtags, List<String> keywords) {
		StringBuilder sb = new StringBuilder();
		for(String user : users)
			sb.append(" @").append(user);
		for(String hashtag : hashtags)
			sb.append(" #").append(hashtag);
		for(String keyword : keywords)
			sb.append(" ").append(keyword);
		
		Query query = new Query(sb.toString());

		QueryResult result;
		try {
			result = _twitter.search(query);
			_model.setStatus(result.getTweets());
			_model.notifyObservers();
		} catch (TwitterException ex) {
			_view.notifyError(ex);
		}

	}
}
