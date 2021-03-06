package ma.ac.ensias.model.service.impl;

import ma.ac.ensias.exception.DaoException;
import ma.ac.ensias.exception.ServiceException;
import ma.ac.ensias.model.dao.MovieDao;
import ma.ac.ensias.model.dao.factory.DaoFactory;
import ma.ac.ensias.model.entity.Movie;
import ma.ac.ensias.model.entity.enums.Genre;
import ma.ac.ensias.model.entity.enums.MovieType;
import ma.ac.ensias.model.entity.enums.Status;
import ma.ac.ensias.model.service.MovieService;
import ma.ac.ensias.util.SearchCriteria;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao = DaoFactory.getInstance().getMovieDao();

    @Override
    public List<Movie> findALlMovies(int page, int count) throws ServiceException {
        List<Movie> movies;
        try {
            movies = movieDao.findAll();
            movies = movies.stream()
                    .filter(movie -> movie.getStatus() == Status.ACTIVE)
                    .skip((page - 1) * count)
                    .limit(count)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return movies;
    }
    @Override
    public List<Movie> findALlMoviesForAdmin() throws ServiceException {
        List<Movie> movies;
        try {
            movies = movieDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return movies;
    }

    @Override
    public List<Movie> searchMovies(Map<SearchCriteria, String> searchMap, int page, int count) throws ServiceException {
        List<Movie> movies;
        try {
             movies = movieDao.findAll();
             Stream<Movie> movieStream = movies.stream()
                    .filter(movie -> movie.getStatus() == Status.ACTIVE);
             for(Map.Entry<SearchCriteria, String> entry : searchMap.entrySet()){
                 SearchCriteria key = entry.getKey();
                 String value = entry.getValue();
                 switch (key){
                     case SEARCH -> movieStream = movieStream.filter(movie ->movie.getTitle().contains(value));
                     case GENRE -> movieStream = movieStream.filter(movie -> movie.getGenre() == Genre.valueOf(value));
                     case MOVIE_TYPE -> movieStream = movieStream.filter(movie -> movie.getMovieType() == MovieType.valueOf(value));
                     case AGE_CATEGORY -> movieStream = movieStream.filter(movie -> movie.getAgeCategory() >= Integer.parseInt(value));
                 }
             }
             movies = movieStream
                     .skip((page - 1) * count)
                     .limit(count)
                     .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return movies;
    }

    @Override
    public Optional<Movie> findMovie(int movieId) throws ServiceException {
        Optional<Movie> optionalMovie;
        try {
            optionalMovie = movieDao.findById(movieId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalMovie;
    }

    @Override
    public boolean updateMovie(Movie movie) throws ServiceException {
        boolean result;
        try{
            result = movieDao.update(movie);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean addMovie(Movie movie) throws ServiceException {
        boolean result;
        try{
            result = movieDao.save(movie);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deleteMovie(int movieId) throws ServiceException {
        boolean result;
        try{
            result = movieDao.delete(movieId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }
}