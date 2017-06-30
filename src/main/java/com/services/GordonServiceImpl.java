package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.model.Dish;
import com.model.DishHolder;

@Service("gordonService")
public class GordonServiceImpl implements GordonService {

	@Autowired
	private ResourceLoader resourceLoader;

	InputStream inputStream = null;
	BufferedReader br = null;

	private static int TOTAL_DISH = 0;

	@Override
	public List<Dish> totalDish() {
		List<Dish> dishList = new ArrayList<Dish>();
		try {
			inputStream = resourceLoader.getResource("classpath:static/menu.txt").getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			int dishID = 1;
			while ((line = br.readLine()) != null) {

				if (line.contains("t")) {
					GordonServiceImpl.TOTAL_DISH = Integer.parseInt(line.replace("t", " ").trim());
					System.out.println("Total Time : " + GordonServiceImpl.TOTAL_DISH);
				} else {
					StringTokenizer st = new StringTokenizer(line);
					Dish dish = new Dish();
					dish.setDishID(dishID);
					while (st.hasMoreElements()) {
						dish.setSatisfaction(Integer.parseInt(st.nextElement().toString()));
						dish.setEatingTime(Integer.parseInt(st.nextElement().toString()));
					}
					dishList.add(dish);
					dishID++;
				}

			}
			br.close();

			Collections.sort(dishList, new Comparator<Dish>() {
				@Override
				public int compare(Dish o1, Dish o2) {
					return o1.getEatingTime() - o2.getEatingTime();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dishList;
	}

	@Override
	public DishHolder selectedSatisfactoyDishs(List<Dish> dishList, int totalTime) {

		int maxsatisfaction = 0;
		int maxsatisfactionindex = 0;
		int index = 0;
		int n = dishList.size();

		ArrayList<DishHolder> dishHolderList = new ArrayList<DishHolder>();

		for (int i = 0; i < (1 << n); i++) {
			int sum = 0;
			int satisfaction = 0;
			List<Dish> diskIdList = new ArrayList<Dish>();
			for (int j = 0; j < n; j++) {

				if ((i & (1 << j)) > 0) {
					diskIdList.add(dishList.get(j));
					sum += dishList.get(j).getEatingTime();
					satisfaction += dishList.get(j).getSatisfaction();
				}
			}

			if (sum != 0 && sum <= totalTime) {

				if (satisfaction > maxsatisfaction) {
					maxsatisfaction = satisfaction;
					maxsatisfactionindex = index;
				}

				dishHolderList.add(index++, new DishHolder(diskIdList, satisfaction, sum));
			}
		}

		return dishHolderList.get(maxsatisfactionindex);
	}

	@Override
	public int totalTimeUsed(List<Dish> dishList) {
		int count = 0;
		for (Dish dish : dishList) {
			count += dish.getEatingTime();
		}
		return count;
	}

	@Override
	public int totalSatisfaction(List<Dish> dishList) {
		int count = 0;
		for (Dish dish : dishList) {
			count += dish.getSatisfaction();
		}
		return count;
	}

}
