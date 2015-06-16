package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Ian Warren
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
	}

	/**
	 * @see bounce.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * @see bounce.
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}
	
	public void setColor(Color colour) {
		_g.setColor(colour);
	}
	
	public Color getColor() {
		return _g.getColor();
	}
	
	public void drawCenteredText(String theText, int x, int y, int width, int height) {
		int sidewaysPosition = (int)_g.getFontMetrics().getStringBounds(theText, _g).getWidth();
		int ascent = (int)_g.getFontMetrics().getAscent();
		int descent = (int)_g.getFontMetrics().getDescent();
		int aThing = (ascent-descent)/2;
		_g.drawString(theText, x+(width/2) - sidewaysPosition/2, y+(height/2)+aThing);
	}
	
	public void drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		_g.drawImage(img, x, y, width, height, null);
	}
}
