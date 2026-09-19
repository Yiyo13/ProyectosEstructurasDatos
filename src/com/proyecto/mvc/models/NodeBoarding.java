package com.proyecto.mvc.models;

public class NodeBoarding {

    private Passenger passenger;
    private NodeBoarding next;

    public NodeBoarding(Passenger passenger) {
        this.passenger = passenger;
        this.next = null;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public NodeBoarding getNext() {
        return next;
    }

    public void setNext(NodeBoarding next) {
        this.next = next;
    }
}

