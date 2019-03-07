package observeurs;

import java.awt.image.BufferedImage;

public interface ObserveurImage extends Observeur  {
	void print(BufferedImage im);
}
