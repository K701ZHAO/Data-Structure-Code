public class PrimMST {

	private boolean[] marked;
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<double> pq;

	public PrimMST(EdgeWeightedGraph G) {
		marked = new boolean[G.V()];
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<double>();

		distTo[0] = 0.0
		for(int i = 1; 1 < G.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}

		pq.insert(0, 0.0);
		while(!pq.isEmpty()) {
			visit(G, pq.delMin());
		}
	
	}

	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			int w = e.other(v);
			if (distTo[w] > e.weight() && !marked[w]) {
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if (pq.contains(w)) {
					pq.change(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
}