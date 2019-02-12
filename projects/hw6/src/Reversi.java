
import java.util.*;
import java.util.ArrayList;

class State {
	char[] board;

	public State(char[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
	}

	public int getScore() {

		// TO DO: return game theoretic value of the board

		int num1s = 0;
		int num2s = 0;

		for (int i = 0; i < board.length; i++) {
			if (board[i] == '1') {
				num1s++;
			}
			if (board[i] == '2') {
				num2s++;
			}
		}

		if (num1s > num2s) {
			return 1;
		} else if (num1s < num2s) {
			return -1;
		}

		return 0;
	}

	public boolean isTerminal() {
		if (getSuccessors('1').length == 0 && getSuccessors('2').length == 0) {
			return true;
		}

		return false;

	}

	public State[] getSuccessors(char player) {

//		if (isTerminal()) {
//			return null;
//		}

		ArrayList<State> Successors = new ArrayList<State>();
		char[][] State;
		State = new char[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				State[i][j] = this.board[4 * i + j];
				//System.out.print(State[i][j] + " ");
			}
			//System.out.println();
		}
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (State[x][y] == '0') {
					boolean moveLegal = false;
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							if (i == 0 && j == 0)
								continue;
							boolean piecesToFlip = false;
							boolean passedOpponent = false;
							int k = 1;
							while (x + j * k >= 0 && x + j * k < 4 && y + i * k >= 0 && y + i * k < 4) {
								if (State[x + j * k][y + i * k] == '0')
									break;
								if (State[x + j * k][y + i * k] == player && !passedOpponent)
									break;
								if (State[x + j * k][y + i * k] == player && passedOpponent) {
									piecesToFlip = true;
									break;
								} else if (State[x + j * k][y + i * k] == (player - '0') % 2 + '1') {
									passedOpponent = true;
									k++;
								}
							}
							if (piecesToFlip) {
								State[x][y] = player;
								for (int h = 1; h < k; h++) {
									State[x + j * h][y + i * h] = player;
								}
								moveLegal = true;
							}
						}
					}
					if (moveLegal) {
						char[] State3;
						State3 = new char[16];
						for (int q = 0; q < 4; q++)
							for (int w = 0; w < 4; w++) {
								State3[q * 4 + w] = State[q][w]; // transfer back
							}
						State addState = new State(State3);
						Successors.add(addState);
						for (int i1 = 0; i1 < 4; i1++) {
							for (int j1 = 0; j1 < 4; j1++) {
								State[i1][j1] = this.board[4 * i1 + j1];
							}
						}
					}
				}
			}
		}
		State[] StateArray = new State[Successors.size()];
		for (int i = 0; i < Successors.size(); i++) {
			StateArray[i] = Successors.get(i);
			System.out.println(StateArray[i].board);
		}
		return StateArray;
	}

	public void printState(int option, char player) {

		// TO DO: print a State based on option (flag)
		System.out.println(option);
 		if (option == 1) {
			getSuccessors(player);
		}

		if (option == 2) {
			if(isTerminal()) {
				System.out.println(getScore());
			} else {
				System.out.println("non-terminal");
			}
		}
		
		if(option == 3) {
			System.out.println(Minimax.run(this, player));
		}
		
		
		
		
		

	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			builder.append(this.board[i]);
		}
		return builder.toString().trim();
	}

	public boolean equals(State src) {
		for (int i = 0; i < 16; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
}

class Minimax {

	static int counter = 0;

	private static int max_value(State curr_state, char player) {
		char opponent = '0';
		if(player == '1') {
			opponent = '2';
		}
		
		else if(player == '2') {
			opponent = '1';
		}
		counter++;
		// TO DO: implement Max-Value of the Minimax algorithm
		if(curr_state.isTerminal()) {
			return curr_state.getScore();
		} 
		
		State[] Successors = curr_state.getSuccessors(player);
		int val = Integer.MIN_VALUE;
		
		for(int i = 0; i < Successors.length; i++) {
			State s = Successors[i];
			val = Math.max(val, min_value(s, opponent));
		}
		
		return val;
	}

	private static int min_value(State curr_state, char player) {
		counter++;
		char opponent = '0';
		if(player == '1') {
			opponent = '2';
		}
		
		else if(player == '2') {
			opponent = '1';
		}
		
		// TO DO: implement Max-Value of the Minimax algorithm
		if(curr_state.isTerminal()) {
			return curr_state.getScore();
		} 
		
		State[] Successors = curr_state.getSuccessors(player);
		int val = Integer.MAX_VALUE;
		
		for(int i = 0; i < Successors.length; i++) {
			State s = Successors[i];
			val = Math.min(val, max_value(s, opponent));
		}
		
		return val;
	}

	private static int max_value_with_pruning(State curr_state, int alpha, int beta, char player) {
		
		char opponent = '0';
		if(player == '1') {
			opponent = '2';
		}
		
		else if(player == '2') {
			opponent = '1';
		}
		++counter;
		int val = Integer.MIN_VALUE;
		if(curr_state.isTerminal()) {
			return curr_state.getScore();
		}
		
		State[] successors = curr_state.getSuccessors(player);
		
		for(int i = 0; i < successors.length; i++){
			
			State s = successors[i];
			val = Math.min(val, min_value_with_pruning(s, alpha, beta, opponent));
			
		}

		// TO DO: implement Max-Value of the alpha-beta pruning algorithm
		return 0;
	}

	private static int min_value_with_pruning(State curr_state, int alpha, int beta, char player) {

		char opponent = '0';
		if(player == '1') {
			opponent = '2';
		}
		
		else if(player == '2') {
			opponent = '1';
		}
		++counter;
		int val = Integer.MAX_VALUE;
		if(curr_state.isTerminal()) {
			return curr_state.getScore();
		}
		
		State[] successors = curr_state.getSuccessors(player);
		
		for(int i = 0; i < successors.length; i++){
			
			State s = successors[i];
			val = Math.max(val, max_value_with_pruning(s, alpha, beta, opponent));
			
		}

		// TO DO: implement Max-Value of the alpha-beta pruning algorithm
		return 0;
	}

	public static int run(State curr_state, char player) {

		// TO DO: run the Minimax algorithm and return the game theoretic value
		int result = 0;
		if(player == '1') {
			result = max_value(curr_state, player);
		}
		if(player == '2') {
			result = min_value(curr_state,player);
		}
		System.out.println(result);
		return counter;
	}

	public static int run_with_pruning(State curr_state, int alpha, int beta, char player) {

		// TO DO: run the alpha-beta pruning algorithm and return the game theoretic
		// value 
		// TO DO: run the Minimax algorithm and return the game theoretic value
				int result = 0;
				if(player == '1') {
					result =  max_value_with_pruning(curr_state, alpha, beta, player);
				}
				if(player == '2') {
					result =  min_value_with_pruning(curr_state, alpha, beta, player);
				}
				System.out.println(result);
				return counter;

	}
}

public class Reversi {
	public static void main(String args[]) {
		if (args.length != 3) {
			System.out.println("Invalid Number of Input Arguments");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		char[] board = new char[16];
		for (int i = 0; i < 16; i++) {
			board[i] = args[2].charAt(i);
			// System.out.println(board[i]);
		}
		int option = flag / 100;
		char player = args[1].charAt(0);
		if ((player != '1' && player != '2') || args[1].length() != 1) {
			System.out.println("Invalid Player Input");
			return;
		}
		State init = new State(board);
		init.printState(option, player);
	}
}