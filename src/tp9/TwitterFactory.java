package tp9;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFactory {
	public final String consumerKey, consumerSecret;
	
	public TwitterFactory(String consumerKey_, String consumerSecret_) {
		consumerKey = consumerKey_;
		consumerSecret = consumerSecret_;
	}

	public Twitter createTwitter() throws TwitterException {
		OAuth2Token token = new twitter4j.TwitterFactory(
				new ConfigurationBuilder()
				.setApplicationOnlyAuthEnabled(true)
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.build()
		).getInstance().getOAuth2Token();

		return new twitter4j.TwitterFactory(
				new ConfigurationBuilder()
				.setApplicationOnlyAuthEnabled(true)
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setOAuth2TokenType(token.getTokenType())
				.setOAuth2AccessToken(token.getAccessToken())
				.build()
		).getInstance();
	}
}
