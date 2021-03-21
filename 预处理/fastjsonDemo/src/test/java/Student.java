import java.util.List;

/**
 * @author XUAN
 * @date 2021/3/2 - 12:08
 * @references
 * @purpose
 * @errors
 */
public class Student {
    int id;
    String name;
    List<String> courses;

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

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
