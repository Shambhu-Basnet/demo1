package com.semantro.demo.controller;

import com.semantro.demo.global.GlobalData;
import com.semantro.demo.model.Product;
import com.semantro.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    public static List<Product> cart= new ArrayList<>();

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/addToCartIndex/{id}")
    public String addToCartIndex(@PathVariable int id) {
        cart.add(productService.getProductById(id).get());

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", cart);

        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        cart.remove(index);
        return ("redirect:/cart");
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        cart.clear();
        return "checkout";
    }
}
