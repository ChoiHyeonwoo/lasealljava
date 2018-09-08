public class FigureTest  {


}
class FigurePoint {
	private int posX;
	private int posY;

	private int figureNum;


	public int getFigureNum() {
		return figureNum;
	}

	public void setFigureNum(int figureNum) {
		this.figureNum = figureNum;
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

	public FigurePoint(int _posX, int _posY, int _figureNum) {
		posX = _posX;
		posY = _posY;
		figureNum = _figureNum;
	}

}

