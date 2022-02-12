public class EdgeWeightedGraph {

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	public EdgeWeightedGraph(int V) {
		this.V = V;
		E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}

	public EdgeWeightedGraph(In in) {
		V = in.readInt();
		EdgeWeightedGraph(V);
		edges = in.readInt();
		for(int i = 0; i < edges; i++) {
			int v = in.readInt();
			int w = in.readInt();
			int weight = in.readDouble();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	public Iterable<Edge> edges() {
		Bag<Edge> edgesBag = new Bag<Edge>();

		for(int v = 0; v < V; v++) {
			for(Edge edge : adj[v]) {
				if(edge.other(v) >= v) edgesBag.add(edge);  
			}
		}
		return edgesBag;
	}


}