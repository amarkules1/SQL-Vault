package main.java.sqlVault;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public class PasswordService {
	@RequestMapping(path = "pw", method = RequestMethod.GET)
	@ResponseBody
	public String checkPassword(@RequestParam("pass") String pw) {
		return SqlVaultDAO.checkPW(pw);
	}
}
