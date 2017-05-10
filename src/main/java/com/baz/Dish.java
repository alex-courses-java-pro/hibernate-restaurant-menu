package com.baz;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by arahis on 5/9/17.
 */
@Entity
@Table(name = "menu")
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
@NoArgsConstructor
@Data public class Dish {

    @Id
    @GeneratedValue()
    private long id;
    private String name;
    private int price;    // UAH
    private int weight;   // gram
    private int discount; // percentage

    public Dish(String name, int price, int weight, int discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }
}
