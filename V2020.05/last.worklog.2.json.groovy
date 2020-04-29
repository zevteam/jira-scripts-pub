import com.atlassian.jira.component.ComponentAccessor
// import com.atlassian.core.util.DateUtils

//import com.atlassian.jira.ComponentAccessor;
//import com.atlassian.jira.issue.Issue;
//import com.atlassian.jira.issue.CustomFieldManager;
//import com.atlassian.jira.issue.MutableIssue;
//import com.atlassian.jira.issue.IssueManager;
//import com.atlassian.jira.user.ApplicationUser;
// import com.atlassian.jira.security.JiraAuthenticationContext;
// import com.atlassian.jira.issue.fields.CustomField;
// import groovy.json.JsonOutput  

 
".sfWorkLogInfo1"

def worklogManager = ComponentAccessor.getWorklogManager()
def worklogs = worklogManager.getByIssue(issue)
if (worklogs) {
    def worklog = worklogs.last()

    def json_out = """
        {
            "issueKey": "${worklog.issue.key}"
          , "worklogId": ${worklog.id}
          , "worklogStartDate": "${worklog.startDate}"
          , "worklogComment": "${worklog.comment}"
          , "worklogTimeSpent": ${worklog.timeSpent},
          , "worklogAuthor": "${worklog.getAuthorFullName()}"
          , "worklogCreated": "${worklog.created}"
          , "worklogUpdateAuthor": "${worklog.getUpdateAuthorFullName()}"
          , "worklogUpdated": "${worklog.updated}"
        }
    """
}
else {
 "<нет записей о выполненных работах>"
}
