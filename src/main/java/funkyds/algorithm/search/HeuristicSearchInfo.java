package funkyds.algorithm.search;

/**
 * Created by debasish on 9/11/14.
 */
public interface HeuristicSearchInfo extends SearchInfo{
    public int getHeuristic(Node node);
}
