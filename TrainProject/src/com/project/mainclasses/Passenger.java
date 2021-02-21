package com.project.mainclasses;

public class Passenger {
	String name;
	int age;
	char gender;
	double fare;
	
	public double getFare() {
		//System.out.println("In passenger: "+fare);
		return fare;
	}
	public void setFare(double fare) {
		//System.out.println("Inside seter: "+fare);
		this.fare = fare;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	

}
