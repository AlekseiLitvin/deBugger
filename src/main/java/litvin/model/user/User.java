package litvin.model.user;

import litvin.controllers.authorization.LoggedUsers;
import litvin.model.project.Issue;
import litvin.model.project.Project;

import javax.persistence.*;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements HttpSessionBindingListener {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private Set<Project> managedProjects = new HashSet<>();

    @OneToMany(mappedBy = "assignee", fetch = FetchType.EAGER)
    private List<Issue> assignIssues = new ArrayList<>();

    public User(String firstName, String lastName, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(Set<Project> managedProjects) {
        this.managedProjects = managedProjects;
    }

    public List<Issue> getAssignIssues() {
        return assignIssues;
    }

    public void setAssignIssues(List<Issue> assignIssues) {
        this.assignIssues = assignIssues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }


    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        LoggedUsers.usersSet.add(this);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        LoggedUsers.usersSet.remove(this);
    }
}
