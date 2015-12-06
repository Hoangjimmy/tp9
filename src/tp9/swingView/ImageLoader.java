package tp9.swingView;

import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ImageLoader {

	public static final ImageIcon brokenImage = new ImageIcon();
	private static final Map<String, ImageIcon> urlCache = new HashMap<>();

	private ImageLoader() {
	}

	public static ImageIcon load(String url) {
		ImageIcon img = urlCache.get(url);
		if (img != null)
			return img;

		try {
			img = new ImageIcon(new URL(url));
		} catch (MalformedURLException ex) {
			System.out.println("ImageLoader : Couldn't load image at `" + url + "`.");
			return brokenImage;
		}

		if (img.getImageLoadStatus() != MediaTracker.COMPLETE) {
			System.out.println("ImageLoader : Couldn't load image at `" + url + "`.");
			return brokenImage;
		}

		urlCache.put(url, img);
		return img;
	}
}
