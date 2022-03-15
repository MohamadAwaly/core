package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {

    public void create( Match match ) {

        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?,?,?)" );

            preparedStatement.setLong( 1, match.getEpreuve().getId() );
            preparedStatement.setLong( 2, match.getVainqueur().getId() );
            preparedStatement.setLong( 3, match.getFinaliste().getId() );

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next()){
                match.setId(rs.getLong(1));
            }

            System.out.println("Match créer");

        } catch ( SQLException e ) {
            e.printStackTrace();
            try {
                if ( conn != null )
                    conn.rollback();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if ( conn != null ) {
                    conn.close();
                }
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

    }


}
