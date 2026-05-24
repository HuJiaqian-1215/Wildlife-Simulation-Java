/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildlifesimulation;

/**
 * Represents an animal in the wildlife reserve.
 * Each animal has species, health, stress level,
 * temperament and a behaviour state.
 *
 * @author jiaqi
 */
public class Animal {
    /**
    * Animal temperament profiles.
    */
    public enum Temperament 
    {
        PASSIVE,
        DEFENSIVE,
        CURIOUS,
        AGGRESSIVE
    }

    /**
    * Animal states.
    */
    public enum AnimalState 
    {
        CALM,
        ALERTED,
        DEFENSIVE,
        CURIOUS,
        AGGRESSIVE,
        FLEEING,
        ATTACKING,
        STABILISED,
        CRITICAL
    }
    
    private String species;
    private int health;
    private int stressLevel;
    private Temperament temperament;
    private AnimalState state;

    /**
     * Creates an Animal object.
     *
     * @param species animal species
     * @param health animal health
     * @param stressLevel animal stress level
     * @param temperament animal temperament profile
     */
    public Animal(String species, int health, int stressLevel, String temperament) 
    {
        this.species = species;
        this.health = health;
        this.stressLevel = stressLevel;
        this.temperament = Temperament.valueOf(temperament.toUpperCase());
        this.state = AnimalState.CALM;
    }

    public String getSpecies() 
    {
        return species;
    }

    public int getHealth() 
    {
        return health;
    }

    public int getStressLevel() 
    {
        return stressLevel;
    }

    public Temperament getTemperament() 
    {
        return temperament;
    }

    public AnimalState getState() 
    {
        return state;
    }
    
    /**
     * Increases stress level.
     */
    public void becomeStressed() 
    {
        stressLevel += 10;

        if(stressLevel >= 80) 
        {
            state = AnimalState.CRITICAL;
        }
    }

    /**
     * Reduces stress level.
     */
    public void reduceStress() 
    {
        stressLevel -= 10;

        if(stressLevel < 0) 
        {
            stressLevel = 0;
        }
    }

    /**
     * Injures the animal.
     */
    public void becomeInjured() 
    {
        health -= 20;

        if(health <= 20) 
        {
            state = AnimalState.CRITICAL;
        }
    }

    /**
     * Improves health.
     */
    public void improveHealth() 
    {
        health += 20;

        if(health > 100) 
        {
            health = 100;
        }
    }

    /**
     * Animal reacts when ranger approaches.
     */
    public void reactToRanger() 
    {
        switch (temperament) 
        {
            case DEFENSIVE:
                state = AnimalState.DEFENSIVE;
                defend();
                break;

            case CURIOUS:
                state = AnimalState.CURIOUS;
                approachRanger();
                break;

            case AGGRESSIVE:
                state = AnimalState.AGGRESSIVE;
                if(Math.random() < 0.5)
                {
                    attack();
                 }
                else
                {
                    flee();
                }
                break;

            default:
                state = AnimalState.ALERTED;
                System.out.println(species + " stays alert.");
        }
    }

    /**
     * Animal attacks.
     */
    public void attack() 
    {
        state = AnimalState.ATTACKING;
        System.out.println(species + " attacks.");
    }

    /**
     * Animal flees.
     */
    public void flee() 
    {
        state = AnimalState.FLEEING;
        System.out.println(species + " flees.");
    }

    /**
     * Animal defends itself.
     */
    public void defend() 
    {
        System.out.println(species + " defends.");
    }

    /**
     * Animal approaches ranger.
     */
    public void approachRanger() 
    {
        System.out.println(species + " approaches ranger.");
    }

    /**
     * Stabilises animal.
     */
    public void stabilise() 
    {
        state = AnimalState.STABILISED;
        reduceStress();
        improveHealth();
    }

    /**
     * Checks whether animal needs help.
     *
     * @return true if help is needed
     */
    public boolean needsHelp() 
    {
        return health <= 50 || stressLevel >= 50 || state == AnimalState.CRITICAL;
    }
}