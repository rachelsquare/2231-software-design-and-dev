import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * firstname - the first name.
     */
    private String firstname;

    /**
     * lastname - the last name.
     */
    private String lastName;

    /**
     * email - email address.
     */
    private String email;

    /**
     * to store lastNames and their N values.
     */
    // needs to be STATIC
    private static Map<String, Integer> dotNumber = new Map1L<String, Integer>();

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastName = lastName;
        String ignoreCase = lastName.toLowerCase();
        if (!dotNumber.hasKey(ignoreCase)) {
            dotNumber.add(ignoreCase, 1);
        } else {
            int n = dotNumber.value(ignoreCase);
            // ++n works not n++
            dotNumber.replaceValue(ignoreCase, ++n);
        }
        int n = dotNumber.value(ignoreCase);
        this.email = ignoreCase + "." + Integer.toString(n) + "@osu.edu";
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {
        String fullName = this.firstname + " " + this.lastName;
        return fullName;
    }

    @Override
    public String emailAddress() {
        return this.email;
    }

    @Override
    public String toString() {
        String name = "Name: " + this.firstname + " " + this.lastName;
        String email = "Email: " + this.email;
        return name + ", " + email;
    }

}
