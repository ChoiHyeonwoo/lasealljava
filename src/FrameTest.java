

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameTest extends JFrame {
	private static FrameTest instance = null;

	private FrameTest() {
		this.setUndecorated(true);
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize((int) dimScreen.getWidth(), ((int) dimScreen.getHeight()));
		setBackground(new Color(255, 255, 255, 0));
		setVisible(true);
	}

	public static FrameTest getInstance() {
		if (instance == null)
			instance = new FrameTest();

		return instance;
	}
}
