package com.project.DaoInterface;

import java.util.List;

import com.project.mainclasses.*;

public interface TrainDaoInterface {
	abstract void addTrainDetails(Train t);
	abstract Train getTrainDetails(int trainID);
	abstract List<Train> getAllTrainDetails();

}
