package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.IProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

	@Autowired
	private IProductService service;
	
	@PostMapping("/save")
	@ApiOperation("SAVE THE PRODUCT")
	public ResponseEntity<String> saveproduct(
			@RequestBody Product product){
		Integer id=service.saveProduct(product);
		return new ResponseEntity<String>("Product ' "+id+" ' created ",HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@ApiOperation("FETCH ALL PRODUCTS")
	public ResponseEntity<List<Product>> getAllStudent(){
		List<Product> list=service.getAllProduct();
		return new ResponseEntity<List<Product>> (list,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	@ApiOperation("FETCH ONE PRODUCTS")
	public ResponseEntity<?> fetchOneProduct(@PathVariable Integer id
			){
		ResponseEntity<?> response=null;
		try {
			Product product=service.getOneProduct(id);
			response=new ResponseEntity<Product>(product , HttpStatus.OK);
		}catch (ProductNotFoundException pnfe) {
		   pnfe.printStackTrace();
		   response = new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	@ApiOperation("REMOVE PRODUCTS")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
		ResponseEntity<String> response=null;
		try {
			service.deleteProduct(id);
			response= new ResponseEntity<String>("Product ' "+id+"' Deleted ",HttpStatus.OK);
		}catch (ProductNotFoundException e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@PutMapping("/modify")
	@ApiOperation("UPDATE PRODUCTS")
	public ResponseEntity<String> updateProduct(
			@RequestBody Product product){
		ResponseEntity<String> response = null;
		try {
			service.updateProduct(product);
			response= new ResponseEntity<String>("Product '"+product.getId()+"' Updated",HttpStatus.OK);
			
		}catch (ProductNotFoundException e) {
			e.printStackTrace();
			response= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		return response;
	}

}
