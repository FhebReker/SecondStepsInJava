/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-12-08
 @Description: Swaps through 3 seperate screensavers: 
 				multiple layers of mandalas, 
 				a beam struggle between 2 cannons, 
 				a shape that switches between a circle and a 
 				rectangle and both shrinks and speeds up as it 
 				hits the edge of the graphics console
***********************************************
*/

package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Interface{
	// Window width and height
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	// Graphics Console
	private static GraphicsConsole gc = new GraphicsConsole (WindowWidth, WindowWidth);
	
	/*
	* Method Name: startScreen
	* Author: Fardin Abbassi
	* Creation Date; November 25, 2023
	* Modified Date: December 6, 2023
	* Description: Creates a start screen that introduces the screensavers that will be used in the program and provides a brief welcome message.
	* @Parameters: n/a
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole
	* Throws/Exceptions: n/a
	*/	
	private static void startScreen() {
		gc.setAntiAlias(true);
		gc.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		gc.setBackgroundColor(Color.CYAN);
		gc.clear();
		gc.drawString("Welcome to The Screensaver Experience", WindowWidth / 20, WindowHeight/2-350);
		gc.drawString("There are 3 screensavers in total:", WindowWidth/20, (WindowHeight / 2) - 300);
		gc.drawString("Flower Mandalas, Cannon Beam Blast, Ball Accelerator", WindowWidth/20, (WindowHeight / 2) - 250);
		gc.drawString("Hope you enjoy this presentation!", WindowWidth/20, (WindowHeight / 2));
		gc.drawString("Starting in 5 seconds...", WindowWidth/20, (WindowHeight) - 300);
		gc.sleep(5000);
	} 
	public static void main (String[] args) {
		startScreen();
		int looperIndex = 0;
		while(true) { // loop through all the screensavers
			switch(looperIndex) {
				case 0: // mandala
					Mandala.mandalaMaker(gc);
					break;
				case 1: // beam struggle
					BeamStruggle.beamBattle(gc);
					break;
				case 2: // ball bouncer
					ShapeAcceleration.Accelerate(gc);
				case 3: // looping back to mandala
					looperIndex = -1;
					break;
			}
			// after playing screensaver, pause for a second and move onto the next one
			looperIndex++;
			gc.sleep(1000);
			gc.clear();
		}
	}
} // end Interface class


class Mandala{
	// Window width and height
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	// position of circles, number of circles
	static int x, y, numCircles;
	
	/*
	* Method Name: mandalaMaker
	* Author: Fardin Abbassi
	* Creation Date; November 27, 2023
	* Modified Date: November 27, 2023
	* Description: Creates 4 layers of mandalas, each different colours from each other
	* @Parameters: GraphicsConsole gc ???
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole, floralDraw()
	* Throws/Exceptions: n/a
	*/	
	public static void mandalaMaker(GraphicsConsole gc) {	
		gc.setBackgroundColor(Color.cyan);
		gc.clear();
		
		numCircles = 3;
		y = WindowHeight/2;
		x = WindowWidth/4; 
		for(int i = 0; i<5; i++) {
			floralDraw(x*i, WindowHeight, Color.magenta, gc);
			floralDraw(x*i, y, Color.magenta, gc);
			floralDraw(x*i, 0, Color.magenta, gc);
		}
		
		numCircles+= 3;
		y = WindowHeight/4;
		x = WindowWidth/3; 
		for(int i = 0; i<4; i++) {
			floralDraw(x*i, y, Color.ORANGE, gc);
			floralDraw(x*i, 3*y, Color.ORANGE, gc);
		}
		
		numCircles+= 5;
		y = WindowHeight/2;
		x = WindowWidth/6; 
		floralDraw(x, y, Color.red, gc);
		floralDraw(x*5, y, Color.red, gc);

		numCircles+= 2;
		x = WindowWidth/2; 
		floralDraw(x, y, Color.GREEN, gc);
	}
	
