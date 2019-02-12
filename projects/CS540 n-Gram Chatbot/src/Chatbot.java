import java.util.*;

import javax.swing.plaf.synth.SynthSpinnerUI;

import java.io.*;

public class Chatbot{
    private static String filename = "WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int counter = 0;
            
            //part a
            for(int i = 0; i < corpus.size(); i++) {
            	if(corpus.get(i) == w) {
            		counter++;
            	}
            }
            
            System.out.println(counter);
            System.out.println(String.format("%.7f",counter/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double ri = (double)n1/n2;
            
            //TODO generate
            //part b
            double sigma = 0;
            int i=0;
            double p0 = 0.0;
            for(i=0;sigma<ri;i++) {
            	int counter = 0;
            	for(int k=0;k<corpus.size();k++) {
                	if(corpus.get(k)==i)
                		counter++;
                }
            	p0=counter/(double)corpus.size();
            	sigma=sigma+p0;
            }
            System.out.println(i-1);
            System.out.println(String.format("%.7f",sigma-p0));
            System.out.println(String.format("%.7f",sigma));

        }

        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int counter = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO
            for(int i = 0; i < corpus.size() - 1; i++) {
            	if(corpus.get(i) == h && corpus.get(i + 1) == w) {
            		counter++;
            	}
            }
            
            for(int j = 0; j < corpus.size() - 1; j++) {
            	if(corpus.get(j) == h) {
            		words_after_h.add(h + 1); //add adjacent words 
            	}
            }

            //output 
            System.out.println(counter);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",counter/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            double r = (double) n1/n2;
            //TODO
            int sigma = 0;
            int counter = 0;
            int sigma2 = 0;
            for(int i = 0; i < corpus.size() - 1; i++) {
            	if(h == corpus.get(i)) {
            		counter++;
            	}
            }
            for(int i = 0; i < corpus.size(); i++) {
            	for(int j = 0; j < corpus.size() - 1; j++) {
            		if(corpus.get(j) == h && corpus.get(j + 1) == i) {
            			sigma++;
            		}
            	}
            	if((double)sigma/counter > r) {
            		System.out.println(i);
            		System.out.println(String.format("%.7f",sigma2/(double)counter));
            		System.out.println(String.format("%.7f",sigma/(double)counter));
            		break;
            	}
            	sigma2 = sigma;
            }
            
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int counter = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO
            for(int i=0;i<corpus.size()-2;i++) {
            	if(corpus.get(i)==h1 && corpus.get(i+1)==h2 && corpus.get(i+2)==w) {
            		counter++;
            		}
            }
            for(int i=0;i<corpus.size()-2;i++) {
            	if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            		words_after_h1h2.add(corpus.get(i+2));
            	}
            }

            //output 
            System.out.println(counter);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",counter/(double)words_after_h1h2.size()));
        }

        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
            double r=n1/(double)n2;
            int sigma=0;
            int sigma2=0;
            int counter=0;
            for(int i=0;i<corpus.size()-2;i++) {
            		if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            			counter++;
            		}
            }
            if(counter ==0) {
            	System.out.println("undefined");
            }
            else {
            for(int i=0;i<corpus.size()-2;i++) {
            		for(int j=0;j<corpus.size()-2;j++) {
            			if(corpus.get(j)==h1 && corpus.get(j+1)==h2 && corpus.get(j+2)==i) {
            				sigma++;
            			}
            		}
            		if((double)sigma/counter>r) {
            			System.out.println(i);
            			System.out.println(String.format("%.7f",sigma2/(double)counter));
            			System.out.println(String.format("%.7f",sigma/(double)counter));
            			break;}
            		sigma2=sigma;
            }
            }
            
        }

        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);
            if(t == 0){
                // TODO Generate first word using r
            		
                double r = rng.nextDouble();
                double sigma=0;
        			for(int i=0;i<corpus.size();i++) {
        				int counter=0;
        				for(int j=0;j<corpus.size();j++) {
        					if(corpus.get(j)==i) counter++;
        				}
        				sigma=sigma+counter/(double)corpus.size();
        				if(sigma>r) {
        					h1=i;
        					break;
        				}
        			}
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                int sigma1=0;
                int counter=0;
                for(int k=0;k<corpus.size()-1;k++) {
                	if(h1==corpus.get(k)) {sigma1++;}
                	}
                for(int k=0;k<corpus.size();k++) {
                		for(int k1=0;k1<corpus.size()-1;k1++) {
                			if(corpus.get(k1)==h1 && corpus.get(k1+1)==k) {
                				counter++;
                			}
                		}
                		if(counter/(double)sigma1>r) {
                			h2=k;
                			break;
                		}
                }
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                int sigma1=0;
                int counter=0;
                for(int k=0;k<corpus.size()-1;k++) {
                	if(h1==corpus.get(k)) {
                		sigma1++;
                		}
                	}
                for(int k=0;k<corpus.size();k++) {
                		for(int k1=0;k1<corpus.size()-1;k1++) {
                			if(corpus.get(k1)==h1 && corpus.get(k1+1)==k) {
                				counter++;
                			}
                		}
                		if(counter/(double)sigma1>r) {
                			h2=k;
                			break;
                		}
                }
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                int sigma=0;
                int counter=0;
                for(int i=0;i<corpus.size()-2;i++) {
            			if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            				sigma++;
            			}
                }
                for(int i=0;i<corpus.size()-2;i++) {
            		for(int k=0;k<corpus.size()-2;k++) {
            			if(corpus.get(k)==h1 && corpus.get(k+1)==h2 && corpus.get(k+2)==i) {
            				counter++;
            			}
            		}
            		if((double)counter/sigma>r) {
            			w=i;
            			break;
            			}
            }
                
                
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
