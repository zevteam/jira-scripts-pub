import com.atlassian.jira.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.issue.fields.CustomField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



// Get some additional info
JiraAuthenticationContext userMgr = ComponentAccessor.getJiraAuthenticationContext();
ApplicationUser currentUserObj = userMgr.getLoggedInUser();

IssueManager issueMgr = ComponentAccessor.getIssueManager();
CustomFieldManager fieldmgr = ComponentAccessor.getCustomFieldManager();
MutableIssue issue = issueMgr.getIssueObject("TST-10") 


/*
//Create the regex matcher(s) you need
def matcher = issue.description =~ /INC\d\d\d\d\d/
try {
    String res = matcher[0]
    log.error "!@!Component matcher found " + res;
} catch ( IndexOutOfBoundsException e ) {
    log.error e;
}
*/


def lggr(String msg) {
    log.error " -::- " + msg;
}


//Print stuff. Just assuming you are playing around/learning in the Scriptrunner console.
//log.error ""; 
//lggr("asd");
//log.error "The current issue special field value is " + issue.getCustomFieldValue(fieldmgr.getCustomFieldObject(10603L));
log.error "............................................................................................................................"
log.error "!@!The current user is " + currentUserObj.name;
log.error "The current issue ID is " + issue.id;
log.error "The current issue key is " + issue.key;
log.error "The current issue description is " + issue.description;

log.error "some info"