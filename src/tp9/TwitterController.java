package tp9;

import java.util.Observable;
import java.util.Observer;
import tp9.model.TwitterModel;

public class TwitterController implements Observer {
	private final TwitterModel _model;
	private final ITwitterView _view;
	
	public TwitterController(TwitterModel model, ITwitterView view) {
		_model = model;
		_view = view;
		
		_model.addObserver(this);
		_view.setController(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
