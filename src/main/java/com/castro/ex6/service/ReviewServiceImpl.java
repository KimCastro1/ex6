package com.castro.ex6.service;

import com.castro.ex6.dto.ReviewDTO;
import com.castro.ex6.entity.Movie;
import com.castro.ex6.entity.Review;
import com.castro.ex6.repositroy.ReviewRepository;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@NoArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(review-> entityToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);
        return review.getRno();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Optional<Review> result = reviewRepository.findById(reviewDTO.getRno());
        if(result.isPresent()){
            Review review = result.get();
            review.changeGrade(reviewDTO.getGrade());
            review.changeText(reviewDTO.getText());
            reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long rno) {
        reviewRepository.deleteById(rno);
    }
}
