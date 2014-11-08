package funkyds.algorithm.search;

/**
 * Created by debasish on 8/11/14.
 */
public interface SearchInfo {
    public Iterable<Node> getDescendantNodes(Node node);
    public boolean isGoal(Node node);
}
