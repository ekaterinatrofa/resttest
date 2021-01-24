package pl.edu.pjwst.jaz.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.model.AuctionEntity;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class CategoryController {
    private final CategoryEntityService categoryEntityService;
    private final AuthenticationService authenticationService;



    public CategoryController(CategoryEntityService categoryEntityService, AuthenticationService authenticationService) {
        this.categoryEntityService = categoryEntityService;
        this.authenticationService = authenticationService;
    }


    @Transactional
    @PostMapping("/addCategory")
    public String addCategory(@RequestBody CategoryRequest categoryRequest) {

        return categoryEntityService.addCategory(categoryRequest, authenticationService.getUserName());
    }

    @Transactional
    @PostMapping("/updateCategory")
    public String updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {

        return categoryEntityService.updateCategory(categoryUpdateRequest);
    }

    @Transactional
    @PutMapping("/updateCategory/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") int id, @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        return categoryEntityService.updateCategoryPut(categoryUpdateRequest,id);
    }


    @GetMapping("/listCategories")
    public List<CategoryEntity> listCategories() {
        return categoryEntityService.listOfAllCategories();
    }
}
