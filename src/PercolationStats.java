
public class PercolationStats {
	
	public PercolationStats(int N, int T){};
	public double mean(){return -1;}
	public double stddev(){return -1;}
	public double confidenceLo(){return -1;}
	public double confidenceHi(){return -1;}
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		StdOut.printf("%d\t", N);
		StdOut.printf("%d", T);
		//StdOut.uniform();
	}

}
