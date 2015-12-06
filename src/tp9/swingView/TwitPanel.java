package tp9.swingView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import tp9.model.Twit;

public class TwitPanel extends JPanel {

	public static final Dimension AVATAR_SIZE = new Dimension(100, 100);

	public TwitPanel(Twit twit) {
		setLayout(new GridBagLayout());
		setBorder(new EmptyBorder(8, 8, 8, 8));

		JLabel avatar = new JLabel(ImageLoader.load(twit.avatar));
		avatar.setPreferredSize(AVATAR_SIZE);
		add(avatar, new GridBagConstraints() {
			{
				gridx = 0;
				gridy = 0;
				gridwidth = 1;
				gridheight = 2;
				weightx = 0.0;
				weighty = 0.0;
				anchor = LINE_START;
			}
		});

		JLabel username = new JLabel(twit.userName);
		add(username, new GridBagConstraints() {
			{
				gridx = 1;
				gridy = 0;
				gridwidth = 1;
				gridheight = 1;
				weightx = 0.0;
				weighty = 0.0;
				anchor = LINE_START;
			}
		});

		// Autosize to fit enclowing width and content height
		JTextPane text = new JTextPane() {
			@Override
			public Dimension getPreferredSize() {
				JTextPane dummy = new JTextPane();
				dummy.setSize(getWidth(), Short.MAX_VALUE);
				dummy.setText(getText());

				return new Dimension(0, dummy.getPreferredSize().height);
			}
		};
		text.setText(twit.text);
		text.setEditable(false);
		add(text, new GridBagConstraints() {
			{
				gridx = 1;
				gridy = 1;
				gridwidth = 1;
				gridheight = 1;
				weightx = 1.0;
				weighty = 1.0;
				fill = BOTH;
			}
		});

		GridBagConstraints c = new GridBagConstraints() {
			{
				gridx = 1;
				gridy = 2;
				gridwidth = 1;
				gridheight = 1;
				weightx = 0.0;
				weighty = 0.0;
				fill = BOTH;
			}
		};
		for (String img : twit.images) {
			final JLabel lbl = new JLabel(ImageLoader.load(img));
			lbl.setOpaque(true);
			lbl.setBackground(Color.white);
			lbl.setBorder(new EmptyBorder(0, 0, 8, 0));
			add(lbl, c);
			++c.gridy;
		}
	}
}
