# Wildlife Simulation System (野生动物保护区模拟系统)

A Java-based Object-Oriented Programming (OOP) project. This system simulates a wildlife conservation environment, modeling the interactions between animals, habitats, rangers, and poachers.

一个基于 Java 面向对象编程的模拟项目，用于模拟野生动物保护区的生态系统。

## 🚀 Key Features (主要功能)

- **OOP Design**: Utilizes inheritance and polymorphism to manage different entities (Animals, Rangers, Poachers).
- **Simulation Logic**: Implements daily cycles, movement, and interaction rules (e.g., poaching, feeding).
- **Modular Structure**: Separated logic for different components to ensure maintainability.

## 📂 Project Structure (项目结构)

- `Main.java`: The entry point of the application. (程序入口)
- `Animal.java`: Abstract class defining animal behaviors. (动物抽象类)
- `Habitat.java`: Manages the grid/environment. (栖息地/环境管理)
- `Ranger.java` / `Poacher.java`: Active agents in the simulation. (护林员与偷猎者)
- `Vehicle.java`: Utility for movement. (交通工具)

## 🛠️ How to Run (如何运行)

1. Clone this repository.
2. Compile all Java files: `javac *.java`
3. Run the main class: `java Main`
