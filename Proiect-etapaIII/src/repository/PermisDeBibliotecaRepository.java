package repository;

import config.DatabaseConfiguration;
import model.PermisDeBiblioteca;

import java.sql.*;

public class PermisDeBibliotecaRepository {
    public void insertPermis(PermisDeBiblioteca permisDeBiblioteca) {
        String insertSql = "INSERT INTO permise(telefon,dreptImprumut,dreptRezervare,dataInceput,dataExpirare,valid,eStudent) VALUES(?,?,?,?,?,?,?)";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(insertSql);
            preparedStatement.setString(1, permisDeBiblioteca.getTelefon());
            preparedStatement.setBoolean(2, permisDeBiblioteca.isDreptImprumut());
            preparedStatement.setBoolean(3, permisDeBiblioteca.isDreptRezervare());
            preparedStatement.setDate(4,new java.sql.Date(permisDeBiblioteca.getDataInceput().getTime()));
            preparedStatement.setDate(5,new java.sql.Date(permisDeBiblioteca.getDataExpirare().getTime()));
            preparedStatement.setBoolean(6, permisDeBiblioteca.isValid());
            preparedStatement.setBoolean(7, permisDeBiblioteca.iseStudent());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PermisDeBiblioteca getPermisByTelefon(String telefon) {
        String selectSql = "SELECT * FROM permise WHERE telefon=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, telefon);
//            preparedStatement.executeUpdate();
            return mapToPermis(preparedStatement.executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void anuleaza_activeaza_permis(String telefon,boolean valid)
    {
        String updateSql = "UPDATE permise SET valid=? WHERE telefon=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setBoolean(1, valid);
            preparedStatement.setString(2, telefon);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePermis(String telefon)
    {
        String deleteSql = "DELETE FROM permise WHERE telefon=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, telefon);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PermisDeBiblioteca mapToPermis(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new PermisDeBiblioteca(resultSet.getString(1), resultSet.getBoolean(2), resultSet.getBoolean(3), resultSet.getDate(4), resultSet.getDate(5), resultSet.getBoolean(6), resultSet.getBoolean(7));
        }
        return null;
    }
}
