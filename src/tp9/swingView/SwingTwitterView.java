package tp9.swingView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tp9.ITwitterView;
import tp9.TwitterController;
import tp9.model.Twit;
import tp9.model.TwitterModel;

public class SwingTwitterView implements ITwitterView {

	private final JFrame frame;
	private final JTextField userField;
	private final JTextField tagField;
	private final JButton searchButton;
	private final JScrollPane resultPane;
	private final JTextField keywords;
	private final JTextArea resultTA;
	private TwitterController controller;
	
	public SwingTwitterView() {

		frame = new JFrame();
		userField = new JTextField();
		tagField = new JTextField();
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadTwits(
						Arrays.asList(userField.getText().split("\\s+")),
						Arrays.asList(tagField.getText().split("\\s+")), 
						Arrays.asList(keywords.getText().split("\\s+")));
			}
		});
		resultTA = new JTextArea();
		resultPane = new JScrollPane(resultTA);
		keywords = new JTextField();
		
		JPanel pane = new JPanel(new BorderLayout());
		JPanel pane2 = new JPanel(new FlowLayout());
		JLabel userLabel = new JLabel("User");
		JLabel tagLabel = new JLabel("Hashtag Search");
		JLabel keyLabel = new JLabel("Keywords Search");
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);

		userField.setPreferredSize(new Dimension(128, 20));
		tagField.setPreferredSize(new Dimension(128, 20));
		keywords.setPreferredSize(new Dimension(128, 20));
		pane.add(resultPane, BorderLayout.CENTER);
		pane.add(pane2, BorderLayout.NORTH);
		
		pane2.add(userLabel);
		pane2.add(userField);
		pane2.add(tagLabel);
		pane2.add(tagField);
		pane2.add(keyLabel);
		pane2.add(keywords);
		pane2.add(searchButton);
		
		frame.add(pane);
		frame.setJMenuBar(menu);
		frame.setSize(800, 400);

	}

	@Override
	public void setController(TwitterController controller) {
		this.controller = controller;
	}

	@Override
	public void notifyModelChanged(TwitterModel tm) {
		for(Twit t : tm.twits)
			System.out.println(t.text);
	}

	@Override
	public void run() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void notifyError(Exception ex) {

	}
}
