package tp9.swingView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import tp9.ITwitterView;
import tp9.TwitterController;
import tp9.model.Twit;
import tp9.model.TwitterModel;

public class SwingTwitterView implements ITwitterView {
	
	private final JFrame frame;
	private final JTextField userField;
	private final JTextField tagField;
	private final JButton searchButton;
	private final JPanel resultsPanel;
	private final JTextField keywordsField;
	private TwitterController controller;
	
	public SwingTwitterView() {
		frame = new JFrame();
		
		final ActionListener launchQuery = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadTwits(
						Arrays.asList(userField.getText().split("\\s+")),
						Arrays.asList(tagField.getText().split("\\s+")),
						Arrays.asList(keywordsField.getText().split("\\s+")));
			}
		};
		
		final JPanel cp = new JPanel(new BorderLayout());
		final JPanel queryPanel = new JPanel(new FlowLayout());
		
		userField = new JTextField();
		userField.setPreferredSize(new Dimension(128, 20));
		userField.addActionListener(launchQuery);
		queryPanel.add(new JLabel("User"));
		queryPanel.add(userField);
		
		tagField = new JTextField();
		tagField.setPreferredSize(new Dimension(128, 20));
		tagField.addActionListener(launchQuery);
		queryPanel.add(new JLabel("Hashtag Search"));
		queryPanel.add(tagField);
		
		keywordsField = new JTextField();
		keywordsField.setPreferredSize(new Dimension(128, 20));
		keywordsField.addActionListener(launchQuery);
		queryPanel.add(new JLabel("Keywords Search"));
		queryPanel.add(keywordsField);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(launchQuery);
		queryPanel.add(searchButton);
		
		cp.add(queryPanel, BorderLayout.NORTH);
		
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new GridBagLayout());
		resultsPanel.setBackground(new Color(0xFF90C0FF));
		
		final JScrollPane scrP = new JScrollPane(resultsPanel);
		scrP.getVerticalScrollBar().setUnitIncrement(16);
		scrP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cp.add(scrP, BorderLayout.CENTER);
		
		frame.getContentPane().add(cp);
		frame.setSize(800, 400);
	}
	
	@Override
	public void setController(TwitterController controller) {
		this.controller = controller;
	}
	
	@Override
	public void notifyModelChanged(TwitterModel tm) {
		resultsPanel.removeAll();
		
		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 0.0;
		
		for (Twit twit : tm.twits) {
			resultsPanel.add(new TwitPanel(twit), c);
			++c.gridy;
		}
		
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		resultsPanel.add(new JPanel(), c);
		
		resultsPanel.revalidate();
		resultsPanel.repaint();
	}
	
	@Override
	public void run() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void notifyError(Exception ex) {
		ex.printStackTrace(System.err);
	}
}
