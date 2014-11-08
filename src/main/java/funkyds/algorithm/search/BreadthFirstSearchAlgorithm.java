package funkyds.algorithm.search;

import funkyds.ds.immutable.List;
import funkyds.ds.immutable.ListFactory;

/**
 * Created by debasish on 9/11/14.
 */
public class BreadthFirstSearchAlgorithm {

    private SearchInfo searchInfo;

    public BreadthFirstSearchAlgorithm(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public List<Node> getSearchedPath(Node start) {
        return getSearchedPath(ListFactory.<Node>getEmptySimpleList().add(start));
    }

    protected List<Node> getSearchedPath(List<Node> step) {
        Node thisNode = step.head().get();
        if(searchInfo.isGoal(thisNode)){
            return step;
        }
        Iterable<Node> descendants = searchInfo.getDescendantNodes(step.head().get());
        for(Node descendant:descendants){
            List<Node> path = getSearchedPath(step.add(descendant));
            if(!path.isEmpty()){
                return path;
            }
        }
        return ListFactory.getEmptySimpleList();
    }
}
