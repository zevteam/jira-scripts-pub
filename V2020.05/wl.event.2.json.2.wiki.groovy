import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.IssueManager

import com.atlassian.jira.issue.worklog.Worklog
import com.atlassian.jira.issue.worklog.DefaultWorklogManager
import com.atlassian.jira.issue.worklog.WorklogImpl2

import com.onresolve.scriptrunner.runner.util.UserMessageUtil


// import com.atlassian.core.util.DateUtils

//import com.atlassian.jira.ComponentAccessor;
//import com.atlassian.jira.issue.Issue;
//import com.atlassian.jira.issue.CustomFieldManager;
//import com.atlassian.jira.issue.MutableIssue;

//import com.atlassian.jira.user.ApplicationUser;
// import com.atlassian.jira.security.JiraAuthenticationContext;
// import com.atlassian.jira.issue.fields.CustomField;
// import groovy.json.JsonOutput  

 
"wl.event.2.json.2.wiki.groovy"

/*def worklogManager = ComponentAccessor.getWorklogManager()
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
 "<нет данных о событии>"
}
*/

// EVENT Parameters
// event.id
// Issue issue
// Map params
// ApplicationUser user
// Long eventTypeId
// Comment Comment
// Worklog worklog
// org.ofbiz.core.entity.GenericValue changeGroup,

def worklogManager = ComponentAccessor.getWorklogManager()

def json_out = "{}"

def event_worklog_id = event.getWorklog().getId() 

def worklog = worklogManager.getById(event_worklog_id)
   
   //"\n\n...wlID:${worklog.id}\r\n wlComment: ${worklog.comment}\t byAuthorFName: ${worklog.getAuthorFullName()}\t byUser: ${worklog.author}\t timeSpent: ${DateUtils.getDurationString(worklog.timeSpent)}".toString()

    


json_out = """
        {
          "event": {
            "eventHashCode": ${event.hashCode()},
            "eventIssueKey": "${event.issue.key}",
            "eventTypeId": ${event.getEventTypeId()},
            "eventTime": ${event.time},
            "eventUser": ${event.user},
            "event.getWorklog": ${event.getWorklog()}
            
          }
          "worklogChangeDetails" : { 
              "worklogId" : $worklog.id
            , "worklogIssueKey": "$worklog.issue.key"
            , "worklogStartDate": "${worklog.startDate}"
            , "worklogComment": "${worklog.comment}"
            , "worklogTimeSpent": ${worklog.timeSpent},
            , "worklogAuthor": "${worklog.getAuthorFullName()}"
            , "worklogCreated": "${worklog.created}"
            , "worklogUpdateAuthor": "${worklog.getUpdateAuthorFullName()}"
            , "worklogUpdated": "${worklog.updated}"
          }
        }
    """

//log.error event.getClass().name;
//log.error event.dump()



StringBuilder output = new StringBuilder()
event.properties.each { output.append("$it.key -> $it.value") }
output
log.error json_out

//UserMessageUtil.success(json_out)
//log.error event.dump()



/*log.error "\n........................................................EVENT____ " + ent.hashCode() + " ___FIRED...................................................................."


def CF = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(".wikilLastEventDetails");   
//def str = issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(".sfWorkLogInfo1"))
MutableIssue issueToUpdate = (MutableIssue) event.issue;
issueToUpdate.setCustomFieldValue(CF, json_out);
issueManager.updateIssue(event.getUser(), issueToUpdate, EventDispatchOption.ISSUE_UPDATED, false);

log.error "\n........................................................DONE EVENT____ " + event.id() + " ___PROCESSING...................................................................."*/