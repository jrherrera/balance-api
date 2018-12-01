package unsl.controllers;

import javassist.NotFoundException;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import unsl.entities.Balance;
import unsl.services.BalanceService;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

import static unsl.utils.Responses.NOT_FOUND_BODY;

@RestController
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @GetMapping(value = "/balance/search")
    @ResponseBody
    public Object searchUser(@RequestParam("user_id") Long userId) {

        Balance balance = balanceService.findByUserId(userId);
        return buildResponse(balance);
    }

    @PostMapping(value = "/balance")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Balance createBalance(@RequestBody Balance balance) {

        return balanceService.saveBalance(balance);
    }

    @GetMapping(value = "/balance/{balanceId}")
    @ResponseBody
    public Object getUser(@PathVariable("balanceId") Long balanceId) {

        Balance balance = balanceService.findById(balanceId);

        return buildResponse(balance);
    }

    /**
     * @param newBalance
     * @param balanceId
     * @return
     */
    @PutMapping(value = "/balance/{balanceId}")
    @ResponseBody
    public Object updateBalance(@RequestBody Balance newBalance, @PathVariable("balanceId") Long balanceId) {

        Balance oldBalance = balanceService.findById(balanceId);

        if ( oldBalance == null) {
            return new ResponseEntity(NOT_FOUND_BODY, HttpStatus.NOT_FOUND);
        }

        return balanceService.updateBalance(oldBalance, newBalance);
    }

    /**
     * @param balance
     * @return
     */
    private Object buildResponse(Balance balance) {
        if ( balance == null) {
            return new ResponseEntity(NOT_FOUND_BODY, HttpStatus.NOT_FOUND);
        }
        return balance;
    }

}

