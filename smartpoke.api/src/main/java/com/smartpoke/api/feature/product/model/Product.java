package com.smartpoke.api.feature.product.model;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    private String ean;
    @Column(columnDefinition = "text")
    private String name;
    private String amount;
    private String nutriscore;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String brand;
    @Column(columnDefinition = "text")
    private String ingredientsText;
    private String origin;
    private String preservation;
    @Column(columnDefinition = "text")
    private String picture;
    private LocalDateTime lastUpdate;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "ean")
    private ProductMacronutrients productMacronutrients;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "ingredients_products",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")}
    )
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "allergens_products",
            joinColumns = {@JoinColumn(name = "allergen_id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "allergen_name", referencedColumnName = "name"),
                    @JoinColumn(
                            name = "allergen_lan", referencedColumnName = "lan")
            }
    )
    private Set<Allergen> allergens;





}