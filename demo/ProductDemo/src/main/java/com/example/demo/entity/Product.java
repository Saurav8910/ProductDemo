package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prodTab")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pid")
	private Integer id;
	@Column(name = "pname")
	private String prodName;
	@Column(name="psku")
	private Integer prodSku;
	@Column(name="pprice")
	private Double prodPrice;
	@Column(name="pdesc")
	private String prodDesc;

}
