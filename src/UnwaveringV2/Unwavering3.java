package UnwaveringV2;

import static UnwaveringV2.Player.down;
import static UnwaveringV2.Player.left;
import static UnwaveringV2.Player.right;
import static UnwaveringV2.Player.up;
import org.newdawn.slick.state.*;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.TrueTypeFont;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

class blocked {

	 static boolean[][] blocked;

	 static boolean[][] getblocked() {

		return blocked;

	}

};










public class Unwavering3 extends BasicGameState {

	public Player player;
	public Item healthpotion, healthpotion1;
	public Item1 speedpotion, speedpotion1;
        public Itembonus bonusitem,bonusitem1,bonusitem2, bonusitem3, bonusitem4, bonusitem5, bonusitem6, bonusitem7, bonusitem8,bonusitem9;
	public Itemwin antidote;

	public ArrayList<Item> stuff = new ArrayList();

	public ArrayList<Item1> stuff1 = new ArrayList();
        
        public ArrayList<Itembonus> stuff2 = new ArrayList();
	
	public ArrayList<Itemwin> stuffwin = new ArrayList();

	private boolean[][] hostiles;

	private static TiledMap grassMap;

	private static AppGameContainer app;

	private static Camera camera;
	
	public static int counter = 0;

	// Player stuff

	

	/**
	 * 
	 * The collision map indicating which tiles block movement - generated based
	 * 
	 * on tile properties
	 */

	// changed to match size of sprites & map

	private static final int SIZE = 64;

	// screen width and height won't change

	 static final int SCREEN_WIDTH = 1000;

	 static final int SCREEN_HEIGHT = 750;

