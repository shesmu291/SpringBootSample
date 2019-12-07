package com.cko.sampleSpringProject;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.dao.ProductDAO;
import com.cko.sampleSpringProject.model.Authority;
import com.cko.sampleSpringProject.model.Films;
import com.cko.sampleSpringProject.model.Product;
import com.cko.sampleSpringProject.model.User;
import com.cko.sampleSpringProject.service.AuthorityService;
import com.cko.sampleSpringProject.service.SMSCService;
import com.cko.sampleSpringProject.service.UserService;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitData {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    SMSCService smscSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    FilmDAO filmDAO;

    @Autowired
    ProductDAO productDAO;

    Faker faker = new Faker();

    public void initData() {
        initFilms();
        initProduct();
        initUserAndRoles();


    }

    public void initFilms() {
        for (int i = 0; i < 100; i++) {
            Films films = new Films(faker.superhero().name(), faker.number().numberBetween(0, 10), faker.number().numberBetween(0, 21));
            filmDAO.save(films);
        }

    }

    public void initProduct() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product(faker.commerce().productName(), faker.number().numberBetween(0,1000000), faker.number().numberBetween(0,10));
            productDAO.save(product);
        }

    }

    private void initUserAndRoles() {
        Authority adminAuthority = new Authority("ROLE_ADMIN");
        Authority userAuthority = new Authority("ROLE_USER");
        authorityService.insert(adminAuthority);
        authorityService.insert(userAuthority);

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(adminAuthority);
       // for(int i=0; i<10;i++) {
            userService.insert(new User("1@mail.ru", bCryptPasswordEncoder.encode("1"), authorities));
        //}

    }


}
