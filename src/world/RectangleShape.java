package world;

import java.awt.Color;

/**
 * Class to represent a simple rectangle.
 * 
 * @author Ian Warren
 */
public class RectangleShape extends Shape {
	
	private Color colour;
	/**
	 * Default constructor that creates a RectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public RectangleShape() {
		super();
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public RectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
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
	public RectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color c) {
		super(x,y,deltaX,deltaY,width,height);
		colour = c;
		
	}
	
	/**
	 * Paints this RectangleShape object using the supplied Painter object.
	 */
	public void paint(Painter painter) {
		painter.setColor(colour);
		painter.drawRect(_x,_y,_width,_height);
	}
	
	public Color GetColour() {
		return(colour);
	}
	
	public void SetColour(Color c) {
		this.colour = c;
	}
}
