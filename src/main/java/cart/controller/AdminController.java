package cart.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import cart.service.JwpCartService;

@Controller
public class AdminController {

    private final JwpCartService jwpCartService;

    public AdminController(JwpCartService jwpCartService) {
        this.jwpCartService = jwpCartService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<ProductResponse> all = jwpCartService.findAll();
        model.addAttribute("products", all);
        return "admin";
    }

    @PostMapping("/admin/products")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        jwpCartService.add(productRequest);
        return ResponseEntity.created(URI.create("/admin/products")).build();
    }

    @PutMapping("/admin/products/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long id,
        @RequestBody @Valid ProductRequest productRequest) {
        jwpCartService.updateById(productRequest, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/products/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        jwpCartService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
