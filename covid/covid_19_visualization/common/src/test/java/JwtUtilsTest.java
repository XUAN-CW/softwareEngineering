import edu.guet.JwtUtils;

/**
 * @author XUAN
 * @date 2021/4/22 - 11:24
 * @references
 * @purpose
 * @errors
 */
public class JwtUtilsTest {
    public static void main(String[] args) {
        String jwtToken = JwtUtils.getJwtToken("12","444");
        System.out.println(jwtToken);
        System.out.println(JwtUtils.checkToken(jwtToken));
    }
}
