package world;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Ian Warren
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * rectangle. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Fills the rectangle with a solid colour. Parameters x and y specify the top left corner of the
	 * rectangle. Parameters width and height specify its width and height.
	 */
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * sets the colour to be used to subsequent drawing calls, notably fillRect
	 * @param colour
	 */
	public void setColor(Color colour);
	
	/**
	 * returns the colour that the graphics object contains
	 * @return
	 */
	public Color getColor();
	
	/**
	 * draws text in the centre of the shape. Parameters x and y specify the top left corner of the
	 * shape. Parameters width and height specify its width and height.
	 * @param theText the text that will be displayed
	 */
	public void drawCenteredText(String theText, int x, int y, int width, int height);
	
	/** 
	 * used to paint images
	 * @param img the image file
	 * @param x position for painting image
	 * @param y
	 * @param width
	 * @param height
	 * @param observer used when image isn't loading or some crap (i don't actually use it)
	 */
	public void drawImage(Image img, int x, int y, int width, int height, ImageObserver observer);
}
