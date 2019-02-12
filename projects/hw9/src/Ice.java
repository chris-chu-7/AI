import java.io.*;
import java.util.*;

public class Ice {
	
	private static Scanner reader;

	static ArrayList<iceDate> dates = new ArrayList<iceDate>();
	
	public static void main(String[] args) {
		double officialZero = 0;
		double officialFirst = 0;
		// TODO Auto-generated method stub
		String flag = args[0];
		try {
			reader = new Scanner(new File("aice.txt"));
			int number = 0;
			while(reader.hasNext()) {
				int num1 = reader.nextInt();
				int num2 = reader.nextInt();
				iceDate newDate = new iceDate();
				newDate.date = num1;
				newDate.days = num2;
				dates.add(newDate);
				number++;
			}
		} catch (Exception e) {
			System.out.println("file not found");
		}
		
		if(flag.equals("100")) {
			for(int i = 0; i < dates.size(); i++) {
				System.out.println(dates.get(i).date + " " + dates.get(i).days);
			}
		}
		
		if(flag.equals("200")) {
			//determine the sample mean
			int numPoints = dates.size();
			System.out.println(numPoints);
			int sum = 0;
			for(int i = 0; i < dates.size(); i++) {
				sum = sum + dates.get(i).days;
			}
			double mean = sum / numPoints;
			System.out.printf("%.2f\n", mean);
			
			//find the standard deviation
			int totalDifference = 0;
			for(int i = 0; i < dates.size(); i++) {
				int difference = (int) Math.pow(dates.get(i).days - mean, 2);
				totalDifference += difference;
			}
			double deviation = Math.pow(totalDifference / (numPoints - 1), 0.5);
			System.out.printf("%.2f", deviation);
		}
		
		if(flag.equals("300")) {
			//find the mean square error
			double numPoints = dates.size();
			double inverse = 1/numPoints;
			double beta0 = Double.parseDouble(args[1]);
			double beta1 = Double.parseDouble(args[2]);
			double sum = 0;
			double MSE = 0;
			for(int i = 0; i < dates.size(); i++) {
				sum += Math.pow(beta0 + beta1 * dates.get(i).date - dates.get(i).days, 2);
			}
			MSE = sum * inverse;
			System.out.printf("%.2f", MSE);
		}
		
		
		if(flag.equals("400")) {
			double numPoints = dates.size();
			double inverse = 2/numPoints;
			double beta0 = Double.parseDouble(args[1]);
			double beta1 = Double.parseDouble(args[2]);
			double sum0 = 0;
			double sum1 = 0;
			for(int i = 0; i < dates.size(); i++) {
				sum0 += beta0 + beta1 * dates.get(i).date - dates.get(i).days;
				sum1 += (beta0 + beta1 * dates.get(i).date - dates.get(i).days) * dates.get(i).date;
			}
			double grad0 = sum0 * inverse;
			double grad1 = sum1 * inverse;
			System.out.printf("%.2f\n", grad0);
			System.out.printf("%.2f\n", grad1);
		}
		
		
		if(flag.equals("500")) {
			double n = Double.parseDouble(args[1]);
			double t = Double.parseDouble(args[2]);
			double[] betaZeros = new double[(int) t + 1];
			double[] betaOnes = new double[(int) t + 1];
			double betaZero = 0;
			double betaOne = 0;
			
			for(int i = 1; i < t + 1; i++) {
				betaZeros[i] = betaZeros[i - 1] - n * delMSE(betaZeros[i-1], betaOnes[i - 1])[0];
				betaOnes[i] = betaOnes[i - 1] - n * delMSE(betaZeros[i-1], betaOnes[i - 1])[1];
			}
			
			for(int i = 0; i < t; i++) {
				System.out.print(i+1 + " ");
				System.out.printf("%.2f %.2f %.2f\n", betaZeros[i + 1], betaOnes[i+1], MSE(betaZeros[i + 1], betaOnes[i + 1]));
			}
			
			
		}
		
		if(flag.equals("600")) {
			//calculate mean x 
			double x = 0;
			double y = 0;
			for(int i = 0; i < dates.size(); i++) {
				x += dates.get(i).date;
				y += dates.get(i).days;
			}
			//mean calculated
			x /= dates.size();
			y /= dates.size();
			
			double sum = 0;
			double sumDiff = 0;
			
			for(int i = 0; i < dates.size(); i++) {
				double xDiff = dates.get(i).date - x;
				double yDiff = dates.get(i).days - y;
				sum += xDiff * yDiff;
				sumDiff += Math.pow(xDiff, 2);
			}
			
			double beta1 = sum / sumDiff;
			double beta0 = y - beta1 * x;
			System.out.printf("%.2f %.2f %.2f", beta0, beta1, MSE(beta0, beta1));
			
		}
		
		if(flag.equals("700")) {
			double x = 0;
			double y = 0;
			for(int i = 0; i < dates.size(); i++) {
				x += dates.get(i).date;
				y += dates.get(i).days;
			}
			//mean calculated
			x /= dates.size();
			y /= dates.size();
			
			double sum = 0;
			double sumDiff = 0;
			
			for(int i = 0; i < dates.size(); i++) {
				double xDiff = dates.get(i).date - x;
				double yDiff = dates.get(i).days - y;
				sum += xDiff * yDiff;
				sumDiff += Math.pow(xDiff, 2);
			}
			
			double beta1 = sum / sumDiff;
			double beta0 = y - beta1 * x;
			int year = Integer.parseInt(args[1]);
			System.out.printf("%.2f\n", beta0 + beta1 * year);
		}
		
		if(flag.equals("800")) {
			double n = Double.parseDouble(args[1]);
			double t = Double.parseDouble(args[2]);
			double sumx = 0;
			
			//calculate the mean of x
			for(int i = 0; i < dates.size(); i++) {
				sumx += dates.get(i).date;
			}
			
			double mean = sumx /= dates.size();
			//calculating the standard deviation of x 
			double multiplier = 1/(dates.size() - 1);
			double sumDiffSquared = 0;
			for(int i = 0; i < dates.size(); i++) {
				double difference = Math.pow(dates.get(i).date - mean, 2);
				sumDiffSquared += difference;
			}
			double std = Math.pow(sumDiffSquared/(dates.size() - 1), 0.5);
			ArrayList<newDate> date = new ArrayList<newDate>();
			for(int i = 0; i < dates.size(); i++) {
				int days = dates.get(i).days;
				double aDate = (dates.get(i).date - mean)/std;
				//System.out.println(aDate);
				newDate aNewDate = new newDate();
				aNewDate.aDate = aDate;
				aNewDate.aDays = days;
				date.add(aNewDate);
			}
			
			//perform gradient descent 
			double[] betaZeros = new double[(int) t + 1];
			double[] betaOnes = new double[(int) t + 1];
			double betaZero = 0;
			double betaOne = 0;
			
			for(int i = 1; i < t + 1; i++) {
				betaZeros[i] = betaZeros[i - 1] - n * newdelMSE(betaZeros[i-1], betaOnes[i - 1], date)[0];
				betaOnes[i] = betaOnes[i - 1] - n * newdelMSE(betaZeros[i-1], betaOnes[i - 1],date)[1];
			}
			
			for(int i = 0; i < t; i++) {
				System.out.print(i+1 + " ");
				System.out.printf("%.2f %.2f %.2f\n", betaZeros[i + 1], betaOnes[i+1], newMSE(betaZeros[i + 1], betaOnes[i + 1], date));
			}
			
			
			
		}
		
		if(flag.equals("900")) {
			//can't figure this out
			double n = Double.parseDouble(args[1]);
			double t = Double.parseDouble(args[2]);
			double sumx = 0;
			
			//calculate the mean of x
			for(int i = 0; i < dates.size(); i++) {
				sumx += dates.get(i).date;
			}
			
			double mean = sumx /= dates.size();
			//calculating the standard deviation of x 
			double multiplier = 1/(dates.size() - 1);
			double sumDiffSquared = 0;
			for(int i = 0; i < dates.size(); i++) {
				double difference = Math.pow(dates.get(i).date - mean, 2);
				sumDiffSquared += difference;
			}
			double std = Math.pow(sumDiffSquared/(dates.size() - 1), 0.5);
			ArrayList<newDate> date = new ArrayList<newDate>();
			for(int i = 0; i < dates.size(); i++) {
				int days = dates.get(i).days;
				double aDate = (dates.get(i).date - mean)/std;
				//System.out.println(aDate);
				newDate aNewDate = new newDate();
				aNewDate.aDate = aDate;
				aNewDate.aDays = days;
				date.add(aNewDate);
			}
			
			//perform gradient descent 
			double[] betaZeros = new double[(int) t + 1];
			double[] betaOnes = new double[(int) t + 1];
			double betaZero = 0;
			double betaOne = 0;
			
			for(int i = 1; i < t + 1; i++) {
				betaZeros[i] = betaZeros[i - 1] - n * finalDelMSE(betaZeros[i - 1], betaOnes[i - 1], date.get(i))[0];
				betaOnes[i] = betaZeros[i - 1] - n * finalDelMSE(betaZeros[i - 1], betaOnes[i - 1], date.get(i))[1];
			}
			
			for(int i = 0; i < t; i++) {
				System.out.print(i+1 + " ");
				System.out.printf("%.2f %.2f %.2f\n", betaZeros[i + 1], betaOnes[i+1], newMSE(betaZeros[i + 1], betaOnes[i + 1], date));
			}
		}
		
		

	}
	
