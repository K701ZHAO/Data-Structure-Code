public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;

	public DirectedCycle(Digraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) dfs(G, v);
		}
	}

	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (hasCycle()) return;
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			} else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for(int i = v; i != w; i = edgeTo[i]) {
					cycle.push(i);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}

	public boolean hasCycle() { return cycle != null; }

	public Iterable<Integer> cycle() { return cycle; }
}