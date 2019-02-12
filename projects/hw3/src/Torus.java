//Christopher Chu HW 3 CS 540
//Yingyu Liang
//the only source used for this is stack overflow.

import java.util.*;

class State { 
	int[] board;
	State parentPt;
	int depth;

	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}

	public State[] getSuccessors() {
		
		// TO DO: get all four successors and return them in sorted order
			State[] successors = new State[4];
			State newState;
			int emptyIndex = 0;
			
			for(int i = 0; i < board.length; i++) { //find the emptyindex
				if(board[i] == 0) {
					emptyIndex = i;
				}	
			}
			
			//add the successors 
					if(emptyIndex == 4) {
						successors[0] = move(4,1);
						successors[1] = move(4,3);
						successors[2] = move(4,5);
						successors[3] = move(4,7);
					} 
					if(emptyIndex == 1) {
						successors[0] = move(1,0);
						successors[1] = move(1,2);
						successors[2] = move(1,4);
						successors[3] = move(1,7);
					} 
					if (emptyIndex == 3) {
						successors[0] = move(3,0);
						successors[1] = move(3,4);
						successors[2] = move(3,5);
						successors[3] = move(3,6);
					} 
					
					if (emptyIndex == 5) {
						successors[0] = move(5,2);
						successors[1] = move(5,3);
						successors[2] = move(5,4);
						successors[3] = move(5,8);
					} 
					
					if(emptyIndex == 7){
						successors[0] = move(7,1);
						successors[1] = move(7,4);
						successors[2] = move(7,6);
						successors[3] = move(7,8);
					}
					
					
					if(emptyIndex == 0) {
						successors[0] = move(0,1);
						successors[1] = move(0,2);
						successors[2] = move(0,3);
						successors[3] = move(0,6);
					} 
					
					if (emptyIndex == 2) {
						successors[0] = move(2,0);
						successors[1] = move(2,1);
						successors[2] = move(2,5);
						successors[3] = move(2,8);
					} 
					
					if (emptyIndex == 6) {
						successors[0] = move(6,0);
						successors[1] = move(6,3);
						successors[2] = move(6,7);
						successors[3] = move(6,8);
					} 
					
					if (emptyIndex == 8){
						successors[0] = move(8,2);
						successors[1] = move(8,5);
						successors[2] = move(8,6);
						successors[3] = move(8,7);
					}
				
					for(int i = 0; i < successors.length; i++) {
						successors[i].parentPt = this;
						successors[i].depth = this.depth + 1;
					}
			
			
			
		return successors;
	}

	public void printState(int option) {
		// TO DO: print a torus State based on option (flag)
		if(option == 1 || option == 2 || option == 4) {
			System.out.println(getBoard());
		} else if (option == 3) {
			if(this.parentPt == null) {
				System.out.println(getBoard() + " parent " + "0 0 0 0 0 0 0 0 0" );
				return;
			}
			System.out.println(getBoard() + " parent " + this.parentPt.getBoard());
		}
		else {
			System.out.println(getBoard());
		}
		
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(State src) {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
	
	public boolean exists(List<State> temp) {
		for(int i = 0; i < temp.size(); i++) {
			if(this.equals(temp.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public State move(int empty, int neighbor) {
		State aState = new State(this.board);
		aState.board[empty] = aState.board[neighbor];
		aState.board[neighbor] = 0;
		return aState;
	}
	
	
	
}

public class Torus {

	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			int maxStackSize = Integer.MIN_VALUE;
			int num = 0;
			boolean isGoal = false;
			int loop = 0;
			int goalCheck = 0;
			

			// needed for Part E
			while (true) {	
				init = new State(board);
				stack = new Stack<>();
				prefix = new ArrayList<>();
				stack.push(init);
				if(option == 2 || option == 3) 
					init.printState(option);

				while (!stack.isEmpty()) {
					//TO DO: perform iterative deepening; implement prefix list
					State s = stack.pop();
					if(prefix.contains(s))
						continue;
					//if(!prefix.contains(s)) {
						int parentState = prefix.indexOf(s.parentPt);
						for(int i = parentState + 1; i < prefix.size(); i++) {
							prefix.remove(i);
							}
						prefix.add(s);
					//}
					if(s.depth <= cutoff) {
						
						
						if((option == 2 || option == 3) && !s.equals(init))
							s.printState(option);
						
						if(s.depth == cutoff && option == 4 && num == 0) {
							num = 1;
							
							for(int i = 0; i < prefix.size(); i++)
								prefix.get(i).printState(4);
						}
						
						isGoal = s.isGoalState();
						goalCheck++;
						if(s.isGoalState()) {
							//System.out.println("success!");
							break;

						}
							
						State[] successors = s.getSuccessors();
						for(int i = 0; i < successors.length; i++)
							if(!successors[i].exists(prefix))
								stack.push(successors[i]);
					}
					maxStackSize = Math.max(maxStackSize, stack.size());
					
					
					
					
				}
				
				
				
				if (option != 5)
					break;
				
				//TO DO: perform the necessary steps to start a new iteration
			        //       for Part E
				
				else {
					if(isGoal) {
						//System.out.println("found!");
						for(int i = 0; i < prefix.size(); i++) {
							prefix.get(i).printState(5);
						}
						
						System.out.println("Goal Check " + goalCheck);
						System.out.println("Max-stack-size " + maxStackSize);
						break;
					}
					cutoff++;
	//				System.out.println(cutoff);
					
					continue;
					
					
				}
				
				

			}
		}
	}
}