	public static double[] delMSE(double beta0, double beta1) {
		double numPoints = dates.size();
		double inverse = 2/numPoints;
		double sum0 = 0;
		double sum1 = 0;
		for(int i = 0; i < dates.size(); i++) {
			sum0 += beta0 + beta1 * dates.get(i).date - dates.get(i).days;
			sum1 += (beta0 + beta1 * dates.get(i).date - dates.get(i).days) * dates.get(i).date;
		}
		double grad0 = sum0 * inverse;
		double grad1 = sum1 * inverse;
		double[] MSEArray = new double[2];
		MSEArray[0] = grad0;
		MSEArray[1] = grad1;
		return MSEArray;
	}
	
	public static double MSE(double beta0, double beta1)
	{
		double numPoints = dates.size();
		double inverse = 1/numPoints;
		double sum = 0;
		double MSE = 0;
		for(int i = 0; i < dates.size(); i++) {
			sum += Math.pow(beta0 + beta1 * dates.get(i).date - dates.get(i).days, 2);
		}
		MSE = sum * inverse;
		return MSE;
	}
	
	public static double newMSE(double beta0, double beta1, ArrayList<newDate> aList) {
		double numPoints = aList.size();
		double inverse = 1/numPoints;
		double sum = 0;
		double MSE = 0;
		for(int i = 0; i < aList.size(); i++) {
			sum += Math.pow(beta0 + beta1 * aList.get(i).aDate - aList.get(i).aDays, 2);
		}
		MSE = sum * inverse;
		return MSE;
	}
	
	public static double[] newdelMSE(double beta0, double beta1, ArrayList<newDate> aList) {
		double numPoints = aList.size();
		double inverse = 2/numPoints;
		double sum0 = 0;
		double sum1 = 0;
		for(int i = 0; i < aList.size(); i++) {
			sum0 += beta0 + beta1 * aList.get(i).aDate - aList.get(i).aDays;
			sum1 += (beta0 + beta1 * aList.get(i).aDate - aList.get(i).aDays) * aList.get(i).aDate;
		}
		double grad0 = sum0 * inverse;
		double grad1 = sum1 * inverse;
		double[] MSEArray = new double[2];
		MSEArray[0] = grad0;
		MSEArray[1] = grad1;
		return MSEArray;
	}
	
	public static double[] finalDelMSE(double beta0, double beta1, newDate date) {
		double delMSE0 = 2 * (beta0 + beta1 * date.aDate - date.aDays);
		double delMSE1 = 2 * date.aDate * (beta0 + beta1 * date.aDate - date.aDays);
		double[] results = new double[2];
		results[0] = delMSE0;
		results[1] = delMSE1;
		return results;
	}
}
