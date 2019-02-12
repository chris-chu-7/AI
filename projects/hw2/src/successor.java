/*
 * Christopher Chu
 * CS 540 
 * Homework 2 Problem 1 
 */


import java.util.*;

public class successor {
    public static class JugState {
        int[] Capacity = new int[]{0,0,0};
        int[] Content = new int[]{0,0,0};
        public JugState(JugState copyFrom)
        {
            this.Capacity[0] = copyFrom.Capacity[0];
            this.Capacity[1] = copyFrom.Capacity[1];
            this.Capacity[2] = copyFrom.Capacity[2];
            this.Content[0] = copyFrom.Content[0];
            this.Content[1] = copyFrom.Content[1];
            this.Content[2] = copyFrom.Content[2];
        }
        public JugState()
        {
        	
        }
        public JugState(int A,int B, int C)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
        }
        public JugState(int A,int B, int C, int a, int b, int c)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
            this.Content[0] = a;
            this.Content[1] = b;
            this.Content[2] = c;
        }
 
        public void printContent()
        {
            System.out.println(this.Content[0] + " " + this.Content[1] + " " + this.Content[2]);
        }
 
        public ArrayList<JugState> getNextStates(){
            ArrayList<JugState> successors = new ArrayList<>();

            // TODO add all successors to the list
            
            //arbitrary data to initialize a new jug to add to the arraylist 
            int a = this.Capacity[0];
            int b = this.Capacity[1];
            int c = this.Capacity[2];
            int d = this.Content[0];
            int e = this.Content[1];
            int f = this.Content[2];
            
            //add all the states where one jug is empty 
            for(int i = 0; i < this.Content.length; i++) {
            	JugState newJug = new JugState(a,b,c,d,e,f);
            	if(newJug.Content[i] != 0) {
            		newJug.Content[i] = 0;
            		successors.add(newJug);
            	}
            }
            
            //add all the states where each jug is filled respectively
            for(int j = 0; j < this.Content.length; j++) {
            	JugState newJug = new JugState(a,b,c,d,e,f);
            	if(newJug.Content[j] != newJug.Capacity[j]) {
            		newJug.Content[j] = newJug.Capacity[j];
            		successors.add(newJug);
            	}
            }
            
            for(int k = 0; k < this.Content.length; k++) {
            	for(int l = 0; l < this.Content.length; l++) {
            		
            		//skip if the jug tries to fill itself
            		if(l == k) {
            			continue;
            		}
            		
            		
            		JugState newJug = new JugState(a,b,c,d,e,f);
            		int pouredState = k; //cup that the pourer pours in
            		int pourerState = l; //the cup that pours
            		
            		boolean pouredFull = newJug.Content[pouredState] == newJug.Capacity[pouredState];
            		boolean pourerEmpty = newJug.Content[pourerState] == 0;
            		
            		//skip if the cup pouring is empty of the cup being poured is full 
            		if(pouredFull || pourerEmpty) {
            			continue;
            		}
            		
            		
            		//the amount of empty space left in the cup being poured in
            		int pouredDifference = newJug.Capacity[pouredState] - newJug.Content[pouredState];
            		
            		//if there is more content in the cup pouring then the empty space,
            		//fill the cup being poured in and subtract the difference from the cup
            		//being poured 
            		
            		
            		if(newJug.Content[pourerState] > pouredDifference) {
            			newJug.Content[pouredState] = newJug.Capacity[pouredState];
            			newJug.Content[pourerState] = newJug.Content[pourerState] - pouredDifference;
            			successors.add(newJug);
            		} else {
            			
            			//else if there is more empty space
            			//empty the cup that is pouring
            			//and fill the cup being poured in with all of the contents
            			//of the former pouring cup.
            			
            			
            			newJug.Content[pouredState] = newJug.Content[pouredState] + newJug.Content[pourerState];
            			newJug.Content[pourerState] = 0;
            			successors.add(newJug);
            		}
            		
            		
            		
            	}
            }
            

            return successors;
        }
    }

    public static void main(String[] args) {
        if( args.length != 6 )
        {
            System.out.println("Usage: java successor [A] [B] [C] [a] [b] [c]");
            return;
        }

        // parse command line arguments
        JugState a = new JugState();
        a.Capacity[0] = Integer.parseInt(args[0]);
        a.Capacity[1] = Integer.parseInt(args[1]);
        a.Capacity[2] = Integer.parseInt(args[2]);
        a.Content[0] = Integer.parseInt(args[3]);
        a.Content[1] = Integer.parseInt(args[4]);
        a.Content[2] = Integer.parseInt(args[5]);

        // Implement this function
        ArrayList<JugState> asist = a.getNextStates();

        // Print out generated successors
        for(int i=0;i< asist.size(); i++)
        {
            asist.get(i).printContent();
        }

        return;
    }
}