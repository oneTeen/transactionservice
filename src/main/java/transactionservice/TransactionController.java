package transactionservice;

import javax.jws.WebParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactionservice")
public class TransactionController {

    private TransactionBean tb = new TransactionBean();

    //PUT /transactionservice/transaction/$transaction_id
    @RequestMapping(path = "/transaction/{transaction_id}", method = RequestMethod.PUT)
    public String createTransaction(
            @PathVariable Long transaction_id,
            @WebParam(name = "body") String body){   
        return tb.addTransaction(transaction_id, body);
    }

    //GET /transactionservice/transaction/$transaction_id
    @RequestMapping(path = "/transaction/{transaction_id}", method = RequestMethod.GET)
    public String getTransaction(@PathVariable Long transaction_id) {   
        return tb.getTransaction(transaction_id);
    }

    //GET /transactionservice/types/$type
    @RequestMapping(path = "/types/{type}", method = RequestMethod.GET)
    public String getTransactionsByType(@PathVariable String type){
        return tb.getTransactionsByType(type);
    }

    //GET /transactionservice/sum/$transaction_id
    @RequestMapping("/sum/{parent_id}")
    public String getSum(@PathVariable Long parent_id){
        return tb.getSum(parent_id);
    }
}