	/*
	* Method Name: colorChange
	* Author: Fardin Abbassi
	* Creation Date; November 27, 2023
	* Modified Date: November 27, 2023
	* Description: Takes a given colour and reduces either the red, blue or green of it by 15, then returns the new colour
	* @Parameters: Color previousColor, int randomIncrease ???
	* @Return Value: Color
	* Data Type: Color
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/	
	private static Color colorChange(Color previousColor, int randomIncrease) {
		Color newColor;
		switch(randomIncrease) {
			case 1:
				newColor = new Color(previousColor.getRed()-15, previousColor.getGreen(), previousColor.getBlue() );
				break;
			case 2:
				newColor = new Color(previousColor.getRed(), previousColor.getGreen()-15, previousColor.getBlue() );
				break;
			case 3:
				newColor = new Color(previousColor.getRed(),previousColor.getGreen(), previousColor.getBlue() - 15);
				break;
			default:
				newColor = previousColor;		
				break;
		}
		return newColor;
	}
	
	/*
	* Method Name: floralDraw
	* Author: Fardin Abbassi
	* Creation Date; November 27, 2023
	* Modified Date: November 27, 2023
	* Description: Draws a floral mandala by making petals, then draws the center which gradually becomes either cyan, purple or yellow
	* @Parameters: int centerX, int centerY, Color firstRingColor, GraphicsConsole gc
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: colorChange()
	* Throws/Exceptions: n/a
	*/	
	private static void floralDraw(int centerX, int centerY, Color firstRingColor, GraphicsConsole gc) {
		int petalX, petalY, radius = 10 + 20*numCircles, numLines = 12, 
			lineLength = 15*numCircles, randomColorChanger = (int)(3*Math.random())+1;
		double angle;
		Color innerCircleColor = new Color(225,225,225);
		
		// draws colored petals
		for (int i = 0; i < numLines; i++) { 
            gc.setColor(firstRingColor);
    		gc.sleep(100);
            angle = Math.toRadians(360.0 / numLines * i);
            petalX = (int) (centerX + Math.cos(angle) * lineLength);
            petalY = (int) (centerY + Math.sin(angle) * lineLength);
            gc.fillOval(petalX-radius, petalY-radius, radius*2, radius*2);
        }
		
		// draws outline for petals
        for (int i = 0; i < numLines; i++) {
    		gc.sleep(50);
            angle = Math.toRadians(360.0 / numLines * i);
        	petalX = (int) (centerX + Math.cos(angle) * lineLength);
            petalY = (int) (centerY + Math.sin(angle) * lineLength);
	        gc.setColor(Color.black);
	        gc.drawOval(petalX - radius, petalY - radius, radius * 2, radius * 2);
        }
        
        // Draw center circles
        for (int i = numCircles; i > 0; i--) {
    		gc.sleep(100);
        	gc.setColor(innerCircleColor);
            gc.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	        gc.setColor(Color.black);
            gc.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            radius -= 20;
    		innerCircleColor = colorChange(innerCircleColor, randomColorChanger);
        }
	}
} // end Mandala class


class BeamStruggle{
	// Window width and height
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	// cannon size, impact size, check for beams meeting each other
	private static int cannonWidth = 150;
	private static int cannonHeight = 150;
	private static int impactSize;
    private static boolean beamsMeeting; // Flag to indicate if beams have met 
	
    // cannon 1 base and beam
    private static int cannon1X = 100; // character 1    
    private static int[] cannon1XBase = {cannon1X, cannon1X+75, cannon1X+150};
    private static int[] cannon1YBase = {500, 400, 500};
    private static Polygon cannon1Base = new Polygon(cannon1XBase, cannon1YBase, 3); 
    private static Rectangle beam1;
   
    // cannon 2 base and beam
    private static int cannon2X = WindowWidth-200; // character 2
    private static int[] cannon2XBase = {cannon2X, cannon2X+75, cannon2X+150};
    private static int[] cannon2YBase = {500, 400, 500};
    private static Polygon cannon2Base = new Polygon(cannon2XBase, cannon2YBase, 3); 
    private static Rectangle beam2;
	
	/*
	* Method Name: beamBattle
	* Author: Fardin Abbassi
	* Creation Date; November 28, 2023
	* Modified Date: December 4, 2023
	* Description: Depending on if the beams meet, either moves the beams closer to each other or randomly pushes the beam impact towards one side
	* @Parameters: GraphicsConsole gc
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole, beamDraw()
	* Throws/Exceptions: n/a
	*/	
    public static void beamBattle(GraphicsConsole gc) {		   		 
		gc.setBackgroundColor(Color.black);
    	impactSize = 150;
        beam1 = new Rectangle(cannon1X + 75, 365, 50, 20); // Beam 1 rectangle
        beam2 = new Rectangle(cannon2X - 25, 365, 50, 20); // Beam 2 rectangle
        beamsMeeting = false; // Flag to indicate if beams have met 
        gc.clear();
		while (!(beam1.height <= 0 || beam2.height <= 0)) { // loop until the heights of the beam have dissipated
			gc.clear();
			if (!beamsMeeting) { // if not meeting, move beams closer to each other and increase height
				beam2.x -= 32;
				beam1.width += 32;
				beam2.width += 32;
				
				beam1.height+=4;
				beam2.height+=4;
				beam1.y-=2;
				beam2.y-=2;
				
				if (beam1.intersects(beam2)) {	// if they intersect, move them back and start the beam battle
						beamsMeeting = true;
						beam1.width -= 15;
						beam2.x += 15;
						beam2.width += 15;
					}
				}
			else { // if they are meeting, move either left or right, and adjust both beam sizes accordingly
				int leftOrRight = (int)(Math.random()*2) + 1;
				if(leftOrRight == 1) {
					beam1.width -= 12;
					beam2.width += 12;
					beam2.x -= 12;
		
					beam1.height+=2;
					beam2.height-=2;
					beam1.y-=1;
					beam2.y+=1;
        
					// beam impact should be big in the middle, small on the outside
					if((beam2.x - (impactSize / 2)) > (WindowWidth / 2))	impactSize-=2;
					else impactSize+=2;
				}
				if(leftOrRight == 2) {
					beam1.width += 12;
					beam2.width -= 12;
					beam2.x += 12;
		
					beam1.height-=2;
					beam2.height+=2;
					beam1.y+=1;
					beam2.y-=1;  
        
					if((beam2.x - (impactSize / 2)) < (WindowWidth / 2))	impactSize-=2;
					else impactSize+=2;
				}
			}		
    		beamDraw(gc);
		 }
	}
	
