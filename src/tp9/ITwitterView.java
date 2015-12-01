package tp9;

import tp9.model.TwitterModel;

public interface ITwitterView {
	void setController(TwitterController controller);
	
	void notifyModelChanged(TwitterModel tm);

	void run();

	void notifyError(Exception ex);
}
