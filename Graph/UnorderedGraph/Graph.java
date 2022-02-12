public class Graph {

	private final int V;

	private int E;

	private Bag<Integer>[] adj;



	// calc v degree
	public static int degree(Graph G, int v) {
			int degree = 0;
			for (int w : G.adj(v)) {
				degree++;
			}
			return degree;
	}

	public static int maxDegree(Graph G) {
		int degree = 0;
		for(int v = 0; v < G.V(); v++) {
			int degreeOfV = degree(G, v);
			if (degreeOfV > degree) degree = degreeOfV;
		}
		return degree;
	}

	public static double avgDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}

	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); v++) {
			for(int w : G.adj(v)) {
				if (v == w) count ++;
			}
		}
		return count / 2;
	}


	public Graph(int V){
		this.V = V;
		E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	public Graph(In in) {
		V = in.readInt();
		E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public String toString() {
		String s = V() + " vertices, " + E() + " edges\n";
		for(int v = 0; v < V(); v++) {
			s += v + ": ";
			for(int w : adj(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s; 
	}


}