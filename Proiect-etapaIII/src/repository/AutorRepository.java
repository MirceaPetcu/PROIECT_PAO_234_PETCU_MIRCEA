package repository;

import config.DatabaseConfiguration;
import model.Autor;

import java.sql.*;
import java.time.LocalDate;

public class AutorRepository {

    public void insertAutor(Autor autor) {
        String preparedSql = "INSERT INTO autori (nume, dataNasterii, nationalitate) VALUES (?, ?, ?)";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setString(1, autor.getNume());
            preparedStatement.setDate(2, java.sql.Date.valueOf(autor.getDataNasterii()));
            preparedStatement.setString(3, autor.getNationalitate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Autor getAutorByNume(String nume) {
        String selectSql = "SELECT * FROM autori WHERE nume=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume);
            return mapToAutor(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateAutor(String nume, LocalDate dataNasterii, String nationalitate)
    {
        String updateSQL = "UPDATE autori SET dataNasterii=?, nationalitate=? WHERE nume=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSQL);
            preparedStatement.setDate(1, java.sql.Date.valueOf(dataNasterii));
            preparedStatement.setString(2, nationalitate);
            preparedStatement.setString(3, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAutor(String nume)
    {
        String deleteSql = "DELETE FROM autori WHERE nume=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Autor mapToAutor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Autor(resultSet.getString(1), resultSet.getDate(2).toLocalDate(), resultSet.getString(3));
        }
        return null;
    }
}
