package sqlVault;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.util.Map;
@RequestMapping
@Service
public class PasswordService {
	@RequestMapping(path = "pw", method = RequestMethod.POST)
	@ResponseBody
	public String checkPassword(@RequestBody Map<String,String> map) {
		return SqlVaultDAO.checkPW(map.get("pass"));
	}
}
