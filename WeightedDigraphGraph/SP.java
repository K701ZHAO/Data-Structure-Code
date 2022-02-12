public class SP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private final int s;

	public SP(EdgeWeightedDigraph G, int s) {

	}

	public double distTo(int v) { return distTo[v]; }

	public double hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY; }

	public Iterable<DirectedEdge> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

	private void relax(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		if(distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}

	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge edge : G.adj(v)) {
			int w = edge.to();
			if (distTo[w] > distTo[v] + edge.weight()) {
				distTo[w] = distTo[v] + edge.weight();
				edgeTo[w] = edge;
			}
		}
	}
}