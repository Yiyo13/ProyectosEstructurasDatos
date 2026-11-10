package com.proyecto.mvc.models;

// Operaciones de "Mis viajes" sobre la pila generica Stack<Trip>.
public class TripLogic {

    public void push(Stack<Trip> stack, Trip trip) {
        stack.push(trip);
    }

    public Trip pop(Stack<Trip> stack) {
        return stack.pop();
    }

    public String printAll(Stack<Trip> stack) {

        if (stack.isEmpty()) {
            return "No hay viajes registrados.";
        }

        String resultado = "";
        Node<Trip> aux = stack.getTop();// se recorre sin sacar nada de la pila
        int i = 1;

        while (aux != null) {
            resultado += i + ". " + aux.getData().toString() + "\n";
            aux = aux.getNext();
            i++;
        }

        return resultado;
    }
}
