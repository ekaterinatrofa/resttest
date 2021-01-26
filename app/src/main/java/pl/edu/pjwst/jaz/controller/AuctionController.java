package pl.edu.pjwst.jaz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.model.AuctionEntity;
import pl.edu.pjwst.jaz.repository.AuctionEntityService;
import pl.edu.pjwst.jaz.repository.CategoryEntityService;
import pl.edu.pjwst.jaz.requestBody.AuctionRequest;
import pl.edu.pjwst.jaz.requestBody.AuctionUpdateRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;

import javax.transaction.Transactional;
import java.util.List;

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
        try{
            return auctionEntityService.addAuction(auctionRequest, authenticationService.getUserName());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "You are not logged in", e);
        }


    }

    @Transactional
    @PostMapping("/updateAuction")
    public AuctionEntity updateAuction(@RequestBody AuctionUpdateRequest auctionUpdateRequest) {
        return auctionEntityService.updateAuction(auctionUpdateRequest, authenticationService.getUserName());
    }


    @Transactional
    @PutMapping ("/updateAuction/{auctionId}")
    public AuctionEntity updateAuction(@PathVariable("auctionId") Long auctionId, @RequestBody AuctionUpdateRequest auctionUpdateRequest) {
        return auctionEntityService.updateAuctionPut(auctionUpdateRequest, authenticationService.getUserName(),auctionId);
    }


   @GetMapping("/listAuctions")
   public List<AuctionEntity> showAllAuctions() {
        return auctionEntityService.listOfAllAuctions();
    }

}
