package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.math.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Random;
import java.util.Scanner;

//import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of shapes. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimised, and maximised. The state of a
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Sheldon Nunes. Added to and edited by Mathew Smith.
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {

	// Frequency in milliseconds to generate ActionEvents.
	//private final int DELAY = 20;

	// Collection of Shapes to animate.
	private RectangleShape[][] _shapes;
	public static long constant = 3;

	//private Timer _timer = new Timer(DELAY, this);

	public Color _colour = Color.BLUE;
	TerrainGeneration test = new TerrainGeneration(2, 2);


	/*public int CircleRadius(int x, int y, int center){
		int distance = Math.abs(center-x);
		distance = 
		return distance;
	}
	 */



	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and 
	 * starts the animation.
	 */

	
	public AnimationViewer(String[] valueArray) {
		_shapes = new RectangleShape[513][513];
		//Creates the initial grid all with blue rectangles
		_shapes[0][0] = (new RectangleShape(0, 0, Integer.parseInt(valueArray[0]), 0, 0, 0, 0, _colour));
		_shapes[0][512] = (new RectangleShape(0, 512, Integer.parseInt(valueArray[2]), 0, 0, 0, 0, _colour));
		_shapes[512][0] = (new RectangleShape(512, 0, 20, Integer.parseInt(valueArray[1]), 0, 0, 0, _colour));
		_shapes[512][512] = (new RectangleShape(512, 512, Integer.parseInt(valueArray[3]), 0, 0, 0, 0, _colour));
		
		DiamondSquareAlgorithm(0,0,512,0,0,512,512,512);
		
		SetNewColours();


	}
	
	public void SetNewColours(){
		for(int i = 0; i<512; i++){
			for(int j = 0; j<512; j++){
				if(_shapes[i][j].getHeight()>300){
					_shapes[i][j].SetColour(Color.GREEN);
				} else if(_shapes[i][j].getHeight()>250){
					_shapes[i][j].SetColour(Color.YELLOW);
				} else {
					_shapes[i][j].SetColour(Color.BLUE);
				}
				
				}
			}
	}
	
	public void DiamondSquareAlgorithm(int tlX, int tlY, int trX, int trY, int blX, int blY, int brX, int brY){
		
		int centre = (tlX+trX)/2;
		int vcentre = (tlY+blY)/2;
		long topLeft = _shapes[tlX][tlY].getHeight();
		long topRight = _shapes[trX][trY].getHeight();
		long bottomLeft = _shapes[blX][blY].getHeight();
		long bottomRight = _shapes[brX][brY].getHeight();
		double squareSize = (((trX-tlX)/512d+.5));

		long middleHeight = (long) (((topLeft+topRight+bottomLeft+bottomRight)/4));
		long error = (long) (constant*((Math.random()*20+1)/2)*squareSize);
		middleHeight = (long) (middleHeight + error);

		System.out.println("topLeft: " + topLeft + " topRight: " + topRight + " bottomLeft: " + bottomLeft + " bottomRight: " + bottomRight);
		System.out.println(middleHeight);
		System.out.println("Square Size: " + squareSize + " trX-tlX = " + (trX-tlX));
		_shapes[centre][vcentre] = (new RectangleShape(centre, vcentre, middleHeight, 0, 0, 0, 0, Color.ORANGE));
		_shapes[tlX][vcentre] = (new RectangleShape(tlX, vcentre, (topLeft+bottomLeft)/2, 0, 0, 0, 0, Color.ORANGE));
		_shapes[trX][vcentre] = (new RectangleShape(trX, vcentre, (topRight+bottomRight)/2, 0, 0, 0, 0, Color.ORANGE));
		_shapes[centre][blY] = (new RectangleShape(centre, blY, (bottomLeft+bottomRight)/2, 0, 0, 0, 0, Color.ORANGE));
		_shapes[centre][trY] = (new RectangleShape(centre, trY, (topLeft+topRight)/2, 0, 0, 0, 0, Color.ORANGE));
		
		if (trX-tlX > 1 ){
			//Top Left Corner
			DiamondSquareAlgorithm(tlX, tlY, centre, trY, blX, vcentre, centre, vcentre);
			//Top Right Corner
			DiamondSquareAlgorithm(centre, tlY, trX, trY, centre, vcentre, brX, vcentre);
			//Bottom Left Corner
			DiamondSquareAlgorithm(tlX, vcentre, centre, vcentre, blX, blY, centre, brY);
			//Bottom Right Corner
			DiamondSquareAlgorithm(centre, vcentre, trX, vcentre, centre, blY, brX, brY);
		}
		
		
		

	}

	public Color GetNeighbours(int i, int j){
		Color currentRectColor;
		int seaCounter = 0;
		int beachCounter = 0;
		int landCounter = 0;

		for(int l = i-1; l <= i+1; l++){
			for(int k = j-1; j <= k+1; j++){
				currentRectColor = _shapes[l][j].GetColour();
				System.out.println("Colour: " + currentRectColor);
				if(currentRectColor == test.tellItLikeItIs(TerrainType.SEA)){
					seaCounter++;
				} else if(currentRectColor == test.tellItLikeItIs(TerrainType.BEACH)){
					beachCounter++;
				} else if(currentRectColor == test.tellItLikeItIs(TerrainType.LAND)){
					landCounter++;
				}

			}

		}
		seaCounter--;
		System.out.println("seacounter: " + seaCounter);
		System.out.println("beachCounter: " + beachCounter);
		System.out.println("landCounter: " + landCounter);
		if(seaCounter > beachCounter && seaCounter > landCounter){
			return(test.tellItLikeItIs(TerrainType.SEA));
		} else if(beachCounter > seaCounter && beachCounter > landCounter){
			return(test.tellItLikeItIs(TerrainType.BEACH));
		} else {
			return(test.tellItLikeItIs(TerrainType.LAND));
		}
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been exposed after being hidden by another window. 
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);

		// Calculate bounds of animation screen area.
		//int width = getSize().width;
		//int height = getSize().height;

		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);

		// Progress the animation.
		Shape s;
		for(int i = 0; i<512; i++){
			for(int j = 0; j<512; j++){
				s = _shapes[i][j];
				s.thePaint(painter);
			}
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() to be
		// called.
		repaint();
	}

	// This lets another class (shapes) know what the current colour is
	public Color getCurrentColour() {
		return _colour;
	}




	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//System.out.println("Enter Constant (Higher values create more mountains and valleys)");
		//constant = in.nextInt();
		//Get the values entered by user and pass into AnimationViewer function
		System.out.println("Please enter a set of 4 numbers seperated by commas representing the outer corners of the square.\n Values should be in range 280-350 for good results. e.g. 280,280,280,280");
		String values = in.nextLine();
		String[] valueArray = values.split(",");
		JFrame frame = new JFrame("Animation viewer");
		frame.add(new AnimationViewer(valueArray));
		
		
		// Set window properties.
		frame.setSize(550, 550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}
