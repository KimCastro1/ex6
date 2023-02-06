package com.castro.ex6.service;

import com.castro.ex6.dto.MovieDTO;
import com.castro.ex6.dto.PageRequestDTO;
import com.castro.ex6.dto.PageResultDTO;
import com.castro.ex6.entity.Movie;
import com.castro.ex6.entity.MovieImage;
import com.castro.ex6.repositroy.MovieImageRepository;
import com.castro.ex6.repositroy.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;
    @Override
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");
        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });

        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());
        Page<Object[]> result = movieRepository.getListPage(pageable);
        Function<Object[], MovieDTO> fn = (arr->entityToDTO(
                (Movie)arr[0],
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );
        return new PageResultDTO<>(result, fn);

    }

    @Override
    public MovieDTO getMovie(Long mno) {
        List<Object[]> result = movieRepository.getMovieWithAll(mno);
        Movie movie = (Movie) result.get(0)[0];
        List<MovieImage> movieImageList = new ArrayList<>();
        result.forEach(arr->{
            MovieImage movieImage = (MovieImage) arr[1];
            movieImageList.add(movieImage);
        });
        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        return entityToDTO(movie, movieImageList, avg, reviewCnt);
    }
}
