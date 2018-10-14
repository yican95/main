package seedu.address.logic.commands.accounts;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.session.UserSession;
import seedu.address.commons.events.ui.LoginEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accounts.Account;
import seedu.address.model.accounts.Password;

/**
 * Logs the user into an existing toLogin, and create a {@link UserSession}.
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Login to the system with an existing account. "
            + "Parameters: "
            + PREFIX_ID + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "azhikai "
            + PREFIX_PASSWORD + "1122qq";

    public static final String MESSAGE_SUCCESS = "Successfully logged in to %s!";
    public static final String MESSAGE_ACCOUNT_NOT_FOUND = "The account does not exists.";
    public static final String MESSAGE_WRONG_PASSWORD = "The credential is invalid.";
    public static final String MESSAGE_ALREADY_AUTHENTICATED = "You are already authenticated.";

    private final Account toLogin;

    public LoginCommand(Account toLogin) {
        requireNonNull(toLogin);
        this.toLogin = toLogin;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (UserSession.isAuthenticated()) {
            throw new CommandException(MESSAGE_ALREADY_AUTHENTICATED);
        }

        if (!model.hasAccount(toLogin)) {
            throw new CommandException(MESSAGE_ACCOUNT_NOT_FOUND);
        }

        Account retrievedAccount = model.getAccount(toLogin);

        boolean isVerified = Password.verifyPassword(toLogin.getPassword().toString(),
                retrievedAccount.getPassword().toString().getBytes());

        if (isVerified) {
            UserSession.login(toLogin);
            EventsCenter.getInstance().post(new LoginEvent(toLogin));
            return new CommandResult(String.format(MESSAGE_SUCCESS, toLogin));
        }

        return new CommandResult(MESSAGE_WRONG_PASSWORD);
    }
}
