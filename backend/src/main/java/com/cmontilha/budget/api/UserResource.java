package com.cmontilha.budget.api;

import com.cmontilha.budget.core.Transaction;
import com.cmontilha.budget.core.User;
import com.cmontilha.budget.db.TransactionDAO;
import com.cmontilha.budget.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import java.util.Date;
import java.util.List;

@Path("/")
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
    @Path("users")
    @UnitOfWork
    public User createUser(User user) {
        return userDAO.create(user);
    }

    @GET
    @Path("users/{id}")
    @UnitOfWork
    public User getUser(@PathParam("id") Long id) {
        return userDAO.findById(id);
    }

    @POST
    @Path("users/login")
    @UnitOfWork
    public User login(User credentials) {
        User user = userDAO.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if (user == null) {
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }
        return user;
    }

    @POST
    @Path("transactions")
    @UnitOfWork
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getUser() != null) {
            transaction.setUser(userDAO.findById(transaction.getUser().getId()));
        }
        if (transaction.getDate() == null) {
            transaction.setDate(new Date());
        }
        return transactionDAO.create(transaction);
    }

    @GET
    @Path("transactions")
    @UnitOfWork
    public List<Transaction> listTransactions(@QueryParam("userId") Long userId) {
        if (userId == null) {
            throw new BadRequestException("userId query parameter is required");
        }
        return transactionDAO.findByUserId(userId);
    }
}
