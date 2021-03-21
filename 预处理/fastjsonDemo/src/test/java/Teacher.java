/**
 * @author XUAN
 * @date 2021/3/16 - 13:03
 * @references
 * @purpose
 * @errors
 */
public class Teacher {
    String jobNumber;
    String name;
    Teacher (String jobNumber,String name){
        this.jobNumber = jobNumber;
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
