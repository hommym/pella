package com.pellanotes.pella.database.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "sub_plan")
public class SubPlan {  // subscription plans model
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    String name;

    String description;

    @Column(name = "payment_gateway_product_id")
    private String paymentGatewayProductId;

    @Column(name = "payment_gateway_price_id")
    private String paymentGatewayPriceId;


    public SubPlan(){  //jpa

    }


    public SubPlan(String name, String description, String productId, String priceId) {
        this.name = name;
        this.description = description;
        this.paymentGatewayProductId = productId;
        this.paymentGatewayPriceId = priceId;
    }


    Long getId(){
        return this.id;
    }


       
    }

