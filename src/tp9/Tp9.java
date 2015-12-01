package tp9;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tp9.model.TwitterModel;
import tp9.swingView.SwingTwitterView;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Tp9 {

	public static void main(String[] args) throws TwitterException {
		
		Twitter twitter = new TwitterFactory("hP49CWN0KuutGM6PCn7pfVk7P", "vQXz8BZ1tKBiSx1YTRwlkzT0Tfqf72hTdTSHv59hw5T1WFc0V8").createTwitter();

		Query query = new Query("xisumavoid");
		
		QueryResult result = twitter.search(query);
		for (Status s : result.getTweets())
			System.out.println(s.getText());

		
		
		final ITwitterView view = new SwingTwitterView();
		final TwitterModel model = new TwitterModel();
		final TwitterController ctrl = new TwitterController(model, view);
		view.run();
	}
}
