package com.cmontilha.budget.api;

import com.cmontilha.budget.core.Transaction;
import com.cmontilha.budget.core.User;
import com.cmontilha.budget.db.TransactionDAO;
import com.cmontilha.budget.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;
    private final TransactionDAO transactionDAO;

    public UserResource(UserDAO userDAO, TransactionDAO transactionDAO) {
        this.userDAO = userDAO;
        this.transactionDAO = transactionDAO;
    }

    @POST
    @UnitOfWork
    public User createUser(User user) {
        return userDAO.create(user);
    }

    @POST
    @Path("/{id}/transactions")
    @UnitOfWork
    public Transaction addTransaction(@PathParam("id") Long id, Transaction transaction) {
        transaction.setUserId(id);
        return transactionDAO.create(transaction);
    }

    @GET
    @Path("/{id}/transactions")
    @UnitOfWork
    public List<Transaction> getTransactions(@PathParam("id") Long id) {
        return transactionDAO.findByUserId(id);
    }

    @GET
    @Path("/{id}/balance")
    @UnitOfWork
    public Double getBalance(@PathParam("id") Long id) {
        return transactionDAO.findByUserId(id).stream()
                .mapToDouble(t -> t.getType().equalsIgnoreCase("INCOME") ? t.getAmount() : -t.getAmount())
                .sum();
    }
}
