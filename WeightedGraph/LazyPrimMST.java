public class LazyPrimMST {

	private boolean[] marked;
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;

	public LazyPrimMST(EdgeWeightedGraph G) {
		marked = new boolean[G.V()];
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();

		visit(G, 0);
		while(!pq.isEmpty()) {
			Edge min = pq.delMin();
			int v = min.either();
			int w = min.other(v);
			if (marked[v] && marked[w]) continue;
			mst.enqueue(min);
			if (marked[v]) visit(G, w);
			if (marked[w]) visit(G, v);
		}

	}

	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			if (!marked[e.other(v)]) pq.insert(e);
		}

	}

	public Iterable<Edge> edges() {
		return mst;
	}

	public double weight() {
		Iterable<Edge> edgesQueue = edges();
		double weight = 0.0;
		for(Edge e : edgesQueue) {
			weight += e.weight();
		}
		return weight;
	}
}