public class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private Bag<DirectedEdge>[] adj;

	public EdgeWeightedDigraph(int V) {
		this.V = V;
		E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<DirectedEdge>();
		}
	}

	public EdgeWeightedDigraph(In in) {
		V = in.readInt();
		int edges = in.readInt();
		for(int i = 0; i < edges; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			DirectedEdge edge = DirectedEdge(v, w, weight);
			addEdge(edge);
		}
	}

	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}

	public int V() { return V; }

	public int E() { return E; }

	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> allEdges = new Bag<DirectedEdge>();
		for(int v = 0; v < V; v++) {
			for(DirectedEdge e : adj[v]) {
				allEdges.add(e);
			}
		}
		return allEdges;
	}
}