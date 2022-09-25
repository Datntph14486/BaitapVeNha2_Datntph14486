package com.example.baitapvenha2.controller;

import com.example.baitapvenha2.model.Person;
import com.example.baitapvenha2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Matcher;

@Controller
public class HomeController {
    @Autowired
    HttpServletRequest request;

    @GetMapping("person")
    public String person(Model model){
        model.addAttribute("person",new Person());

        return"formPersonSubmit";
    }

    @PostMapping("show-person")
    public String showPerson(Model model, @ModelAttribute("person") Person person){
        String regexNumber = "\\d+";
        int yearOfBirth =  person.getYearOfBirth();

        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        if(person.getName().isBlank() || person.getYearOfBirth() >year || person.getAddress().isBlank()  ){

            if(person.getName().isBlank()){
                model.addAttribute("name","name không được để trống");
            }
            if( person.getYearOfBirth() >year){
                model.addAttribute("yearOfBirth","năm sinh không được lớn hơn năm : "+year);

            }
            if(person.getAddress().isBlank()){
                model.addAttribute("address","address không được để trống");
            }
            return "formPersonSubmit";

        }else{
            model.addAttribute("name",person.getName());
            model.addAttribute("address",person.getAddress());
            model.addAttribute("myYearOfBirth",year-person.getYearOfBirth());
            return "showPerson";
        }



    }

    @GetMapping("/product")
    public  String product(Model model){

        model.addAttribute("product", new Product());
        return "formProduct";
    }

    @PostMapping("show-product")
    public String showProduct(Model model, @ModelAttribute("product") Product product){

        if(product.getName().isBlank() ){

            if(product.getName().isBlank()){
                model.addAttribute("nameProduct","name không được để trống");
            }


            return "formProduct";

        }else{


            LocalDateTime date = LocalDateTime.now();
            int day = date.getDayOfMonth();
            int Month =date.getMonthValue();
            int year = date.getYear();

            model.addAttribute("name",product.getName());
            model.addAttribute("price",product.getPrice());
            model.addAttribute("quantity",product.getQuantity());
            double price = product.getPrice();
            int quantity = product.getQuantity();

            double pay = price * quantity;
            System.out.println(pay);
            model.addAttribute("pay",product.getQuantity() * product.getPrice());
            model.addAttribute("datePay","Ngày : "+ day + "Tháng : "+ Month + "Năm : "+year);


            return "showProduct";
        }

    }
}
