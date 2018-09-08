

import java.awt.Color;

public class ColorTest {

	private Color stringColor;
	private Color drawColor = Color.BLACK;

	private static ColorTest instance;

	public static ColorTest getInstance() {
		if (instance == null)
			instance = new ColorTest();

		return instance;
	}

	public Color getStringColor() {
		return stringColor;
	}

	public void setStringColor(Color stringColor) {
		this.stringColor = stringColor;
	} 

	public Color getDrawColor() {
		return drawColor;
	}

	public void setDrawColor(Color drawColor) {
		this.drawColor = drawColor;
	}

	public Color stringToColor(String rgb) {
		System.out.println(rgb);
		String hex;
		if(rgb.length()>8) {
			hex = rgb.replaceFirst("0xff", "#");
		}
		else {
			hex = rgb;
		}
//		String hex = rgb.replaceFirst("#", "#");
//		hex = hex.substring(0, 7);
		
		Color c = new Color(Integer.valueOf(hex.substring(1, 3), 16), Integer.valueOf(hex.substring(3, 5), 16),
				Integer.valueOf(hex.substring(5, 7), 16));
		return c;
	}
}
