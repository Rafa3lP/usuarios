/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public abstract class Observable {
    private List<Observer> observers = new ArrayList<>();
   
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for(Observer observer: observers) {
            observer.update();
        }
    }
}
