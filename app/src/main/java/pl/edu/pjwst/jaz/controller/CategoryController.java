package pl.edu.pjwst.jaz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.model.UserEntity;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class CategoryController {
    private final CategoryEntityService categoryEntityService;

    public CategoryController(CategoryEntityService categoryEntityService) {
        this.categoryEntityService = categoryEntityService;
    }


    @Transactional
    @PostMapping("/create")
    public CategoryEntity create(@RequestBody CategoryRequest categoryRequest) {
        //написать

     //   return userService.register(user);
        return categoryEntityService.saveCategory(categoryRequest);
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
