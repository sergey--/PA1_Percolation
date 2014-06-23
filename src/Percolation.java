
public class Percolation {
	//blocks are blocked = -1, open = 0, full = 1
	private int grid[][];
	public Percolation(int N){
		grid = new int[N][N];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				grid[i][j] = -1;
			}
		}
	};
	
	public void open(int i, int j){
		if (i < 1 || i > grid.length || j < 1 || j > grid.length) 
            throw new IndexOutOfBoundsException("Illegal subarray range");
		grid[i-1][j-1] = 0;
	}
	
	public void fill(int i, int j){
		if (i < 1 || i > grid.length || j < 1 || j > grid.length) 
            throw new IndexOutOfBoundsException("Illegal subarray range");
		grid[i-1][j-1] = 1;
	}
	
	public boolean isOpen(int i, int j){
		if (i < 1 || i > grid.length || j < 1 || j > grid.length) 
            throw new IndexOutOfBoundsException("Illegal subarray range");
		return grid[i-1][j-1] == 0;
	}
	
	public boolean isFull(int i, int j){
		if (i < 1 || i > grid.length || j < 1 || j > grid.length) 
            throw new IndexOutOfBoundsException("Illegal subarray range");
		return grid[i-1][j-1] == 1;
	}
	
	public boolean percolates(){
		int N = grid.length;
        //checking union without bounds
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N * N);
		for(int i = 2; i < N; i++){
			for(int j = 2; j < N; j++){
				if(!isOpen(i,j)) 
					continue;
				else {
					int inj = i * N + j; 
					if(isOpen(i - 1, j))
						uf.union(inj, inj - N);
					if(isOpen(i + 1, j))
						uf.union(inj, inj + N);
					if(isOpen(i, j - 1))
						uf.union(inj, inj - 1);
					if(isOpen(i, j + 1))
						uf.union(inj,  inj + 1);						
				}
			}
		}
		if(isOpen(1, 1) && isOpen(1, 2)) uf.union(0, 1);
		if(isOpen(1, 1) && isOpen(2, 1)) uf.union(0, N);
		if(isOpen(1, N) && isOpen(2, N)) uf.union(N - 1, 2 * N - 1);
		if(isOpen(1, N) && isOpen(1, N - 1)) uf.union(N - 1, N - 2);
		if(isOpen(N, 1) && isOpen(N, 2)) uf.union(N * (N - 1), N * (N - 1) + 1);
		if(isOpen(N, 1) && isOpen(N - 1, 1)) uf.union((N - 1) * N, (N - 1) * (N - 1));
		if(isOpen(N, N) && isOpen(N, N - 1)) uf.union(N * N - 1, N * N - 2);
		if(isOpen(N, N) && isOpen(N - 1, N)) uf.union(N * N - 1, N * (N - 1));
		
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				if(uf.connected(i, (N - 1) * N + j))
					return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Percolation a = new Percolation(N);
		for (int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				a.grid[i][j] = StdRandom.uniform(2);
				StdOut.printf(" %d", a.grid[i][j]);
			}
			StdOut.println();
		}
		////////////////////////
		StdOut.println(a.percolates());
		
	}

}
