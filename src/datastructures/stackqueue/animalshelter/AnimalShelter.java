package datastructures.stackqueue.animalshelter;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 Problem 3.6 Animal Shelter: An animal shelter which holds Dogs and Cats operates on "FIFO" basis. Create Data structure
 to maintain this system.
 OPERATIONS: enqueue, dequeueAny, dequeueDog, dequeueCat
 */
//Super class Animal
abstract class Animal{
    private int timestamp;          //smallest timestamp is oldest, and we pick that animal
    protected String name;

    public Animal(){}               //default constructor

    public Animal(String name){     //constructor
        this.name = name;
    }

    //getter - setter
    public int getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }
}

//Sub-classes Dog and Cat
class Dog extends Animal{
    public Dog(String name){
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name){
        super(name);
    }
}

public class AnimalShelter {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int timestampCounter = 0;

    /**
     * OPERATIONS: enqueue, dequeueAny, dequeueDog, dequeueCat
    */
    public void enqueue(Animal animal){
        animal.setTimestamp(timestampCounter);
        timestampCounter++;

        //IMP: Using instanceOf
        if(animal instanceof Dog){
            dogs.add((Dog) animal);                 //Cast will not fail as we checked instanceof
        }else if(animal instanceof Cat){
            cats.add((Cat) animal);
        }
    }

    public Animal dequeueAny(){
        Dog dog = dogs.peek();                      //Retrieves, but does not remove, the head (first element) of this list.
        Cat cat = cats.peek();

        if(dog.getTimestamp() < cat.getTimestamp()){    //dog is older
            return dogs.poll();
        }else{
            return cats.poll();
        }
    }

    public Dog dequeueDog(){
        if(dogs.isEmpty()){
            System.out.println("No more dogs available");
            return null;
        }
        return dogs.poll();                     //Retrieves and removes the head (first element) of this list.
    }

    public Cat dequeueCat(){
        if(cats.isEmpty()){
            System.out.println("No more cats available");
            return null;
        }
        return cats.poll();
    }

    //Driver main
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        Dog d1 = new Dog("Sugar");
        Dog d2 = new Dog("Banjo");
        Dog d3 = new Dog("Stallion");

        Cat c1 = new Cat("White cat");
        Cat c2 = new Cat("Gray cat");

        shelter.enqueue(c1);
        shelter.enqueue(d1);
        shelter.enqueue(d2);
        shelter.enqueue(c2);
        shelter.enqueue(d3);

        System.out.println("Dequeue Any: "+ shelter.dequeueAny().name);
        System.out.println("Dequeue Dog: "+ shelter.dequeueDog().name);
        System.out.println("Dequeue Cat: "+ shelter.dequeueCat().name);
        System.out.println("Dequeue Cat: "+ shelter.dequeueCat());          //null
        System.out.println("Dequeue Dog: "+ shelter.dequeueDog().name);
    }
}
