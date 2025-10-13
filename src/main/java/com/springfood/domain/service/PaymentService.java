package com.springfood.domain.service;


import com.springfood.domain.exception.ConflictException;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Payment;
import com.springfood.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public void create(Payment payment) {
        this.repository.save(payment);
    }

    public Payment update(Long id, Payment payment) {
        payment.setId(id);

        this.findById(payment.getId());

        return this.repository.save(payment);
    }

    public List<Payment> listAll() {
        return this.repository.findAll();
    }

    public Payment findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Pagamento de Id %d não foi encontrado", id)));
    }

    public void delete(Long id) {
        Payment payment = this.findById(id);

        if(payment.getRestaurants().size() > 0){
            throw new ConflictException(String.format("O Pagamento de Id %d não pode ser deletado, pois está em uso por outra entidade", id));
        }

        this.repository.deleteById(id);
    }
}
