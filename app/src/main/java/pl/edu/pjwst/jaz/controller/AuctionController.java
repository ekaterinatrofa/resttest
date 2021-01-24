package pl.edu.pjwst.jaz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.model.AuctionEntity;
import pl.edu.pjwst.jaz.repository.AuctionEntityService;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.AuctionRequest;
import pl.edu.pjwst.jaz.requestBody.AuctionUpdateRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;

import javax.transaction.Transactional;

@RestController
public class AuctionController {
    private final AuctionEntityService auctionEntityService;
    private final AuthenticationService authenticationService;



    public AuctionController(AuctionEntityService auctionEntityService, AuthenticationService authenticationService) {
        this.auctionEntityService = auctionEntityService;
        this.authenticationService = authenticationService;
    }


    @Transactional
    @PostMapping("/addAuction")
    public AuctionEntity addAuction(@RequestBody AuctionRequest auctionRequest) {

        return auctionEntityService.addAuction(auctionRequest, authenticationService.getUserName());
    }

    @Transactional
    @PostMapping("/updateAuction")
    public AuctionEntity updateAuction(@RequestBody AuctionUpdateRequest auctionUpdateRequest) {
        return auctionEntityService.updateAuction(auctionUpdateRequest, authenticationService.getUserName());
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
