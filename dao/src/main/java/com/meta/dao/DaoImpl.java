package com.meta.dao;

import com.meta.exception.DataInvalidException;
import com.meta.model.LinkedinProfile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * Gets the implementation of the linkedin profile.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class DaoImpl extends AbstractLinkedinDao<LinkedinProfile> {

    private final static DatabaseConnection DATABASE_CONNECTION = DatabaseConnection.getDatabaseConnectionInstance();
    private static DaoImpl daoImpl;

    private DaoImpl() {
    }

    /**
     * <p>
     * Gets the {@link DaoImpl} instance.
     * </p>
     *
     * @return dao impl instance.
     */
    public static DaoImpl getDaoImplInstance() {
        if (daoImpl == null) {
            daoImpl = new DaoImpl();
        }

        return daoImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param userProfile The {@link LinkedinProfile} to sign in.
     * @return True if the sign in was successful, false otherwise.
     */
    @Override
    public boolean signIn(final LinkedinProfile userProfile) {
        DATABASE_CONNECTION.driverConnection();

        try (Connection connection = DATABASE_CONNECTION.getConnection()) {

            if (selectEmail(connection, userProfile) && selectPassword(connection, userProfile)) {
                return true;
            } else return selectMobileNumber(connection, userProfile) && selectPassword(connection, userProfile);
        } catch (SQLException | IOException exception) {
            throw new DataInvalidException("Please Enter valid details");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param userProfile The {@link LinkedinProfile} to update.
     * @return True if the product was updated successfully, false otherwise.
     */
    @Override
    public boolean update(final LinkedinProfile userProfile) {
        try (Connection connection = DATABASE_CONNECTION.getConnection()) {
            updateProfile(connection, userProfile);
        } catch (SQLException | IOException e) {
            throw new DataInvalidException("Please Enter valid details");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the profile to get.
     * @return The {@link LinkedinProfile} with the specified id, or null if no such profile exists.
     */
    @Override
    public LinkedinProfile get(final Long id) {
        DATABASE_CONNECTION.driverConnection();

        try (Connection connection = DATABASE_CONNECTION.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT P.ID, P.SKILLS, P.EXPERIENCE, P.EDUCATION, P.NAME, P.PASSWORD, M.MOBILENUMBER, E.EMAILADDRESS FROM PROFILE P LEFT JOIN MOBILE M ON P.ID = M.PROFILE_ID LEFT JOIN EMAIL E ON P.ID = E.PROFILE_ID WHERE P.ID = ?");

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final LinkedinProfile linkedinProfile = new LinkedinProfile();

                linkedinProfile.setId(resultSet.getLong("ID"));
                linkedinProfile.setEducation(resultSet.getString("EDUCATION"));
                linkedinProfile.setSkill(resultSet.getString("SKILLS"));
                linkedinProfile.setExperience(resultSet.getString("EXPERIENCE"));
                linkedinProfile.setName(resultSet.getString("NAME"));
                linkedinProfile.setEmailAddress(resultSet.getString("EMAILADDRESS"));
                linkedinProfile.setMobileNumber(resultSet.getString("MOBILENUMBER"));
                linkedinProfile.setPassword(resultSet.getString("PASSWORD"));

                return linkedinProfile;
            }
        } catch (IOException | SQLException exception) {
            throw new DataInvalidException("Please Enter valid details");
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the profile to delete.
     * @return True if the profile was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        try (Connection connection = DATABASE_CONNECTION.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PROFILE WHERE ID = ? RETURNING ID");

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException | IOException e) {
            throw new DataInvalidException("Please Enter valid details");
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param userProfile The {@link LinkedinProfile} to create.
     * @return The id of the profile.
     */
    @Override
    public Long create(final LinkedinProfile userProfile) {
        DATABASE_CONNECTION.driverConnection();

        try (Connection connection = DATABASE_CONNECTION.getConnection()) {
            return insertProfile(connection, userProfile);
        } catch (SQLException | IOException e) {
            throw new DataInvalidException("Please Enter valid details");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Map} of all products, keyed by their id.
     */
    @Override
    public Collection<LinkedinProfile> getAll(Long profileId) {
        return null;
    }

    /**
     * <p>
     * Inserts the email into the table.
     * </p>
     *
     * @param connection   To connect the database object.
     * @param emailAddress The email address inserted into the table.
     * @param profileId    The profile id inserted into the table.
     * @throws SQLException
     */
    private void insertEmailAddress(final Connection connection, final String emailAddress, final Long profileId) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO EMAIL (EMAILADDRESS, PROFILE_ID) VALUES (?,?)");

        preparedStatement.setString(1, emailAddress);
        preparedStatement.setLong(2, profileId);
        preparedStatement.executeUpdate();
    }

    /**
     * <p>
     * Inserts the mobile number into the table.
     * </p>
     *
     * @param connection   To connect the database object.
     * @param mobileNumber The mobile number inserted into the table.
     * @param profileId    The profile id inserted into the table.
     * @throws SQLException
     */
    private void insertMobileNumber(final Connection connection, final String mobileNumber, final Long profileId) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MOBILE (MOBILENUMBER,PROFILE_ID) VALUES (?,?)");

        preparedStatement.setString(1, mobileNumber);
        preparedStatement.setLong(2, profileId);
        preparedStatement.executeUpdate();
    }

    /**
     * <p>
     * Inserts the user profile into the table.
     * </p>
     *
     * @param connection  To connect the database object.
     * @param userProfile The user profile inserted into the table.
     * @return The id of the profile.
     * @throws SQLException
     */
    private Long insertProfile(final Connection connection, final LinkedinProfile userProfile) throws SQLException {
        if ((!selectEmail(connection, userProfile) && Objects.nonNull(userProfile.getEmailAddress())) || (!selectMobileNumber(connection, userProfile) && Objects.nonNull(userProfile.getMobileNumber()))) {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PROFILE (PASSWORD, SKILLS, EXPERIENCE, EDUCATION,NAME) VALUES (?, ?, ?, ?, ?) RETURNING ID");

            preparedStatement.setString(1, userProfile.getPassword());
            preparedStatement.setString(2, userProfile.getSkill());
            preparedStatement.setString(3, userProfile.getExperience());
            preparedStatement.setString(4, userProfile.getEducation());
            preparedStatement.setString(5, userProfile.getName());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final long id = resultSet.getLong("id");

                if (Objects.isNull(userProfile.getEmailAddress())) {
                    insertMobileNumber(connection, userProfile.getMobileNumber(), id);
                } else {
                    insertEmailAddress(connection, userProfile.getEmailAddress(), id);
                }

                return id;
            }
        }

        return null;
    }

    /**
     * <p>
     * Updates the user profile.
     * </p>
     *
     * @param connection  To connect the database object.
     * @param userProfile The user profile updated into the table.
     * @throws SQLException
     */
    private void updateProfile(final Connection connection, final LinkedinProfile userProfile) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PROFILE SET PASSWORD = ?, SKILLS = ?, EXPERIENCE = ?, EDUCATION = ?, NAME = ? WHERE ID = ? RETURNING ID");

        preparedStatement.setString(1, userProfile.getPassword());
        preparedStatement.setString(2, userProfile.getSkill());
        preparedStatement.setString(3, userProfile.getExperience());
        preparedStatement.setString(4, userProfile.getEducation());
        preparedStatement.setString(5, userProfile.getName());
        preparedStatement.setLong(6, userProfile.getId());
        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            final Long id = resultSet.getLong("ID");

            if (!selectEmail(connection, userProfile) && Objects.nonNull(userProfile.getEmailAddress())) {
                insertEmailAddress(connection, userProfile.getEmailAddress(), id);
            } else if (!selectMobileNumber(connection, userProfile) && Objects.nonNull(userProfile.getMobileNumber())) {
                insertMobileNumber(connection, userProfile.getMobileNumber(), id);
            }
        }
    }

    /**
     * <p>
     * Selects the email address from email table.
     * </p>
     *
     * @param connection  To connect the database object.
     * @param userProfile select the user email from the table.
     * @return True, if the email equals to the user email otherwise false.
     * @throws SQLException
     */
    private boolean selectEmail(final Connection connection, final LinkedinProfile userProfile) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT EMAILADDRESS FROM EMAIL");
        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            if (resultSet.getString("EMAILADDRESS").equals(userProfile.getEmailAddress())) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>
     * Selects the mobile number from the mobile table.
     * </p>
     *
     * @param connection  To connect the database object.
     * @param userProfile select the user mobile number from the table.
     * @return True, if the mobile number equals to the user mobile number otherwise false.
     * @throws SQLException
     */
    private boolean selectMobileNumber(final Connection connection, final LinkedinProfile userProfile) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT MOBILENUMBER FROM MOBILE");
        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            if (resultSet.getString("MOBILENUMBER").equals(userProfile.getMobileNumber())) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>
     * Selects the password from the table.
     * </p>
     *
     * @param connection  To connect the database object.
     * @param userProfile select the user password from the table.
     * @return True, if the password equals to the user password otherwise false.
     * @throws SQLException
     */
    private boolean selectPassword(final Connection connection, final LinkedinProfile userProfile) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM PROFILE");
        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            final String password = resultSet.getString("PASSWORD");

            if (password.equals(userProfile.getPassword())) {
                return true;
            }
        }

        return false;
    }
}




