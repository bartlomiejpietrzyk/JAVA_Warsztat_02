package pl.coderslab.plain;

public class UserGroup {
    private int id;
    private String name;

    public UserGroup() {

    }

    public UserGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserGroup(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("['UserGroup' 'ID': '%s' ['UserGroupName: %s']\n", id, name);

       
    }
}
