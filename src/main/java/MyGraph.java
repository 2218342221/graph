import lombok.*;
import org.junit.Test;

import java.util.*;

public class MyGraph {

    @Getter@Setter
    private class Vertex{
        private String name;
        private LinkedList<Edge> edges = new LinkedList<>();
        public Vertex(String name, Edge... edgeArray){
            this.name = name;
            for (Edge edge:edgeArray
                 ) {
                edges.addLast(edge);
            }
        }
        public void addEdges(Edge... edgeArray){
            for (Edge edge :edgeArray
                    ) {
                this.edges.addLast(edge);
            }
        }

    }
    @Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
    public class Edge{
        private String name;
        private int weight;
    }

    Map<String,Vertex> vertexsMap;

    public MyGraph() {
        this.vertexsMap = new HashMap<>();
    }

    @Deprecated
    public void insertVertex(String vertexName){
        vertexsMap.put(vertexName,new Vertex(vertexName));
    }
    @Deprecated
    public void insertVertex(String vertexName, Edge... edges){
        vertexsMap.put(vertexName,new Vertex(vertexName,edges));
        for (Edge edge:edges
             ) {
            if(vertexsMap.get(edge.getName())==null){
                vertexsMap.put(edge.getName(),new Vertex(edge.getName()));
            }
        }
    }
    /**
     * default weight is 1
     * @param vertexName
     * @param destinationNames
     */
    @Deprecated
    public void insertVertex(String vertexName,String... destinationNames){
        if(destinationNames.length>0) {
            ArrayList<Edge> edges = new ArrayList<>();
            for (String destinationName : destinationNames
            ) {
                edges.add(new Edge(destinationName,1));

            }
            Edge[] edgeArray = new Edge[edges.size()];
            edges.toArray(edgeArray);
            insertVertex(vertexName,edgeArray);
        }else {
            insertVertex(vertexName);
        }
    }
    @Deprecated
    public void insertEdge(String begin, String end, int weight){
        if(vertexsMap.get(begin)==null){
           this.insertVertex(begin,new Edge(end,weight));

        }else {
            vertexsMap.get(begin).addEdges(new Edge(end,weight));
            if(vertexsMap.get(end)==null){
                vertexsMap.put(end,new Vertex(end));
            }
        }
    }
    @Deprecated
    public void insertEdges(String vertexName, Edge... edges){
        if(vertexsMap.get(vertexName)!=null) {
            for (Edge edge:edges
                 ) {
                if(vertexsMap.get(edge.getName())==null){
                    vertexsMap.put(edge.getName(),new Vertex(edge.getName()));
                }
            }
            vertexsMap.get(vertexName).addEdges(edges);
        }
    }



    public void addVertex(String... vertexNames){
        for (String vertexName:vertexNames
        ) {
            if(!vertexsMap.containsKey(vertexName)){
                vertexsMap.put(vertexName,new Vertex(vertexName));
            }
        }
    }
    public void removeVertex(String... vertexNames){
        for (String vertexName:vertexNames
        ) {
            if(vertexsMap.containsKey(vertexName)){
                vertexsMap.remove(vertexName);
                Set<Map.Entry<String, Vertex>> entries = vertexsMap.entrySet();
                for (Map.Entry<String, Vertex> entry:entries
                ) {
                    LinkedList<Edge> edges = entry.getValue().getEdges();
                    for (Edge edge:edges
                    ) {
                        if(edge.getName().equals(vertexName)){
                            edges.remove(edge);
                        }
                    }


                }

            }

        }
    }
    public void setEdge(String begin, String end, int weight){
        if(vertexsMap.containsKey(begin)&&vertexsMap.containsKey(end)){
            for (Edge edge:vertexsMap.get(begin).getEdges()
                 ) {
                if(edge.getName().equals(end)){
                    edge.setWeight(weight);
                    return;
                }
            }
            vertexsMap.get(begin).getEdges().addLast(new Edge(end,weight));
        }
    }
    public void setEdge(String begin, String... ends){
        for (String end:ends
        ) {
            this.setEdge(begin,end,1);
        }
    }
    public void removeEdge(String begin, String... ends){
        for (String end:ends
             ) {
            if (vertexsMap.containsKey(begin) && vertexsMap.containsKey(end)) {
                for (Edge edge : vertexsMap.get(begin).getEdges()
                ) {
                    if (edge.getName().equals(end)) {
                        vertexsMap.get(begin).getEdges().remove(edge);
                        break;
                    }
                }
            }
        }
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Set<Map.Entry<String, Vertex>> entries = vertexsMap.entrySet();
        for (Map.Entry<String, Vertex> entry:entries
             ) {
            stringBuilder.append(entry.getValue().getName()+"\n");
            LinkedList<Edge> edges = entry.getValue().getEdges();
            for (Edge edge:edges
                 ) {
                stringBuilder.append("\t--->\t"+edge.getName()+"\twith a weight       "+edge.getWeight()+"\n");
            }

        }
        return stringBuilder.substring(0);
        }

    public LinkedList<String> deepPrioritySearch(String startVertexName){
        if(vertexsMap.containsKey(startVertexName)) {
            LinkedList<String> visitedSequence = new LinkedList<>();
            deepPrioritySearch(startVertexName,visitedSequence);
            return visitedSequence;
        }
        return null;
    }

    private void deepPrioritySearch(String startVertexName,LinkedList<String> visitedSequence) {
        if (!visitedSequence.contains(startVertexName)) {
            visitedSequence.addLast(startVertexName);
            for (Edge edge : vertexsMap.get(startVertexName).getEdges()
            ) {
                    deepPrioritySearch(edge.getName(),visitedSequence);
            }

        }
    }

    public LinkedList<String> broadPrioritySearch(String startVertexName){
        if(vertexsMap.containsKey(startVertexName)) {
            LinkedList<String> visitedSequence = new LinkedList<>();
            LinkedList<String> visitedTmp = new LinkedList<>();
            visitedSequence.addLast(startVertexName);
            visitedTmp.addLast(startVertexName);
            while(!visitedTmp.isEmpty()){
                String first = visitedTmp.removeFirst();
                for (Edge edge:vertexsMap.get(first).getEdges()
                     ) {
                    if(!visitedSequence.contains(edge.getName())) {
                        visitedSequence.addLast(edge.getName());
                        visitedTmp.addLast(edge.getName());
                    }
                }

            }

            return visitedSequence;

        }
        return null;
    }

}

