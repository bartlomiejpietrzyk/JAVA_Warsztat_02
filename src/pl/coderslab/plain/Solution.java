package pl.coderslab.plain;

import java.sql.Date;

/*
Dodatkowe metody:
Utwórz implementację dodatkowych metod realizujących zadania:
pobranie wszystkich rozwiązań danego użytkownika (dopisz metodę do klasy
Solution),
findAllByUserId
pobranie wszystkich rozwiązań danego zadania, posortowanych od najnowszego do najstarszego
(dopisz metodę findAllByExerciseId do klasy Solution),
pobranie wszystkich członków danej grupy (dopisz metodę findAllByGroupId do klasy User )
 */
public class Solution extends User {
    private Date created;
    private Date updated;
    private int userId;
    private int solutionId;
    private int exerciseId;
    private String solutionDescription;



    public Solution() {}

    public Solution(int id, int userGroupId, int exerciseId, String solutionDescription) {
        super(id, userGroupId);
        this.exerciseId = exerciseId;
        this.solutionDescription = solutionDescription;
    }

    public Solution(int id, int userGroupId, int exerciseId, String name, String email, String solutionDescription) {
        super(id, name, email, userGroupId);
        this.exerciseId = exerciseId;
        this.solutionDescription = solutionDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "created=" + created +
                ", updated=" + updated +
                ", userId=" + userId +
                ", solutionId=" + solutionId +
                ", exerciseId=" + exerciseId +
                ", solutionDescription='" + solutionDescription + '\'' +
                '}';
    }

}


//zeby dodac solucje do usera:
//Podac ID usera, podac ID cwiczenia, podac DESCRIPTION

//    Solution(getId(), int userGroupId, int exerciseId, String description)
// public Solution[] findAllByUserId(int userId) {//copy z userDAO = coś zmoenić
//        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
//            Solution[] solutions = new Solution[0];
//            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_BY_USER_ID_QUERY);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Solution solution = new Solution();
//                solution.setId(resultSet.getInt("id"));
//                solution.setName(resultSet.getString("name"));
//                solution.setEmail(resultSet.getString("email"));
//                solution.setPassword(resultSet.getString("password"));
//                solution.setUserGroupId(resultSet.getInt("user_group_id"));
//                solutions = addToArray(solution, solutions);
//            }
//            return solutions;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
