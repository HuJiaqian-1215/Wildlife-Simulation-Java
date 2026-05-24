/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildlifesimulation;

import java.util.List;

/**
 * Represents a poacher in the wildlife reserve.
 * Poachers target specific animal species, disturb wildlife,
 * and may injure animals.
 */
public class Poacher {

    private String name;
    private String targetSpecies;
    private int dangerLevel;

    /**
     * Creates a Poacher object.
     *
     * @param name poacher name
     * @param targetSpecies species targeted by the poacher
     * @param dangerLevel danger level of the poacher
     */
    public Poacher(String name, String targetSpecies, int dangerLevel) 
    {
        this.name = name;
        this.targetSpecies = targetSpecies;
        this.dangerLevel = dangerLevel;
    }

    public String getName() 
    {
        return name;
    }

    public String getTargetSpecies() 
    {
        return targetSpecies;
    }

    public int getDangerLevel()
    {
        return dangerLevel;
    }
    
    /**
     * Hunts the first animal matching the target species.
     *
     * @param animals list of animals
     * @return targeted animal, or null if no matching animal is found
     */
    public Animal huntAnimal(List<Animal> animals) 
    {
        for(Animal animal : animals) 
        {
            if(animal.getSpecies().equalsIgnoreCase(targetSpecies)) 
            {
                System.out.println(name + " hunts " + animal.getSpecies() + ".");
                return animal;
            }
        }
        return null;
    }

    /**
     * Disturbs all animals in the habitat by increasing their stress.
     *
     * @param animals list of animals in the habitat
     */
    public void disturbWildlife(List<Animal> animals) 
    {
        System.out.println(name + " disturbs wildlife.");

        for(Animal animal : animals)
        {
            animal.becomeStressed();
        }
    }

    /**
     * Injures an animal if it is caught.
     *
     * @param animal animal to injure
     */
    public void hurtAnimal(Animal animal) 
    {
        if(animal != null) 
        {
            System.out.println(name + " hurts " + animal.getSpecies() + ".");
            animal.becomeInjured();
        }
    }
}