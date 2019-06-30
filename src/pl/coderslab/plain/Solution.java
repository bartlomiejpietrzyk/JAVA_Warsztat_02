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

    public Solution(int id, int userGroupId, int solutionId, int exerciseId, String solutionDescription) {
        super(id, userGroupId);
        this.userId = id;
        this.solutionId = solutionId;
        this.exerciseId = exerciseId;
        this.solutionDescription = solutionDescription;
    }

    public Solution(int id, int exerciseId, String solutionDescription) {
        super(id);
        this.userId = id;
        this.exerciseId = exerciseId;
        this.solutionDescription = solutionDescription;
    }

    public Solution(int id, int userGroupId, int exerciseId, String name, String email, String solutionDescription) {
        super(id, name, email, userGroupId);
        this.userId = id;
        this.exerciseId = exerciseId;
        this.solutionDescription = solutionDescription;
    }

    public Solution(int id, int exerciseId) {
        super(id);
        this.userId = id;
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
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
        return String.format("['Solution' 'ID': '%s'] ['Exercise' 'ID': '%s'] ['User ID': '%s'] ['Created: %s'] ['Updated: %s']\n['Solution' 'Description': %s']\n", solutionId, exerciseId, userId, created, updated, solutionDescription);
    }

}