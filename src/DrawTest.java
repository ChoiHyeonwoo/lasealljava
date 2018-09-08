

import java.awt.Color;

public class DrawTest {

	
}

class DrawPoint {
	private int posX;
	private int posY;

	private Color posC;

	private int drawCnt;
	
	public int getDrawCnt() {
		return drawCnt;
	}

	public void setDrawCnt(int drawCnt) {
		this.drawCnt = drawCnt;
	}

	public Color getPosC() {
		return posC;
	}

	public void setPosC(Color posC) {
		this.posC = posC;
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

	public DrawPoint(int _posX, int _posY, int _drawCnt ,Color _posC) {
		posX = _posX;
		posY = _posY;
		drawCnt = _drawCnt;
		posC = _posC;

	}

}
