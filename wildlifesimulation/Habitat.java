/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildlifesimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a habitat in the wildlife reserve.
 * A habitat stores animals and may temporarily become unsafe.
 */
public class Habitat {

    private String name;
    private int capacity;
    private List<Animal> animals;
    private List<Poacher> poachers;
    private boolean unsafe;

    /**
     * Creates a Habitat object.
     *
     * @param name habitat name
     * @param capacity maximum animal capacity
     */
    public Habitat(String name, int capacity) 
    {
        this.name = name;
        this.capacity = capacity;
        this.animals = new ArrayList<>();
        this.poachers = new ArrayList<>();
        this.unsafe = false;
    }
    
    public String getName() 
    {
        return name;
    }

    public int getCapacity() 
    {
        return capacity;
    }

    public List<Animal> getAnimals() 
    {
        return animals;
    }

    public List<Poacher> getPoachers() 
    {
        return poachers;
    }

    /**
     * Adds an animal into the habitat.
     *
     * @param animal animal entering habitat
     */
    public void enterAnimal(Animal animal) 
    {
        if(animals.size() < capacity) 
        {
            animals.add(animal);
            System.out.println(animal.getSpecies() + " enters " + name + ".");
        } 
        else 
        {
            System.out.println(name + " is full.");
        }
    }

    /**
     * Removes an animal from the habitat.
     *
     * @param animal animal leaving habitat
     */
    public void leaveAnimal(Animal animal) 
    {
        if(animals.remove(animal)) 
        {
            System.out.println(animal.getSpecies() + " leaves " + name + ".");
        }
    }

    /**
     * Adds a poacher into the habitat.
     * All animals become stressed when poachers enter.
     *
     * @param poacher poacher entering habitat
     */
    public void addPoacher(Poacher poacher)
    {
        poachers.add(poacher);
        System.out.println(poacher.getName() + " enters " + name + ".");
        stressAllAnimals();
    }

    /**
     * Removes a poacher from the habitat.
     *
     * @param poacher poacher leaving habitat
     */
    public void removePoacher(Poacher poacher)
    {
        poachers.remove(poacher);
    }

    /**
     * Increases stress for all animals in habitat.
     */
    public void stressAllAnimals() 
    {
        for(Animal animal : animals) 
        {
            animal.becomeStressed();
        }
    }

    /**
     * Marks habitat as temporarily unsafe.
     */
    public void shutDownUnsafe() 
    {
        unsafe = true;
        System.out.println(name + " is temporarily unsafe.");
    }

    /**
     * Reopens habitat.
     */
    public void reopen() 
    {
        unsafe = false;
        System.out.println(name + " is safe again.");
    }

    /**
     * Prints habitat report.
     */
    public void report() 
    {
        System.out.println("\nHabitat Report: " + name);
        System.out.println("Unsafe: " + unsafe);

        for(Animal animal : animals) 
        {
            System.out.println(animal.getSpecies() + " | Health: " + animal.getHealth() + " | Stress: " + animal.getStressLevel() + " | State: " + animal.getState());
        }
    }

    public boolean isUnsafe() 
    {
        return unsafe;
    }
}