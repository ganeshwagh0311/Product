package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private double price;

   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false)
   @JsonIgnore
   private Category category;

   // Getters and Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }
}



