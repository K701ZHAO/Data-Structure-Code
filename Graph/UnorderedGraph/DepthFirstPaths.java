public class DepthFirstPaths {

	private int[] toEdge;
	private boolean[] marked;
	private int s;


	public DepthFirstPaths(Graph G, int s) {
		toEdge = new int[G.V()];
		marked = new boolean[G.V()];
		this.s = s;
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;

		for (int w: G.adj(v)) {
			if (!marked(w)) {
				toEdge[w] = v;
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> path(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		for (int x = v; x != s; x = toEdge[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	} 

}