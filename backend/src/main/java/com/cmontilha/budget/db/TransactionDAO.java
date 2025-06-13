package com.cmontilha.budget.db;

import com.cmontilha.budget.core.Transaction;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class TransactionDAO extends AbstractDAO<Transaction> {
    public TransactionDAO(SessionFactory factory) {
        super(factory);
    }

    public Transaction create(Transaction transaction) {
        return persist(transaction);
    }

    public List<Transaction> findByUserId(Long userId) {
        return list(namedQuery("Transaction.findByUserId")
                .setParameter("userId", userId));
    }
}
