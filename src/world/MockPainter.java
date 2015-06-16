package world;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging the request in a buffer. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Ian Warren
 */
public class MockPainter implements Painter {
	// Internal log.
	private StringBuffer _log = new StringBuffer();

	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
	}
	
	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}
	
	
	public void fillRect(int x, int y, int width, int height) {
		_log.append("(fillRectangle " + x + "," + y + "," + width + "," + height + ")");
	}
	
	public void setColor(Color colour) {
		_log.append("(setColor " + colour.toString() + ")");
	}
	
	public Color getColor() {
		//_log.append("(getColor " + getColor().toString() + ")");
		return Color.BLACK;
	}

	@Override
	public void drawCenteredText(String theText, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawImage(Image img, int x, int y, int width, int height,
			ImageObserver observer) {
		// TODO Auto-generated method stub
		
	}
	
	
}