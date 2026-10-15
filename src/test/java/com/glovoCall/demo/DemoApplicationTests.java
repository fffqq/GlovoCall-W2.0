package com.glovoCall.demo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.glovoCall.demo.Restaurants.RestaurantsInterface;
import com.glovoCall.demo.Restaurants.RestaurantsService;
import com.glovoCall.demo.Restaurants.restaurantDTOResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private RestaurantsService restaurantsService;


	@Test
	void loadingRestaurantsTest(){
		boolean check=true;
		List<restaurantDTOResponse> list=restaurantsService.ImageAndNameOfAllRest();
		HashMap<String, Integer> map=new HashMap<>();
		for(restaurantDTOResponse item:list){
			map.put(item.name(),map.getOrDefault(item.name(),0)+1);
		}
		for (restaurantDTOResponse item:list){
			if(map.get(item.name())>1) {
				check = false;
				break;
			}
		}
		assertTrue(check,"Duplicates");
	}

}
