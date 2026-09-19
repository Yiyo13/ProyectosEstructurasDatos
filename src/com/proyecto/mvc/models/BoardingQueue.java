package com.proyecto.mvc.models;

public class BoardingQueue {

    private NodeBoarding first;
    private NodeBoarding last;

    public BoardingQueue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public NodeBoarding getFirst() {
        return first;
    }

    public void setFirst(NodeBoarding first) {
        this.first = first;
    }

    public NodeBoarding getLast() {
        return last;
    }

    public void setLast(NodeBoarding last) {
        this.last = last;
    }
}