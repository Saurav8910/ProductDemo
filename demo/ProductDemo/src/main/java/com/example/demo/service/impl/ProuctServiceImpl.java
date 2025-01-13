package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.IProductService;

@Service
public class ProuctServiceImpl implements IProductService {


	@Autowired
	private ProductRepository repo;
	

	public Integer saveProduct(Product product) {
		Integer id = repo.save(product).getId();
		return id;
	}

	
	public List<Product> getAllProduct() {
	List<Product> list = repo.findAll();
		return list;
	}

	public Product getOneProduct(Integer id) {
		return repo.findById(id).orElseThrow(()->new ProductNotFoundException("Product "+id+ " not Found"));
	}

	public void deleteProduct(Integer id) {
		repo.delete(getOneProduct(id));

	}

	
	public void updateProduct(Product product) {
	if(product.getId()==null || !repo.existsById(product.getId()))
		throw new ProductNotFoundException("Product "+product.getId()+" not Found...");
	else
		repo.save(product);
	}

}
