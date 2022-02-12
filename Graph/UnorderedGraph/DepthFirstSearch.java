public class DepthFirstSearch {
	private int count;
	private boolean[] marked;

	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		count = 0;
		for (int i = 0; i < marked.length; i++) {
			marked[i] = false;
		}
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
		for(int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
		}
	}

	public boolean search(int v) {
		return marked[v];
	}

	public int count() {
		return count;
	} 

}