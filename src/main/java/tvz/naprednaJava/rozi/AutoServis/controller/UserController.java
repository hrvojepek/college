package tvz.naprednaJava.rozi.AutoServis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tvz.naprednaJava.rozi.AutoServis.enums.UserStatus;
import tvz.naprednaJava.rozi.AutoServis.form.UserForm;
import tvz.naprednaJava.rozi.AutoServis.model.Role;
import tvz.naprednaJava.rozi.AutoServis.service.RoleService;
import tvz.naprednaJava.rozi.AutoServis.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

	@Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    private String createPublicUser (@ModelAttribute UserForm form, Model model, RedirectAttributes redirectAttributes) {
        if (form.getNewPassword().equals(form.getNewPasswordConfirm()) && !form.getNewPassword().equals("") && !form.getNewPasswordConfirm().equals("")) {
            form.getUser().setPassword(passwordEncoder.encode(form.getNewPassword()));
        } else {
            redirectAttributes.addFlashAttribute("errors", "Lozinke se ne podudaraju");
            return "redirect:/";
        }
        if (userService.getByUsername(form.getUser().getUsername()) != null) {
            redirectAttributes.addFlashAttribute("errors", String.format("Korisničko ime %s se već koristi.", form.getUser().getUsername()));
            return "redirect:/";
        }
        if (userService.getByEmail(form.getUser().getEmail()) != null) {
            redirectAttributes.addFlashAttribute("errors", String.format("E-mail adresa %s se već koristi.", form.getUser().getEmail()));
            return "redirect:/";
        }

        form.getUser().setUserStatus(UserStatus.ACTIVE);
        Role role = roleService.getByName("Customer");
        form.getUser().setRole(role);

        userService.create(form.getUser());

        redirectAttributes.addFlashAttribute("success", "Korisnik uspješno kreiran");
        return "redirect:/";
    }


}
