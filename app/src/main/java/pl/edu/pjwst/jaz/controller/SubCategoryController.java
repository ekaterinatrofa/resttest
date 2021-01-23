package pl.edu.pjwst.jaz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.repository.SubCategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;
import pl.edu.pjwst.jaz.requestBody.SubCategoryRequest;

import javax.transaction.Transactional;

@RestController
public class SubCategoryController {
    private final SubCategoryEntityService subCategoryEntityService;
    private final AuthenticationService authenticationService;

    public SubCategoryController(SubCategoryEntityService subCategoryEntityService, AuthenticationService authenticationService) {
        this.subCategoryEntityService = subCategoryEntityService;
        this.authenticationService = authenticationService;
    }


    @Transactional
    @PostMapping("/addSubCategory")
    public String addSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {

        return subCategoryEntityService.addSubCategory(subCategoryRequest);
    }

//    @Transactional
//    @PostMapping("/updateCategory")
//    public String updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
//
//      //  return categoryEntityService.updateCategory(categoryUpdateRequest);
//    }

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
