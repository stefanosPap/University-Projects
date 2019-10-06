package gr.auth.ee.dsproject.pacman;

import java.util.ArrayList;

import gr.auth.ee.dsproject.node.Node88858881;

/**
 * 
 * 
 * ΠΑΠΑΔΑΜ ΣΤΕΦΑΝΟΣ ΑΕΜ:8885 ΤΗΛ:6987112819 EMAIL:stefanospapadam@gmail.com
 * ΠΑΝΑΓΟΥΛΑΣ ΠΑΝΑΓΙΩΤΗΣ ΑΕΜ:8881 ΤΗΛ:6984749063 EMAIL:panagoulas@gmail.com 
 * 
 * <p>Title: DataStructures2011</p>
 *
 * <p>Description: Data Structures project: year 2011-2012</p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: A.U.Th.</p>
 *
 * @author Michael T. Tsapanos
 * @version 1.0
 */


public class Creature implements gr.auth.ee.dsproject.pacman.AbstractCreature
{

  public String getName ()
  {
    return "Mine";
  }

  private int step = 1;
  private boolean amPrey;

  public Creature (boolean isPrey)
  {
    amPrey = isPrey;

  }

  public int calculateNextPacmanPosition (Room[][] Maze, int[] currPosition)
      {
	    ArrayList<Node88858881> array=new ArrayList<Node88858881>();//δηλώνουμε και δημιουργουμε το ArrayList μας
	    
	
	    int moveToReturn=-1;//αρχικοποιούμε το moveToReturn
        
        int pacmanX;//δηλώνουμε την pacmanX 

		int pacmanY;//δηλώνουμε την pacmanY

	  for(int i=0;i<4;i++) {//με τη for παράγουμε κάθε κίνηση απο 0 έως 3
		 
	    if(Maze[currPosition[0]][currPosition[1]].walls[i]==1) {//ελέγχουμε αν συναντά τοίχο και απορρίπτουμε αυτες τις κινήσεις
	   
	        pacmanX=currPosition[0];//αποθηκεύουμε στην pacmanX την συγκεκριμένη σειρά του πακμαν
	      
	    	pacmanY=currPosition[1];//αποθηκεύουμε στην pacmanΥ την συγκεκριμένη στήλη του πακμαν

	     //ανάλογα με την κίνηση του αλλάζουμε την γραμμή ή τη στήλη που θα πάει
	    	if (i==Room.NORTH) {
	    	
	    	 pacmanX=pacmanX-1;
	    
	    	
	     
	    	}
	     
	     
	    	if(i==Room.SOUTH) {
	    	
	    	 pacmanX=pacmanX+1;
	    	
	    
	    	}
	     
	    	if(i==Room.WEST) {
	    	
	    		pacmanY=pacmanY-1;
	
	     
	    	}
	     
	    	if(i==Room.EAST) {
	    	
	    		pacmanY=pacmanY+1;
	
	     }
	    //δημιουργούμε το αντικείμενο της κίνησης το οποίο είναι τύπου Node88858881 
	    Node88858881 move=new Node88858881(pacmanX,pacmanY,i,Maze);
		
	    //το προσθέτουμε στην ArrayList μας 
	    array.add(move);
	    }
	    }
	  //αν η ArrayList δεν είναι άδεια 
	    if(!array.isEmpty()) {
	     //βρίσκουμε σε ποιό αντικείμενο έχουμε το μεγαλύτερο nodeEvaluation
	    	double max=-1000000;//στη μεταβλητή αυτή αποθηκευουμε το μέγιστο του πίνακα 
	   	  
	    	int pos=0;//αποθηκεύουμε τη θέση του πίνακα 
	   
	    	for(int a=0;a<array.size();a++) {//παίρνουμε όλα τα στοιχεία του 
	    	
	    		if(array.get(a).nodeEvaluation>max) {
	   			
	    			max=array.get(a).nodeEvaluation;
    	        
	    			pos=a;	
    	
	    		}
	   
	   	
	    	}
	   
	    //επιστρέφουμε την κίνηση του πίνακα με το μεγαλύτερο nodeEvaluation δηλαδή τη βέλτιστη
	    	moveToReturn=array.get(pos).nodeMove;
	    
	   	
	    }
	  
	    return moveToReturn;
  }


  public int[] calculateNextGhostPosition (Room[][] Maze, int[][] currentPos)
  {

    int[] moves = new int[4];
    int[] eval = new int[4];
    boolean[] ghostColision = new boolean[PacmanUtilities.numberOfGhosts];

    // System.out.println("Ghosts Current Positions ");
    for (int i = 0; i < PacmanUtilities.numberOfGhosts; i++) {
      // System.out.println("ghost x = "+currentPos[i][0]+" ghost y = "+ currentPos[i][1]);
    }
    // System.out.println(" ");

    int pacmanX = 0;
    int pacmanY = 0;

    for (int i = 0; i < PacmanUtilities.numberOfRows; i++)
      for (int j = 0; j < PacmanUtilities.numberOfColumns; j++) {
        if (Maze[i][j].isPacman()) {
          pacmanX = i;
          pacmanY = j;

        }
      }

    // pacmanX = 0;
    // pacmanY = 0;

    // System.out.println(pacmanX);
    // System.out.println(pacmanY);
    // System.out.println("");

    eval = newGhostDir(pacmanX, pacmanY, currentPos);
    ghostColision = checkCollision(eval, currentPos);

    for (int i = 0; i < PacmanUtilities.numberOfGhosts; i++) {
      while (!evaluateNewDirection(currentPos, i, eval, Maze) || ghostColision[i] || !checkFlag(currentPos, i, eval, Maze)) {
        eval[i] = (int) (4 * Math.random());
        ghostColision = checkCollision(eval, currentPos);
      }
    }

    moves = eval;

    // for (int i = 0; i < PacmanUtilities.numberOfGhosts; i++)
    // System.out.println("direction of ghost "+i+" = " +moves[i]);
    // System.out.println(" ");

    return moves;

  }

