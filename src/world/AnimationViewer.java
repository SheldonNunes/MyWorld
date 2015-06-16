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
		for(int i = 0; i<500; i++){
			for(int j = 0; j<500; j++){
				_shapes[i][j] = (new RectangleShape(i, j, 0, 0, 0, 0, _colour));
			}
		}
		//Generates the circles for land and beach
		for(int i = 0; i<numberOfCircles; i++){
			int xPos = randomGenerator.nextInt(500);
			int yPos = randomGenerator.nextInt(500);
			GenerateCircles(xPos, yPos);
		}





		/*
		for(int i=10; i<490; i++) {
			for (int j=10; j<490; j++) {
				randomInt = randomGenerator.nextInt(100);
				Color c = GetNeighbours(i, j);
				if(c == test.tellItLikeItIs(TerrainType.SEA)){
					if(randomInt< 98){
						_colour = test.tellItLikeItIs(TerrainType.SEA);
					} else {
						_colour = test.tellItLikeItIs(TerrainType.BEACH);
					}
				} else if((c == test.tellItLikeItIs(TerrainType.BEACH))){
					if(randomInt< 40){
						_colour = test.tellItLikeItIs(TerrainType.SEA);
					} else {
						_colour = test.tellItLikeItIs(TerrainType.LAND);
					}
				} else if((c == test.tellItLikeItIs(TerrainType.LAND))){
					if(randomInt< 80){
						_colour = test.tellItLikeItIs(TerrainType.LAND);
					} else {
						_colour = test.tellItLikeItIs(TerrainType.MOUNTAIN);
					}
				}
				_shapes[i][j].SetColour(_colour);
				System.out.println(_colour);
			}
		}
		 */



		// Start the animation.
		//_timer.start();
	}
	public void GenerateCircles(int posX, int posY) {
		// Populate the list of Shapes.
		int xdistance;
		int ydistance;
		Random randomGenerator = new Random();
		int radius = randomGenerator.nextInt(70);
		radius += 30;
		int[][] store = new int[500][500];
		

		for(int i=0; i<500; i++) {
			for (int j=0; j<500; j++) {
				//int randomizer = randomGenerator.nextInt(4);
				xdistance = Math.abs(posX-i);
				ydistance = Math.abs(posY-j);
				int count = 0;
				boolean up = true;
				if(Math.hypot(xdistance, ydistance) < radius-8){
					_shapes[i][j].SetColour(Color.GREEN);

				} else if(Math.hypot(xdistance, ydistance) < radius){
					if(_shapes[i][j].GetColour() == Color.BLUE){

						_shapes[i][j].SetColour(Color.YELLOW);
						store[i][j] = 1;
					}

				} 
			}
		}
		
		int displacementCircles = 0;
		int displacementradius = randomGenerator.nextInt(50);
		while(displacementCircles < 10){
			
			int xRandomizer = randomGenerator.nextInt(500);
			int yRandomizer = randomGenerator.nextInt(500);
			if(store[xRandomizer][yRandomizer] == 1){
				store[xRandomizer][yRandomizer] = 0;
				for(int i=0; i<500; i++) {
					for (int j=0; j<500; j++) {
						xdistance = Math.abs(xRandomizer-i);
						ydistance = Math.abs(yRandomizer-j);
		
						if(Math.hypot(xdistance, ydistance) < displacementradius-8){
							_shapes[i][j].SetColour(Color.BLUE);
						} else if(Math.hypot(xdistance, ydistance) < displacementradius && _shapes[i][j].GetColour() == Color.GREEN){
							_shapes[i][j].SetColour(Color.YELLOW);
						}
					}
				}
				displacementCircles += 1;
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
