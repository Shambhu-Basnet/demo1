package com.semantro.demo.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONValue;
import com.semantro.demo.global.GlobalData;
import com.semantro.demo.model.Category;
import com.semantro.demo.model.Product;
import com.semantro.demo.service.CategoryService;
import com.semantro.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
//        System.out.println(JSONValue.toJSONString(categoryService.getAllCategory()));
//        System.out.println(JSONValue.toJSONString(productService.getAllProductByName("Black Forest")));
//        return JSONValue.toJSONString(categoryService.getAllCategory());
//        return JSONValue.toJSONString(productService.getAllProduct())
//        return "ttt";
//        System.out.println(productService.getAllProductByName("Black Forest"));

        Set<String> categoryName = new HashSet<>(productService.getProductName());

        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategory());
//        model.addAttribute("cartCount", CartController.cart.size());
//        model.addAttribute("Bread", productService.getAllByName("Bread"));
        List<Product> productList = new ArrayList<>();
        for(String category: categoryName) {
            model.addAttribute(category, productService.getAllByName(category));
            productList.addAll(productService.getAllByName(category));
        }


//        List<Product> productList = new ArrayList<>();
//        for(String p: categoryName) {
//            productList.add((Product) productService.getAllByName(p));
//        }


//        return JSONArray.toJSONString(productList);
//        return "index";
        System.out.println(categoryName);

        System.out.println(JSONArray.toJSONString(productList));

//        return JSONArray.toJSONString(productService.getAllProduct());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("cartCount", CartController.cart.size());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        model.addAttribute("cartCount", CartController.cart.size());

        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount", CartController.cart.size());

        return "viewProduct";
    }

    @GetMapping("/index/viewproduct/{id}")
    public String viewIndexProduct(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount", CartController.cart.size());
        return "viewIndexProduct";
    }
}