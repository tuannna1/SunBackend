package me.sunrise.api;



import me.sunrise.entity.ProductCategory;
import me.sunrise.exception.ResourceNotFoundException;
import me.sunrise.repository.ProductCategoryRepository;
import me.sunrise.vo.response.CategoryPage;
import me.sunrise.entity.ProductInfo;
import me.sunrise.service.CategoryService;
import me.sunrise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductCategoryRepository categoryRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/categoryList")
    public Page<ProductCategory> orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "3") Integer size,
                                           Authentication authentication) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductCategory> categoryPage;

            categoryPage = categoryService.findAll(request);

        return categoryPage;
    }

    /**
     * Show products in category
     *
     * @param categoryType
     * @param page
     * @param size
     * @return
     */

    @GetMapping("/category/{type}")
    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size) {

        ProductCategory cat = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInCategory = productService.findAllInCategory(categoryType, request);
        CategoryPage tmp = new CategoryPage("", productInCategory);
        tmp.setCategory(cat.getCategoryName());
        return tmp;
    }

    @PostMapping("/seller/category/new")
    public ResponseEntity<?> add(@RequestBody ProductCategory category) {
        try {
            ProductCategory returnedCategory = categoryService.save(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


@PutMapping("/seller/category/{id}/edit")
public ResponseEntity<ProductCategory> updateEmployee(@PathVariable(value = "id") Integer categoryId,
                                                      @Valid @RequestBody ProductCategory employeeDetails) throws ResourceNotFoundException {
    ProductCategory employee = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + categoryId));

    employee.setCategoryName(employeeDetails.getCategoryName());
    employee.setCategoryType(employeeDetails.getCategoryType());
    final ProductCategory updatedEmployee = categoryRepository.save(employee);
    return ResponseEntity.ok(updatedEmployee);
}

    @GetMapping("/categoryy/{categoryId}")
    public ProductCategory showOne(@PathVariable("categoryId") Integer categoryId) {

        ProductCategory category = categoryService.findOne(categoryId);

//        // Product is not available
//        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
//            productInfo = null;
//        }

        return category;
    }

    @DeleteMapping("/delete/category/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }

}
