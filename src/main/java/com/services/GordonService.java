package com.services;

import java.util.List;

import com.model.Dish;
import com.model.DishHolder;

public interface GordonService {

	List<Dish> totalDish();

	DishHolder selectedSatisfactoyDishs(List<Dish> dishList, int totalTime);

	int totalTimeUsed(List<Dish> dishList);

	int totalSatisfaction(List<Dish> dishList);

}
