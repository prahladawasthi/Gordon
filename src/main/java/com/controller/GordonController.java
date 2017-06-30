package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Dish;
import com.model.DishHolder;
import com.services.GordonService;

@Controller
public class GordonController {

	@Autowired
	GordonService gordonService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getDishes(ModelAndView modelAndView) {
		List<Dish> totalDishList =gordonService.totalDish();
		modelAndView.addObject("totalDishList", totalDishList);
		modelAndView.addObject("totalSatisfaction", gordonService.totalSatisfaction(totalDishList));
		modelAndView.setViewName("home");

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView getFinalDishes(HttpServletRequest request, ModelAndView modelAndView) {

		List<Dish> totalDishList = gordonService.totalDish();
		DishHolder dishHolder = gordonService.selectedSatisfactoyDishs(gordonService.totalDish(),
				Integer.parseInt(request.getParameter("minutes")));
		List<Dish> satisfactoryDishList = dishHolder.getDishes();

		modelAndView.addObject("totalDishList", totalDishList);
		modelAndView.addObject("totalTime", request.getParameter("minutes"));

		modelAndView.addObject("finalDishList", satisfactoryDishList);
		modelAndView.addObject("timeUsed", dishHolder.getTotalTime());
		modelAndView.addObject("totalSatisfaction", gordonService.totalSatisfaction(totalDishList));
		modelAndView.addObject("totalSatisfactionAchived", dishHolder.getTotalSatisfaction());
		modelAndView.setViewName("home");

		return modelAndView;
	}

}
