/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildlifesimulation;

/**
 * Represents a support vehicle used by rangers.
 * Vehicles can be refuelled, deployed to habitats,
 * and carry emergency equipment.
 */
public class Vehicle {

    private String id;
    private int fuelLevel;
    private int capacity;
    private String equipment;

    /**
     * Creates a Vehicle object.
     *
     * @param id vehicle id
     * @param fuelLevel initial fuel level
     * @param capacity carrying capacity
     * @param equipment equipment carried by vehicle
     */
    public Vehicle(String id, int fuelLevel, int capacity, String equipment) 
    {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.capacity = capacity;
        this.equipment = equipment;
    }

    public String getId() 
    {
        return id;
    }

    public int getFuelLevel() 
    {
        return fuelLevel;
    }

    public int getCapacity() 
    {
        return capacity;
    }

    public String getEquipment() 
    {
        return equipment;
    }
    
    /**
     * Refuels vehicle to full capacity.
     */
    public void refuel() 
    {
        fuelLevel = 100;
        System.out.println(id + " has been refuelled.");
    }

    /**
     * Deploys vehicle to a habitat.
     *
     * @param habitat destination habitat
     */
    public void deployToHabitat(Habitat habitat)
    {
        if(fuelLevel > 0) 
        {
            fuelLevel -= 10;
            System.out.println(id + " deployed to " + habitat.getName() + ".");
        } 
        else 
        {
            System.out.println(id + " has no fuel.");
        }
    }

    /**
     * Checks if vehicle carries specified equipment.
     *
     * @param item equipment name
     * @return true if equipment is available
     */
    public boolean hasEquipment(String item) 
    {
        return equipment.equalsIgnoreCase(item);
    }

    /**
     * Uses Mobile Medic Unit to stabilise an animal.
     *
     * @param animal animal receiving treatment
     */
    public void useMedicUnit(Animal animal) 
    {
        if(animal != null && hasEquipment("Mobile_Medic_Unit")) 
        {
            System.out.println(id + " uses Mobile Medic Unit on " + animal.getSpecies() + ".");
            animal.stabilise();
        }
    }

    /**
     * Uses Tranquilliser Kit to calm aggressive animals.
     *
     * @param animal animal being calmed
     */
    public void useTranquilliserKit(Animal animal)
    {
        if(animal != null && hasEquipment("Tranquilliser_Kit")) 
        {
            System.out.println(id + " uses Tranquilliser Kit on " + animal.getSpecies() + ".");
            animal.reduceStress();
        }
    }
}