package tvz.naprednaJava.rozi.AutoServis.form;


import tvz.naprednaJava.rozi.AutoServis.enums.FormMode;
import tvz.naprednaJava.rozi.AutoServis.model.User;

/**
 * The Class UserForm.
 */
public class UserForm {

	/** The user. */
	private User user;

	/** The form mode. */
	private FormMode formMode;

	/** The new password. */
	private String newPassword;

	/** The new password confirm. */
	private String newPasswordConfirm;

	/**
	 * Instantiates a new user form.
	 */
	public UserForm() {
		super();
	}

	/**
	 * Instantiates a new user form.
	 *
	 * @param mode the mode
	 */
	public UserForm(FormMode mode) {
		super();
		this.setFormMode(mode);
	}

	/**
	 * Instantiates a new user form.
	 *
	 * @param mode the mode
	 * @param user the user
	 */
	public UserForm(FormMode mode, User user) {
		super();
		this.setFormMode(mode);
		this.setUser(user);
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the form mode.
	 *
	 * @return the form mode
	 */
	public FormMode getFormMode() {
		return formMode;
	}

	/**
	 * Sets the form mode.
	 *
	 * @param formMode the new form mode
	 */
	public void setFormMode(FormMode formMode) {
		this.formMode = formMode;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword the new new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets the new password confirm.
	 *
	 * @return the new password confirm
	 */
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	/**
	 * Sets the new password confirm.
	 *
	 * @param newPasswordConfirm the new new password confirm
	 */
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
}
