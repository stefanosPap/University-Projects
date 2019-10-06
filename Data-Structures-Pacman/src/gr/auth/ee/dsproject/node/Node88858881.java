package gr.auth.ee.dsproject.node;

import gr.auth.ee.dsproject.pacman.*;

/*
 * 
 * ������� �������� ���:8885 ���:6987112819 EMAIL:stefanospapadam@gmail.com
 * ���������� ���������� ���:8881 ���:6984749063 EMAIL:panagoulas@gmail.com 
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


  public Node88858881 () //����� Constructor
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
  public Node88858881 (int x,int y,int move,Room[][] Maze) // Constructor �� �������� 
  {
	  nodeX=x;
	  nodeY=y;
	  nodeMove=move;
	  nodeEvaluation=evaluate(Maze);
	  currentGhostPos=findGhosts(Maze);
	  flagPos=findFlags(Maze);
	  currentFlagStatus=checkFlags(Maze);
	  }
  /*� ������� findGhosts ������� �� ���� ��� ����������� ���� ���� ������� ��� ���������� 
   * �������� ��� ��������� k ��� ����� � ������� ��� ������ ��� �����������
   * � ����� for ������ ��� ���� ������ 
   * � ������� for ������ ��� ���� �����
   * ��������� �� ��� if �� ��� ������������ ���� ��������� �������� 
   * ������������ ��� ������������� ���
   * ��������� �� ��������� � 
   * ��� ������������ ��� ������ currentGhostPos
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
   *� ������� ���� ������� ��� ���������� ��� ������ ��� �������
   *�������� ��� ��������� � ��� ����� � ������� ��� ���� ������� 
   * � �����for ������ ��� ���� ������ 
   * � ������� for ������ ��� ���� �����
   * ��������� �� ��� if �� ��� ������������ ���� ��������� ������ 
   * ������������ ��� ������������� ���
   * ��������� �� � ���� 1 ���� ���� �� ���� ���� ������� ���� ��� ������  
   * ��� ������������ ��� ������ flagPos �� ��� ������ ��� �������
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
/*� ������� ���� ������� ��� ��������� ��� ���� ������� ��� ���������� true 
 * �� ���� ��������� ��� ��� ������ ��� false �� ����� ����� ������ 
 * �� �� for ��������� ���� ������
 * �� ��� if ��������� �� � ������������ ������ ����� ������ � ��� 
 * ������������ ��� ������
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

/*H ������� ���� ��� ������� �� ����� ���� �������������� � pacman
 * ���������� true � false ������� �� �� �� �������������� � ������
 * �������� ��� ��������� eval � ����� ����� � ��������� ����������
 * �������� ��� ������� count � ������ ���� ����� ����������� ��� 3 �������� ��� �� ��������� 
 * ���� ���� ����������� ��� ���� �� ���� if ����� ���� ���� ��� ���������� 
 * �������������� ��� for ��� ���� �������� ��� ��������� 
 * �� � ������ ��� �� �������� ���������� ���� ���� ����� ���� �� � �������� ���� ����� ��������� ��� 3������ ���� ��� �� �������
 * ������ �� � ������ ��� �� �������� ���������� ���� ���� ����� ���� �� � �������� ���� ����� ��������� ��� 3 ������ �� ������� ���� 1
 * ������ �� ��� ��������� ���� ���� ���� ����� ���� ���� ���� ����� ��� ����� �������� ��������� ��� ��� ���� ������ ���
 * ��������� �� � �������� ������ �� 3 ��� ������� true �� eval ������ ��� ���� ������������
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
   /*� ������� ���� ��������������� ��� �� ��������� �� ���� ���� ��� �� ���� � ������ ������ 
    * �� ��������� ������ ��������
    * �������� �� ��������� ghost �� ��������� ����������
    * �� ��� if ��������� �� � ���� ��������� ����� ����� ��� ������ ��� �� ���������� ����� ecxeption
    * ��� ���� �������� ��������� �� � ���� �� ���� ������ �� ��������� �������� 
    * ������� ��� ���� ������� ��� ���� ������ ����
    * ��� ������������ �� boolean ���� ��� �����  
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
/*� ������� ���� ���������� �� �������� ��������� ������
 * �������� �� double ��������� ��� ��� �� ������������
 * �������� ��� ���������� ������ flags ���� ����� ������� �� ������ findFlags ��� �� ������������� ��� ������������� ��� ���� ������� 
 *�������� ��� ������ ������������ flagDisPacX ���� ����� �� ������������ ��� ���������� �������� ��� ������ ��� ��� ���� ������   
 *�������� ��� ������ ������������ flagDisPacY ���� ����� �� ������������ ��� ��������� �������� ��� ������ ��� ��� ���� ������
 *�������� ��� ������ ������������ wholeDisFlag ���� ����� �� ������������ �� �������� �������� ��� ������ ��� ��� ���� ������
 *�������� ��� ������ ������������ check ���� ����� ������� �� ������ checkFlags ��� �� ������������� ��� ��������� ��� ���� ������� 
 *�� ��� for ��������� ��� ���������� ��� ������ ��� ��� ���� ������ ���� ��������� ��� ����� ��������� � ������
 *��������� �� ��� ������� for �� minimum ��� ������ wholeDisFlag ������ ��� ����������� ������ ���� ������  
 *������� ����� ��� evaluation 
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
	//����������� ��������� ���������
	    for(int i=0;i<PacmanUtilities.numberOfFlags;i++) {
	
	    	if(!check[i]) {
	    	
	    		flagDisPacX[i]=Math.abs(nodeX-flags[i][0]);
	    		    		
	    	    flagDisPacY[i]=Math.abs(nodeY-flags[i][1]);
	    	    	    	    
	    		wholeDisFlag[i]=flagDisPacY[i]+flagDisPacX[i];
	    		
      	
	    	}
    

	    }
	    //������ ��������� ����� 
	   	    int minDis=1000000;
  	    	     	    
	   	    for(int i=0;i<PacmanUtilities.numberOfFlags;i++) {
	    		    	
	    	
	   	    	if(!currentFlagStatus[i]) {
	    		
	    	
	    		
	   	    		if(wholeDisFlag[i]<minDis)
	    		
	    		
	   	    		{
	    		
	   	    			minDis=wholeDisFlag[i];
	   
	    		    }
	    
	    	}
	    }
	        
	
	   	    evaluation=evaluation-minDis;/*��������� ��� �� evaluation �� minDis
	   	                                 ����� ��� ��������� �������� ������ ��� �� ������ 
	   	                                 ���� ��������� ������ �������
	  	                                  ������ ���� ���������� evaluation ������
	                                       */
	         
	   	    //�� � ������ ����� ����� ��� ������ �� ��������� ���� 100 ��� �� �������� ������� ���� �� ���� 
	   	    
	   	    
	    	if(Maze[nodeX][nodeY].isFlag() && !Maze[nodeX][nodeY].isCapturedFlag()) {
	    		
	    		evaluation=evaluation+100;
	    	
	    	}
	    	
	    	//�� ����� ����� �� �������� �� ��������� ���� 100 ������ ������� ��� ��������� ��������� 
	    	
	    	if(Maze[nodeX][nodeY].isGhost()) {
	    		
	    		evaluation=evaluation-100;
	    	
	    	}
	    	//�� ���� �� ���� ���� ��������� �� ��������� �������� ������� ��� ������� ��������� 
	    	//��������� ��� ���� ��� �� ��������� ���� 99
	    	//������� �� ghost ��� ���� ��� ������
	    	
	    	if(ghost(Maze,nodeX,nodeY)) {
	    	
	    		evaluation=evaluation-99;
	    	
	    	}
	    	
	    	//�� �������������� ��� ���������� ������� ���� ��� ��������� ��������� ��� �� ��������� ���� 100
	    	//������� �� ghostCir ��� �� �� ������ 
	    	
	    	if(ghostCir(Maze,nodeX,nodeY)) {
	    		
	    		evaluation=evaluation-100;
	    	
	    	}
	    	
	    	// �� ����������� ������ ��� 1/4 ��� 3/4 ��� ������ ������ ��� �������� ��� ������ 
	    	//�� ��������� ���� 10 ����� ���� ������������ ����������� �� ��� ��������� �����
	    	
	      if(PacmanUtilities.numberOfColumns/4<nodeY && (PacmanUtilities.numberOfColumns*3/4)>nodeY &&
	        	PacmanUtilities.numberOfRows/4<nodeX && (PacmanUtilities.numberOfRows*3/4)>nodeX) {
	        		evaluation=evaluation+10;
	        }
	        //������������ ��� ����
	    return evaluation;
}
}