package unsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unsl.entities.Balance;
import unsl.services.BalanceService;


@RestController
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    /**
     * @param userId
     * @return
     */
    @GetMapping(value = "/balance/search")
    @ResponseBody
    public Object searchUser(@RequestParam("user_id") Long userId) {

        return balanceService.findByUserId(userId);
    }

    /**
     * @param balance
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/balance")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Object createBalance(@RequestBody Balance balance) throws Exception {
        /*TODO: si el balance ya existe no dejo crear otro*/
        return balanceService.saveBalance(balance);
    }

    /**
     * @param balanceId
     * @return
     */
    @GetMapping(value = "/balance/{balanceId}")
    @ResponseBody
    public Object getUser(@PathVariable("balanceId") Long balanceId) {

        return balanceService.findById(balanceId);
    }

    /**
     * @param newBalance
     * @param balanceId
     * @return
     */
    @PutMapping(value = "/balance/{balanceId}")
    @ResponseBody
    public Object updateBalance(@RequestBody Balance newBalance, @PathVariable("balanceId") Long balanceId) {

        return balanceService.updateBalance(newBalance, balanceId);
    }

}

