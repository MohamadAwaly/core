package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create( Tournoi tournoi ) {

        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO tournoi (NOM,CODE) VALUES (?,?)" );

            preparedStatement.setString( 1, tournoi.getNom() );
            preparedStatement.setString( 2, tournoi.getCode() );

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if ( rs.next() ) {
                tournoi.setId( rs.getLong( 1 ) );
            }

            System.out.println( "Tournoi créé" );

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

    public void update( Tournoi tournoi ) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE tournoi SET NOM=?,CODE=? WHERE ID=?" );

            preparedStatement.setString( 1, tournoi.getNom() );
            preparedStatement.setString( 2, tournoi.getCode() );

            preparedStatement.setLong( 3, tournoi.getId() );

            preparedStatement.executeUpdate();

            System.out.println( "Tournoi modifié" );
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

    public void delete( Long id ) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement( "DELETE FROM tournoi WHERE ID=?" );

            preparedStatement.setLong( 1, id );

            preparedStatement.executeUpdate();

            System.out.println( "Tournoi supprimé" );
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

    public Tournoi getById( Long id ) {
        Connection conn = null;
        Tournoi tournoi = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT NOM, CODE FROM tournoi WHERE ID=?" );

            preparedStatement.setLong( 1, id );

            ResultSet rs = preparedStatement.executeQuery();

            if ( rs.next() ) {
                tournoi = new Tournoi();
                tournoi.setId( id );
                tournoi.setNom( rs.getString( "NOM" ) );
                tournoi.setCode( rs.getString( "CODE" ) );
            }

            System.out.println( "Tournoi lu" );
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
        return tournoi;
    }

    public List<Tournoi> list() {
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement( "SELECT * FROM tournoi" );

            ResultSet rs = preparedStatement.executeQuery();

            while ( rs.next() ) {
                Tournoi tournoi = new Tournoi();
                tournoi.setNom( rs.getString( "NOM" ) );
                tournoi.setCode( rs.getString( "CODE" ) );
                tournois.add( tournoi );
            }

            System.out.println( "Tournoi lus" );
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
        return tournois;
    }
}
