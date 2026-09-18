package com.proyecto.mvc.models;

public class TripLogic {

    public void push(TripStack stack, Trip trip) {

        NodeTrip node = new NodeTrip(trip);

        node.setNext(stack.getTop());
        stack.setTop(node);
    }

    public Trip pop(TripStack stack) {

        if (stack.isEmpty()) {
            return null;
        }

        Trip trip = stack.getTop().getTrip();
        stack.setTop(stack.getTop().getNext());

        return trip;
    }

    public String printAll(TripStack stack) {

        if (stack.isEmpty()) {
            return "No hay viajes registrados.";
        }

        String resultado = "";
        NodeTrip aux = stack.getTop();
        int i = 1;

        while (aux != null) {
            resultado += i + ". " + aux.getTrip().toString() + "\n";
            aux = aux.getNext();
            i++;
        }

        return resultado;
    }
}