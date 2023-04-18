package id_authentication.controller;
import id_authentication.domain.Badge;
import id_authentication.dto.BadgeDTO;
import id_authentication.errorhandler.CustomErrorType;
import id_authentication.exceptions.ResourceNotFoundException;
import id_authentication.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/badges")
public class BadgeController {
    @Autowired
    private BadgeService badgeService;

    @GetMapping("/{badgeId}")
    public ResponseEntity<?> getBadge(@PathVariable String badgeId) {
        BadgeDTO badge = badgeService.getBadge(Long.parseLong(badgeId));
        if (badge == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Badge Not Found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BadgeDTO>(badge, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllBadges(){
        return new ResponseEntity<>(badgeService.getAllBadges(), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<?> createBadges(@RequestBody BadgeDTO badgeDTO){
        return new ResponseEntity<>(badgeService.createBadge(badgeDTO), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBadge(@PathVariable Long id, @RequestBody BadgeDTO badgeDTO){
        try {
            return new ResponseEntity<>(badgeService.updateBadge(badgeDTO, id), HttpStatus.OK);
        }catch(ResourceNotFoundException e){
        return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{badgeId}")
    public ResponseEntity<?> deleteBadge(@PathVariable Long  badgeId) {
              var badege1 = badgeService.deleteBadge(badgeId);
        return badege1!=null? new ResponseEntity<>(badege1.getStatus().toString(),HttpStatus.OK):
                new ResponseEntity<CustomErrorType>(new CustomErrorType("Badge Not Found"), HttpStatus.NOT_FOUND);
        }
    }

