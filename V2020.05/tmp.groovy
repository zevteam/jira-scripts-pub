import com.atlassian.core.util.DateUtils
import com.atlassian.jira.component.ComponentAccessor
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
import java.time.* // a
import groovy.json.JsonOutput  



@Override
public String toString() 
{
    return new ToStringBuilder(this)
      .append("id", name)
      .append("author", location)
      //.append("address", address)
      .toString(); 
}


//// additional code

JiraAuthenticationContext userMgr = ComponentAccessor.getJiraAuthenticationContext();
ApplicationUser currentUserObj = userMgr.getLoggedInUser();

IssueManager issueMgr = ComponentAccessor.getIssueManager();
CustomFieldManager fieldmgr = ComponentAccessor.getCustomFieldManager();
MutableIssue issue = issueMgr.getIssueObject("TST-10") 


/// EOAC



def str

def concStr (String str, String addtxt) {
    str += addtxt;
    return str;
}
 
def worklogManager = ComponentAccessor.getWorklogManager()
def worklogs = worklogManager.getByIssue(issue)
if (worklogs) {
    def worklog = worklogs.last()
    concStr("asd", "123");
   //"\n\n...wlID:${worklog.id}\r\n wlComment: ${worklog.comment}\t byAuthorFName: ${worklog.getAuthorFullName()}\t byUser: ${worklog.author}\t timeSpent: ${DateUtils.getDurationString(worklog.timeSpent)}".toString()
    log.error LocalDateTime.now().toLocalDate();
    log.error LocalDateTime.now().toLocalTime();
    //log.error concStr("asd", "1223").toString();
    
worklog.toString()
groovy.json.JsonOutput.prettyPrint(groovy.json.JsonOutput.toJson(worklog.id, worklog.author))


 //JsonOutput.toJson(worklog)
 //groovy.json.JsonOutput.prettyPrint(groovy.json.JsonOutput.toJson(myObject))


//println "Person Object in JSON : " + JsonOutput.toJson(worklog)
////println "JSON Pretty Print"
//println "-----------------"
// prettyPrint requires a String and NOT an Object
//println JsonOutput.prettyPrint(JsonOutput.toJson(worklog))

}
else {
 "No worklog defined"
}
