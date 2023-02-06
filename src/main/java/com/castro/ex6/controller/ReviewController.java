package com.castro.ex6.controller;

import com.castro.ex6.dto.ReviewDTO;
import com.castro.ex6.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping({"/{mno}/all"})
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno){
        log.info("read mno: "+mno);
        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }
    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO){
        log.info("insert reviewDTO: "+reviewDTO);
        Long rno = reviewService.register(reviewDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }
    @PutMapping("/{mno}/{rno}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long rno, @RequestBody ReviewDTO reviewDTO){
        log.info("update reviewDTO: "+reviewDTO);
        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }
    @DeleteMapping("/{mno}/{rno}")
    public ResponseEntity<Long> removeReview(@PathVariable Long rno){
        log.info("delete rno:"+rno);
        reviewService.remove(rno);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

}
