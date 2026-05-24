/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wildlifesimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class that runs the wildlife simulation.
 */
public class Main {

    /**
     * Starts the simulation.
     *
     * @param args command line arguments
     * @throws FileNotFoundException if input files are missing
     */
    public static void main(String[] args) throws FileNotFoundException 
    {

        List<Animal> animals = readAnimals("animals.txt");
        List<Ranger> rangers = readRangers("rangers.txt");
        List<Poacher> poachers = readPoachers("poachers.txt");
        List<Vehicle> vehicles = readVehicles("vehicles.txt");
        List<Habitat> habitats = readHabitats("habitats.txt");

        if(habitats.isEmpty()) 
        {
            System.out.println("No habitat found.");
            return;
        }

        Habitat habitat = habitats.get(0);

        for(Animal animal : animals)
        {
            habitat.enterAnimal(animal);
        }

        for(int day = 1; day <= 3; day++) 
        {

            System.out.println("\n========== DAY " + day + " ==========");

            runDay(habitat, rangers, poachers, vehicles);

            System.out.println("\nEnd of Day Report:");
            habitat.report();

            habitat.reopen();
        }

        System.out.println("\nSimulation Finished.");
    }

    /**
     * Runs one day of simulation.
     *
     * @param habitat habitat being simulated
     * @param rangers list of rangers
     * @param poachers list of poachers
     * @param vehicles list of vehicles
     */
    public static void runDay(Habitat habitat, List<Ranger> rangers, List<Poacher> poachers, List<Vehicle> vehicles) 
    {
        // Poacher event
        if(!poachers.isEmpty()) 
        {

            Poacher poacher = poachers.get(0);

            habitat.addPoacher(poacher);

            Animal huntedAnimal = poacher.huntAnimal(habitat.getAnimals());

            poacher.hurtAnimal(huntedAnimal);

            poacher.disturbWildlife(habitat.getAnimals());
        }

        // Ranger event
        if(!rangers.isEmpty())
        {

            Ranger ranger = rangers.get(0);

            ranger.patrolHabitat(habitat);

            Animal animalInNeed = ranger.detectAnimal(habitat.getAnimals());

            if(animalInNeed != null)
            {

                animalInNeed.reactToRanger();

                ranger.assistAnimal(animalInNeed);
            }

            Poacher foundPoacher = ranger.findPoacher(habitat.getPoachers());

            if(foundPoacher != null) 
            {

                boolean win = ranger.fightPoacher(foundPoacher);

                if(win) 
                {
                    habitat.removePoacher(foundPoacher);
                } 
                else 
                {
                    habitat.shutDownUnsafe();
                }
            }
        }

        // Vehicle event
        if(!vehicles.isEmpty())
        {

            Vehicle vehicle = vehicles.get(0);

            vehicle.deployToHabitat(habitat);

            Animal criticalAnimal = findCriticalAnimal(habitat.getAnimals());

            if(criticalAnimal != null) 
            {
                vehicle.useMedicUnit(criticalAnimal);
                vehicle.useTranquilliserKit(criticalAnimal);
            }
        }
    }

    /**
     * Finds first critical animal.
     *
     * @param animals list of animals
     * @return critical animal or null
     */
    public static Animal findCriticalAnimal(List<Animal> animals) 
    {
        for(Animal animal : animals) 
        {
            if(animal.getState() == Animal.AnimalState.CRITICAL) 
            {
                return animal;
            }
        }
        return null;
    }

    /**
     * Reads animals from file.
     *
     * @param fileName input file name
     * @return list of animals
     * @throws FileNotFoundException file missing
     */
    public static List<Animal> readAnimals(String fileName) throws FileNotFoundException 
    {
        List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        scanner.nextLine();

        while(scanner.hasNext()) 
        {

            String species = scanner.next();
            int health = scanner.nextInt();
            int stress = scanner.nextInt();
            String temperament = scanner.next();

            animals.add(new Animal(species, health, stress, temperament));
        }

        scanner.close();
        return animals;
    }

    /**
     * Reads rangers from file.
     *
     * @param fileName input file name
     * @return list of rangers
     * @throws FileNotFoundException file missing
     */
    public static List<Ranger> readRangers(String fileName) throws FileNotFoundException {

        List<Ranger> rangers = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        scanner.nextLine();

        while(scanner.hasNext()) 
        {

            String name = scanner.next();
            int experience = scanner.nextInt();
            int efficiency = scanner.nextInt();

            rangers.add(new Ranger(name, experience, efficiency));
        }

        scanner.close();
        return rangers;
    }

    /**
     * Reads poachers from file.
     *
     * @param fileName input file name
     * @return list of poachers
     * @throws FileNotFoundException file missing
     */
    public static List<Poacher> readPoachers(String fileName) throws FileNotFoundException {

        List<Poacher> poachers = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        scanner.nextLine();

        while(scanner.hasNext()) 
        {

            String name = scanner.next();
            String targetSpecies = scanner.next();
            int dangerLevel = scanner.nextInt();

            poachers.add(new Poacher(name, targetSpecies, dangerLevel));
        }

        scanner.close();
        return poachers;
    }

    /**
     * Reads vehicles from file.
     *
     * @param fileName input file name
     * @return list of vehicles
     * @throws FileNotFoundException file missing
     */
    public static List<Vehicle> readVehicles(String fileName) throws FileNotFoundException {

        List<Vehicle> vehicles = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        scanner.nextLine();

        while(scanner.hasNext()) 
        {

            String id = scanner.next();
            int fuel = scanner.nextInt();
            int capacity = scanner.nextInt();
            String equipment = scanner.next();

            vehicles.add(new Vehicle(id, fuel, capacity, equipment));
        }

        scanner.close();
        return vehicles;
    }

    /**
     * Reads habitats from file.
     *
     * @param fileName input file name
     * @return list of habitats
     * @throws FileNotFoundException file missing
     */
    public static List<Habitat> readHabitats(String fileName) throws FileNotFoundException {
        List<Habitat> habitats = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        scanner.nextLine();

        while(scanner.hasNext()) 
        {

            String name = scanner.next();
            int capacity = scanner.nextInt();

            habitats.add(new Habitat(name,capacity));
        }

        scanner.close();
        return habitats;
    }
}