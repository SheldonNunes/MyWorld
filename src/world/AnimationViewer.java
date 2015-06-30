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
 * @author Ian Warren. Added to and edited by Mathew Smith and Sheldon Nunes
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {

	// Frequency in milliseconds to generate ActionEvents.
	//private final int DELAY = 20;

	// Collection of Shapes to animate.
	private RectangleShape[][] _shapes;

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
	public AnimationViewer(int circle) {
		_shapes = new RectangleShape[500][500];
		Random randomGenerator = new Random();
		int randomInt = 0;
		int numberOfCircles = circle;
		//Creates the initial grid all with blue rectanges
		int zMax;
		int zMean = 0;
		for(int i = 0; i<500; i++){
			for(int j = 0; j<500; j++){
				int x = i-250;
				int y = j-250;
				//Max Value = 125000
				long zValue = (long) (Math.pow(x,2) + Math.pow(y, 2));
				long landOne = (long) Math.abs(9000*(Math.cos(x*y)));
						//*(Math.pow(x, 2)-Math.pow(y, 2))));
				long testValue = (long) (1*landOne);
				//cos(x*y)*(x^2-y^2)
				zMean += zValue;
				_shapes[i][j] = (new RectangleShape(i, j, testValue, 0, 0, 0, 0, _colour));

			}
		}
		zMean = zMean/250000;
		//Generates the circles for land and beach
			int xPos = randomGenerator.nextInt(500);
			int yPos = randomGenerator.nextInt(500);
			GenerateCircles(zMean, yPos);

	}
	public void GenerateCircles(int posX, int posY) {
		// Populate the list of Shapes.
		Random randomGenerator = new Random();
		int[][] store = new int[500][500];


		for(int i=0; i<500; i++) {
			for (int j=0; j<500; j++) {
				long height = Math.abs(_shapes[i][j].getHeight());
				if(_shapes[i][j].getHeight() > posX){
					_shapes[i][j].SetColour(Color.BLUE);
				} else {
					_shapes[i][j].SetColour(Color.YELLOW);
				}
				/*
				if(_shapes[i][j].getHeight() > 0){
					if(height < 50){
						Color yellow = new Color(height+200, height+200, 0);
						_shapes[i][j].SetColour(yellow);
						
					} else {
						if(height>100){
							height = 100;
						}
						Color green = new Color(0, 255-height, 0);
						_shapes[i][j].SetColour(green);
					} 
					
					
				} else {

					if(height > 100){
						height = 100;
					}
					int blueValue = 255-height;
					Color test = new Color(0, 0, blueValue);


					_shapes[i][j].SetColour(test);

				} */
			}
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
		for(int i = 0; i<500; i++){
			for(int j = 0; j<500; j++){
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
		System.out.println("Enter Number of Circles");
		int circles = in.nextInt();
		JFrame frame = new JFrame("Animation viewer");
		frame.add(new AnimationViewer(circles));
		// Set window properties.
		frame.setSize(500, 550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}