    /*
	* Method Name: beamDraw
	* Author: Fardin Abbassi
	* Creation Date; December 4, 2023
	* Modified Date: December 6, 2023
	* Description: Draws beams, characters and if beams are meeting, the beam impact
	* @Parameters: GraphicsConsole gc
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole
	* Throws/Exceptions: n/a
	*/	
    private static void beamDraw(GraphicsConsole gc) {
		 // Draw beam 1
		 gc.setColor(Color.MAGENTA);
		 gc.fillRect(beam1.x, beam1.y, beam1.width, beam1.height);

		 // Draw beam 2
		 gc.setColor(Color.WHITE);
		 gc.fillRect(beam2.x, beam2.y, beam2.width, beam2.height);

		 if (beamsMeeting) {
			 // Draw beam impact
			 gc.setColor(Color.MAGENTA);
			 gc.fillStar(beam2.x - (impactSize / 2) - 55, beam1.y + (int)(beam1.height/2) - (impactSize/2) - 25, impactSize + 40, impactSize + 40);
			 gc.setColor(Color.WHITE);
			 gc.fillStar(beam2.x - (impactSize / 2) + 5, beam1.y + (int)(beam1.height/2) - (impactSize/2) - 25, impactSize + 40, impactSize + 40);
			 
			 // Draw character 1
			 gc.setColor(new Color(243, 169, 3));
			 gc.fillOval(cannon1X, 300, cannonWidth, cannonHeight);
			 gc.setColor(new Color(24, 20, 99));
			 gc.fillPolygon(cannon1Base);

			 // Draw character 2
			 gc.setColor(new Color(199, 199, 109));
			 gc.fillOval(cannon2X, 300, cannonWidth, cannonHeight);
			 gc.setColor(new Color(6, 163, 104));
			 gc.fillPolygon(cannon2Base);
			 
			 gc.setColor(Color.CYAN);
			 gc.fillOval(beam2.x - (impactSize / 2), beam1.y + (int)(beam1.height/2) - (impactSize/2), impactSize, impactSize);
		 }
		 else {
			 // Draw character 1
			 gc.setColor(new Color(243, 169, 3));
			 gc.fillOval(cannon1X, 300, cannonWidth, cannonHeight);
			 gc.setColor(new Color(24, 20, 99));
			 gc.fillPolygon(cannon1Base);

			 // Draw character 2
			 gc.setColor(new Color(199, 199, 109));
			 gc.fillOval(cannon2X, 300, cannonWidth, cannonHeight);
			 gc.setColor(new Color(6, 163, 104));
			 gc.fillPolygon(cannon2Base);
		 }
		 
		 gc.sleep(20);
    }
} // end BeamStruggle class


class ShapeAcceleration{
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	private static int shapeX, shapeY; // Initial x and y position of the shape
	private static int dx, dy; // Direction of the shape
	private static int ballRadius; // Width of the shape
	private static int speedIncrease; // Speed of the shape
	private static int backgroundRGB; // Background color
	private static boolean shapeChange = true; // boolean to change shape: circle for true, square if false
	
	private static Color[] colours = {new Color(0, 89, 83), new Color(228, 116, 32), new Color(90, 64, 111), new Color(110, 230, 167), new Color(110, 106, 167)};
	private static int currentColor = 0;
		
	private static Line[] lines;
	private static int lineIndex; // Index to track the next available position in the array
	
