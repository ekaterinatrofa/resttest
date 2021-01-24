package pl.edu.pjwst.jaz.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.model.SubCategoryEntity;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.repository.SubCategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;
import pl.edu.pjwst.jaz.requestBody.SubCategoryRequest;
import pl.edu.pjwst.jaz.requestBody.SubCategoryUpdateRequest;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    @PostMapping("/updateSubCategory")
    public String updateSubCategory(@RequestBody SubCategoryUpdateRequest subCategoryUpdateRequest) {
        return subCategoryEntityService.updateSubCategory(subCategoryUpdateRequest);
    }

    @Transactional
    @PutMapping("/updateSubCategory/{subCategoryId}")
    public String updateSubCategory(@PathVariable("subCategoryId") int id, @RequestBody SubCategoryUpdateRequest subCategoryUpdateRequest) {
        return subCategoryEntityService.updateSubCategoryPut(subCategoryUpdateRequest,id);
    }


    @GetMapping("/listSubCategories")
    public List<SubCategoryEntity> listSubCategories() {
        return subCategoryEntityService.listOfAllSubCategories();
    }



}
