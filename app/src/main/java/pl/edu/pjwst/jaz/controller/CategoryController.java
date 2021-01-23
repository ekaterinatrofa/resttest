package pl.edu.pjwst.jaz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;

import javax.transaction.Transactional;

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

//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping("/listUsers")
////    public String sayWord() {
////
////
////        return "Hello there!";
////    }
//
//
//
//    public List<UserEntity> hello() {
//        return userEntityService.print();
//    }

}
