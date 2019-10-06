package gr.auth.ee.dsproject.node;

import gr.auth.ee.dsproject.pacman.*;

/*
 * 
 * ΠΑΠΑΔΑΜ ΣΤΕΦΑΝΟΣ ΑΕΜ:8885 ΤΗΛ:6987112819 EMAIL:stefanospapadam@gmail.com
 * ΠΑΝΑΓΟΥΛΑΣ ΠΑΝΑΓΙΩΤΗΣ ΑΕΜ:8881 ΤΗΛ:6984749063 EMAIL:panagoulas@gmail.com 
 * 
 * 
 */
public class Node88858881
{

  public int nodeX;
  public int nodeY;
  public int nodeMove;
  public double nodeEvaluation;
  int[][] currentGhostPos=new int[4][2];
  int[][] flagPos=new int[4][2];
  boolean[] currentFlagStatus=new boolean[4];


  public Node88858881 () //Κενός Constructor
  {
	  nodeX=0;
	  nodeY=0;
	  nodeMove=0;
	  nodeEvaluation=0;
	for(int i=0;i<4;i++){
		
	   currentGhostPos[i][0]=0;
	   currentGhostPos[i][1]=0;
	   flagPos[i][0]=0;
	   flagPos[i][1]=0;
	   currentFlagStatus[i]=false;
	 
	}}
  public Node88858881 (int x,int y,int move,Room[][] Maze) // Constructor με ορίσματα 
  {
	  nodeX=x;
	  nodeY=y;
	  nodeMove=move;
	  nodeEvaluation=evaluate(Maze);
	  currentGhostPos=findGhosts(Maze);
	  flagPos=findFlags(Maze);
	  currentFlagStatus=checkFlags(Maze);
	  }
  /*Η μέθοδος findGhosts βρίσκει τη θέση των φαντασμάτων πάνω στην περιοχή του παιχνιδιού 
   * Ορίζουμε μια μεταβλητή k που είναι ο δείκτης του πίνακα των φαντασμάτων
   * Η πρώτη for τρέχει για κάθε γραμμή 
   * Η δεύτερη for τρέχει για κάθε στήλη
   * Ελέγχουμε με την if αν στη συγκεκριμένη θέση βρίσκεται φάντασμα 
   * Αποθηκεύουμε τις συντεταγμένες του
   * Αυξάνουμε τη μεταβλητή κ 
   * και επιστρέφουμε τον πίνακα currentGhostPos
   */
  private int[][] findGhosts (Room[][] Maze)
  {
	   int k=0;
		 
	       
		 for(int i=0;i<PacmanUtilities.numberOfRows;i++) {
	    	 
			 for(int j=0;j<PacmanUtilities.numberOfColumns;j++) {
	    	
				 if(Maze[i][j].isGhost()) {
	    		
					 currentGhostPos[k][0]=i;
					 currentGhostPos[k][1]=j;
	    		     k=k+1;
				 }
	    	 }
		
	    	 
	    	 
	     
  }
  
  return currentGhostPos;
  }
  /*
   *Η μέθοδος αυτή βρίσκει και επιστρέφει τις θέσεις των σημαιών
   *Ορίζουμε μια μεταβλητή Κ που ειναι ο δείκτης της κάθε σημαίας 
   * Η πρωτηfor τρέχει για κάθε γραμμή 
   * Η δευτερη for τρέχει για κάθε στήλη
   * Ελέγχουμε με την if αν στη συγκεκριμένη θέση βρίσκεται σημαία 
   * Αποθηκεύουμε τις συντεταγμένες της
   * Αυξάνουμε το κ κατα 1 ετσί ωστε να πάμε στην επόμενη θέση του πίνακα  
   * και επιστρέφουμε τον πίνακα flagPos με τις θεσεις των σημαιών
   */
  private int[][] findFlags (Room[][] Maze)
  {
      
	  int k=0;
	 
	  
	    for(int i=0;i<PacmanUtilities.numberOfRows;i++) {
	    	 
			 for(int j=0;j<PacmanUtilities.numberOfColumns;j++) {
	    	
				 if(Maze[i][j].isFlag()) {
		
					 flagPos[k][0]=i;
					 flagPos[k][1]=j;
					 k=k+1;
				 
				 }
			 }
			 }
	  
	return flagPos;  
  }
/*Η μέθοδος αυτή βρίσκει την κατάσταση της κάθε σημαίας και επιστρέφει true 
 * αν έχει αποκτηθεί απο τον πακμαν και false αν είναι ακόμα ενεργή 
 * Με τη for παίρνουμε κάθε σημαία
 * Με την if ελέγχουμε αν η συγκέκριμενη σημαία είναι ενεργή ή οχι 
 * Επιστρέφουμε τον πίνακα
 */
  private boolean[] checkFlags (Room[][] Maze)
  {
	    
		for(int i=0;i<PacmanUtilities.numberOfFlags;i++) {
	    
			
			if(Maze[flagPos[i][0]][flagPos[i][1]].isCapturedFlag()) {
	        
				currentFlagStatus[i]=true;
				
			}
			
			else { 
				currentFlagStatus[i]=false;			
				
			}
			}
		return currentFlagStatus;
		}

/*H μέθοδος αυτή μας βοηθάει να δούμε πότε περικυκλώνεται ο pacman
 * Επιστρέφει true ή false ανάλογα με το αν περικυκλώνεται ο πακμαν
 * ορίζουμε μια μεταβλητή eval η οποία είναι η μεταβλητή επίστροφης
 * ορίζουμε ένα μετρητή count ο οποίος όταν γίνει μεγαλύτερος του 3 σημαίνει ότι το πρόγραμμα 
 * έχει μπεί τουλάχιστον μία φορά σε κάθε if οπότε έχει γύρω του φαντάσματα 
 * Χρησιμοποιούμε μία for για κάθε φάντασμα και ελέγχουμε 
 * Αν ο πακμαν και το φάντασμα βρίσκονται στην ίδια σειρά τότε αν η απόσταση τους είναι μικρότερη από 3αύξησε κατα ενα το μετρητη
 * Αλλιως Αν ο πακμαν και το φάντασμα βρίσκονται στην ίδια στήλη τότε αν η απόσταση τους είναι μικρότερη του 3 αύξησε το μετρητή κατα 1
 * Αλλιως αν δεν βρίσκοντα ούτε στην ίδια σειρά ούτε στην ίδια στήλη και έχουν απόσταση μικρότερη του δύο τότε αυξησε τον
 * Ελέγχουμε αν ο μετρητής πέρασε το 3 και κάνουμε true το eval δηλαδή οτι έχει περικυκλωθεί
 */
   private boolean ghostCir(Room[][] Maze,int pacmanX,int pacmanY) {
    	
    	boolean eval=false;
        
    	int count=0;
    	    	   	  	
    	
    	for(int i=0;i<PacmanUtilities.numberOfGhosts;i++) {
    			
    		if(pacmanX==currentGhostPos[i][0]) {
    			   if((Math.abs(nodeX-currentGhostPos[i][0]))<3) {
    			    	count=count+1;
    	 
    			   }
    			   }
    		
    		else if(pacmanY==currentGhostPos[i][1]) {
    				   if((Math.abs(nodeY-currentGhostPos[i][1]))<3 ) {
    				    	count=count+1;
    	
    				   }
    				   }
    		
    		else if((Math.abs(nodeX-currentGhostPos[i][0]))<2 || (Math.abs(nodeY-currentGhostPos[i][1]))<2 ) {
    				    	count=count+1;
    		
    		}
    		
    				   }
    	    
    	if( count>3) {
        	   eval=true;
    	       
    	}
    	
    	return eval;

      }
   /*Η μέθοδος αυτή χρησιμοποιείται για να ελέγξουμε αν στην θέση που θα πάει ο πάκμαν μπορεί 
    * να καταλήξει κάποιο φάντασμα
    * Ορίζουμε τη μεταβλητή ghost ως μεταβλητή επιστροφής
    * Με την if ελέγχουμε αν η θέση βρίσκεται όντως εντός του πίνακα για να αποφύγουμε τυχόν ecxeption
    * και αφού μπορούμε ελέγχουμε αν σ αυτή τη θέση μπορεί να καταλήξει φάντασμα 
    * Κάνουμε την ίδια δουλεια για κάθε πιθανή θέση
    * και επίστρεφουμε τη boolean τιμή στο τέλος  
  */     
   public boolean  ghost(Room[][] Maze,int x,int y) {
	   
	   boolean ghost=false;
	   
	   if(x+1<PacmanUtilities.numberOfRows) {
		   
		   if(Maze[x+1][y].isGhost()) {
			   ghost=true;
		   }
		   
	   }
	   if(x-1>0) {
		   
		       if(Maze[x-1][y].isGhost()) {
				   ghost=true;
			   }
	   }
	   if(y+1<PacmanUtilities.numberOfColumns) {
		   
		   if(Maze[x][y+1].isGhost()) {
			   ghost=true;
		   }
	   }
	   if(y-1>0) {
	   
		   if(Maze[x][y-1].isGhost()) {
			   ghost=true;
			   
		   } 
	   }
		return ghost;
   }
/*Η μέθοδος αυτή υπολογίζει τη βέλτιστη διαθέσιμη κίνηση
 * Ορίζουμε τη double μεταβλητή μας που θα επιστρέφουμε
 * Ορίζουμε τον διδιάστατο πίνακα flags στον οποίο καλούμε τη μέθοδο findFlags για να αποθηκεύσουμε τις συντεταγμένες της κάθε σημαίας 
 *Ορίζουμε ενα πίνακα μονοδιάστατο flagDisPacX στον οποίο θα αποθηκεύουμε την κατακόρυφη απόσταση του πάκμαν απο την κάθε σημαία   
 *Ορίζουμε ενα πίνακα μονοδιάστατο flagDisPacY στον οποίο θα αποθηκεύουμε την οριζόντια απόσταση του πάκμαν απο την κάθε σημαία
 *Ορίζουμε ενα πίνακα μονοδιάστατο wholeDisFlag στον οποίο θα αποθηκεύουμε τη συνολική απόσταση του πάκμαν απο την κάθε σημαία
 *ορίζουμε ένα πίνακα μονοδιάστατο check στον οποίο καλούμε τη μέθοδο checkFlags για να αποθηκεύσουμε την κατάσταση της κάθε σημαίας 
 *Με μια for βρίσκουμε τις αποστάσεις του πάκμαν από την κάθε σημαία αφού ελέγξουμε ότι είναι διαθέσιμη η σημαία
 *Βρίσκουμε με την επόμενη for το minimum του πίνακα wholeDisFlag δηλαδή την κοντινότερη σημαία στον πακμαν  
 *Δίνουμε τιμές στο evaluation 
 * */
 

    
   public double evaluate (Room[][] Maze)
      {

        double evaluation = 0;

	    int[][] flags=new int[4][2];
	
	    int[] flagDisPacX=new int[PacmanUtilities.numberOfFlags];
	
	    int[] flagDisPacY=new int[PacmanUtilities.numberOfFlags];
	
	    int[] wholeDisFlag=new int[PacmanUtilities.numberOfFlags];
	   
	    boolean[] check=new boolean[4];
	
	    flags=findFlags(Maze);
	
	    check=checkFlags(Maze);
	//υπολογισμός συνολικής απόστασης
	    for(int i=0;i<PacmanUtilities.numberOfFlags;i++) {
	
	    	if(!check[i]) {
	    	
	    		flagDisPacX[i]=Math.abs(nodeX-flags[i][0]);
	    		    		
	    	    flagDisPacY[i]=Math.abs(nodeY-flags[i][1]);
	    	    	    	    
	    		wholeDisFlag[i]=flagDisPacY[i]+flagDisPacX[i];
	    		
      	
	    	}
    

	    }
	    //εύρεση ελάχιστης τιμής 
	   	    int minDis=1000000;
  	    	     	    
	   	    for(int i=0;i<PacmanUtilities.numberOfFlags;i++) {
	    		    	
	    	
	   	    	if(!currentFlagStatus[i]) {
	    		
	    	
	    		
	   	    		if(wholeDisFlag[i]<minDis)
	    		
	    		
	   	    		{
	    		
	   	    			minDis=wholeDisFlag[i];
	   
	    		    }
	    
	    	}
	    }
	        
	
	   	    evaluation=evaluation-minDis;/*Αφαιρούμε από το evaluation το minDis
	   	                                 γιατι όσο μικρότερη απόσταση απέχει από τη σημαία 
	   	                                 τόσο μικρότερο αριθμό αφαιρεί
	  	                                  δηλαδή τόσο μεγαλύτερο evaluation εχουμε
	                                       */
	         
	   	    //αν ο πακμαν είναι δίπλα στη σημαία το αυξάνουμε κατα 100 για να επιλέξει σίγουρα αυτή τη θέση 
	   	    
	   	    
	    	if(Maze[nodeX][nodeY].isFlag() && !Maze[nodeX][nodeY].isCapturedFlag()) {
	    		
	    		evaluation=evaluation+100;
	    	
	    	}
	    	
	    	//αν είναι δίπλα σε φάντασμα το μειώνουμε κατά 100 δηλαδη είμαστε στη χειρότερη περίπτωση 
	    	
	    	if(Maze[nodeX][nodeY].isGhost()) {
	    		
	    		evaluation=evaluation-100;
	    	
	    	}
	    	//αν πάει σε θέση όπου ενδέχεται να καταλήξει φάντασμα είμαστε στη δεύτερη χειρότερη 
	    	//περίπτωση για αυτό και το μειώνουμε κατα 99
	    	//καλούμε τη ghost για αύτο τον έλεγχο
	    	
	    	if(ghost(Maze,nodeX,nodeY)) {
	    	
	    		evaluation=evaluation-99;
	    	
	    	}
	    	
	    	//αν περικυκλωνεται απο φαντάσματα είμαστε πάλι στη χειρότερη περίπτωση άρα το μειώνουμε κατα 100
	    	//καλούμε τη ghostCir για να το βρούμε 
	    	
	    	if(ghostCir(Maze,nodeX,nodeY)) {
	    		
	    		evaluation=evaluation-100;
	    	
	    	}
	    	
	    	// Αν βρισκόμαστε μεταξυ του 1/4 και 3/4 του πίνακα δηλαδη πιο κεντρικά του πίνακα 
	    	//το αυξάνουμε κατα 10 γιατι έχει περισσότερες πιθανότητες να μην συναντήση τοίχο
	    	
	      if(PacmanUtilities.numberOfColumns/4<nodeY && (PacmanUtilities.numberOfColumns*3/4)>nodeY &&
	        	PacmanUtilities.numberOfRows/4<nodeX && (PacmanUtilities.numberOfRows*3/4)>nodeX) {
	        		evaluation=evaluation+10;
	        }
	        //επιστρέφουμε την τιμή
	    return evaluation;
}
}