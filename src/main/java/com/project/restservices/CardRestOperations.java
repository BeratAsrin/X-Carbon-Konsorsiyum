package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Card;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class CardRestOperations {

    @PostMapping("/register")
    private void addNewCard(@RequestBody Card card){
        HibernateOperations.addNewObject(card);
    }

    @GetMapping("/get/{id}")
    private Card getCardById(@PathVariable Long id){
        return (Card) HibernateOperations.getObjectById(new Card(), id);
    }

    @GetMapping("/getall")
    private List<?> getAllAccounts(){
        return HibernateOperations.getAll(new Card());
    }

    @DeleteMapping("/delete/{id}")
    private void deleteCardById(@PathVariable Long id){
        HibernateOperations.deleteObjectById(new Card(), id);
    }

}
