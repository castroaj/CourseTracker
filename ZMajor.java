import java.util.HashSet;

public class ZMajor {
	private HashSet<Cluster> clusters;
	private String name;

	public ZMajor(String name, HashSet<Cluster> clusters) {
		this.name = name;
		this.clusters = clusters;
	}

	public String toString() {
		String s = "Major: " + this.name+"\n";
		
		for(Cluster c : clusters) {
			s+= c.toString() + "\n";
		}
		return s;
	}
}
