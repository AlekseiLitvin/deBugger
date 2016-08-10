package litvin.constants;

public class ConstAddress {
    private static final String CONFIDENT_PAGES_PATH = "/WEB-INF/views/";

    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String REG_PAGE = "/registration.jsp";
    public static final String MAIN_PAGE = CONFIDENT_PAGES_PATH + "welcome.jsp";
    public static final String MAIN_PAGE_SERVLET = "/main";

    public static final String PROJECT_PAGE_INIT_SERVLET = "/admin/addProjectPage";
    public static final String PROJECT_PAGE = CONFIDENT_PAGES_PATH + "admin/addProject.jsp";
    public static final String ALL_PROJECTS_PAGE = CONFIDENT_PAGES_PATH + "admin/allProjectsPage.jsp";

    public static final String ISSUE_PAGE_INIT_SERVLET = "/operations/addIssuePage";
    public static final String ADD_ISSUE_PAGE = CONFIDENT_PAGES_PATH + "addIssue.jsp";
    public static final String EDIT_ISSUE_PAGE = CONFIDENT_PAGES_PATH + "editIssue.jsp";
    public static final String EDIT_ISSUE_SERVLET = "/editIssuePage/";

    public static final String ADD_ISSUE_ATTR_PAGE = CONFIDENT_PAGES_PATH + "admin/addIssueAttribute.jsp";
    public static final String ALL_ISSUE_ATTRIBUTES_PAGE = CONFIDENT_PAGES_PATH + "admin/allIssueAttributesPage.jsp";

    public static final String EDIT_MY_PROFILE_PAGE = "/editUserProfile.jsp";
    public static final String EDIT_MY_PASSWORD_PAGE = "/editMyPassword.jsp";
    public static final String EDIT_USER_PROFILE_PAGE = CONFIDENT_PAGES_PATH + "admin/editUserProfile.jsp";
    public static final String USER_SEARCH_PAGE = CONFIDENT_PAGES_PATH + "admin/userSearchPage.jsp";

    public static final String ERROR_PAGE = "templates/wrongUrlPage.jsp";
}
