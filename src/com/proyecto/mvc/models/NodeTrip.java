package com.proyecto.mvc.models;

public class NodeTrip {

    private Trip trip;
    private NodeTrip next;

    public NodeTrip(Trip trip) {
        this.trip = trip;
        this.next = null;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public NodeTrip getNext() {
        return next;
    }

    public void setNext(NodeTrip next) {
        this.next = next;
    }
}