	/*
	* Method Name: Accelerate
	* Author: Fardin Abbassi
	* Creation Date; December 6, 2023
	* Modified Date: December 8, 2023
	* Description: Bounces a shape inside the graphics console and attaches lines from the collision with the edge of the screen until a max amount of lines are created
	* @Parameters: GraphicsConsole gc
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole, Line, collision(), drawer()
	* Throws/Exceptions: n/a
	*/	
	public static void Accelerate(GraphicsConsole gc) {
		shapeX = WindowWidth / 2 - 200; // Initial x position of the shape
		shapeY = WindowHeight / 2 - 200; // Initial y position of the shape
		ballRadius = 225; // Width of the shape
		backgroundRGB = 225; // resets background to light grey
		currentColor = 0; // index to track current shape color
		lines = new Line[200]; // max amount of lines to attach to the shape
		lineIndex = 0; // Index to track the next available position in the array
		
		gc.setBackgroundColor(new Color(backgroundRGB, backgroundRGB, backgroundRGB));
		dx = dy = 1;
		speedIncrease = 5;
		while (true) { // bounces shape until max amount of lines reached
			shapeX += dx * speedIncrease;
			shapeY += dy * speedIncrease;
			
			// line made from collision to shape
	        if (shapeX <= 0)	
	        	collision(shapeX, shapeY, shapeX, shapeY + (ballRadius/2), colours[currentColor], true, gc); 	      
	        else if (shapeX >= WindowWidth - ballRadius)	
	        	collision(shapeX, shapeY, shapeX + ballRadius, shapeY + (ballRadius/2), colours[currentColor], true, gc); 	       
	        
	        if (shapeY <= 0)	
	        	collision(shapeX, shapeY, shapeX + (ballRadius/2), shapeY, colours[currentColor], false, gc); 	       
	        else if (shapeY >= WindowHeight - ballRadius)	
	        	collision(shapeX, shapeY, shapeX + (ballRadius / 2), shapeY + ballRadius, colours[currentColor], false, gc); 
	        
	        drawer(gc);
	        gc.sleep(50);	
	        
	        // once max amount of lines reached, set background to black, then leave loop
	    	if(lines[lines.length - 1] != null) {
	    		gc.setBackgroundColor(Color.black);
	    		gc.clear();
	            break;
	        }
		}
	} 
	
	/*
	* Method Name: drawer
	* Author: Fardin Abbassi
	* Creation Date; December 6, 2023
	* Modified Date: December 8, 2023
	* Description: Draws the lines connecting to the shape, and either a circle or rectangle to represent the shape
	* @Parameters: GraphicsConsole gc
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole, Line
	* Throws/Exceptions: n/a
	*/	
	private static void drawer(GraphicsConsole gc) {
		gc.clear();
	    for (int i = 0; i < lines.length; i++) { // draws each line from the shape's position to their respective bounce's position	
	        if (lines[i] != null) {
	        	lines[i].x1 = shapeX +(ballRadius/2);
	        	lines[i].y1 = shapeY + (ballRadius/2);
	            gc.setColor(lines[i].color);
	            gc.drawLine(lines[i].x1, lines[i].y1, lines[i].x2, lines[i].y2);
	        }
	    }
	    gc.setColor(colours[currentColor]);
	
	    // either draws a circle or square
	    if (shapeChange)	gc.fillOval(shapeX, shapeY, ballRadius, ballRadius);
	    else				gc.fillRect(shapeX, shapeY, ballRadius, ballRadius);
	}
	
	/*
	* Method Name: collision
	* Author: Fardin Abbassi
	* Creation Date; December 6, 2023
	* Modified Date: December 8, 2023
	* Description: 	Keeps ball inside the graphics console, increases speed, switches colour, 
	* 				decreases ball radius, darkens background, switches shape, makes line at collision position
	* @Parameters: int x1, int y1, int x2, int y2, Color color, boolean heightOrWidthBounding, GraphicsConsole gc
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: GraphicsConsole, Line
	* Throws/Exceptions: n/a
	*/
	private static void collision(int x1, int y1, int x2, int y2, Color color, boolean heightOrWidthBounding, GraphicsConsole gc) {
		// reverses either x or y direction
		if (heightOrWidthBounding)	dx = -dx;
		else dy = -dy;
		
		speedIncrease++;
	    currentColor = (currentColor + 1) % colours.length;
	    ballRadius--;
	    backgroundRGB--;
	    shapeChange = !shapeChange;
		gc.setBackgroundColor(new Color(backgroundRGB, backgroundRGB, backgroundRGB));
		
	    lines[lineIndex] = new Line(x1, y1, x2, y2, color);
	    lineIndex = (lineIndex + 1) % lines.length; // Move to the next position, wrapping around if necessary
	}
} // end ShapeAcceleration class


class Line {
	int x1, y1, x2, y2; // co-ordinates of the line
	Color color; // color of the line
	
	// sets points and color of the line
	public Line(int x1, int y1, int x2, int y2, Color color) {
	    this.x1 = x1;
	    this.y1 = y1;
	    this.x2 = x2;
	    this.y2 = y2;
	    this.color = color;
	}
} // end Line class