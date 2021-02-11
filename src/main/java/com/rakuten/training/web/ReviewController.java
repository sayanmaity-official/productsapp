package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

//Create a new Controller called ReviewController
//
//2 operations
//a. Retrieve all reviews for a given product - done
//b. add a review to a product 


// ----------mine implementation------------//
//@RestController
//public class ReviewController {
//
//	@Autowired
//	ReviewService reviewService;
//
//	@Autowired
//	ProductService productService;
//
//	@GetMapping("/products/{productId}/reviews")
//	public List<Review> getAllReviews(@PathVariable("productId") int p_id) {
////		Product p = productService.findById(p_id);
//
//		List<Review> reviewList = reviewService.findByProduct_Id(p_id);
//
//		return reviewList;
//	}
//
//	@PostMapping("/products/{productId}/reviews")
//	public ResponseEntity<Review> addReviewToProduct(@PathVariable("productId") int p_id, @RequestBody Review reviewToBeAdded){
//		
//		Product p = productService.findById(p_id);
//		if(p!=null) {
//		
//				int id = reviewService.addReviewToProduct(reviewToBeAdded, p_id);
//				HttpHeaders headers = new HttpHeaders();
//				headers.setLocation(URI.create("/products/"+p_id+"/reviews/"+id));
//				return new ResponseEntity<Review>(reviewToBeAdded, headers, HttpStatus.CREATED);
//		}else{
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//
//	}
//
//}



//----------sir's implementation------------//

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ReviewController{
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService ProductService;
	
	@GetMapping("/products/{id}/reviews")
	public ResponseEntity getAllReviewsForAProduct(@PathVariable("id")  int productId) {
		Product p = ProductService.findById(productId);
		if(p==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			List<Review> reviews = reviewService.findByProduct_Id(productId);
			return new ResponseEntity<>(reviews,HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/products/{id}/reviews")
	public ResponseEntity addReviewToProduct(@PathVariable("id") int productId,@RequestBody Review reviewToBeAdded) {
		Product p = ProductService.findById(productId);
		if(p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Review added = reviewService.addReviewToProduct(reviewToBeAdded, productId);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+productId+"/reviews/"+added.getId()));
			return new ResponseEntity<>(added,headers,HttpStatus.CREATED);
			
		}
	}
	
}
