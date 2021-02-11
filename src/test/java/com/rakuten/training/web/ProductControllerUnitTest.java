package com.rakuten.training.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@ExtendWith(SpringExtension.class)	//tells JUNIT that this test require Spring framework
@WebMvcTest(controllers = {ProductController.class})	// tells Spring to bring up its Web parts/functionality
public class ProductControllerUnitTest {
	
	@MockBean			// tells spring treat productservice as bean object wherever possible
	ProductService mockService;
	
	@Autowired
	MockMvc mockMVC;	//this object is used to perform various requests(GET,POST) and give the response
	
	@Test
	void testGetProductById() throws Exception{
		
		//AAA
		//ARRANGE
		int id = 1;
		Product dataReturnedByMock = new Product("test",12345,2);
		dataReturnedByMock.setId(id);
		
		
		Mockito.when(mockService.findById(id)).thenReturn(dataReturnedByMock);
		
		
		//ACT and ASSERT
		mockMVC
		.perform(
				MockMvcRequestBuilders.get("/products/{id}",id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(
						MockMvcResultMatchers.status().isOk()
				).andExpect(jsonPath("$.id", CoreMatchers.is(1)));
		
	}
	

}
