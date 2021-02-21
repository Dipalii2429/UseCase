package com.project.mainclasses;

import java.util.Date;
import java.util.*;

public class Ticket {
	static int counter = 100;

	static int incrementCounter() {
		counter++;
		return counter;
	}

	List<Passenger> passenger = new ArrayList<Passenger>();
	String pnr;
	Date traveldate;

	public Date getTraveldate() {
		return traveldate;
	}

	public void setTraveldate(Date traveldate) {
		this.traveldate = traveldate;
	}

	public String generatePNR(Train t, Ticket ticket) {
		char source = t.getSource().charAt(0);
		//System.out.println("PNR first:" + source);
		char destination = t.getDestination().charAt(0);
		Date d = ticket.getTraveldate();
		int day = d.getDay();
		int month = d.getMonth();
		int year = d.getYear();
		String date = day + "" + month + "" + year;
		String pnr = source + "" + destination + "_" + date + "_" + Ticket.incrementCounter();
		return pnr;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	void addPassenger(String name, int age, char gender) {
		Passenger p = new Passenger();
		p.setName(name);
		p.setAge(age);
		p.setGender(gender);
		passenger.add(p);

	}

	double calculateTotalTicketPrice() {
		double fare = 0;
		for (int i = 0; i < passenger.size(); i++) {
			Passenger p = passenger.get(i);
			fare = fare + p.getFare();
		}
		return fare;
	}

}
