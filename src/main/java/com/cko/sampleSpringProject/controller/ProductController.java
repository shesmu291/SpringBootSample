package com.cko.sampleSpringProject.controller;


import com.cko.sampleSpringProject.dao.ProductDAO;
import com.cko.sampleSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductDAO productDAO;
    Long qq;



    @GetMapping("/creat")
    public String showCreatProduct() {
        return "creatProduct";
    }

    @PostMapping("/creat")
    public RedirectView addNewProduct(Product product) {
        productDAO.save(product);
        return new RedirectView("/products/all");
    }

    @GetMapping("/edit")
    public ModelAndView showEditProduct(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productDAO.findProductById(id);
        modelAndView.addObject("product",product);
        modelAndView.setViewName("editProduct");

        return modelAndView;
    }

    @PostMapping("/edit")
    public RedirectView editProduct(Product product){

        productDAO.save(product);

        return new RedirectView("/products/all");
    }

    @GetMapping("/all")
    public ModelAndView allProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allProduct");
        List<Product> productList=productDAO.findAll();
        modelAndView.addObject("products",productList);

        return modelAndView;


    }


//    @GetMapping("/buy")
//    public RedirectView deleteProduct(@RequestParam Long id){
//        List<Product> productList=productDAO.findAll();
//
//
//
//        return new RedirectView("/products/all");
//    }
    @GetMapping("/buy")
    public ModelAndView ProcBuy(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Product products = productDAO.findProductById(id);
        qq = products.getId();
        modelAndView.addObject("product", products);
        modelAndView.setViewName("Products/ProductPage");
        return modelAndView;
    }
    @PostMapping("/buy")
    public RedirectView Buy(int count) {
        Product products = productDAO.findProductById(qq);
        if (products.getAmount() > count) {
            products.setAmount(products.getAmount() - count);
            productDAO.save(products);
            return new RedirectView("/products/tenQ");
        } else if (products.getAmount() == count) {
            productDAO.deleteById(qq);
            return new RedirectView("/products/tenQ");
        } else {
            return new RedirectView("/products/error");
        }
    }

    @GetMapping("/tenQ")
    public String tenQ() {
        return "Products/ProductTenQ";
    }

    @GetMapping("/error")
    public String error() {
        return "Products/ProductError";
    }

}
