package ma.ac.ensias.model.dao;

import ma.ac.ensias.exception.DaoException;
import ma.ac.ensias.model.entity.Movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieDao {

    List<Movie> findAll() throws DaoException;

    List<Movie> findAllByCriteria(Map<String, String> criteria) throws DaoException;

    Optional<Movie> findById(int movieId) throws DaoException;

    boolean update(Movie movie) throws  DaoException;

    boolean save(Movie movie) throws  DaoException;

    boolean delete(int movieId) throws DaoException;

}
