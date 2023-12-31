package org.example;

public class Node {

    String start;
    String end;
    Integer path;

    public Node(String start, String end, Integer path){
        this.start = start;
        this.end = end;
        this.path = path;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }

    public Integer getPath(){
        return path;
    }
    public static Node readNode(String line) {
        String[] data = line.split("\\s");
        String start = data[0];
        String end = data[1];
        Integer path = Integer.parseInt(data[2]);
        return new Node(start, end, path);
    }

}
