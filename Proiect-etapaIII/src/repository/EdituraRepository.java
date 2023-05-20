package repository;

import config.DatabaseConfiguration;
import model.Editura;

import java.sql.*;

public class EdituraRepository {
    public void insertEditura(Editura editura) {
        String preparedSql = "{call insertEditura(?,?,?,?)}";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, editura.getNume());
            cstmt.setString(2, editura.getAdresa());
            cstmt.setString(3, editura.getNrTelefon());
            cstmt.registerOutParameter(4, Types.VARCHAR); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("A fost adaugata editura cu numele:" + cstmt.getString(4));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Editura getEdituraByNume(String nume) {
        String selectSql = "SELECT * FROM edituri WHERE nume=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToEditura(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateEditura(String nume,String adresa, String telefon)
    {
        String updateSql = "UPDATE edituri SET adresa=?, nr_telefon=? WHERE nume=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setString(1, adresa);
            preparedStatement.setString(2, telefon);
            preparedStatement.setString(3, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEditura(String nume)
    {
        String deleteSql = "DELETE FROM edituri WHERE nume=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Editura mapToEditura(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Editura(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }


}
