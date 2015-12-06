package tp9.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import twitter4j.Status;

public class TwitterModel extends Observable {

	public final List<Twit> twits = new ArrayList<>();

	public void loatTweets(List<Status> status) {
		twits.clear();
		for(Status s : status)
			twits.add(new Twit(s));
		setChanged();
	}
}
