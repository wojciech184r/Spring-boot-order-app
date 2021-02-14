package pl.sda.controller;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.model.Order;
import pl.sda.service.OrderService;
import java.time.LocalDateTime;



@RequestMapping("/order")
@Controller
public class OrderController {

    private final OrderService orderService;


    public OrderController(@Qualifier("jpaOrderService") OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/create")
    public String createOrderForm(Model model) {

        model.addAttribute("emptyOrder", new Order());

        return "createOrder";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("emptyOrder") Order order) {

        System.out.println("Zapisuję order: " + order);

        order.setCreateDate(LocalDateTime.now().withNano(0));

        orderService.save(order);

//        return "welcome";//forward
        return "redirect:/order/list"; //redirect
    }

    @GetMapping("/list")
    public String orderList(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orderList";
    }

    @GetMapping("/details/{orderId}")
    public String orderDetails(@PathVariable Integer orderId, Model model) {
        model.addAttribute("order", orderService.getById(orderId));

        return "orderDetails";
    }

    @GetMapping("/edit/{orderId}")
    public String createEditOrderForm(@PathVariable Integer orderId, Model model) {
        model.addAttribute("orderToEdit", orderService.getById(orderId));

        return "editOrder";
    }

    @GetMapping("/update/{orderId}")
    public String updateOrder(@ModelAttribute("orderToEdit") Order order, Model model) {
        System.out.println("Zapisuję zaktualizowany order: " + order);
        model.addAttribute("ordrtToEdit",orderService.update(order));
        orderService.save(order);
        return "updateOrder";
    }



    @GetMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId, Model model){

        model.addAttribute("orderDelete", orderService.delete(orderId));

        return "redirect:/order/list";
    }

}
