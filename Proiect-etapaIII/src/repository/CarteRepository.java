package repository;

import config.DatabaseConfiguration;
import model.Carte;
import utile.GenCarte;
import utile.TipCarte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarteRepository {

    AutorRepository autorRepository = new AutorRepository();
    EdituraRepository edituraRepository = new EdituraRepository();

    public void insertCarte(Carte carte)
    {
        String preparedSql = "INSERT INTO carti (titlu, numeAutor, numeEditura, genCarte,anPublicatie,tipCarte,rezervata,telefonCititor,imprumutata) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setString(1, carte.getTitlu());
            preparedStatement.setString(2, carte.getAutor().getNume());
            preparedStatement.setString(3, carte.getEditura().getNume());
            preparedStatement.setString(4, carte.getGen().name());
            preparedStatement.setInt(5, carte.getAnPublicatie());
            preparedStatement.setString(6, carte.getTipCarte().name());
            preparedStatement.setBoolean(7, carte.isRezervata());
            preparedStatement.setString(8, carte.getTelefon());
            preparedStatement.setBoolean(9, carte.isImprumutata());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Carte getCarteByTitlu(String titlu)
    {
        String selectSql = "SELECT * FROM carti WHERE titlu=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, titlu);
            return mapToCarte(preparedStatement.executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCarte(String titlu,GenCarte genCarte,int anPublicatie,boolean rezervata,String telefon,boolean imprumutata)
    {
        String updateSql = "UPDATE carti SET genCarte=?, anPublicatie=?, rezervata=?, telefonCititor=?, imprumutata=? WHERE titlu=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setString(1, genCarte.name());
            preparedStatement.setInt(2, anPublicatie);
            preparedStatement.setBoolean(3, rezervata);
            preparedStatement.setString(4, telefon);
            preparedStatement.setBoolean(5, imprumutata);
            preparedStatement.setString(6, titlu);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCarte(String titlu)
    {
        String deleteSql = "DELETE FROM carti WHERE titlu=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, titlu);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Carte mapToCarte(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            return new Carte(resultSet.getString(1), autorRepository.getAutorByNume(resultSet.getString(2)), edituraRepository.getEdituraByNume(resultSet.getString(3)), GenCarte.valueOf(resultSet.getString(4)), resultSet.getInt(5), TipCarte.valueOf(resultSet.getString(6)), resultSet.getBoolean(7), resultSet.getString(8), resultSet.getBoolean(9));
        }
        return null;
    }

}