  public int[] newGhostDir (int pacX, int pacY, int[][] currentPos)
  {
    int[] tempDirections = new int[4];

    for (int k = 0; k < PacmanUtilities.numberOfGhosts; k++) {
      if (pacX < currentPos[k][0]) // pacman norther than ghost
      {
        tempDirections[k] = Room.NORTH;
      }

      if (pacX > currentPos[k][0]) // pacman souther than ghost
      {
        tempDirections[k] = Room.SOUTH;
      }

      if (pacY > currentPos[k][1]) // pacman easter than ghost
      {
        tempDirections[k] = Room.EAST;
      }

      if (pacY < currentPos[k][1]) // pacman wester than ghost
      {
        tempDirections[k] = Room.WEST;
      }
    }

    return tempDirections;
  }

  public boolean evaluateNewDirection (int[][] curPos, int i, int[] direction, Room[][] Maze)
  {
    boolean validChoice = true;

    if (Maze[curPos[i][0]][curPos[i][1]].walls[direction[i]] == 0)
      validChoice = false;

    return validChoice;

  }

  public boolean checkFlag (int[][] curPos, int i, int[] direction, Room[][] Maze)
  {
    boolean validChoice = true;

    if (direction[i] == Room.NORTH) {
      if (Maze[curPos[i][0] - 1][curPos[i][1]].isFlag()) {
        validChoice = false;
      }
    }

    if (direction[i] == Room.SOUTH) {
      if (Maze[curPos[i][0] + 1][curPos[i][1]].isFlag()) {
        validChoice = false;
      }
    }

    if (direction[i] == Room.EAST) {
      if (Maze[curPos[i][0]][curPos[i][1] + 1].isFlag()) {
        validChoice = false;
      }
    }

    if (direction[i] == Room.WEST) {
      if (Maze[curPos[i][0]][curPos[i][1] - 1].isFlag()) {
        validChoice = false;
      }
    }
    return validChoice;
  }

  public boolean[] checkCollision (int[] moves, int[][] currentPos)
  {
    boolean[] collision = new boolean[PacmanUtilities.numberOfGhosts];

    int[][] newPos = new int[4][2];

    for (int i = 0; i < PacmanUtilities.numberOfGhosts; i++) {

      if (moves[i] == 0) {
        newPos[i][0] = currentPos[i][0];
        newPos[i][1] = currentPos[i][1] - 1;
      } else if (moves[i] == 1) {
        newPos[i][0] = currentPos[i][0] + 1;
        newPos[i][1] = currentPos[i][1];
      } else if (moves[i] == 2) {
        newPos[i][0] = currentPos[i][0];
        newPos[i][1] = currentPos[i][1] + 1;
      } else {
        newPos[i][0] = currentPos[i][0] - 1;
        newPos[i][1] = currentPos[i][1];
      }

      collision[i] = false;
    }

    for (int k = 0; k < moves.length; k++) {
      // System.out.println("Ghost " + k + " new Position is (" + newPos[k][0] + "," + newPos[k][1] + ").");
    }

    for (int i = 0; i < PacmanUtilities.numberOfGhosts; i++) {
      for (int j = i + 1; j < PacmanUtilities.numberOfGhosts; j++) {
        if (newPos[i][0] == newPos[j][0] && newPos[i][1] == newPos[j][1]) {
          // System.out.println("Ghosts " + i + " and " + j + " are colliding");
          collision[j] = true;
        }

        if (newPos[i][0] == currentPos[j][0] && newPos[i][1] == currentPos[j][1] && newPos[j][0] == currentPos[i][0] && newPos[j][1] == currentPos[i][1]) {
          // System.out.println("Ghosts " + i + " and " + j + " are colliding");
          collision[j] = true;
        }

      }

    }
    return collision;
  }

  public int newPacmanDir (int pacX, int pacY, int[] currentPos)
  {
    int tempDirections = 5;

    if (pacX < currentPos[0]) // pacman norther than ghost
    {
      tempDirections = Room.NORTH;
    }

    if (pacX > currentPos[0]) // pacman souther than ghost
    {
      tempDirections = Room.SOUTH;
    }

    if (pacY > currentPos[1]) // pacman easter than ghost
    {
      tempDirections = Room.EAST;
    }

    if (pacY < currentPos[1]) // pacman wester than ghost
    {
      tempDirections = Room.WEST;
    }

    return tempDirections;
  }

}
