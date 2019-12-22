package com.creditshelf.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"order_number", "company_id"})
	)
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "order_number")
	private Long orderNumber;
	private LocalDate orderDate;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private List<SalesOrderDetails> salesOrderDetails = new ArrayList<>();

	@Transient
    public BigDecimal getTotal() {
		
		return getSalesOrderDetails().stream()
	                .map(p -> p.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        
    }
	 @Transient
	    public String getCurrencyCode() {
	        return getSalesOrderDetails().get(0).getCurrencyCode();
	    }
   
}
