package com.castro.ex6.service;

import com.castro.ex6.dto.ReviewDTO;
import com.castro.ex6.entity.Member;
import com.castro.ex6.entity.Movie;
import com.castro.ex6.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfMovie(Long mno);
    Long register(ReviewDTO reviewDTO);
    void modify(ReviewDTO reviewDTO);
    void remove(Long rno);
    default Review dtoToEntity(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .rno(reviewDTO.getRno())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .member(Member.builder().mid(reviewDTO.getMid()).build())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .build();
        return review;
    }
    default ReviewDTO entityToDTO(Review review){
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .rno(review.getRno())
                .mno(review.getMovie().getMno())
                .mid(review.getMember().getMid())
                .nickname(review.getMember().getNickname())
                .email(review.getMember().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();
        return reviewDTO;
    }

}
