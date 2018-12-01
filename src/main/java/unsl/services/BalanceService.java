package unsl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unsl.entities.Balance;
import unsl.entities.ResponseError;
import unsl.repository.BalanceRepository;
import unsl.utils.RestService;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    RestService restService;

    /**
     * @param userId
     * @return
     */
    public Object findByUserId(Long userId) {

        Balance balance =  balanceRepository.findByUserId(userId);

        if (balance == null) {
            return new ResponseEntity(new ResponseError(404, "Balance not found"), HttpStatus.NOT_FOUND);
        }

        return balance;
    }

    /**
     * @param balance
     * @return
     * @throws Exception
     */
    public Object saveBalance(Balance balance) throws Exception {

        Long userId = balance.getUserId();

        restService.getUser(String.format("http://localhost:8888/users/%d", userId));

        return balanceRepository.save(balance);
    }

    /**
     * @param balanceId
     * @return
     */
    public Object findById(Long balanceId) {

        Balance balance = balanceRepository.findById(balanceId).orElse(null);

        if (balance == null) {
            return new ResponseEntity(new ResponseError(404, "Balance not found"), HttpStatus.NOT_FOUND);
        }

        return balance;
    }

    /**
     * @param newBalance
     * @param balanceId
     * @return
     */
    public Object updateBalance(Balance newBalance, Long balanceId) {

        Balance balance = balanceRepository.findById(balanceId).orElse(null);

        if (balance == null) {
            return new ResponseEntity(new ResponseError(404, "Balance not found"), HttpStatus.NOT_FOUND);
        }

        balance.setAmount(newBalance.getAmount());

        return balanceRepository.save(balance);
    }

}
