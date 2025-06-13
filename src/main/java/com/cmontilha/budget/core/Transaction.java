package com.cmontilha.budget.core;

import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Transaction.findByUserId",
                query = "FROM Transaction WHERE userId = :userId"
        )
})
public class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String type; // "INCOME" ou "EXPENSE"
    private Double amount;
    private String description;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
