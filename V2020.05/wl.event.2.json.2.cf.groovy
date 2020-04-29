import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.IssueManager

import com.atlassian.jira.issue.worklog.Worklog
import com.atlassian.jira.issue.worklog.DefaultWorklogManager
import com.atlassian.jira.issue.worklog.WorklogImpl2

import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.ModifiedValue
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder

import com.onresolve.scriptrunner.runner.util.UserMessageUtil
import groovy.json.*
import java.sql.Timestamp


 
"wl.event.2.json.2.cf.groovy"


def issue = event.issue
def customFieldManager = ComponentAccessor.customFieldManager
def worklogManager = ComponentAccessor.getWorklogManager()

def customFieldName = ".jsonLastWLChangeInfo"
def jsonStr = "{}"
def jsonOut = "{}"

def event_worklog = event.getWorklog();         log.error event_worklog.toString()
def event_worklog_id = event_worklog.getId();   log.error event_worklog_id.toString()

def worklog = worklogManager.getById(event_worklog_id)
def customField = customFieldManager.getCustomFieldObjects(issue).findByName(customFieldName)



def worklogDetails  = [:]
def eventDetails = [:]

eventDetails.Nuid = event.time.format('mmss')
eventDetails.HRTime1 = event.time.format( 'yyyy.MM.dd :: HH.mm.ss', TimeZone.getTimeZone("Europe/Moscow") )
eventDetails.HRTime2 = new Timestamp(event.time.getTime())
eventDetails.EpochTime = event.time.getTime()
eventDetails.HashCode = event.hashCode()
eventDetails.IssueKey = event.issue.key
eventDetails.TypeId = event.getEventTypeId()
eventDetails.User = event.user
//eventDetails.WorklogObj = event_worklog

worklogDetails.Id = worklog.getId()
worklogDetails.IssueKey = worklog.issue.key
worklogDetails.StartDate = worklog.startDate
worklogDetails.Comment = worklog.comment
worklogDetails.TimeSpent = worklog.timeSpent
worklogDetails.Author = worklog.getAuthorFullName()
worklogDetails.Created = worklog.created
worklogDetails.UpdateAuthor = worklog.getUpdateAuthorFullName()
worklogDetails.Updated = worklog.updated

/*
jsonStr = """
        {
          "event": {
            "eventTime": "${event.time}",
            "eventHashCode": ${event.hashCode()},
            "eventIssueKey": "${event.issue.key}",
            "eventTypeId": ${event.getEventTypeId()},
            "eventUser": "${event.user}",
            "eventWorklogObj": "${event.getWorklog()}"
            
          }
               , 
             "worklogChangeDetails" : { 
              "worklogId" : $worklog.id
            , "worklogIssueKey": "$worklog.issue.key"
            , "worklogStartDate": "${worklog.startDate}"
            , "worklogComment": "${worklog.comment}"
            , "worklogTimeSpent": ${worklog.timeSpent}
            , "worklogAuthor": "${worklog.getAuthorFullName()}"
            , "worklogCreated": "${worklog.created}"
            , "worklogUpdateAuthor": "${worklog.getUpdateAuthorFullName()}"
            , "worklogUpdated": "${worklog.updated}"
          }
        }
    """
*/


log.error "pre issue summary: " + issue.summary

JsonBuilder json = new JsonBuilder()                                                // <<<<<<<<<< WORKS 1
json.WLEvent ([eventDetails: eventDetails, worklogChangeDetails: worklogDetails]) // <<<<<<<<<< WORKS 2


// JsonBuilder json = new JsonBuilder(event.time) /////////// ЗАГЛУШКА


customField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(customField), json.toString()), new DefaultIssueChangeHolder())
issue.setSummary(issue.summary + "-1") \\ DBG
customField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(customFieldManager.getCustomFieldObjects(issue).findByName(".tmp")), "DBG"), new DefaultIssueChangeHolder())  \\ DBG




issue.setSummary(issue.summary + "-1"); 
//def user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()


/////////////////////////////////////////////////////
///////// WE NEED TO MANUALLY UPDATE THE ISSUE??
/// https://community.atlassian.com/t5/Answers-Developer-Questions/Update-an-issue-field-in-a-listener-changes-are-not-made/qaq-p/566449



ComponentAccessor.getIssueManager().updateIssue(event.user, issue, EventDispatchOption.ISSUE_UPDATED, false) // <<<<<<<<<<<<<<<<<<<<<<<<<<< !!!
issueManager.updateIssue(event.user, issue, EventDispatchOption.DO_NOT_DISPATCH, false) // <<<<<<<<<<<<<<<<<<<<<<<<<<< !!!



 import com.atlassian.jira.event.issue.AbstractIssueEventListener;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.util.ImportUtils;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.index.IssueIndexingService;


 MutableIssue mutableIssue = ComponentAccessor.getIssueManager().getIssueObject(issue.getKey());
 boolean isIndex = ImportUtils.isIndexIssues();
 ImportUtils.setIndexIssues(true);
 IssueIndexingService IssueIndexingService = (IssueIndexingService) ComponentAccessor.getComponent(IssueIndexingService.class);
 IssueIndexingService.reIndex(mutableIssue);

 ImportUtils.setIndexIssues(isIndex);






////////////////////////////////////////////////////////////


log.error json.toPrettyString()



/*log.error "\n........................................................EVENT____ " + ent.hashCode() + " ___FIRED...................................................................."


def CF = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(".wikilLastEventDetails");   
//def str = issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(".sfWorkLogInfo1"))
MutableIssue issueToUpdate = (MutableIssue) event.issue;
issueToUpdate.setCustomFieldValue(CF, json_out);
issueManager.updateIssue(event.getUser(), issueToUpdate, EventDispatchOption.ISSUE_UPDATED, false);

log.error "\n........................................................DONE EVENT____ " + event.id() + " ___PROCESSING...................................................................."*/


