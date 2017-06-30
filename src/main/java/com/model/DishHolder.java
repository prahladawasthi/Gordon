package com.model;

import java.util.List;

public class DishHolder {

	List<Dish> dishes;

	int totalSatisfaction;

	int totalTime;

	public DishHolder(List<Dish> dishes, int totalSatisfaction, int totalTime) {

		this.dishes = dishes;
		this.totalSatisfaction = totalSatisfaction;
		this.totalTime = totalTime;
	}

	public int getTotalSatisfaction() {
		return totalSatisfaction;
	}

	public void setTotalSatisfaction(int totalSatisfaction) {
		this.totalSatisfaction = totalSatisfaction;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

}
