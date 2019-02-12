//Christopher Chu
//Yingyu Liang AI Program Homework
//this is my work only confirmed. 

import java.io.*;
import java.util.Scanner;

public class Neural {

	private static Scanner reader;
	
	public static void main(String[] args) throws FileNotFoundException {
		String flag = args[0];
		

		if(flag.equals("100")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double ua = w1 + x1 * w2 + x2 * w3;
			double va = reLU(ua);
			double ub = w4 + x1 * w5 + x2 * w6;
			double vb = reLU(ub);
			double uc = w7 + va * w8 + vb * w9;
			double vc = sigmoid(uc);
			System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f", ua, va, ub, vb, uc, vc);
		}
		
		if(flag.equals("200")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double ua = w1 + x1 * w2 + x2 * w3;
			double va = reLU(ua);
			double ub = w4 + x1 * w5 + x2 * w6;
			double vb = reLU(ub);
			double uc = w7 + va * w8 + vb * w9;
			double vc = sigmoid(uc);
			double y = Double.parseDouble(args[12]);
			double error = 0.5 * Math.pow(vc - y, 2);
			double dev = vc - y;
			double deu = vc * (1 - vc) * dev;
			System.out.printf("%.5f %.5f %.5f", error, dev, deu);
		}
		
		if(flag.equals("300")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double ua = w1 + x1 * w2 + x2 * w3;
			double va = reLU(ua);
			double ub = w4 + x1 * w5 + x2 * w6;
			double vb = reLU(ub);
			double uc = w7 + va * w8 + vb * w9;
			double vc = sigmoid(uc);
			double y = Double.parseDouble(args[12]);
			double error = 0.5 * Math.pow(vc - y, 2);
			double dev = vc - y;
			double deu = vc * (1 - vc) * dev;
			double deva = w8 * deu;
			double deua = deva * discretereLU(ua);
			double devb = w9 * deu;
			double deub = devb * discretereLU(ub);
			System.out.printf("%.5f %.5f %.5f %.5f", deva, deua, devb, deub);	
		}
		
		if(flag.equals("400")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double ua = w1 + x1 * w2 + x2 * w3;
			double va = reLU(ua);
			double ub = w4 + x1 * w5 + x2 * w6;
			double vb = reLU(ub);
			double uc = w7 + va * w8 + vb * w9;
			double vc = sigmoid(uc);
			double y = Double.parseDouble(args[12]);
			double error = 0.5 * Math.pow(vc - y, 2);
			double dev = vc - y;
			double deu = vc * (1 - vc) * dev;
			double deva = w8 * deu;
			double deua = deva * discretereLU(ua);
			double devb = w9 * deu;
			double deub = devb * discretereLU(ub);
			double dew1 = deua;
			double dew2 = deua * x1;
			double dew3 = deua * x2;
			double dew4 = deub;
			double dew5 = deub * x1;
			double dew6 = deub * x2;
			double dew7 = deu;
			double dew8 = deu * va;
			double dew9 = deu * vb;
			System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f", dew1, dew2, dew3, dew4, dew5, dew6, dew7, dew8, dew9);
		}
		
		if(flag.equals("500")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double y = Double.parseDouble(args[12]);
			double n = Double.parseDouble(args[13]);
			double ua = w1 + x1 * w2 + x2 * w3;
			double va = reLU(ua);
			double ub = w4 + x1 * w5 + x2 * w6;
			double vb = reLU(ub);
			double uc = w7 + va * w8 + vb * w9;
			double vc = sigmoid(uc);
			double error = 0.5 * Math.pow(vc - y, 2);
			double dev = vc - y;
			double deu = vc * (1 - vc) * dev;
			double deva = w8 * deu;
			double deua = deva * discretereLU(ua);
			double devb = w9 * deu;
			double deub = devb * discretereLU(ub);
			double dew1 = deua;
			double dew2 = deua * x1;
			double dew3 = deua * x2;
			double dew4 = deub;
			double dew5 = deub * x1;
			double dew6 = deub * x2;
			double dew7 = deu;
			double dew8 = deu * va;
			double dew9 = deu * vb;
			double nw1 = w1 - n * dew1;
			double nw2 = w2 - n * dew2;
			double nw3 = w3 - n * dew3;
			double nw4 = w4 - n * dew4;
			double nw5 = w5 - n * dew5;
			double nw6 = w6 - n * dew6;
			double nw7 = w7 - n * dew7;
			double nw8 = w8 - n * dew8;
			double nw9 = w9 - n * dew9;
			
			double nua = nw1 + x1 * nw2 + x2 * nw3;
			double nva = reLU(nua);
			double nub = nw4 + x1 * nw5 + x2 * nw6;
			double nvb = reLU(nub);
			double nuc = nw7 + nva * nw8 + nvb * nw9;
			double nvc = sigmoid(nuc);
			
			double oldError = 0.5 * Math.pow(vc - y, 2);
			double newError = 0.5 * Math.pow(nvc - y, 2);
			
			System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", w1, w2, w3, w4, w5, w6, w7, w8, w9);
			System.out.printf("%.5f\n", oldError);
			System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", nw1, nw2, nw3, nw4, nw5, nw6, nw7, nw8, nw9);
			System.out.printf("%.5f\n", newError);
		}
		
		if(flag.equals("600")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
	
			double n = Double.parseDouble(args[10]);
			reader = new Scanner(new File("hw2_midterm_A_train.txt"));
			for(int i = 0; i < 67; i++) {
				Scanner eval = new Scanner(new File("hw2_midterm_A_eval.txt"));

				double x1 = reader.nextDouble();
				double x2 = reader.nextDouble();
				double y = reader.nextDouble();
				
				System.out.printf("%.5f %.5f %.5f\n", x1, x2, y);
				double ua = w1 + x1 * w2 + x2 * w3;
				double va = reLU(ua);
				double ub = w4 + x1 * w5 + x2 * w6;
				double vb = reLU(ub);
				double uc = w7 + va * w8 + vb * w9;
				double vc = sigmoid(uc);
				double error = 0.5 * Math.pow(vc - y, 2);
				double dev = vc - y;
				double deu = vc * (1 - vc) * dev;
				double deva = w8 * deu;
				double deua = deva * discretereLU(ua);
				double devb = w9 * deu;
				double deub = devb * discretereLU(ub);
				double dew1 = deua;
				double dew2 = deua * x1;
				double dew3 = deua * x2;
				double dew4 = deub;
				double dew5 = deub * x1;
				double dew6 = deub * x2;
				double dew7 = deu;
				double dew8 = deu * va;
				double dew9 = deu * vb;
				double nw1 = w1 - n * dew1;
				double nw2 = w2 - n * dew2;
				double nw3 = w3 - n * dew3;
				double nw4 = w4 - n * dew4;
				double nw5 = w5 - n * dew5;
				double nw6 = w6 - n * dew6;
				double nw7 = w7 - n * dew7;
				double nw8 = w8 - n * dew8;
				double nw9 = w9 - n * dew9;

				w1 = nw1;
				w2 = nw2;
				w3 = nw3;
				w4 = nw4;
				w5 = nw5;
				w6 = nw6;
				w7 = nw7;
				w8 = nw8;
				w9 = nw9;

				double totalError = 0;
				for(int j = 0; j < 25; j++) {
					double ex1 = eval.nextDouble();
					double ex2 = eval.nextDouble();
					double ey = eval.nextDouble();
					double eua = w1 + ex1 * w2 + ex2 * w3;
					double eva = reLU(eua);
					double eub = w4 + ex1 * w5 + ex2 * w6;
					double evb = reLU(eub);
					double euc = w7 + eva * w8 + evb * w9;
					double evc = sigmoid(euc);
					double eerror = 0.5 * Math.pow(evc - ey, 2);
					totalError += eerror;
				}
				double nua = nw1 + x1 * nw2 + x2 * nw3;
				double nva = reLU(nua);
				double nub = nw4 + x1 * nw5 + x2 * nw6;
				double nvb = reLU(nub);
				double nuc = nw7 + nva * nw8 + nvb * nw9;
				double nvc = sigmoid(nuc);
				
				double oldError = 0.5 * Math.pow(vc - y, 2);
				double newError = 0.5 * Math.pow(nvc - y, 2);
				
				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", nw1, nw2, nw3, nw4, nw5, nw6, nw7, nw8, nw9);

				System.out.printf("%.5f\n", totalError);
			
			}
		}
		
		
		
		if(flag.equals("700")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double n = Double.parseDouble(args[10]);
			double T = Double.parseDouble(args[11]);
			
			double realerror = 0;
			
			
			double f1 = 0;
			double f2 = 0;
			double f3 = 0;
			double f4 = 0;
			double f5 = 0;
			double f6 = 0;
			double f7 = 0;
			double f8 = 0;
			double f9 = 0;
			
			for(int j = 0; j < T; j++) {
				reader = new Scanner(new File("hw2_midterm_A_train.txt"));
				for(int i = 0; i < 67; i++) {
					Scanner eval = new Scanner(new File("hw2_midterm_A_eval.txt"));
					double x1 = reader.nextDouble();
					double x2 = reader.nextDouble();
					double y = reader.nextDouble();
					double ua = w1 + x1 * w2 + x2 * w3;
					double va = reLU(ua);
					double ub = w4 + x1 * w5 + x2 * w6;
					double vb = reLU(ub);
					double uc = w7 + va * w8 + vb * w9;
					double vc = sigmoid(uc);
					double error = 0.5 * Math.pow(vc - y, 2);
					double dev = vc - y;
					double deu = vc * (1 - vc) * dev;
					double deva = w8 * deu;
					double deua = deva * discretereLU(ua);
					double devb = w9 * deu;
					double deub = devb * discretereLU(ub);
					double dew1 = deua;
					double dew2 = deua * x1;
					double dew3 = deua * x2;
					double dew4 = deub;
					double dew5 = deub * x1;
					double dew6 = deub * x2;
					double dew7 = deu;
					double dew8 = deu * va;
					double dew9 = deu * vb;
					double nw1 = w1 - n * dew1;
					double nw2 = w2 - n * dew2;
					double nw3 = w3 - n * dew3;
					double nw4 = w4 - n * dew4;
					double nw5 = w5 - n * dew5;
					double nw6 = w6 - n * dew6;
					double nw7 = w7 - n * dew7;
					double nw8 = w8 - n * dew8;
					double nw9 = w9 - n * dew9;

					w1 = nw1;
					w2 = nw2;
					w3 = nw3;
					w4 = nw4;
					w5 = nw5;
					w6 = nw6;
					w7 = nw7;
					w8 = nw8;
					w9 = nw9;

					double totalError = 0;
					for(int k = 0; k < 25; k++) {
						double ex1 = eval.nextDouble();
						double ex2 = eval.nextDouble();
						double ey = eval.nextDouble();
						double eua = w1 + ex1 * w2 + ex2 * w3;
						double eva = reLU(eua);
						double eub = w4 + ex1 * w5 + ex2 * w6;
						double evb = reLU(eub);
						double euc = w7 + eva * w8 + evb * w9;
						double evc = sigmoid(euc);
						double eerror = 0.5 * Math.pow(evc - ey, 2);
						totalError += eerror;
					}
					double nua = nw1 + x1 * nw2 + x2 * nw3;
					double nva = reLU(nua);
					double nub = nw4 + x1 * nw5 + x2 * nw6;
					double nvb = reLU(nub);
					double nuc = nw7 + nva * nw8 + nvb * nw9;
					double nvc = sigmoid(nuc);
					
					double oldError = 0.5 * Math.pow(vc - y, 2);
					double newError = 0.5 * Math.pow(nvc - y, 2);
					
					
					
					f1 = w1;
					f2 = w2; 
					f3 = w3;
					f4 = w4;
					f5 = w5; 
					f6 = w6;
					f7 = w7;
					f8 = w8; 
					f9 = w9;
					
					
					realerror = totalError;
				
				}
				
				
				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", f1, f2, f3, f4, f5, f6, f7, f8, f9);
				System.out.printf("%.5f\n", realerror);
			}
			

		}
		
		if(flag.equals("800")) {
			double w1 = Double.parseDouble(args[1]);
			double w2 = Double.parseDouble(args[2]);
			double w3 = Double.parseDouble(args[3]);
			double w4 = Double.parseDouble(args[4]);
			double w5 = Double.parseDouble(args[5]);
			double w6 = Double.parseDouble(args[6]);
			double w7 = Double.parseDouble(args[7]);
			double w8 = Double.parseDouble(args[8]);
			double w9 = Double.parseDouble(args[9]);
			double n = Double.parseDouble(args[10]);
			double T = Double.parseDouble(args[11]);
			
			double realerror = Integer.MIN_VALUE;
			double aerror = Integer.MAX_VALUE;
			
			
			double f1 = 0;
			double f2 = 0;
			double f3 = 0;
			double f4 = 0;
			double f5 = 0;
			double f6 = 0;
			double f7 = 0;
			double f8 = 0;
			double f9 = 0;
			
			//solution weights
			double sol1 = 0;
			double sol2 = 0;
			double sol3 = 0;
			double sol4 = 0;
			double sol5 = 0;
			double sol6 = 0;
			double sol7 = 0;
			double sol8 = 0;
			double sol9 = 0;
			
			
			int numepochs = 0;
			
			for(int j = 0; j < T; j++) {
				reader = new Scanner(new File("hw2_midterm_A_train.txt"));
				for(int i = 0; i < 67; i++) {
					Scanner eval = new Scanner(new File("hw2_midterm_A_eval.txt"));
					double x1 = reader.nextDouble();
					double x2 = reader.nextDouble();
					double y = reader.nextDouble();
					double ua = w1 + x1 * w2 + x2 * w3;
					double va = reLU(ua);
					double ub = w4 + x1 * w5 + x2 * w6;
					double vb = reLU(ub);
					double uc = w7 + va * w8 + vb * w9;
					double vc = sigmoid(uc);
					double error = 0.5 * Math.pow(vc - y, 2);
					double dev = vc - y;
					double deu = vc * (1 - vc) * dev;
					double deva = w8 * deu;
					double deua = deva * discretereLU(ua);
					double devb = w9 * deu;
					double deub = devb * discretereLU(ub);
					double dew1 = deua;
					double dew2 = deua * x1;
					double dew3 = deua * x2;
					double dew4 = deub;
					double dew5 = deub * x1;
					double dew6 = deub * x2;
					double dew7 = deu;
					double dew8 = deu * va;
					double dew9 = deu * vb;
					double nw1 = w1 - n * dew1;
					double nw2 = w2 - n * dew2;
					double nw3 = w3 - n * dew3;
					double nw4 = w4 - n * dew4;
					double nw5 = w5 - n * dew5;
					double nw6 = w6 - n * dew6;
					double nw7 = w7 - n * dew7;
					double nw8 = w8 - n * dew8;
					double nw9 = w9 - n * dew9;

					w1 = nw1;
					w2 = nw2;
					w3 = nw3;
					w4 = nw4;
					w5 = nw5;
					w6 = nw6;
					w7 = nw7;
					w8 = nw8;
					w9 = nw9;

					double totalError = 0;
					for(int k = 0; k < 25; k++) {
						double ex1 = eval.nextDouble();
						double ex2 = eval.nextDouble();
						double ey = eval.nextDouble();
						double eua = w1 + ex1 * w2 + ex2 * w3;
						double eva = reLU(eua);
						double eub = w4 + ex1 * w5 + ex2 * w6;
						double evb = reLU(eub);
						double euc = w7 + eva * w8 + evb * w9;
						double evc = sigmoid(euc);
						double eerror = 0.5 * Math.pow(evc - ey, 2);
						totalError += eerror;
					}
					double nua = nw1 + x1 * nw2 + x2 * nw3;
					double nva = reLU(nua);
					double nub = nw4 + x1 * nw5 + x2 * nw6;
					double nvb = reLU(nub);
					double nuc = nw7 + nva * nw8 + nvb * nw9;
					double nvc = sigmoid(nuc);
					
					double oldError = 0.5 * Math.pow(vc - y, 2);
					double newError = 0.5 * Math.pow(nvc - y, 2);
					
					
					
					f1 = w1;
					f2 = w2; 
					f3 = w3;
					f4 = w4;
					f5 = w5; 
					f6 = w6;
					f7 = w7;
					f8 = w8; 
					f9 = w9;
					
					
					realerror = totalError;
				
				}
				
				numepochs++;
//				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", f1, f2, f3, f4, f5, f6, f7, f8, f9);
				if(realerror >= aerror) {
					System.out.println(numepochs);
					System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", f1, f2, f3, f4, f5, f6, f7, f8, f9);
					System.out.printf("%.5f\n", realerror);
					sol1 = f1;
					sol2 = f2;
					sol3 = f3; 
					sol4 = f4; 
					sol5 = f5; 
					sol6 = f6;
					sol7 = f7;
					sol8 = f8;
					sol9 = f9;
					break;
				}
				aerror = realerror;
//				if(aerror > realerror) {
//					break;
//				}
				//System.out.printf("%.5f\n", realerror);
			}
			
			//test the set weights.
			 Scanner tester = new Scanner(new File("hw2_midterm_A_test.txt"));
			 int numTrue = 0;
			for(int i = 0; i < 25; i++) {
				double input1 = tester.nextDouble();
				double input2 = tester.nextDouble();
				double input3 = tester.nextDouble();
				double ua = sol1 + input1 * sol2 + input2 * sol3;
				double va = reLU(ua);
				double ub = sol4 + input1 * sol5 + input2 * sol6;
				double vb = reLU(ub);
				double uc = sol7 + va * sol8 + vb * sol9;
				double vc = sigmoid(uc);
				if(vc > 0.5) {
					vc = 1;
				} else {
					vc = 0;
				}
				
				if(vc == input3) {
					numTrue++;
				}
			}
			
			double percent = (double) numTrue / 25.00000;
			System.out.printf("%.5f", percent);
			
			
		}
		
		
	
	}
	
	public static double reLU(double arg) {
		if(arg > 0) {
			return arg;
		}
		return 0;
	}
	
	public static double discretereLU(double arg) {
		if(arg >= 0) {
			return 1;
		}
		return 0;
	}
	
	public static double sigmoid(double arg) {
		return 1/(1 + Math.exp(-arg));
	}

}