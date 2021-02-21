package com.project.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.connection.*;
import com.project.dao.*;

import com.project.mainclasses.*;

public class MainClass {

	public static void main(String[] args) {

		DBConnection db = new DBConnection();
		db.getConnection();
		Train train = new Train();
		Ticket ticket = new Ticket();
		TrainDaoImpl traindao = new TrainDaoImpl();
		//PassengerDaoImpl passengerdao = new PassengerDaoImpl();
		Scanner s = new Scanner(System.in);
		String ans;
		System.out.println("Welcome to Train Booking Portal");
		System.out.println("*******************************");
		do {
			System.out.println("Enter you choice\n1.Enter Train Details\n2.Book Train Ticket");
			int ch = s.nextInt();
			switch (ch) {
			case 1:
				System.out.print("\nEnter Train No: ");
				train.setTrainNo(s.nextInt());
				System.out.print("\nEnter Train name: ");
				train.setTrainName(s.next());
				System.out.print("\nEnter Source: ");
				String source = s.next();
				train.setSource(source);
				System.out.print("\nEnter Destination: ");
				train.setDestination(s.next());
				System.out.print("\nEnter Train fare: ");
				train.setTicketprice(s.nextDouble());
				traindao.addTrainDetails(train);
				System.out.println("Train record inserted successfully");
				break;
			case 2:
				System.out.println("************************************************************");
				System.out.println("-----------------------All Train Details--------------------");
				System.out.println("Train No	Train Name	Source	Destination	Fare");
				List<Train> list = traindao.getAllTrainDetails();
				for (int i = 0; i < list.size(); i++) {

					Train t = list.get(i);
					System.out.println(t.getTrainNo() + "		" + t.getTrainName() + "		" + t.getSource()
							+ "	" + t.getDestination() + "		" + t.getTicketprice());
				}
				System.out.print("\n\n\nPlease enter a Train Number and date to book a ticket: ");
				System.out.print("\nTrain No: ");
				Train t = new Train();
				t = traindao.getTrainDetails(s.nextInt());
			//	Double ticketprice = t.getTicketprice();
				//System.out.println(ticketprice);
				System.out.print("\nDay: ");
				int day = s.nextInt();
				System.out.print("\nMonth: ");
				int month = s.nextInt();
				System.out.print("\nYear: ");
				int year = s.nextInt();
				Date d = new Date(day, month, year);
				System.out.println(d);
				ticket.setTraveldate(d);
				List<Passenger> passenger = new ArrayList<Passenger>();
				String answer = null;
				do {
					System.out.println("Please enter the passanger details");
					Passenger p = new Passenger();
					System.out.print("Enter Name: ");
					p.setName(s.next());
					System.out.print("\nEnter Age: ");
					int age = s.nextInt();
					p.setAge(age);
					System.out.print("\nEnter Gender (Male/Female/Other)");

					char gender = s.next().charAt(0);
					System.out.println(gender);
					p.setGender(gender);
					double ticketprice =  t.getTicketprice();
					System.out.println(ticketprice);
					if (age <= 12) {
					//	System.out.println("in age: "+ticketprice);
						double price = ticketprice/2;
						//System.out.println("In if"+price);
						p.setFare(price);
						
					} else if (age >= 60) {
						double price = (6*ticketprice)/10;
						p.setFare(price);
					} else if (gender == 'F' || gender == 'f') {
						double price = ticketprice/4;
						System.out.println(price);
						p.setFare(price);
					}

					passenger.add(p);
					System.out.println("Do you want to add more passenger details, if yes, press [Y/y]");
					answer = s.next();
				} while (answer.equals("Y") || answer.equals("y"));

				String pnr = ticket.generatePNR(t, ticket);
				System.out.println("**************Ticket Details***************");
				System.out.println("PNR: " + ticket.generatePNR(t, ticket));
				System.out.println("Train No: " + t.getTrainNo());
				System.out.println("Train Name: " + t.getTrainName());
				System.out.println("From: " + t.getSource());
				System.out.println("To: " + t.getDestination());
				System.out.println("Date: " + ticket.getTraveldate());
				System.out.println("Passenger Details: ");
				System.out.println("\nName	Age	Gender	Fare");

				for (int i = 0; i < passenger.size(); i++) {
					Passenger p = new Passenger();
					p = passenger.get(i);
					System.out.println(p.getName() + "	" + p.getAge() + "	" + p.getGender() + "	" + p.getFare());
				}
				String filename = pnr + ".txt";
				File ticketfile = new File(filename);
				try {
					ticketfile.createNewFile();
					System.out.println("File created");
					FileWriter writer = new FileWriter(ticketfile);
					writer.write("********************TIcket Details**********************");
					writer.write("\nPNR: " + ticket.generatePNR(t, ticket));
					writer.write("\nTrain No: " + t.getTrainNo());
					writer.write("\nTrain Name: " + t.getTrainName());
					writer.write("\nFrom: " + t.getSource());
					writer.write("\nTo: " + t.getDestination());
					writer.write("\nDate: " + ticket.getTraveldate());
					writer.write("\nPassenger Details: ");
					writer.write("\nName	Age	Gender	Fare");
					for (int i = 0; i < passenger.size(); i++) {
						Passenger p = passenger.get(i);
						writer.write(
								"\n" + p.getName() + "	" + p.getAge() + "	" + p.getGender() + "	" + p.getFare());
					}
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}

			System.out.println("Do you want to continue L, if yes press [Y/y]");
			ans = s.nextLine();
		} while (ans.equals("Y") || ans.equals("y"));
	}

}
