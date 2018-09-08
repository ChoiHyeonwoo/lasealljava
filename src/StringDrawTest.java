
import java.awt.Color;

public class StringDrawTest{


}

class StringPoint  {
	private int posX;
	private int posY;
	private Color posC;
	private String str;


	public StringPoint(int _posX, int _posY, Color _posC,  String _str) {
		this.posX = _posX;
		this.posY = _posY;
		this.posC = _posC;
		this.str = _str;
	}
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getPosC() {
		return posC;
	}

	public void setPosC(Color posC) {
		this.posC = posC;
	}


	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
