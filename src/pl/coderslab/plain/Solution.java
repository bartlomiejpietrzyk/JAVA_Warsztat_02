package pl.coderslab.plain;

import java.sql.Date;

public class Solution extends User {
    private Date created;
    private Date updated;
    private int userId;
    private int solutionId;
    private int exerciseId;
    private String solutionDescription;


    public Solution() {
    }

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
        return String.format("['Solution' 'ID': '%s' ['Created: %s'] ['Updated: %s']\n['User' ['ID': '%s'] ['Solution' 'ID': '%s'] ['Exercise' 'ID': '%s']\n" +
                "['Solution' 'Description': %s] ", solutionId, created, updated, userId, solutionId, exerciseId, solutionDescription);
    }

}