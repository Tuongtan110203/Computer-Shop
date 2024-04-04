
import java.sql.SQLException;
import java.util.List;
import tuongnt.loginGoogle.UserGoogleDto;
import tuongnt.registration.RegistrationDAO;
import tuongnt.registration.RegistrationDTO;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
         RegistrationDAO dao = new RegistrationDAO();
            List<RegistrationDTO> list = dao.getListUser();
            for (RegistrationDTO registrationDTO : list) {
                System.out.println(registrationDTO);
        }
    }
}
