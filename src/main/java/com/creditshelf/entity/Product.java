package com.creditshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"product_id", "company_id"})
	)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private String name;
    private BigDecimal price;
    private String currency;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    
    
    @Override
    public String toString() {
        return "{" +
            " productId='" + getProductId() + "'" +
            ", price='" + getPrice() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", companyName='" + getCompany().getName() + "'" +
            "}";
    }
}
