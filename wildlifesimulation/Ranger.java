/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildlifesimulation;

import java.util.List;
import java.util.Random;

/**
 * Represents a ranger in the wildlife reserve.
 * Rangers patrol habitats, detect animals that need help,
 * assist animals, and fight poachers.
 */
public class Ranger {

    private String name;
    private int experience;
    private int efficiency;
    private int stressLevel;

    /**
     * Creates a Ranger object.
     *
     * @param name ranger name
     * @param experience ranger experience level
     * @param efficiency ranger efficiency level
     */
    public Ranger(String name, int experience, int efficiency) 
    {
        this.name = name;
        this.experience = experience;
        this.efficiency = efficiency;
        this.stressLevel = 0;
    }

    public String getName() 
    {
        return name;
    }

    public int getExperience() 
    {
        return experience;
    }

    public int getEfficiency() 
    {
        return efficiency;
    }

    public int getStressLevel() 
    {
        return stressLevel;
    }
    
    /**
     * Patrols a habitat.
     *
     * @param habitat habitat to patrol
     */
    public void patrolHabitat(Habitat habitat) 
    {
        System.out.println(name + " patrols " + habitat.getName() + ".");
    }

    /**
     * Detects the first animal that needs help.
     *
     * @param animals list of animals
     * @return animal that needs help, or null if none is found
     */
    public Animal detectAnimal(List<Animal> animals) 
    {
        for(Animal animal : animals) 
        {
            if(animal.needsHelp()) 
            {
                return animal;
            }
        }
        return null;
    }

    /**
     * Assists an animal by stabilising it.
     *
     * @param animal animal to assist
     */
    public void assistAnimal(Animal animal) 
    {
        if(animal != null) 
        {
            System.out.println(name + " assists " + animal.getSpecies() + ".");
            animal.stabilise();
            stressLevel += 5;
        }
    }

    /**
     * Finds the first poacher in the habitat.
     *
     * @param poachers list of poachers
     * @return poacher found, or null if none exists
     */
    public Poacher findPoacher(List<Poacher> poachers) 
    {
        if(poachers.isEmpty()) 
        {
            return null;
        }
        return poachers.get(0);
    }

    /**
     * Fights a poacher.
     * The result is based on randomly generated strength values.
     *
     * @param poacher poacher to fight
     * @return true if the ranger wins, otherwise false
     */
    public boolean fightPoacher(Poacher poacher) 
    {
        if(poacher == null) 
        {
            return false;
        }

        Random random = new Random();

        int rangerStrength = random.nextInt(10) + 1 + experience + efficiency;
        int poacherStrength = random.nextInt(10) + 1 + poacher.getDangerLevel();

        stressLevel += 10;

        if(rangerStrength >= poacherStrength)
        {
            System.out.println(name + " wins against " + poacher.getName() + ".");
            return true;
        } 
        else 
        {
            System.out.println(name + " loses against " + poacher.getName() + ".");
            makeDangerousChoice();
            return false;
        }
    }

    /**
     * Represents a dangerous decision caused by stress.
     */
    public void makeDangerousChoice() 
    {
        System.out.println(name + " makes a dangerous choice because of stress.");
    }
}