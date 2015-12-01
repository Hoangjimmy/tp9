package tp9;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
		JTextField userField = new JTextField();
		JTextField tagField = new JTextField();
		JButton	searchButton = new JButton("Search");
		JLabel userLabel = new JLabel("User");
		JLabel tagLabel = new JLabel("Hashtag Search");
		JScrollPane resultPane = new JScrollPane();
				
				
			
		
	}
}