	public Unwavering3(int xSize, int ySize) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)

	throws SlickException {
		
		 gc.setTargetFrameRate(60);

		gc.setShowFPS(false);

		// *******************

		// Scenerey Stuff

		// ****************

		grassMap = new TiledMap("res/d3.tmx");

		// Ongoing checks are useful
                //this checks how large the map is
		//System.out.println("Tile map is this wide: " + grassMap.getWidth());

		camera = new Camera(gc, grassMap);
                
                player = new Player();

		

		

		// *****************************************************************

		// Obstacles etc.

		// build a collision map based on tile properties in the TileD map

		blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

		// System.out.println("Map height:" + grassMap.getHeight());

		// System.out.println("Map width:" + grassMap.getWidth());

		// There can be more than 1 layer. You'll check whatever layer has the
		// obstacles.

		// You could also use this for planning traps, etc.

		// System.out.println("Number of tile layers: "
		// +grassMap.getLayerCount());

                
                //this says the size of the set map
//		System.out.println("The grassmap is " + grassMap.getWidth() + "by "
//				+ grassMap.getHeight());

		for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

			for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

				// int tileID = grassMap.getTileId(xAxis, yAxis, 0);

				// Why was this changed?

				// It's a Different Layer.

				// You should read the TMX file. It's xml, i.e.,human-readable
				// for a reason

				int tileID = grassMap.getTileId(xAxis, yAxis, 1);

				String value = grassMap.getTileProperty(tileID,

				"blocked", "false");
//                              this checks what is blocked and not blocked in the chat
				if ("true".equals(value)) {

//					System.out.println("The tile at x " + xAxis + " andy axis "
//							+ yAxis + " is blocked.");

					blocked.blocked[xAxis][yAxis] = true;

				}

			}

		}

                //another check for the size of boxes
		//System.out.println("Array length" + blocked.blocked[0].length);

		// A remarkably similar process for finding hostiles

		hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

		for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
				int xBlock = (int) xAxis;
				int yBlock = (int) yAxis;
				if (!blocked.blocked[xBlock][yBlock]) {
					if (yBlock % 8 == 0 && xBlock % 16== 0 ) {
						Item i = new Item(xAxis * SIZE, yAxis * SIZE);
						stuff.add(i);
						//stuff1.add(h);
						hostiles[xAxis][yAxis] = true;
					}
				}
			}
		}
		
		for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
				int xBlock = (int) xAxis;
				int yBlock = (int) yAxis;
				if (!blocked.blocked[xBlock][yBlock]) {
					if (xBlock % 9 == 0	&& yBlock % 25 == 0) {
						Item1 h = new Item1(xAxis * SIZE, yAxis * SIZE);
					//	stuff.add(i);
						stuff1.add(h);
						hostiles[xAxis][yAxis] = true;
					}
				}
			}
		}
                
		healthpotion = new Item(100, 100);
		healthpotion1 = new Item(450, 400);
               
		stuff.add(healthpotion);
		stuff.add(healthpotion1);
		
		speedpotion = new Item1(100,150);
		speedpotion1 = new Item1(450,100);	
		stuff1.add(speedpotion);
		stuff1.add(speedpotion1);
		
		antidote = new Itemwin(3004,92);
		stuffwin.add(antidote);
                
                bonusitem = new Itembonus(403,69);
                bonusitem1 = new Itembonus(64,2489);
                bonusitem2 = new Itembonus(944,2659);
                bonusitem3 = new Itembonus(1094,68);
                bonusitem4 = new Itembonus(734,1738);
                bonusitem5 = new Itembonus(514,1648);
                bonusitem6 = new Itembonus(1115,1412);
                bonusitem7 = new Itembonus(1676,1526);
                bonusitem8 = new Itembonus(2090,85);
                bonusitem9 = new Itembonus(2872,2636);
                stuff2.add(bonusitem);
                stuff2.add(bonusitem1);
                stuff2.add(bonusitem2);
                stuff2.add(bonusitem3);
                stuff2.add(bonusitem4);
                stuff2.add(bonusitem5);
                stuff2.add(bonusitem6);
                stuff2.add(bonusitem7);
                stuff2.add(bonusitem8);
                stuff2.add(bonusitem9);
                
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)

	throws SlickException {

		camera.centerOn((int) Player.x, (int) Player.y);

		camera.drawMap();

		camera.translateGraphics();

		// it helps to add status reports to see what's going on

		// but it gets old quickly

		// System.out.println("Current X: " +player.x + " \n Current Y: "+ y);

		player.sprite.draw((int) Player.x, (int) Player.y);
		
		//g.drawString("x: " + (int)Player.x + "y: " +(int)Player.y , Player.x, Player.y - 10);

		g.drawString("Health: " + Player.health/1000, camera.cameraX + 10,
				camera.cameraY + 10);
		
		g.drawString("speed: " + (int)(Player.speed *10), camera.cameraX + 10,
				camera.cameraY + 25);

		//g.draw(Player.rect);

		g.drawString("time passed: " +counter/1000, camera.cameraX +600,camera.cameraY );
		// moveenemies();
                
                g.drawString("Bonus Points: " +Player.bonus, camera.cameraX +800,camera.cameraY );
		for (Item i : stuff) {
			if (i.isvisible) {
				i.currentImage.draw(i.x, i.y);
				// draw the hitbox
				//g.draw(i.hitbox);

			}
		}
		
		
		for (Item1 h : stuff1) {
			if (h.isvisible) {
				h.currentImage.draw(h.x, h.y);
				// draw the hitbox
				//g.draw(h.hitbox);

			}
		}
		
		for (Itemwin w: stuffwin) {
			if (w.isvisible) {
				w.currentImage.draw(w.x, w.y);
				// draw the hitbox
				//g.draw(w.hitbox);

			}
		}
                
                for (Itembonus y: stuff2) {
			if (y.isvisible) {
				y.currentImage.draw(y.x, y.y);
				// draw the hitbox
				//g.draw(y.hitbox);

			}
		}
                

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)

	throws SlickException {
		
		counter += delta;

		Input input = gc.getInput();

		float fdelta = delta * Player.speed;

		Player.setpdelta(fdelta);

		double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

		// System.out.println("Right limit: " + rightlimit);

		float projectedright = Player.x + fdelta + SIZE;

		boolean cangoright = projectedright < rightlimit;

		// there are two types of fixes. A kludge and a hack. This is a kludge.

		if (input.isKeyDown(Input.KEY_UP)) {

			player.sprite = up;

			float fdsc = (float) (fdelta - (SIZE * .15));

			if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

				player.sprite.update(delta);

				// The lower the delta the slower the sprite will animate.

				Player.y -= fdelta;

			}

		} else if (input.isKeyDown(Input.KEY_DOWN)) {

			player.sprite = down;

			if (!isBlocked(Player.x, Player.y + SIZE + fdelta)

			|| !isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta)) {

				player.sprite.update(delta);

				Player.y += fdelta;

			}

		} else if (input.isKeyDown(Input.KEY_LEFT)) {

			player.sprite = left;

			if (!(isBlocked(Player.x - fdelta, Player.y) || isBlocked(Player.x

			- fdelta, Player.y + SIZE - 1))) {

				player.sprite.update(delta);

				Player.x -= fdelta;

			}

		} else if (input.isKeyDown(Input.KEY_RIGHT)) {

			player.sprite = right;

			// the boolean-kludge-implementation

			if (cangoright
					&& (!(isBlocked(Player.x + SIZE + fdelta,

					Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y
							+ SIZE - 1)))) {

				player.sprite.update(delta);

				Player.x += fdelta;

			} // else { System.out.println("Right limit reached: " +
				// rightlimit);}

		}

		Player.rect.setLocation(Player.getplayershitboxX(),
				Player.getplayershitboxY());

		for (Item i : stuff) {

			if (Player.rect.intersects(i.hitbox)) {
				//System.out.println("yay");
				if (i.isvisible) {

					Player.health += 10000;
					i.isvisible = false;
				}

			}
		}
		
		for (Item1 h : stuff1) {

			if (Player.rect.intersects(h.hitbox)) {
				//System.out.println("yay");
				if (h.isvisible) {

					Player.speed += .1f;
					h.isvisible = false;
				}

			}
		}
		
		for (Itemwin w : stuffwin) {

			if (Player.rect.intersects(w.hitbox)) {
				//System.out.println("yay");
				if (w.isvisible) {
					w.isvisible = false;
					makevisible();
                                        //MAKE NEW WIN SCREEN
					sbg.enterState(8, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
					
				}

			}
		}
                
                for (Itembonus y : stuff2) {

			if (Player.rect.intersects(y.hitbox)) {
				//System.out.println("yay");
				if (y.isvisible) {

					Player.bonus += 1;
					y.isvisible = false;
				}

			}
		}
		 
		Player.health -= counter/1000;
		if(Player.health <= 0){
			makevisible();
			sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}

	}

	public int getID() {

		return 6;

	}

	public void makevisible(){
		for (Item1 h : stuff1) {
			
		h.isvisible = true;}
		
		for (Item i : stuff) {
			
			i.isvisible = true;}
                for (Itembonus y : stuff2) {
			
			y.isvisible = true;}
		}
	
	private boolean isBlocked(float tx, float ty) {

		int xBlock = (int) tx / SIZE;

		int yBlock = (int) ty / SIZE;

		return blocked.blocked[xBlock][yBlock];

		// this could make a better kludge

	}

}
