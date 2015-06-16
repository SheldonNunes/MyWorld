package world;

import java.awt.Color;

/**
 * Class to represent a simple DynamicRectangle.
 * 
 */
public class DynamicRectangleShape extends Shape {
	
	private static final Color DEFAULT_COLOUR = new Color(255, 255, 255, 1);
	
	private Color _COLOUR;
	private boolean isColor = true;
	/**
	 * Default constructor that creates a DynamicRectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicRectangleShape() {
		super();
		_COLOUR = DEFAULT_COLOUR;
	}
	
	public DynamicRectangleShape(Color colour) {
		
		super();
		_COLOUR = colour;
		
	}
	
	/**
	 * Creates a DynamicRectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	public DynamicRectangleShape(Color colour, int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
		_COLOUR = colour;
	}
	/**
	 * Creates a DynamicRectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	
	public DynamicRectangleShape(Color colour, int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_COLOUR = colour;
	}
	
	@Override
	public void move(int width, int height) {
		int tempX = _deltaX;
		int tempY = _deltaY;
		super.move(width, height);
		
		if ((tempY > 0 && _deltaY < 0) || (tempY < 0 && _deltaY > 0)) {
			isColor = false;
		}
		if ((tempX > 0 && _deltaX < 0) || (tempX < 0 && _deltaX > 0)) {
			isColor = true;
		}
	}
	
	
	
	/**
	 * Paints this DynamicRectangleShape object using the supplied Painter object.
	 */
	public void paint(Painter painter) {
		
		if (isColor == true) {
			Color oldColor = painter.getColor();
			painter.setColor(_COLOUR);
			painter.fillRect(_x,_y,_width,_height);
			painter.setColor(oldColor);
		}
		painter.drawRect(_x,_y,_width,_height);
	}
}