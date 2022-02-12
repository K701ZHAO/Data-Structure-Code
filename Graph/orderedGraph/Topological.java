public class Topological {

	private Iterable<Integer> order;

	public Topological(Digraph G) {
		DirectedCycle dc = new DirectedCycle(G);
		if (!dc.hasCycle()) {
			DepthFirstOrder dfo = new DepthFirstOrder(G);
			order = dfo.reversePost();
		}
	}

	public boolean isDAG() { return order != null; }

	public Iterable<Integer> order() {
		return order;
	}


}