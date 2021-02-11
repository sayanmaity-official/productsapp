package com.rakuten.training.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest_With_Mocks {

	@Test
	void createNewProduct_Must_Return_Id_When_Product_Value_GTEQ_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10000, 2);
		Product returnedByMethod = new Product("anyName", 10001, 3);
		returnedByMethod.setId(1);

		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.save(argToMethod)).thenReturn(returnedByMethod);
		objUnderTest.setDao(mockDAO);
		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		// Assert
		assertTrue(id > 0);

	}

	@Test
	public void createNewProduct_Must_ThrowException_When_Product_Value_LT_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 9999, 1);

		//Assert
		Assertions.assertThatThrownBy(() -> {
			objUnderTest.createNewProduct(argToMethod);
		}).isInstanceOf(IllegalArgumentException.class);
		
		
		
//		try{
//			objUnderTest.createNewProduct(argToMethod);
//			Assertions.fail("did not throw exception");
//		}catch(IllegalArgumentException e) {
//			
//		}

	}
	
	@Test
	public void removeExisting_Must_Delete_Product_When_Existing_Product_Value_LT_100k() {
		//Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		int id = 1;
		Product returnedByMock = new Product("test", 10000, 2);
		returnedByMock.setId(100);
		
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(id)).thenReturn(returnedByMock);
		objUnderTest.setDao(mockDAO);
		//Act
		objUnderTest.removeExisting(id);
		
		//Assert
		Mockito.verify(mockDAO).deleteById(id); // this is to verify the function was actually called.
	}
	
	
	
	
}