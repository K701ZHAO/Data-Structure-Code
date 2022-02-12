public class DijsktraAllPairsSP {
	private DijsktraSP[] all;

	public DijsktraAllPairsSP (EdgeWeightedDigraph G) {
		all = new DijsktraSP[G.V()];
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DijsktraSP(G, v);
		}
	}

	public Iterable<DirectedEdge> pathTo(int v, int w) {
		return all[v].pathTo(w);
	}

}