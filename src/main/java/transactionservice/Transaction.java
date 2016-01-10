
package transactionservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Transaction {
    
    @JsonIgnore
    private Long transaction_id;
    private Double amount;
    private String type;
    private Long parent_id; //optional

    //transaction_id is a long specifying a new transaction
    //amount is a double specifying the amount
    //type is a string specifying a type of the transaction.
    //parent_id is an optional long that may specify the parent transaction of this transaction.
    
    public Transaction() {
    }

    public Transaction(long transaction_id, double amount, String type) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(long transaction_id, double amount, String type, long parent_id) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.type = type;
        this.parent_id = parent_id;
    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }
  
}
