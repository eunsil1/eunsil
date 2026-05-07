package com.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "demo_items")
public class DemoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int qty;

    public DemoItem() {
    }

    public DemoItem(Long id, String name, int qty) {
        this.id = id;
        this.name = name;
        this.qty = qty;
    }

    public DemoItem( String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }
}
