import com.atlassian.jira.component.pico.ComponentManager
import com.onresolve.scriptrunner.runner.ScriptRunnerImpl;
import com.onresolve.scriptrunner.runner.customisers.WithPlugin;
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.fields.CustomField
///import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.customfields.manager.OptionsManager
import java.lang.Object
import com.atlassian.jira.issue.worklog.Worklog
import com.atlassian.jira.issue.worklog.DefaultWorklogManager
import com.atlassian.jira.issue.worklog.WorklogImpl2
import java.text.SimpleDateFormat
import com.atlassian.crowd.embedded.api.User
import com.atlassian.jira.component.ComponentAccessor

import com.atlassian.jira.security.roles.ProjectRoleManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.security.roles.ProjectRoleManager

import com.atlassian.jira.datetime.LocalDate
import org.apache.log4j.Logger
import org.apache.log4j.Level

import java.time.*
    
    
def componentManager = ComponentAccessor.getComponentClassManager()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def issueManager = ComponentAccessor.getIssueManager()
def worklogManager = ComponentAccessor.getWorklogManager()
def projectRoleManager = ComponentAccessor.getComponent(ProjectRoleManager)


StringBuilder comment = new StringBuilder()

MutableIssue issue = issueManager.getIssueObject('FROM-10')
def authorKey = issue.assignee.name
LocalDateTime  dtn = LocalDateTime.now()
def createdDate = issue.getCreated().getTime()
Date startDate = issue.resolutionDate
//def resolutionDate = issue.getResolutionDate().getTime()

Integer duration = new Double(createdDate - createdDate + 1000).toInteger() / (1000*60*60*24).toInteger() // In days
Long timeSpent = (duration * 60 * 60 *8).toLong()

def UsersRole = projectRoleManager.getProjectRole("Users")

comment.append("wl created on: " + dtn.toString() )
comment.append("\nwl authorKey: " + authorKey)
comment.append("\nwl startDate: " + startDate)
comment.append("\nwl duration: " + duration)
comment.append("\nwl timeSpent: " + timeSpent)

// WorklogImpl2(Issue issue, Long id, String authorKey, String comment, Date startDate, String groupLevel, Long roleLevelId, Long timeSpent, ProjectRole projectRole) 
//def worklog = new WorklogImpl2(issue, null, issue.assignee.name, comment, issue.resolutionDate, null, null, timeSpent, UsersRole)
def worklog = new WorklogImpl2(issue, null, authorKey, comment.toString(), startDate, null, null, timeSpent, UsersRole)

def wlr_created_id = "wlr_created_id: " +worklogManager.create(issue.assignee, worklog, 0L, true).getId()

//issue.store()
