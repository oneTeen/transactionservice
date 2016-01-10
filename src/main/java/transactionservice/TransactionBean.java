package transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionBean {

    private Map<Long, Transaction> transactionMap = new HashMap();
    private ObjectMapper mapper = new ObjectMapper();
    private String jsonTransaction = null;

    public TransactionBean() {
        generateTestTransactions();
    }

    //Adds a transaction to the transactionMap
    public String addTransaction(Long transaction_id, String body) {

        try {
            Transaction tr = mapper.readValue(body, Transaction.class);
            tr.setTransaction_id(transaction_id);
            transactionMap.put(transaction_id, tr);
            return "{\"status\":ok}";
        } catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
            return "{\"status\": error}";
        }
    }

    //Returns a String containing the values in the Transaction object
    public String getTransaction(Long transaction_id) {

        Transaction t = transactionMap.get(transaction_id);

        try {
            jsonTransaction = mapper.writeValueAsString(t);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonTransaction;
    }

    //Returns a string with all the IDs that share the same type
    public String getTransactionsByType(String type) {

        List<Long> idsByType = new ArrayList();

        transactionMap.entrySet().stream().forEach((item) -> {
            if (item.getValue().getType().equals(type)) {
                idsByType.add(item.getValue().getTransaction_id());
            }
        });

        try {
            jsonTransaction = mapper.writeValueAsString(idsByType);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonTransaction;
    }

    public String getSum(Long parent_id) {

        Double sum = 0d;

        for (Map.Entry<Long, Transaction> item : transactionMap.entrySet()) {

            try {
                if (item.getValue().getParent_id() == parent_id) {
                    sum = sum + item.getValue().getAmount();
                }
            } catch (Exception ex) {
                Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "{\"sum\":" + sum.toString() + "}";

    }

    //I created this so you guys have some transactions to play with
    //when testing all this: 
    //GET /transactionservice/transaction/$transaction_id
    //GET /transactionservice/types/$type
    //GET /transactionservice/sum/$transaction_id
    private void generateTestTransactions() {

        //transaction with no parent transactions
        transactionMap.put(1000l, new Transaction(1000l, 15000d, "Cars"));
        transactionMap.put(2000l, new Transaction(2000l, 3000d, "Bikes"));
        transactionMap.put(3000l, new Transaction(3000l, 130000000d, "Yachts"));
        transactionMap.put(4000l, new Transaction(4000l, 50000000d, "Jets"));
        transactionMap.put(5000l, new Transaction(5000l, 5000d, "Horses"));

        //transaction with no parent transactions
        transactionMap.put(1001l, new Transaction(1001l, 38000d, "Cars", 1000l));
        transactionMap.put(1002l, new Transaction(1002l, 11000d, "Cars", 1000l));
        transactionMap.put(2001l, new Transaction(2001l, 350d, "Bikes", 2000l));
        transactionMap.put(2002l, new Transaction(2002l, 100d, "Bikes", 2000l));
        transactionMap.put(2003l, new Transaction(2003l, 25d, "Bikes", 2000l));
        transactionMap.put(2004l, new Transaction(2004l, 2540d, "Bikes", 2000l));
        transactionMap.put(2005l, new Transaction(2005l, 1100d, "Bikes", 2000l));
        transactionMap.put(4001l, new Transaction(4001l, 110000000d, "Jets", 4000l));
        transactionMap.put(4002l, new Transaction(4002l, 39000000d, "Jets", 4000l));
        transactionMap.put(4003l, new Transaction(4003l, 205000000d, "Jets", 4000l));
    }

    public Map<Long, Transaction> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(Map<Long, Transaction> transactionMap) {
        this.transactionMap = transactionMap;
    }

}
