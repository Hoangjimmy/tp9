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
		
		
		
		JFrame frame = new JFrame();
		JPanel pane = new JPanel(new BorderLayout());
		JPanel pane2 = new JPanel(new FlowLayout());
		JTextField userField = new JTextField();
		JTextField tagField = new JTextField();
		JButton	searchButton = new JButton("Search");
		JLabel userLabel = new JLabel("User");
		JLabel tagLabel = new JLabel("Hashtag Search");

		JScrollPane resultPane = new JScrollPane( new JTextArea());
		
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);
		pane.add( pane2, BorderLayout.NORTH);
		pane2.add(userLabel);
		userField.setPreferredSize(new Dimension(128, 24));
		pane2.add(userField);
		pane2.add(tagLabel);
		tagField.setPreferredSize(new Dimension(128, 24));
		pane2.add(tagField);

		pane2.add(searchButton);
		pane.add(resultPane,BorderLayout.CENTER);
		frame.add(pane);
		
		frame.setJMenuBar(menu);
		frame.setSize(800, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			

		
		
		final ITwitterView view = new SwingTwitterView();
		final TwitterModel model = new TwitterModel();
		final TwitterController ctrl = new TwitterController(model, view);
		view.run();
	}
}
