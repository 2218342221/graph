import org.junit.Test;

import java.util.Map;

public class MyGraphTest {

    @Test
    public void test(){
        MyGraph myGraph = new MyGraph();
        myGraph.addVertex("1","2","3","4","5","6","7","8");
        myGraph.setEdge("1","2","3");
        myGraph.setEdge("2","1","4","5");
        myGraph.setEdge("3","1","6","7");
        myGraph.setEdge("4","2","8");
        myGraph.setEdge("5","2","8");
        myGraph.setEdge("6","3","7");
        myGraph.setEdge("7","3","6");
        myGraph.setEdge("8","4","5");
        System.out.println(myGraph);
        System.out.println(myGraph.deepPrioritySearch("1"));
        System.out.println(myGraph.broadPrioritySearch("1"));
    }
}
