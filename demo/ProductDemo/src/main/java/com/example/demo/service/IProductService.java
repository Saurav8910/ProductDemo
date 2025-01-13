package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface IProductService {

	Integer saveProduct(Product product);
	List<Product> getAllProduct();
	Product getOneProduct(Integer id);
	void deleteProduct(Integer id);
	void updateProduct(Product product);
}
