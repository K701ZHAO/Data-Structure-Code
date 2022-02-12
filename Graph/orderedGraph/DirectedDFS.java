public class DirectedDFS {

	private boolean[] marked;

	public DirectedDFS(Digraph D, int s) {
		marked = new boolean[D.V()];
		dfs(D, s);
	}

	public DirectedDFS(Digraph D, Iterable<Integer> source) {
		marked = new boolean[D.V()];
		for(int s : source) {
			if (!marked[s]) dfs(D, s);
		}
	}

	private void dfs(Digraph D, int s) {
		marked[s] = true;
		for(int v : D.adj(s)) {
			if (!marked[v]) dfs(D, v);
		}
	}

	public boolean marked(int v) {
		return marked[v];
	}
}