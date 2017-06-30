package com.model;

public class Dish {

	private int dishID;

	private int satisfaction;

	private int eatingTime;

	public Dish(int dishID, int satisfaction, int eatingTime) {
		super();
		this.dishID = dishID;
		this.satisfaction = satisfaction;
		this.eatingTime = eatingTime;
	}

	public Dish() {
		super();
	}

	public int getDishID() {
		return dishID;
	}

	public void setDishID(int dishID) {
		this.dishID = dishID;
	}

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	public int getEatingTime() {
		return eatingTime;
	}

	public void setEatingTime(int eatingTime) {
		this.eatingTime = eatingTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dishID;
		result = prime * result + eatingTime;
		result = prime * result + satisfaction;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Dish)) {
			return false;
		}
		Dish other = (Dish) obj;
		if (dishID != other.dishID) {
			return false;
		}
		if (eatingTime != other.eatingTime) {
			return false;
		}
		if (satisfaction != other.satisfaction) {
			return false;
		}
		return true;
	}
}
