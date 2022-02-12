public class DijkstraSP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	private double s;

	public class DijkstraSP(EdgeWeightedDigraph G, int s) {
		this.s = s;
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq.insert(s, 0.0);

		while(!pq.isEmpty()) {
			relax(0, pq.delMin());
		}
	}

	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > e.weight() + distTo[v]) {
				edgeTo[w] = e;
				distTo[w] == e.weight() + distTo[v];
				if (pq.contains(w)) pq.change(w, distTo[w]);
				else pq.insert(w, distTo[w]);                                                                                                                                                                          
			}
		}
	}

	public Iterable<DirectedEdge> pathTo(int w) {
		Stack path = new Stack<DirectedEdge>();
		if (edgeTo[w] == null) return null;
		for(int v = w; edgeTo[v]. != null; v = edgeTo[v].from()) {
			path.push(edgeTo[v]);
		}
		return path;
	}
}