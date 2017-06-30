package com;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Dish;
import com.model.DishHolder;
import com.services.GordonService;
import com.services.GordonServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class GordonServiceTest {

	GordonService gordonService = new GordonServiceImpl();
	List<Dish> dishList = new ArrayList<Dish>();

	@Before
	public void setUp() throws Exception {

		Dish dish1 = new Dish();
		dish1.setDishID(2);
		dish1.setEatingTime(3);
		dish1.setSatisfaction(7);
		dishList.add(dish1);

		Dish dish2 = new Dish();
		dish2.setDishID(2);
		dish2.setEatingTime(4);
		dish2.setSatisfaction(6);
		dishList.add(dish2);

		Dish dish3 = new Dish();
		dish3.setDishID(3);
		dish3.setEatingTime(4);
		dish3.setSatisfaction(9);
		dishList.add(dish3);
	}

	@Test
	public void testSelectedSatisfactoyDishs() {

		DishHolder dishHolder = gordonService.selectedSatisfactoyDishs(dishList, 10);
		List<Dish> finalList = dishHolder.getDishes();
		
		assertNotNull(finalList);
		assertTrue(finalList.size() == 2);
		assertTrue(finalList.get(0).getEatingTime() == 3);
		assertTrue(finalList.get(0).getSatisfaction() == 7);

		assertTrue(finalList.get(1).getEatingTime() == 4);
		assertTrue(finalList.get(1).getSatisfaction() == 9);

	}

	@Test
	public void testTotalTimeUsed() {
		int count = gordonService.totalTimeUsed(dishList);
		assertTrue(count == 11);
	}

	@Test
	public void testTotalSatisfaction() {
		int count = gordonService.totalSatisfaction(dishList);
		assertTrue(count == 22);
	}
}
