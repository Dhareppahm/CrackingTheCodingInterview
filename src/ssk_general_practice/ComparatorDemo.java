package ssk_general_practice;

import java.util.*;

class Person implements Comparable<Person>{
	int personId;
	String name;
	int age;
	
	public Person(int personId, String name, int age) {
		super();
		this.personId = personId;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "personId=" + personId + ", name=" + name + ", age="	+ age + "\n";
	}

	@Override
	public int compareTo(Person p) {
		if(this.personId ==  p.personId){
			return 0;
		}else if(this.personId < p.personId){
			return -1;
		}else{
			return 1;
		}
	}	
}


class NameComparator implements Comparator<Person>{
	@Override
	public int compare(Person a, Person b) {
		return a.name.compareToIgnoreCase(b.name);
	}	
}

class AgeComparator implements Comparator<Person>{
	
	//Ascending order of age
	@Override
	public int compare(Person a, Person b) {
		if(a.age == b.age){
			return 0;
		}else if(a.age < b.age){
			return -1;
		}else{
			return 1;
		}
	}	
}

public class ComparatorDemo {

	public static void main(String[] args) {
		
		List<Person> personList = Arrays.asList(
									new Person(3, "Sandeep",27),
									new Person(1, "Pooja", 23),
									new Person(2, "Pappa", 50),
									new Person(4, "Mumma", 40)
									);
		
		System.out.println("Original List :");
		System.out.println(personList);
		
		System.out.println("One particular sort order using Comparable: Using PersonId");
		Collections.sort(personList);
		System.out.println(personList);
		
		//With Comparator we can define multiple sort ordering
		System.out.println("Sort by Name :");
		Collections.sort(personList, new NameComparator());
		System.out.println(personList);
		
		System.out.println("Sort by Age :");
		Collections.sort(personList, new AgeComparator());
		System.out.println(personList);
	}

}

