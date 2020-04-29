import com.atlassian.jira.issue.*
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.worklog.Worklog
import com.atlassian.jira.security.JiraAuthenticationContext


def issue = context.issue as Issue
def workLogManager = ComponentAccessor.getWorklogManager()
//get worklogs for issue
def logsForIssue = workLogManager.getByIssue(issue)
//draw a table via html
String fin ='<table border=\"1\"><tr><th>Author</th><th>Time Spent</th><th>Created</th></tr>'
for(Worklog worklog : logsForIssue)
{
 fin = fin +'<tr><td>'+worklog.getAuthorObject().getDisplayName() + "</td><td>" + worklog.getTimeSpent()/60 +"</td><td>"+ worklog.getCreated().toString() + "</td></tr>"
}
fin = fin +'</table>'

writer.write(fin)


////////// def authenticationContext = ComponentAccessor.getJiraAuthenticationContext();

