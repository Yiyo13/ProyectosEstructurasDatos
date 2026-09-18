package com.proyecto.mvc.models;

public class TripStack {

    private NodeTrip top;

    public TripStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public NodeTrip getTop() {
        return top;
    }

    public void setTop(NodeTrip top) {
        this.top = top;
    }
}