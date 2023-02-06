package com.castro.ex6.repository;

import com.castro.ex6.entity.Member;
import com.castro.ex6.entity.Movie;
import com.castro.ex6.entity.Review;
import com.castro.ex6.repositroy.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews(){
        IntStream.rangeClosed(1,200).forEach(i->{
            Long mno = (long)(Math.random()*100)+1;
            Long mid = (long)(Math.random()*100)+1;
            int grade = (int)(Math.random()*5)+1;
            Member member = Member.builder().mid(mid).build();
            Movie movie = Movie.builder().mno(mno).build();
            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade(grade)
                    .text("test review..."+i)
                    .build();
            reviewRepository.save(review);
        });
    }

    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder().mno(97L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(movieReview->{
            System.out.print(movieReview.getRno());
            System.out.print("\t"+movieReview.getGrade());
            System.out.print("\t"+movieReview.getText());
            System.out.print("\t"+movieReview.getMember().getEmail());
            System.out.println("");
        });
    }

}
