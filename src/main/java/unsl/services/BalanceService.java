package unsl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unsl.entities.Balance;
import unsl.repository.BalanceRepository;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    public Balance findByUserId(Long userId) {

        return balanceRepository.findByUserId(userId);
    }

    public Balance saveBalance(Balance balance) {

        return balanceRepository.save(balance);
    }

    public Balance findById(Long balanceId) {

        return balanceRepository.findById(balanceId).orElse(null);
    }

    public Balance updateBalance(Balance oldBalance, Balance newBalance) {

        oldBalance.setAmount(newBalance.getAmount());

        return saveBalance(oldBalance);
    }
}
