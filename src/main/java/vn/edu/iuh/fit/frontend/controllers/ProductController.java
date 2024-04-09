package vn.edu.iuh.fit.frontend.controllers;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.dto.ProductShowing;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.IProductPriceRepository;
import vn.edu.iuh.fit.backend.services.IProductService;
import vn.edu.iuh.fit.backend.services.impl.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/products")
@Controller
@AllArgsConstructor
public class ProductController {
    private IProductService productService;
    private IProductPriceRepository productPriceRepository;

    @GetMapping("")
    public ModelAndView getProductPaging(ModelAndView modelAndView, @RequestParam("pageNo") Optional<Integer> pageNo,
                                         @RequestParam("sizeNo") Optional<Integer> sizeNo
    , @ModelAttribute("msgAddToCart") Object msgAddToCartObj){
        String msgAddToCart =  msgAddToCartObj.toString();
        if (msgAddToCart != null && !msgAddToCart.contains("java")) {
            modelAndView.addObject("msgAddToCart", msgAddToCart);
        } else {
            modelAndView.addObject("msgAddToCart", "");
        }
        int pageNoFinal = pageNo.orElse(1);
        int sizeNoFinal = sizeNo.orElse(5);

        Page<Product> productPage = productService.findAll(pageNoFinal - 1, sizeNoFinal, "name", "desc");
        List<ProductShowing> products = new ArrayList<>();

        for (Product product : productPage.getContent()) {
            Optional<ProductPrice> newestPriceOpt = productPriceRepository.getNewestProductPriceByProductId(product.getProductId());
            ProductPrice newestPrice = newestPriceOpt.orElse(null);
            if (newestPrice != null) {
                products.add(new ProductShowing(product, newestPrice));
            }
        }
        modelAndView.addObject("productsShowing", products);
        modelAndView.addObject("pageNoSelected", pageNoFinal);
        int totalsPage = productPage.getTotalPages();
        if (totalsPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalsPage).boxed().collect(Collectors.toList());
            modelAndView.addObject("productPageNumbers", pageNumbers);

        }
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
}
